#!/usr/bin/env python3

import re
import json


with open('pyconfig.json', 'r') as config_file:
    SETTINGS: dict[str, any] = json.load(config_file)

with open(SETTINGS['languageFile'], 'r') as lang_file:
    # Remove lines with comments
    contents: str = re.compile(
        r'^\s*//.*$', re.MULTILINE).sub('', lang_file.read())
    descriptions_raw: dict[str, any] = json.loads(contents)
# DESCRIPTIONS has following structure:
# { "ruleName": { "desc": "...", "extra": ["...", "..."] } }
DESCRIPTIONS: dict[str, dict] = {}
for k, v in descriptions_raw.items():
    rule_name = re.compile(
        r'carpet\.rule\.(\w+)\.(?:desc|extra\.\d+)').search(k).group(1)
    if rule_name not in DESCRIPTIONS.keys():
        DESCRIPTIONS[rule_name] = {
            'desc': '',
            'extra': [],
        }
    if k.endswith('.desc'):
        DESCRIPTIONS[rule_name]['desc'] = v
    elif re.compile(r'\.extra\.\d+$').search(k):
        index = int(k.split('.')[-1])
        while len(DESCRIPTIONS[rule_name]['extra']) <= index:
            DESCRIPTIONS[rule_name]['extra'].append('')
        DESCRIPTIONS[rule_name]['extra'][index] = v
    else:
        print(f'\x1b[1;33mWarning: unknown key {k} in language file\x1b[0m')


class Rule:
    type: str
    name: str
    value: str
    desc: str
    extra: list[str] = []
    options: list[str] = []
    strict: bool
    categories: list[str]
    restriction: str = ""
    additional: str = ""

    def __repr__(self):
        nl: str = '\n'
        options: list[str] = [
            'true', 'false'] if self.type == 'boolean' else self.options
        if 'COMMAND' in self.categories:
            options: list[str] = ['true', 'false', 'ops']

        self.categories.sort()

        out = f'### {self.name}\n' \
              f'{self.desc}{nl + nl + ("  " + nl).join(self.extra) if self.extra else ""}\n' \
              f'- Type: `{self.type}`\n' \
              f'- Default value: `{self.value}`\n' \
              f'- {"Required" if self.strict else "Suggested"} ' \
              f'options: `{"`, `".join(options)}`\n' \
              f'- Categories: `{"`, `".join(self.categories)}`'

        if self.restriction or self.additional:
            out += '\n- Additional notes:'
            if self.restriction:
                out += f'\n  - {self.restriction}'
            if self.additional:
                out += f'\n  - {self.additional}'

        return out


def read_rules() -> list[Rule]:
    with open(SETTINGS['carpetSettingsClass'], 'r') as settings_file:
        print('Reading settings file\n')
        settings_string = settings_file.read()
    raw_rules: list[str] = [i.split(';')[0] for i in re.compile(
        '^[^/\n]*[^/\n]*@Rule', re.MULTILINE).split(settings_string)[1:]]

    rules: list[Rule] = []
    for raw_rule in raw_rules:
        rule: Rule = Rule()
        field: list[str] = raw_rule.split(
            '\n')[-1].split('public static ')[-1].split(' ')
        rule.type = field[0]
        rule.name = field[1]
        print(f'Parsing rule {rule.name}')
        rule.value = field[3].replace('"', '')

        attr_dict: dict[str: str] = {
            match.group(1): match.group(2) for match in re.finditer(
                r'(options|categories|strict|appSource|validators|conditions) = ([\s\S]+?)(?=,\n?\s*?\w+?\s?=\s?|\n?\s*?\)\n)',
                raw_rule
            )
        }

        rule.desc = DESCRIPTIONS[rule.name]['desc']
        rule.extra = DESCRIPTIONS[rule.name]['extra']
        if 'options' in attr_dict.keys():
            rule.options = [re.sub(r'\s|\n', '', option)[
                1:-1] for option in re.compile(r',\s|,\n').split(attr_dict['options'][1:-1])]
        rule.strict = not ('strict' in attr_dict.keys()
                           ) or attr_dict['strict'] == 'true'
        rule.categories = [
            category for category in attr_dict['categories'][1:-1].replace(' ', '').split(',')]
        main_category: str = SETTINGS['mainCategory']
        if main_category not in rule.categories:
            print(
                f'\033[1;31m{main_category} category is missing in {rule.name}!\033[22m Exiting...\033[0m')
            return []
        if 'validators' in attr_dict.keys():
            validator: str = attr_dict['validators'].replace('.class', '')
            rule.restriction = settings_string.split(
                f'class {validator} extends')[1].split('"')[1]
        found_additional: list[str] = settings_string.split(
            f'// {rule.name}Additional: ')
        if len(found_additional) > 1:
            rule.additional = re.sub(
                r'\n\s+?//\s?', ' ', found_additional[1].split(':::')[0])

        rules.append(rule)
        print(f'Successfully parsed {rule.name}')
    print('\nFinished parsing rules\n')
    return rules


def write_files(rules: list[Rule]):
    with open(SETTINGS['readmeHeader'], 'r') as header_file:
        print('Reading header file')
        out: str = header_file.read()

    print('Listing all categories')
    all_categories: list[str] = list(set(
        [item for sublist in [rule.categories for rule in rules] for item in sublist]))
    all_categories = [category for category in all_categories if category.upper(
    ) != SETTINGS['mainCategory']]
    all_categories.sort()

    out += '## Lists of Categories\n'
    for category in all_categories:
        out += f'- [`{category}`](markdown/{category}_Category.md)\n'
    out += '\n'

    print('Sorting rules')
    rules.sort(key=lambda e: e.name)

    out += list_rules(rules, 'Implemented Rules')

    with open('README.md', 'w') as readme_file:
        print('Writing README.md\n')
        readme_file.write(out[:-1])

    for category in all_categories:
        print(f'Listing rules in {category} category')
        rules_in_category: list[Rule] = [
            rule for rule in rules if category in rule.categories]
        rules_in_category.sort(key=lambda e: e.name)
        out: str = f'# List of Rules in the {category} Category\n\n' \
                   f'For a list of all implemented Rules go [here](../README.md)\n'
        out += list_rules(rules_in_category, f'Rules in {category} Category')

        with open(f'markdown/{category}_Category.md', 'w') as category_readme:
            print(f'Writing {category}_Category.md')
            category_readme.write(out[:-1])

    curseforge_list(rules)
    modrinth_list(rules)


def list_rules(rules: list[Rule], rule_headline: str) -> str:
    out: str = f'## Index\n' \
               f'Count: {len(rules)}\n'
    for rule in rules:
        out += f'- [{rule.name}](#{rule.name.lower()})\n'
    out += f'\n## {rule_headline}\n\n'
    for rule in rules:
        out += str(rule) + '\n\n'

    return out


def curseforge_list(rules: list[Rule]):
    with open(SETTINGS['curseForgeHeader'], 'r') as header_file:
        out: str = header_file.read()
    out += f'Count: {len(rules)}  \n'
    for rule in rules:
        out += f'- {rule.name}  \n'
    with open('markdown/curseforge.md', 'w') as curse_file:
        print('\nWriting curseforge.md')
        curse_file.write(out)


def modrinth_list(rules: list[Rule]):
    with open(SETTINGS['modrinthHeader'], 'r') as header_file:
        out: str = header_file.read()
    out += f'Count: {len(rules)}\n'
    for rule in rules:
        out += f'- {rule.name}\n'
    with open('markdown/modrinth.md', 'w') as modrinth_file:
        print('\nWriting modrinth.md')
        modrinth_file.write(out)


if __name__ == '__main__':
    write_files(read_rules())
    print('\nDone!')

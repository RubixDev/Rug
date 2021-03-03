class Rule:
    type: str
    name: str
    value: str
    desc: str
    extra: str = ""
    options: list = []
    strict: bool
    categories: list
    restriction: str = ""
    additional: str = ""

    def __repr__(self):
        nl = '\n'
        out = f'### {self.name}\n' \
              f'{self.desc}  {nl + self.extra if self.extra else ""}  \n' \
              f'- Type: `{self.type}`\n' \
              f'- Default value: `{self.value}`\n' \
              f'- {"Required" if self.strict else "Suggested"} ' \
              f'options: `{"`, `".join(["true", "false"] if self.type == "boolean" else self.options)}`\n' \
              f'- Categories: `{"`, `".join(self.categories)}`'
        if self.restriction or self.additional:
            out += '\n- Additional notes:'
            if self.restriction:
                out += f'\n  - {self.restriction}'
            if self.additional:
                out += f'\n  - {self.additional}'
        return out


def read_rules() -> list:
    with open('src/main/java/com/rubixdev/rug/RugSettings.java', 'r') as settings_file:
        settings_string = settings_file.read()
    raw_settings = [i.split(';')[0] for i in settings_string.split('@Rule')[1:]]

    rules = []
    for raw_rule in raw_settings:
        rule = Rule()
        field = raw_rule.split('\n')[-1][18:].split(' ')
        rule.type = field[0]
        rule.name = field[1]
        rule.value = field[3]

        keys = [i[12:].split(' = ')[0] for i in raw_rule.split('\n')[1:-2]]
        values = [i[12:].split(' = ')[1] for i in raw_rule.split('\n')[1:-2]]
        attr_dict = {k: v for k, v in zip(keys, values)}

        rule.desc = attr_dict['desc'][1:-2]
        if 'extra' in keys:
            rule.extra = attr_dict['extra'][1:-2]
        if 'options' in keys:
            rule.options = [i[1:-1] for i in attr_dict['options'][1:-2].split(', ')]
        rule.strict = not ('strict' in keys)
        rule.categories = [i.replace('}', '') for i in attr_dict['category'][1:-1].split(', ')]
        if not rule.strict:
            validator = attr_dict['validate'].replace(',', '')[:-6]
            rule.restriction = settings_string.split(f'class {validator} extends')[1].split('"')[1]
        found_additional = settings_string.split(f'// {rule.name}Additional: ')
        if len(found_additional) > 1:
            rule.additional = found_additional[1].split('\n')[0]

        rules.append(rule)
    return rules


def write_file(rules: list):
    with open('README-header.md', 'r') as header_file:
        out = header_file.read()
    rules.sort(key=lambda e: e.name)

    out += f'## Index\n' \
           f'Count: {len(rules)}\n'
    for rule in rules:
        out += f'- [{rule.name}](#{rule.name.lower()})\n'
    out += '\n## Implemented Rules\n\n'
    for rule in rules:
        out += str(rule) + '\n\n'

    with open('README.md', 'w') as readme_file:
        readme_file.write(out[:-1])


write_file(read_rules())

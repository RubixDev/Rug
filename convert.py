import json

with open('src/main/resources/assets/rug/lang/en_us.json5', 'r') as file:
    raw = json.load(file)

rule_names = sorted(list(set([rule.removeprefix('carpet.rule.').split('.')[0] for rule in raw.keys()])))

print('rug:')
print('  rule:')

for rule in rule_names:
    print(f'    {rule}:')
    print('      desc: ' + raw[f'carpet.rule.{rule}.desc'])
    if f'carpet.rule.{rule}.extra.0' in raw:
        print('      extra:')
        i = 0
        while f'carpet.rule.{rule}.extra.{i}' in raw:
            print(f"        '{i}': " + raw[f'carpet.rule.{rule}.extra.{i}'])
            i += 1
    if f'carpet.rule.{rule}.additional' in raw:
        print('      additional: ' + raw[f'carpet.rule.{rule}.additional'])

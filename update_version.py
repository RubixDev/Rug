#!/usr/bin/env python3

import json

with open('pyconfig.json', 'r') as config_file:
    SETTINGS = json.load(config_file)

with open('gradle.properties', 'r') as gradle_file:
    content = gradle_file.read()
    old_version = content.split('mod_version = ')[1].split('\n')[0]

VERSION = input(f'Current version: {old_version}\nNew version: ')

with open('gradle.properties', 'w') as gradle_file:
    gradle_file.write(content.replace(old_version, VERSION))

with open(SETTINGS['mainClass'], 'r') as server_file:
    content = server_file.read()
    old_version = content.split('String VERSION = "')[1].split('";\n')[0]

with open(SETTINGS['mainClass'], 'w') as server_file:
    server_file.write(content.replace(old_version, VERSION))

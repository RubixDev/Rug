#!/usr/bin/env bash

./update_version.py
./gen_md.py
./gradlew build

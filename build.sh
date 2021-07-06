#!/usr/bin/env bash

./update_version.py
./generate_readmes.py
./gradlew build

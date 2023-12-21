#!/usr/bin/env bash

# This is a deploy script for this java program

# 1. Build the jar
rm -rf build/libs
JAVA_HOME="/usr/lib/jvm/java-17-openjdk" ./gradlew build

# 2. Copy the jar to the server
jar_name=$(ls -v build/libs/aqua-*.jar | grep -v "plain" | head -n 1)
scp "$jar_name" lux:/root/aqua/aqua.jar

# 3. Restart the service
ssh lux "systemctl restart aqua"

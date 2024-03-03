#!/bin/bash

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Java is not installed"
    exit 1
fi

# Get Java version
java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')

# Extract major version number
major_version=$(echo "$java_version" | cut -d '.' -f 1)

# Check if Java version is 11 or higher
if [ "$major_version" -ge 11 ]; then
    echo "Java version $java_version is installed"
else
    echo "Java version 11 or higher is required"
    exit 1
fi

# Build the Java project using Gradle
./gradlew build

# Check if the build was successful
if [ $? -ne 0 ]; then
    echo "BUILD FAILED. Fix it to run the command."
    exit 1
fi

# Run the JAR file only if the Java version check and build are successful
java -jar ./build/libs/Cron-Expression-Parser.jar "$1"

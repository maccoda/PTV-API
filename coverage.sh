#!/usr/bin/env bash
set -e
./gradlew jacocoTestReport

open build/reports/jacoco/test/html/index.html

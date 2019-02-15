#!/usr/bin/env bash
./gradlew jacocoTestReport

open build/reports/jacoco/test/html/index.html

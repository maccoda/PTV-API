#!/bin/bash

if [ -z ${TRAVIS_TAG} ]; then
    echo "Not a tag. Will not deploy"
else
    echo "Deploying ${TRAVIS_TAG}"
    ./gradlew bintrayUpload
fi
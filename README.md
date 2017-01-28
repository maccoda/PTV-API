# PTV-API
[![BuildStatus](https://travis-ci.org/maccoda/PTV-API.svg?branch=master)](https://travis-ci.org/maccoda/PTV-API)
[![GitHub tag](https://img.shields.io/github/tag/maccoda/PTV-API.svg?maxAge=2592000)]()
[![Codecov](https://img.shields.io/codecov/c/github/maccoda/PTV-API.svg)]()

A library to make simple interface to the Public Transport Victoria API in Java.

## Build
Build is done by Gradle. `gradle build` in the root directory will do the job. The jar is created in the default directory of `build/libs/PTV-API.jar`.

## Usage
To use this library will first need to obtain the developer ID and private key from PTV, [here](https://www.ptv.vic.gov.au/about-ptv/ptv-data-and-reports/digital-products/ptv-timetable-api/) would be a good place to start I would say with that.

Once you've got these you can create a `PtvRequest` object and that is all you need to obtain the information from PTV. This will build your request and return the JSON objects as Java bean classes. To determine which request you need please refer to the PTV documentation and hopefully I have documented them well enough to help.

# PTV-API

[![BuildStatus](https://travis-ci.org/maccoda/PTV-API.svg?branch=master)](https://travis-ci.org/maccoda/PTV-API)
[![GitHub tag](https://img.shields.io/github/tag/maccoda/PTV-API.svg?maxAge=2592000)](https://github.com/maccoda/PTV-API/releases)
[ ![Download](https://api.bintray.com/packages/maccoda/maven/PTV-API/images/download.svg) ](https://bintray.com/maccoda/maven/PTV-API/_latestVersion)
[![Codecov](https://img.shields.io/codecov/c/github/maccoda/PTV-API.svg)](https://codecov.io/gh/maccoda/PTV-API)

A library to make simple interface to the Public Transport Victoria API in Java.

## UNDER RENOVATIONS!

PTV has now shut down V2 API. Currently putting together V3 of the API from their [Swagger](http://timetableapi.ptv.vic.gov.au/swagger/ui/index#/).

### Current Status
- [ ] Departures
    - [X] route type + stop id
    - [X] route type + stop id + route id
    - [ ] query parameters
- [ ] Directions
    - [X] route id
    - [ ] direction id
    - [ ] direction id + route type
- [ ] Disruptions
    - [ ] view all
    - [ ] route id
    - [ ] route id + stop id
    - [ ] stop id
    - [ ] disruption id
    - [ ] modes
- [ ] Outlets
    - [ ] all
    - [ ] lat + lon
- [ ] Patterns
    - [ ] run id + route type
- [ ] Routes
    - [X] all
    - [X] route id
    - [ ] query parameters
- [ ] Route Types
    - [ ] all
- [ ] Runs
    - [ ] route id
    - [ ] route id + route type
    - [ ] run id
    - [ ] run id + route type
- [ ] Search
    - [ ] search term
- [ ] Stops
    - [ ] stop id + route type
    - [ ] route id + route type
    - [ ] lat + lon
    
 
## Build
Build is done by Gradle. `./gradlew build` in the root directory will do the job.
The jar is created in the default directory of `build/libs/PTV-API.jar`.

## Usage

To use this library will first need to obtain the developer ID and private key
from PTV,
[here](https://www.ptv.vic.gov.au/about-ptv/ptv-data-and-reports/digital-products/ptv-timetable-api/)
would be a good place to start I would say with that.

### Dependency

The artifacts are published to
[Bintray](https://bintray.com/maccoda/maven/PTV-API) and can be obtained by your
build tool of choice.

### Code

*This was V2 API, V3 API will be documented once more has been completed*

Once you've got these you can create a `PtvRequest` object and that is all you
need to obtain the information from PTV. This will build your request and return
the JSON objects as Java bean classes. To determine which request you need
please refer to the PTV documentation and hopefully I have documented them well
enough to help.

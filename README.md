# Event Control

This is a log management system, that will read a file and then process the logs in there and generate alerts.

## How to build and run

gradle build

java -jar build/libs/eventcontrol-0.0.1-SNAPSHOT.jar --input=example.log

It will produce a alert.mv.db file containing the log registers, some of them flagged with alert=true.

## Features

### How to find the alerts

## Next steps

* Testing corner cases
* Transforming from springboot to springbatch application

# FPL Crawler

This repository contains the code of a FPL crawler application.

## Requirements

* Docker
* JDK 11 or newer & Maven for development.

## Building the Docker image

* Run `./mvnw clean install` to build the application. 
* Run `docker build -t fpl-crawler .` to build a docker image.
* Run `docker run -p 8080:8080 fpl-crawler` to run the docker image.

## Running the application

* Run `docker-compose up --build -d` to run the docker images.
* Run `docker-compose stop` to stop the docker images.
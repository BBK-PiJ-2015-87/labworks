#!/usr/bin/env bash

sbt clean
sbt assembly
docker build -t akka_app .
#!/bin/sh
export $(grep -v '^#' .env | xargs)
java -jar $@
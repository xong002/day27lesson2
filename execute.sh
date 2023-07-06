#!/bin/bash

echo "Starting script"

export SPRING_DATA_MONGODB_URI="mongodb://localhost:27017/games"

mvn spring-boot:run

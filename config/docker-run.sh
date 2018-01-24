#!/usr/bin/env bash

docker run -d --rm \
    --name sprio-config \
    -v "/home/darek/Devel/Spring/spring-io-lab11-config:/config-store" \
    -p 9000:8080 \
    -e SERVER_PORT=8080 \
    -e SPRING_CLOUD_CONFIG_SERVER_GIT_URI=file:///config-store sprio/config

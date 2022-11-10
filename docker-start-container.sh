#!/usr/bin/env bash

DOCROOT="$( cd "$( dirname "$0" )" && pwd )"

export APP_ENV=${APP_ENV:-dev}

# set suffix to username if it does not exist
USER_ID=$(id -u -n | sed 's/\./_/g')
export SUFFIX=${SUFFIX:-"_${USER_ID}"}

echo "APP_ENV is set to: ${APP_ENV} SUFFIX is: ${SUFFIX}"

# Start
docker-compose -f $DOCROOT/docker-compose.yml up -d \
   redis nacos gateway-server

sleep 5
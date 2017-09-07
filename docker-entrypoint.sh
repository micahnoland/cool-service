#!/bin/sh

set -e
	
if [ -z "$1" -o  "${1:0:1}" = '-' ]; then
    exec su-exec ${USER} java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ${APP_HOME}/${APP_JAR} "$@"
fi

exec "$@"

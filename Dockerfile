FROM openjdk:8-jre-alpine
MAINTAINER Micah Noland <micah.noland@here.com>

# Only property to set, this should be your (Maven) artifactId. 
# Make sure it's lower-case since it will also be used to name the Docker image
ENV APP_JAR_NAME cool-service

ENV APP_HOME /opt/${APP_JAR_NAME}/
ENV APP_JAR ${APP_JAR_NAME}.jar
ENV USER spring-boot
ENV GROUP spring-boot
ENV HOME /home/${USER}

ADD target/*.jar ${APP_HOME}/${APP_JAR} 

COPY docker-entrypoint.sh /docker-entrypoint.sh

RUN apk upgrade --update && \
	apk add \
		su-exec \
	&& addgroup ${GROUP} \ 
	&& adduser -S -G ${GROUP} ${USER} \
	&& mkdir -p ${HOME} \
	&& sh -c 'touch ${APP_HOME}/${APP_JAR}' \
    && chown -R ${USER}:${GROUP} ${APP_HOME} \
	&& chown -R ${USER}:${GROUP} ${HOME} \ 
	&& chmod a+x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 8080
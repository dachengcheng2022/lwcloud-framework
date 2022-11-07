FROM openwhisk/java8action

COPY target/micro-gateway-server.jar /usr/local/micro-gateway-server/micro-gateway-server.jar
COPY docker/micro-gateway-server.conf /etc/supervisor/conf.d/
COPY docker/supervisord.conf /etc/supervisord.conf

ENV APP_ENV "dev"
ENV APP_DIR  /usr/local/micro-gateway-server

RUN  mkdir -p "${APP_DIR}/logs"
VOLUME [ "${APP_DIR}/logs" ]

CMD [ "/usr/bin/supervisord", "-c", "/etc/supervisord.conf"]

EXPOSE 10763


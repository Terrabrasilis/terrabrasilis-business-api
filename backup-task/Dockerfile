FROM alpine:3.15

LABEL "br.inpe.dpi"="INPE/DPI-TerraBrasilis"
LABEL br.inpe.dpi.terrabrasilis="task"
LABEL author="Andre Carvalho"
LABEL author.email="andre.carvalho@inpe.br"
LABEL description="This image provides a background task to run daily MongoDB backup."

RUN apk update \
    && apk add --no-cache --update \
    mongodb-tools \
    tzdata \
    && rm -rf /var/cache/apk/*

# define the timezone to run cron
ENV TZ=America/Sao_Paulo

# define the install path env var
ENV INSTALL_PATH /usr/local

# install and enable cron job scripts
ADD backup-task/cron_exec.sh ${INSTALL_PATH}/
ADD backup-task/mongodb-backup.sh ${INSTALL_PATH}/
ADD backup-task/backup-schedule.cron /etc/crontabs/root
ADD backup-task/docker-entrypoint.sh ${INSTALL_PATH}/bin/docker-entrypoint.sh
RUN chmod +x ${INSTALL_PATH}/*.sh \
    && chmod +x ${INSTALL_PATH}/bin/*.sh \
    && chmod +x /etc/crontabs/root \
    && ln -s usr/local/bin/docker-entrypoint.sh /

VOLUME ["${INSTALL_PATH}/data"]

ENTRYPOINT [ "/docker-entrypoint.sh" ]
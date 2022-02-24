#!/bin/sh

## THE ENV VARS ARE NOT READED INSIDE A SHELL SCRIPT THAT RUNS IN CRON TASKS.
## SO, WE WRITE INSIDE THE /etc/environment FILE AND READS BEFORE RUN THE SCRIPT.
echo "export MONGO_HOST=\"$MONGO_HOST\"" >> /etc/environment
echo "export MONGO_PORT=\"$MONGO_PORT\"" >> /etc/environment
echo "export MONGO_DB=\"$MONGO_DB\"" >> /etc/environment
echo "export INSTALL_PATH=\"$INSTALL_PATH\"" >> /etc/environment
# run cron in foreground
crond -l 2 -f

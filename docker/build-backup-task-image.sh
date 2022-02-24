#!/bin/bash

VERSION=$(git describe --tags --abbrev=0)
export VERSION

# build all images
docker build -t terrabrasilis/mongo-backup-task:$VERSION -f ../backup-task/Dockerfile ../

# send to dockerhub
echo "The building was finished! Do you want sending this new image to Docker HUB? Type yes to continue." ; read SEND_TO_HUB
if [[ ! "$SEND_TO_HUB" = "yes" ]]; then
    echo "Ok, not send the image."
else
    echo "Nice, sending the image!"
    docker push terrabrasilis/mongo-backup-task:$VERSION
fi
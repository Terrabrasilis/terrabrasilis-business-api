# For generate an image

- Change the image version into pom.xml
- Check the application-docker.properties and adjust the connection string if needed;
 > To use in homologation mode mongodb://mongodbhomologation:27017/businessapi
 
 > To use in production mode mongodb://mongodb:27017/businessapi
- Use the build-image.sh script

# For run homologation

We think the homologation environment is our Docker Swarm cluster, so we use the yaml stack file from [docker-stack/api](https://github.com/Terrabrasilis/docker-stacks/blob/master/api/business-api-homologation.yaml).

# For run in local

```sh
docker-compose up -d
```

Use the [docker-compose.yaml from docker-stacks/demo](https://github.com/Terrabrasilis/docker-stacks/blob/master/demo/business-api/docker-compose.yaml)
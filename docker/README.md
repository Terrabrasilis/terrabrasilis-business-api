# Dependencies

 To build this project, it's required to have docker-ce installed and configured.
 For instruction on how to install it, take a look at https://docs.docker.com/install/linux/docker-ce/ubuntu/

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


# About container health check

To improve the stability of business API-based services, we use docker healthcheck.

See the "business-api" stack into [Github repository](https://github.com/terrabrasilis/docker-stacks).

Look for some lines like this:
```yml
    # used to check API integrity
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:8090/api/v1/download/all || exit 1"]

    # used to check the MongoDB integrity
    healthcheck:
      test: ["CMD-SHELL", "echo 'db.getMongo()' | mongo --norc --quiet --host=localhost:27017 || exit 1"]
      interval: 1m10s
      timeout: 5s
      retries: 3
```

Reference: https://docs.docker.com/compose/compose-file/compose-file-v3/#healthcheck
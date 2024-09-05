# TerraBrasilis Business API - Migration to SSL

To be able to publish TerraBrasilis Webmap it is necessary to change the datasources inside mongodb database. 

## 1) Make a backup from production mongodb database:

./mongodb-businessapi.sh export

Obs: Make sure to configure the mongodb-businessapi.sh file to extract from production and saving inside a folder (ex: "bkp-collections-production")

## 2) Runing the changes on database export JSON files:

./change-host.sh ./bkp-collections-production

Obs: Make sure to configure the change-hosts.sh file to replace the host from "http://terrabrasilis.dpi.inpe.br" to "https://terrabrasilis.dpi.inpe.br"

## 3) Import the changed files to production mongodb:

./mongodb-businessapi.sh import

Obs: Make sure to configure the mongodb-businessapi.sh file to import to production from the changed folder (ex: "bkp-collections-production")
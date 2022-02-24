#!/bin/sh

# url to read data, input data source.
export_host="${MONGO_HOST}:${MONGO_PORT}"
WORK_DIR="${INSTALL_PATH}/data"

echo "exporting collections from production"
## download
mongoexport --host ${export_host} --db ${MONGO_DB} --collection download --sort '{category:1,name:1,lang:1,created:1}' --out ${WORK_DIR}/download.json

## tool
mongoexport --host ${export_host} --db ${MONGO_DB} --collection tool --sort '{name:1,created:1}' --out ${WORK_DIR}/tool.json

## datasource
mongoexport --host ${export_host} --db ${MONGO_DB} --collection datasource --sort '{name:1,type:1,created:1}' --out ${WORK_DIR}/datasource.json

## subdomain
mongoexport --host ${export_host} --db ${MONGO_DB} --collection subdomain --sort '{name:1,created:1}' --out ${WORK_DIR}/subdomain.json

## layer
mongoexport --host ${export_host} --db ${MONGO_DB} --collection layer --sort '{workspace:1,name:1,created:1}' --out ${WORK_DIR}/layer.json

## vision
mongoexport --host ${export_host} --db ${MONGO_DB} --collection vision --sort '{name:1,created:1}' --out ${WORK_DIR}/vision.json

## vision_to_vision
mongoexport --host ${export_host} --db ${MONGO_DB} --collection vision_to_vision --out ${WORK_DIR}/vision_to_vision.json

DT=$(date '+%Y-%m-%d')
# ZIP all JSON
echo "ZIP all JSONs to mongodb-collections-bkp-${DT}.zip"
zip -9 -j ${WORK_DIR}/mongodb-collections-bkp-${DT}.zip ${WORK_DIR}/*.json

echo "Remove all JSONs"
rm ${WORK_DIR}/*.json

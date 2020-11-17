#!/bin/bash

# URL for homologation instance
#export_host="terrabrasilis2.dpi.inpe.br:30043"
# URL for production instance
#import_host="terrabrasilis2.dpi.inpe.br:30046"

# url for local instance
export_host="localhost:27017"
import_host="localhost:27017"

#++++++++++++++++++++++++++++++++++++++++++++++++++++++
# Export
#++++++++++++++++++++++++++++++++++++++++++++++++++++++
# bkp-collections is data from server
#WORK_DIR="bkp-collections"
# bkp-collections-local is data from localhost
#WORK_DIR="bkp-collections-local"

if [[ "$1" = "export" ]]; then
    echo "Remove old files in $WORK_DIR..."
    rm $WORK_DIR/*.json
    echo "exporting collections from production"
    ## download
    mongoexport --host $export_host --db businessapi --collection download --sort '{name:1,lang:1,created:1}' --out $WORK_DIR/download.json

    ## tool
    mongoexport --host $export_host --db businessapi --collection tool --sort '{name:1,created:1}' --out $WORK_DIR/tool.json

    ## datasource
    mongoexport --host $export_host --db businessapi --collection datasource --sort '{name:1,type:1,created:1}' --out $WORK_DIR/datasource.json

    ## subdomain
    mongoexport --host $export_host --db businessapi --collection subdomain --sort '{name:1,created:1}' --out $WORK_DIR/subdomain.json

    ## layer
    mongoexport --host $export_host --db businessapi --collection layer --sort '{name:1,workspace:1,created:1}' --out $WORK_DIR/layer.json

    ## vision
    mongoexport --host $export_host --db businessapi --collection vision --sort '{name:1,created:1}' --out $WORK_DIR/vision.json

    ## vision_to_vision
    mongoexport --host $export_host --db businessapi --collection vision_to_vision --out $WORK_DIR/vision_to_vision.json
fi

#++++++++++++++++++++++++++++++++++++++++++++++++++++++
# Import
#++++++++++++++++++++++++++++++++++++++++++++++++++++++
if [[ "$1" = "import" ]]; then
    echo "Drop database \"businessapi\" of the $import_host HOST before import? Type yes to proceed..." ; read DROP
    if [[ "$DROP" = "yes" ]]; then
        mongo --host $import_host businessapi --eval "db.dropDatabase()"
        
    else
        echo "Aborting drop...proceeding with import."
    fi
    echo "importing collections to localhost"
    mongoimport --host $import_host --db businessapi --collection download --file $WORK_DIR/download.json
    mongoimport --host $import_host --db businessapi --collection tool --file $WORK_DIR/tool.json
    mongoimport --host $import_host --db businessapi --collection datasource --file $WORK_DIR/datasource.json
    mongoimport --host $import_host --db businessapi --collection subdomain --file $WORK_DIR/subdomain.json
    mongoimport --host $import_host --db businessapi --collection layer --file $WORK_DIR/layer.json
    mongoimport --host $import_host --db businessapi --collection vision --file $WORK_DIR/vision.json
    mongoimport --host $import_host --db businessapi --collection vision_to_vision --file $WORK_DIR/vision_to_vision.json
fi

if [[ "$1" = "" ]]; then
    echo "miss arg"
    echo "use export or import"
    echo "The export runs the mongoexport to export from production"
    echo "The import runs the mongoimport to import to localhost"
    echo "To change the target hosts, edit the script file."
fi
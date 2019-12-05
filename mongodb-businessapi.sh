#!/bin/bash

# URL for homologation instance
#export_host="terrabrasilis2.dpi.inpe.br:30046"
# URL for production instance
#import_host="terrabrasilis2.dpi.inpe.br:30043"

# url for local instance
export_host="localhost:27018"
#import_host="localhost:27018"
# URL for homologation instance
import_host="terrabrasilis2.dpi.inpe.br:30043"

#++++++++++++++++++++++++++++++++++++++++++++++++++++++
# Export
#++++++++++++++++++++++++++++++++++++++++++++++++++++++

if [[ "$1" = "export" ]]; then
    echo "Remove old files in bkp-collections..."
    rm bkp-collections/*.json
    echo "exporting collections from production"
    ## download
    mongoexport --host $export_host --db businessapi --collection download --out bkp-collections/download.json

    ## tool
    mongoexport --host $export_host --db businessapi --collection tool --out bkp-collections/tool.json

    ## datasource
    mongoexport --host $export_host --db businessapi --collection datasource --out bkp-collections/datasource.json

    ## subdomain
    mongoexport --host $export_host --db businessapi --collection subdomain --out bkp-collections/subdomain.json

    ## layer
    mongoexport --host $export_host --db businessapi --collection layer --out bkp-collections/layer.json

    ## vision
    mongoexport --host $export_host --db businessapi --collection vision --out bkp-collections/vision.json

    ## vision_to_vision
    mongoexport --host $export_host --db businessapi --collection vision_to_vision --out bkp-collections/vision_to_vision.json
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
    mongoimport --host $import_host --db businessapi --collection download --file bkp-collections/download.json
    mongoimport --host $import_host --db businessapi --collection tool --file bkp-collections/tool.json
    mongoimport --host $import_host --db businessapi --collection datasource --file bkp-collections/datasource.json
    mongoimport --host $import_host --db businessapi --collection subdomain --file bkp-collections/subdomain.json
    mongoimport --host $import_host --db businessapi --collection layer --file bkp-collections/layer.json
    mongoimport --host $import_host --db businessapi --collection vision --file bkp-collections/vision.json
    mongoimport --host $import_host --db businessapi --collection vision_to_vision --file bkp-collections/vision_to_vision.json
fi

if [[ "$1" = "" ]]; then
    echo "miss arg"
    echo "use export or import"
    echo "The export runs the mongoexport to export from production"
    echo "The import runs the mongoimport to import to localhost"
    echo "To change the target hosts, edit the script file."
fi
#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ONTOGENESIS_PORT=${PORT:-8090}
MS_PORT=${PORT:-8081}
SUBSET=${SUBSET:-40}

pushd "$DIR"

# We have provided this script to facilitate the process of running experiments
# although we have run all experiments mannualy one at once.

echo "extracting DataService_Repository.zip"
rm -fr ms/data 2>&1 /dev/null
pushd ms/data
unzip ../../DataService_Repository.zip
popd


#Setup ontogenesis
sed -i s/config.externalSourceBD:.*/config.externalSourceBD: dfas-$SUBSET.db/ ontogenesis/application.yml
sed -i s/server.port:.*/server.port: $ONTOGENESIS_PORT/ ontogenesis/application.yml

#Setup microservice
sed -i s/server.port:.*/server.port: $MS_PORT/ ms/application.yml
sed -i s@config.uriOntogenesis:.*@config.uriOntogenesis: http://localhost:$ONTOGENESIS_PORT@ ms/application.yml

for threshold in 80 60; do
    sed -i s/config.propertiesIntersectionThreshold:.*/config.propertiesIntersectionThreshold: 0.$threshold/ ontogenesis/application.yml
    for i in {1..7}; do
        pushd ontogenesis
        java -jar ontogenesis-api.jar &
        ONTOGENESIS_PID=$!
        popd
        
        if [ -z "$MS_PID" ]; then
            pushd ms
            java -jar criminal-report-person-microservice-0.0.1-SNAPSHOT.jar &
            MS_PID=$!
            popd 
        fi
        
        curl http://localhost:8081/criminal-report-person-microservice/person/startOntoGenesisExperiment?requisitions=100
        kill $ONTOGENESIS_PID
        wait $ONTOGENESIS_PID
    done    

    kill $MS_PID
    wait $MS_PID
done

popd

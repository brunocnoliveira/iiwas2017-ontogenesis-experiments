# Configuring the Experiments
Instructions to run data service of police reports and OntoGenesis API.

## Pre-Requirement
- Java Runtime Environment 8+
- 3 GB RAM Memory

## Setup Instructions

1. Configure OntoGenesis API:
    1. Obtaining the config and repository files:
    - Download the application.yml and dfas-xx.db file in the repository of OntoGenesis API.
    - The "xx" means the percent of DBpedia used in the index repository. For instance, dfas-40.db is the index repository that contains Geonames (Brazil and country names) and the 40% most frequent terms of DBpedia person data.
    2. Setting the application.yml file:
    - `server.port` port that OntoGenesis API will run
    - `config.maxNumberRepresentationsBuffer` the representation buffer size. It represents the maximium of representations that OntoGenesis will store before performing the enrichment.
    - `config.propertiesIntersectionThreshold` value from 0 to 1. The strength threshold for the equivalent properties.
    - `config.externalSourceBD` the index repository file path+name (dfas-xx.db)
    - `config.alignatorEnable` Not required. This feature is under development yet.
    - `config.ontologyMaxIndividuals` Not required. This feature is under development yet.
    - `config.minAlignmentStrength` Not required. This feature is under development yet.
 
2. Extract the `DataService_Repository.zip` in any directory in the server that will run the data service.

3. Configure the `application.yml` located at `Sources/criminal-report-person-dataservice`
    - `server.port` port that data service will run
    - `dataDir` the directory where the `DataService_Repository.zip` was extracted
    - `config.uriOntogenesis` The URI address where OntoGenesis API is running
    - `config.microserviceDescriptionFile` a fake description, remain the same. This feature is under development.

## Run Artifacts and Send Requests

1. First, run OntoGenesis API:
 ```bash
 java -jar ontogenesis-api.jar 
 ```
 
2. Second, run Data Service:
 ```bash
 java -jar criminal-report-person-microservice-0.0.1-SNAPSHOT.jar
 ```

3. Performing an HTTP request (GET):
 - In order to faciliate sending a high number of requests to the data service, we have created an API, which the client/consumer sends the number of requests, and the data service automatically randomly selects different data from the repository and sends to OntoGenesis. The URI is as follows:
 - `http://localhost:8081/criminal-report-person-microservice/person/startOntoGenesisExperiment?requisitions={number}`
 where `{number}` is the number of requests that the dataService will perform to OntoGenesis.

4. Getting any police report:
 - `http://localhost:8081/criminal-report-person-microservice/report/{idReport}`
 where `{idReport}` is the identification number of a police report.
 - How to know the id of a police report? You can find all the police reports in the folder data (extracting the [DataService_Repository.zip](https://github.com/brunocnoliveira/iiwas2017-ontogenesis-experiments/blob/master/DataService_Repository.zip))

### Automating the steps

1. We have provided a script (run.sh located in the run directory) to facilitate the process of running experiments. However, it is worthnoting that we have run all experiments mannualy one by once (setting the requisition number to 100, in criminal-report-person-microservice/person/startOntoGenesisExperiment API). One should unzip the necessary folders, follow the steps in the file and run it. Make sure all the configured ports are available in the OS.


## Analyze Reports

1. How to see the .csv reports generated by both the Data Service and OntoGenesis:
 - For the Data Service: It is in the root directory where your criminal-report-person-microservice-0.0.1-SNAPSHOT.jar is running.
     - Two .csv files are generated containing the equivalent properties yielded by OntoGenesis and another one for the precision, recall and F-measure scores of each request.
 - For the OntoGenesis: It is in the root directory where your ontogenesis-api-0.0.1-SNAPSHOT.jar is running.
      - One .csv file is generated containing the processing times of each request.

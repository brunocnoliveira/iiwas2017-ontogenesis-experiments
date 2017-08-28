# Setup Instructions
Instructions to run data service of police reports and OntoGenesis API.

## Pre-Requirement
- Java Runtime Environment 8+
- 3 GB RAM Memory

## Setup Instructions

1. Configure OntoGenesis API:
    1.1 Obtaining the config and repository files:
	 - Download the application.yml and dfas-xx.db file in the repository of OntoGenesis API.
	 - The "xx" means the percent of DBpedia used in the index repository. For instance, dfas-40.db is the index repository that contains Geonames (Brazil and country names) and the 40% most frequent terms of DBpedia person data.
    1.2 Setting the application.yml file:
	 - `server.port` port that OntoGenesis API will run
	 - `config.maxNumberRepresentationsBuffer` the representation buffer size. It represents the maximium of representations that OntoGenesis will store before performing the enrichment.
	 - `config.propertiesIntersectionThreshold` value from 0 to 1. The strength threshold for the equivalent properties.
	 - `config.externalSourceBD` the index repository file path+name (dfas-xx.db)
	 - `config.mostFrequentDatasetSize` Not required. The most frequent terms is already in the index repository.
	 - `config.alignatorEnable` Not required. This feature is under development yet.
	 - `config.ontologyMaxIndividuals` Not required. This feature is under development yet.
	 - `config.minAlignmentStrength` Not required. This feature is under development yet.
 
2. Run OntoGenesis API:
 ```bash
 java -jar ontogenesis-api-0.0.1-SNAPSHOT.jar 
 ```

3. Extract the `DataService_Repository.zip` in any directory in the server that will run the data service.

4. Configure the `application.yml` located at `Sources/criminal-report-person-dataservice`
    - `server.port` port that data service will run
    - `dataDir` the directory where the `DataService_Repository.zip` was extracted
    - `config.uriOntogenesis` The URI address where OntoGenesis API is running
    - `config.microserviceDescriptionFile` a fake description, remain the same. This feature is under development.

5. Run Data Service:
 ```bash
 java -jar criminal-report-person-microservice-0.0.1-SNAPSHOT.jar
 ```

6. Performing an HTTP request (GET):
 - `http://localhost:8081/criminal-report-person-microservice/person/startOntoGenesisExperiment?requisitions=10`
 where `requisitions` is the number of requests that the dataService will perform to OntoGenesis.

7. How to see the reports:
 - For the Data Service: It is in the root directory where your criminal-report-person-microservice-0.0.1-SNAPSHOT.jar is running.
     - There are two csv files.
 - For the OntoGenesis: It is in the root directory where your ontogenesis-api-0.0.1-SNAPSHOT.jar is running.
      - There are two csv files.
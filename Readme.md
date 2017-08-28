# OntoGenesis Experiments
This public repository encompasses the source codes as well as the results obtained in the experiments performed for the iiWAS 2017 (19th International Conference on Information Integration and Web-based Applications & Services).
The experiments concerns OntoGenesis, an architecture for semantically enriching data services representations.


## Repository Info
* `Results` Contains all the results in .csv of experiments: 
  * DBpedia Subset Size Pilot Experiment (20%, 40%, 60%)
  * Thresholds Comparison (0.4, 0.6, 0.8)
  * Ontology Matchers Comparison (AROMA and PARIS)
* `Sources` Source codes for the data service developed for experiment purposal. 
It also contains the .jar file of data service, which has a SpringBoot application.
* `DataService_Repository.zip` The data service repository. It contains all data (in JSON) regarding police reports of SSP/SP used in the experiments.
* `AROMA-PARIS-OntoGenesis-data.zip` All data used in the experiments of Ontology Matchers (AROMA and PARIS). Note that the dbpedia-tdb was removed due to its huge size not acceptable in git repository.
* `ontogenesis-api-jar.zip` Contains the executable jar file of OntoGenesis API, which also includes the Engine
* `SourceRepo` Contains the URL of all the source codes available.
* `SetupInstructions` All the instructions to set up the data service and run OntoGenesis.

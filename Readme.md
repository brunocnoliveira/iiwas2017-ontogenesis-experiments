# OntoGenesis Experiments
This public repository encompasses the source codes as well as the results obtained in the experiments performed for the iiWAS 2017 (19th International Conference on Information Integration and Web-based Applications & Services).
The experiments concerns OntoGenesis, an architecture for semantically enriching data service representations.


## Repository Info

* `Results` Contains all the [results](https://github.com/brunocnoliveira/iiwas2017-ontogenesis-experiments/tree/master/Results) of experiments in .csv format: 
  * DBpedia Subset Size Pilot Experiment (the 20%, 40%, 60% most frequent terms of DBpedia person data)
  * Thresholds Comparison (0.4, 0.6, 0.8)
  * Ontology Matchers Comparison (AROMA[<sup>1</sup>](#footnote1) and PARIS[<sup>2</sup>](#footnote2))
* `Sources` Source codes for the [Data Service](https://github.com/brunocnoliveira/iiwas2017-ontogenesis-experiments/tree/master/Sources/criminal-report-person-dataservice) desgined for experimental purposes. Its executable .jar file is in the run directory.
* `run` The script to facilitate the process for [running](https://github.com/brunocnoliveira/iiwas2017-ontogenesis-experiments/tree/master/run) the experiments, containing the Service and Ontogenesis API (which also includes the Engine) jars and configurations files. 
* `DataService_Repository.zip` The data service repository. It contains all data (in JSON) regarding police reports of SSP/SP (Secretariat of Public Security of the state of São Paulo, Brazil) used in the experiments.
* `AROMA-PARIS-OntoGenesis-data.zip` All data used in the experiments of Ontology Matchers (AROMA and PARIS). Note that the dbpedia-tdb has been removed due to its huge size not acceptable in git repository.
* `SetupInstructions` All the [instructions](https://github.com/brunocnoliveira/iiwas2017-ontogenesis-experiments/blob/master/SetupInstructions.md) to set up the data service and run OntoGenesis.
* `SourceRepo` Contains the URL of all other [source codes](https://github.com/brunocnoliveira/iiwas2017-ontogenesis-experiments/blob/master/SourceRepo) available.


## Footnotes
<a name="footnote1"><sup>1</sup></a>AROMA: Jérôme David. [Association Rule Ontology Matching Approach](http://dx.doi.org/10.4018/jswis.2007040102). International Journal
on Semantic Web and Information Systems 3, 2 (2007), 27–49.
<a name="footnote2"><sup>1</sup></a>PARIS: 

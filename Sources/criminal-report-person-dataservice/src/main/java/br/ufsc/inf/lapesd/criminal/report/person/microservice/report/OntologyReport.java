package br.ufsc.inf.lapesd.criminal.report.person.microservice.report;

import java.util.List;

import br.ufsc.inf.lapesd.ontogenesis.engine.matcher.EquivalentProperty;

public class OntologyReport {

	private String requisitionNumber;
	private String elapsedTime;
	private Integer totalCorrectEquivProps; //Relevant Retrieved precision/recall up
	private Integer totalEquivalentProperties; //retrieved properties (precision bottom)
	private Integer totalRelevantProps = 9; //Relevant: recall bottom
	
	private Double precision;
	private Double recall;
	private Double fMeasrue;
	
	private List<EquivalentProperty> equivPropsList; //EquivalentProperty Report
	
	public OntologyReport(Integer requisitionNumber, long elapsedTime, List<EquivalentProperty> equivPropsList) {
		this.requisitionNumber = requisitionNumber.toString();
		this.elapsedTime = elapsedTime + "";
		this.totalEquivalentProperties = equivPropsList.size();
		this.totalCorrectEquivProps = getTotalCorrects(equivPropsList);

		this.precision = (double) totalCorrectEquivProps / totalEquivalentProperties;
		this.recall = (double) totalCorrectEquivProps / totalRelevantProps;
		this.fMeasrue = (double) 2 * ((precision*recall)/(precision+recall));
		
		this.equivPropsList = equivPropsList;
	}
	
	public String getRequisitionNumber() {
		return requisitionNumber;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public String getTotalCorrectEquivProps() {
		return totalCorrectEquivProps+"";
	}
	public String getTotalEquivalentProperties() {
		return totalEquivalentProperties+"";
	}
	public String getTotalRelevantProps() {
		return totalRelevantProps+"";
	}
	public List<EquivalentProperty> getEquivalentProperties() {
		return this.equivPropsList;
	}
	public String getPrecision() {
		return precision+"";
	}
	public String getRecall() {
		return recall+"";
	}
	public String getFMeasure() {
		return fMeasrue+"";
	}

	public int getTotalCorrects(List<EquivalentProperty> equivPropsList) {
		int count =0 ;
		
		for(EquivalentProperty eqProp : equivPropsList) {
			
			String externalProp = eqProp.getExternalPropertyUri();
			
			switch (eqProp.getRepresentationPropertyUri()) {
			
				case NOME:
					if(externalProp.equals("http://xmlns.com/foaf/0.1/name"))
						count++;
					break;
					
				case PRIMEIRO_NOME:
					if(externalProp.equals("http://xmlns.com/foaf/0.1/givenName")
						//|| externalProp.equals("http://xmlns.com/foaf/0.1/name")
							)
						count++;
					break;
					
				case DATA_NASCIMENTO:
					if(externalProp.equals("http://dbpedia.org/ontology/date")
						//|| externalProp.equals("http://dbpedia.org/ontology/birthDate") 
							)
						count++;
					break;
					
				case PROFISSAO:
					if(externalProp.equals("http://purl.org/dc/elements/1.1/description"))
						count++;
					break;
					
				case NATURALIDADE:
					if(externalProp.equals("http://www.geonames.org/ontology#name"))
						count++;
					break;
					
				case NACIONALIDADE:
					if(externalProp.equals("http://www.geonames.org/ontology#name"))
						count++;
					break;
					
				case SEXO:
					if(externalProp.equals("http://www.omg.org/spec/EDMC-FIBO/FND/AgentsAndPeople/People/hasGender"))
						count++;
					break;
					
				case CUTIS:
					if(externalProp.equals("http://dbpedia.org/ontology/skinColor"))
						count++;
					break;
	
				case RG:
					if(externalProp.equals("http://www.omg.org/spec/EDMC-FIBO/FND/AgentsAndPeople/People/NationalIdentificationNumber"))
						count++;
					break;
					
				default:
					break;
			}
		}
		return count;
	}
	
	
	/* Properties */
	private final String NATURALIDADE = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#naturalidade";
	private final String CUTIS = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#cutis";
	private final String DATA_NASCIMENTO = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#dataNascimento";
	private final String RG = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#rg";
	private final String PROFISSAO = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#profissao";
	private final String NACIONALIDADE = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#nacionalidade";
	private final String NOME = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#nome";
	private final String SEXO = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#sexo";
	private final String PRIMEIRO_NOME = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#primeiroNome";
	//There is no semantic concept for instrucao in external sources
	//private final String INSTRUCAO = "http://127.0.0.1:8081/criminal-report-person-microservice/ontology#instrucao";
}

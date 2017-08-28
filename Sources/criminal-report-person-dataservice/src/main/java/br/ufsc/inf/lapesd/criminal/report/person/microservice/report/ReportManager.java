package br.ufsc.inf.lapesd.criminal.report.person.microservice.report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.ufsc.inf.lapesd.ontogenesis.engine.matcher.EquivalentProperty;


public class ReportManager {
	
	private Path pathOntologyReport;
	private Path pathEqPropsReport;	
	private List<OntologyReport> ontologyReportList = new ArrayList<OntologyReport>();
	
	public ReportManager() throws IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmm");
		String dateTime = df.format(new Date());
		
		String fileNameCSV = String.format("%s_ReportOntologyProperties.%s", dateTime,"csv");	
		String header = "RequisitionNumber, ElapsedTime, totalCorrectEquivProps, totalEquivalentProperties, totalRelevantProps, Precision, Recall, FMeasure";
		this.pathOntologyReport = Paths.get(fileNameCSV);		
		if(!Files.exists(Paths.get(fileNameCSV)))
			createReportFileCSV(pathOntologyReport, header);
		
		fileNameCSV = String.format("%s_ReportEquivPropsStrength.%s", dateTime,"csv");
		header = "RequisitionNumber, microservicePropertyURI, externalSourcePropertyURI, strengthOfEquivalence, quantityOfValuesMatched";
		this.pathEqPropsReport = Paths.get(fileNameCSV);		
		if(!Files.exists(Paths.get(fileNameCSV)))
			createReportFileCSV(pathEqPropsReport, header);
	}
	
	
	public void add(OntologyReport ontologyReport) {
		ontologyReportList.add(ontologyReport);
	}
	
	
	public void flushToFile() {		
		flushOntologyReport();
		flushEquivalentProperties();
		ontologyReportList.clear();
	}
	
	private void flushOntologyReport() {
		
		if(ontologyReportList.size() > 0 ) {
			try {
				String lines;
				StringBuilder line = new StringBuilder();
				
				for(OntologyReport data : ontologyReportList) {
					line.append(data.getRequisitionNumber()).append(",");
					line.append(data.getElapsedTime()).append(",");
					line.append(data.getTotalCorrectEquivProps()).append(",");
					line.append(data.getTotalEquivalentProperties()).append(",");
					line.append(data.getTotalRelevantProps()).append(",");
					line.append(data.getPrecision()).append(",");
					line.append(data.getRecall()).append(",");
					line.append(data.getFMeasure());
					line.append(System.getProperty("line.separator"));
				}	
				
				lines = line.toString();
				Files.write(pathOntologyReport, lines.getBytes(), StandardOpenOption.APPEND);
				System.out.printf("[REPORT] Saved %s records to file: %s\n", ontologyReportList.size(), pathOntologyReport.toString());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void flushEquivalentProperties() {
		
		if(ontologyReportList.size() > 0 ) {
			try {
				int records=0;
				String lines;
				StringBuilder line = new StringBuilder();
				
				for(OntologyReport data : ontologyReportList) {
					for(EquivalentProperty eqProperty : data.getEquivalentProperties()) {
						line.append(data.getRequisitionNumber()).append(",");
						line.append(eqProperty.getRepresentationPropertyUri()).append(",");
						line.append(eqProperty.getExternalPropertyUri()).append(",");
						line.append(eqProperty.getEquivalenceStrength()).append(",");
						line.append(eqProperty.getMapPropertiesStrengths().size());
						line.append(System.getProperty("line.separator"));
						records++;
					}
				}
				
				lines = line.toString();
				Files.write(pathEqPropsReport, lines.getBytes(), StandardOpenOption.APPEND);
				System.out.printf("[REPORT] Saved %s records to file: %s\n", records, pathEqPropsReport.toString());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createReportFileCSV(Path path, String header) throws IOException {
		Files.write(path
				, Arrays.asList(header)
				, StandardCharsets.UTF_8
				,StandardOpenOption.CREATE);
	}

}

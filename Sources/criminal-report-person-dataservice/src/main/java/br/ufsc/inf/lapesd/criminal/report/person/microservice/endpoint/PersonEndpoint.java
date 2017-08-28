package br.ufsc.inf.lapesd.criminal.report.person.microservice.endpoint;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import br.ufsc.inf.lapesd.criminal.report.person.microservice.config.OntoGenesisAdapter;
import br.ufsc.inf.lapesd.criminal.report.person.microservice.model.CriminalReport;
import br.ufsc.inf.lapesd.criminal.report.person.microservice.model.Person;
import br.ufsc.inf.lapesd.criminal.report.person.microservice.report.OntologyReport;
import br.ufsc.inf.lapesd.criminal.report.person.microservice.report.ReportManager;
import br.ufsc.inf.lapesd.criminal.report.person.microservice.service.PersonService;
import br.ufsc.inf.lapesd.criminal.report.person.microservice.service.ReportService;
import br.ufsc.inf.lapesd.ontogenesis.engine.SemanticFeatures;
import br.ufsc.inf.lapesd.ontogenesis.engine.matcher.EquivalentProperty;

@Path("person")
public class PersonEndpoint {

	@Autowired
    private PersonService personService;
	@Autowired
    private ReportService reportService;
	@Autowired
	private OntoGenesisAdapter ontoGenesisAdapter;
    
    @Context
    private UriInfo uriInfo;
    
    
    @GET
    @Path("rg/{idPerson}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadPersonById(@PathParam("idPerson") String idPerson) {

        try {
            Person person = personService.loadPersonByRg(idPerson);
            if(person != null) {
//            	setPersonId(person);
            	return Response.ok(new Gson().toJson(person)).build();
            } 
            else {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (FileNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("gender/{gender}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadPersonsByGenre(@PathParam("gender") String gender) {
       
        try {
            List<Person> persons = personService.loadAllPersonsByGender(gender);
//            setPersonId(persons);
            return Response.ok(new Gson().toJson(persons)).build();
        } catch (FileNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("skin/{skinColor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadPersonsBySkinColor(@PathParam("skinColor") String skinColor) {
       
        try {
            List<Person> persons = personService.loadAllPersonsBySkinColor(skinColor);
//            setPersonId(persons);
            return Response.ok(new Gson().toJson(persons)).build();
        } catch (FileNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("education/{education}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadPersonsByEducation(@PathParam("education") String education) {
       
        try {
            List<Person> persons = personService.loadAllPersonsByEducation(education);
//            setPersonId(persons);
            return Response.ok(new Gson().toJson(persons)).build();
        } catch (FileNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    
//    private void setPersonId(Person person) {
//    	person.setId(uriInfo.getRequestUri().toASCIIString());
//    }
//    
//    private void setPersonId(List<Person> persons) {
//    	for (Person p: persons) {
//    		p.setId(uriInfo.getRequestUri().toASCIIString());
//    	}
//    }
    

    //EXAMPLE PURPOSE
//    public void getOnt() {
//    	String representation = "{ " + 
//    			"  \"PoliceReport\": {" + 
//    			"     \"reportID\": \"2015-10004-794\"," + 
//    			"     \"location\": \"Train Station ...\"," + 
//    			"     \"personInvolved\": {" + 
//    			"        " + 
//    			"        \"name\": \"CARLOS ALBERTO DOS SANTOS\"," + 
//    			"        \"docID\": \"015****18\"," + 
//    			"        \"birthDate\": \"12-21-1966\"," + 
//    			"        \"placeOfBirth\": \"Sao Paulo-SP\"," + 
//    			"        \"nationality\": \"Brazilian\"," + 
//    			"        \"gender\" : \"Male\"" + 
//    			"       " + 
//    			"     }" + 
//    			"  }" + 
//    			"}";
//    
//    	ontoGenesisAdapter.registryMicroservice();
//    	SemanticFeatures semanticFeatures = ontoGenesisAdapter.sendToEnrich(representation);
//    	System.out.println("===Final Ontology===\n "+semanticFeatures.getOntologyAsString());
//    }
    
    
    /**
     * Example of requisition to start experiment:
     * http://localhost:8081/criminal-report-person-microservice/person/startOntoGenesisExperiment?requisitions=10
     * @param requisitions
     * @return
     */
    @GET
    @Path("startOntoGenesisExperiment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startOntoGenesisExperiment(@QueryParam("requisitions") int requisitions) {
    	
    	try {
    		ReportManager reportManager = new ReportManager();
        	SemanticFeatures semanticFeatures = null;
        	
        	long startTime = System.currentTimeMillis();        	
        	ontoGenesisAdapter.registryMicroservice();
        	
        	List<CriminalReport> reports = reportService.loadAllReports();
        	int countFlushReport = 1;
        	
        	reportFor : for(int c=0; c<reports.size(); c++) {
        		
        		//Get Random Person
        		int i = getRandomInt(reports.size());
        		
        		CriminalReport criminalReport = reportService.loadReport(reports.get(i).getIdBO());
        		List<Person> persons = criminalReport.getPartesEnvolvidas();
        		if(persons != null && persons.size() > 0) {
        			for(Person p : persons) {
//        				setPersonId(p);
        				p.applyValuesEN();
                    	System.out.printf("Representation %d of %d:\n",countFlushReport,requisitions);
        				String representation = new Gson().toJson(p);
        				System.out.println(representation);
        				
        				long requisitionStartTime = System.currentTimeMillis();
                    	semanticFeatures = ontoGenesisAdapter.sendToEnrich(representation);
                    	long requisitionTotalTime = (System.currentTimeMillis() - requisitionStartTime);
                    	               	
                    	List<EquivalentProperty> equivPropsList = semanticFeatures.getEquivalentProperties();
                    	OntologyReport ontologyReport = new OntologyReport(countFlushReport, requisitionTotalTime, equivPropsList);
                    	reportManager.add(ontologyReport);
                    	
                    	if(countFlushReport%10 == 0)
                    		reportManager.flushToFile();
                    	                 	
                    	if(++countFlushReport > requisitions) break reportFor;
        			}
        		}
        	}
        	reportManager.flushToFile();
        	
        	long totalTime = (System.currentTimeMillis() - startTime);
        	
        	System.out.println("===Final Ontology===\n "+semanticFeatures.getOntologyAsString());
    		System.out.printf("\n End of execution in: %d ms\n", totalTime);
    		
    		
        	return Response.status(Status.OK).build();
        } 
    	catch (FileNotFoundException e) {
        	e.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        } 
    	catch (IOException e) {
			e.printStackTrace();
        	return Response.status(Status.NOT_FOUND).build();
		}
    }
    
    
    private Set<Integer> setOfIds = new HashSet<Integer>();
    
    private int getRandomInt(int limit) {
    	Random random = new Random();
    	int i = random.nextInt(limit) + 0;
    	while(setOfIds.contains(i)) {
    		i = random.nextInt(limit) + 0;
    	}
    	setOfIds.add(i);
    	return i;
    }
    
    
    public void start(int requisitions) {
    	
    }
    
}

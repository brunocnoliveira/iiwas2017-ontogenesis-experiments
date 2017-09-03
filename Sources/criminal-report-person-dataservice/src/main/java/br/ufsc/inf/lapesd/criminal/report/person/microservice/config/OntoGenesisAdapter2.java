package br.ufsc.inf.lapesd.criminal.report.person.microservice.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.ufsc.inf.lapesd.alignator.core.entity.loader.ServiceDescription;
import br.ufsc.inf.lapesd.ontogenesis.engine.SemanticFeatures;

@Component
public class OntoGenesisAdapter2 {
	
	@Value("${config.uriOntogenesis}")
    private String uriOntogenesis;
	
	@Value("${config.microserviceDescriptionFile}")
    private String microserviceDescriptionFile;
	
	SemanticFeatures semanticFeatures;
	
	
	/** Registers this on OntoGenesis */
	public void registryMicroservice() {
//			try {
//			ServiceDescription microserviceDescription = getThisDescription();
//			
//			Client client = ClientBuilder.newClient();
//	        WebTarget webTarget = client.target(uriOntogenesis).path("microservice/description");
//	
//	        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//        
//            String stringDescription = new Gson().toJson(microserviceDescription);
//            Response response = invocationBuilder.post(Entity.entity(stringDescription, MediaType.APPLICATION_JSON));
//
//            int status = response.getStatus();
//            
//            if (status == 200) {
//                System.out.println("Microservice successfully registred on OntoGenesis");
//                String readEntity = response.readEntity(String.class);
//                this.semanticFeatures = new Gson().fromJson(readEntity, SemanticFeatures.class);
//                System.out.println("==Ontology:\n"+semanticFeatures.getOntologyAsString());
//                System.out.println("==Semantic Mapping:\n"+semanticFeatures.getMappingSyntSemantic());
//            } 
//            else if (status == 404) {
//                System.out.println("Microservice NOT registred - HTTP 404");
//            }
//        } catch (Exception e) {
//            System.out.println("Microservice not registered");
//            e.printStackTrace();
//        }
	
	}
	
	public ServiceDescription getThisDescription() throws IOException {		
		String microserviceDescriptionJson = new String(Files.readAllBytes(Paths.get(microserviceDescriptionFile)));
		ServiceDescription microserviceDescription = new Gson().fromJson(microserviceDescriptionJson, ServiceDescription.class);
		microserviceDescription.setServerPort("8081");
		microserviceDescription.setUriBase("/criminal-report-person-microservice");
		microserviceDescription.setIpAddress("localhost");
		return microserviceDescription;
	}
	
	/** Sends a syntactic json of a person to be enriched
	 * @param json
	 */
	public SemanticFeatures sendToEnrich(String json) {
//		try {
//			ServiceDescription microserviceDescription = getThisDescription();
//			String serverPort = microserviceDescription.getServerPort();
//			String uriBase = microserviceDescription.getUriBase();
//			
//			Client client = ClientBuilder.newClient();
//	        WebTarget webTarget = client.target(uriOntogenesis)
//	        								.path("microservice/representation")
//	        								.queryParam("serverPort", serverPort)
//	        								.queryParam("uriBase", uriBase);
//	
//	        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//        
//            Response response = invocationBuilder.post(Entity.entity(json, MediaType.APPLICATION_JSON));
//
//            int status = response.getStatus();
//            
//            if (status == 200) {
//                String readEntity = response.readEntity(String.class);
//                this.semanticFeatures = new Gson().fromJson(readEntity, SemanticFeatures.class); 
//                return semanticFeatures;
//            } 
//            else if (status == 404) {
//                System.out.println("Representation NOT Sent - HTTP 404");
//            }
//        } catch (Exception e) {
//            System.out.println("Microservice not registered");
//            e.printStackTrace();
//        }
		return null;
	}

}

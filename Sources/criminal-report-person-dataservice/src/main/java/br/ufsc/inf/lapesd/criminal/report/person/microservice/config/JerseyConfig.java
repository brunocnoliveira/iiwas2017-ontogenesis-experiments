package br.ufsc.inf.lapesd.criminal.report.person.microservice.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.ufsc.inf.lapesd.alignator.core.entity.loader.ServiceDescription;
import br.ufsc.inf.lapesd.ontogenesis.adapter.OntoGenesisAdapter;
import br.ufsc.inf.lapesd.ontogenesis.adapter.config.SimpleOntoGenesisAdapterConfig;
import br.ufsc.inf.lapesd.ontogenesis.adapter.providers.InstanceOntoGenesisAdapterContextResolver;


@Component
@ApplicationPath("/criminal-report-person-microservice")
public class JerseyConfig extends ResourceConfig {

	
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${config.uriOntogenesis}")
	private String uriOntoGenesis;

	
	@PostConstruct
	public void init() {
		this.register(RequestContextFilter.class);
        this.register(CorsInterceptor.class);
        this.packages("br.ufsc.inf.lapesd.criminal.report.person.microservice.endpoint");
        this.packages("br.ufsc.inf.lapesd.ontogenesis.adapter");
        this.packages("br.ufsc.inf.lapesd.ld_jaxrs.jena");
        
        //OntoGenesis
        SimpleOntoGenesisAdapterConfig cfg;
        OntoGenesisAdapter adapter;
        ServiceDescription description;
        
        description = new ServiceDescription();
        description.setUriBase("/criminal-report-person-microservice");
        description.setServerPort(serverPort);

        cfg = new SimpleOntoGenesisAdapterConfig();
        cfg.setOntologyResourcePath("ontology");
        cfg.setDescription(description);
        cfg.setOntogenesisURI(uriOntoGenesis);

        adapter = new OntoGenesisAdapter(cfg);                
        this.register(new InstanceOntoGenesisAdapterContextResolver(adapter));
        adapter.registerService();
	}
}
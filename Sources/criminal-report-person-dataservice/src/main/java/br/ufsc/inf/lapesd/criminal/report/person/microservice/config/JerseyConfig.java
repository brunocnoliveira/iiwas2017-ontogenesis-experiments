package br.ufsc.inf.lapesd.criminal.report.person.microservice.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/criminal-report-person-microservice")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.register(RequestContextFilter.class);
        this.register(CorsInterceptor.class);
        this.packages("br.ufsc.inf.lapesd.criminal.report.person.microservice.endpoint");
    }
}
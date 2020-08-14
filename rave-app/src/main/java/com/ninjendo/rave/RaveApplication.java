package com.ninjendo.rave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableScheduling
public class RaveApplication 
{
    public static void main(String[] args) {
        SpringApplication.run(RaveApplication.class, args);
    }
 
    @Bean
    HazelcastInstance hazelcastInstance() {
        // for client HazelcastInstance LocalMapStatistics will not available
        return HazelcastClient.newHazelcastClient();
    }
  
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

    public static final Contact DEFAULT_CONTACT
    = new Contact(
    "Jett Olano",
    "htp://ninjendo.com",
    "jettish@gmail.com");
    
    public static final ApiInfo DEFAULT_API_INFO
    = new ApiInfo(
    "RAVe Lead Funnelling System",
    "RAVE API",
    "1.0",
    "urn:tos",
    DEFAULT_CONTACT,
    "Apache 2.0",
    "http://www.apache.org/licenses/LICENSE-2.0",
    new ArrayList<>());  
    
    public static final Set<String>	DEFAULT_CONSUMES_AND_PRODUCES = 
    		new HashSet<String>(Arrays.asList("application/json", "application/xml"));
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(DEFAULT_API_INFO)
          .produces(DEFAULT_CONSUMES_AND_PRODUCES)
          .consumes(DEFAULT_CONSUMES_AND_PRODUCES);
    }
}

package com.ninjendo.rave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@ComponentScan("com.ninjendo.rave")
public class RaveApplication 
{
    public static void main(String[] args) {
        SpringApplication.run(RaveApplication.class, args);
    }
	
//    public static void main(String[] args) {
//        new SpringApplicationBuilder()
//                .sources(RaveApplication.class)
//                .profiles("client")
//                .run(args);
//    }
//    
    
    @Bean
    //@Profile("hzclient")
    HazelcastInstance hazelcastInstance() {
        // for client HazelcastInstance LocalMapStatistics will not available
        return HazelcastClient.newHazelcastClient();
        // return Hazelcast.newHazelcastInstance();
    }
    
    
//    @Bean
//    public HazelcastInstance getHazelcastInstance() throws IOException {        
//        HazelcastInstance hazelcastInstance=  HazelcastClient.newHazelcastClient(new XmlClientConfigBuilder("classpath:hazelcast-client.xml").build());
//        return hazelcastInstance;
//    }    
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
          "My REST API",
          "Some custom description of API.",
          "API TOS",
          "Terms of service",
          "myeaddress@company.com",
          "License of API",
          "API license URL");
        return apiInfo;
    }
}

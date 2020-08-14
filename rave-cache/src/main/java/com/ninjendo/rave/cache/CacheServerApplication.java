package com.ninjendo.rave.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.hazelcast.config.Config;

/**
 * 
 *
 */
@SpringBootApplication
@EnableCaching
public class CacheServerApplication 
{
    
    public static void main(String[] args) {
        SpringApplication.run(CacheServerApplication.class, args);
    }
    
    @Bean
    public Config hazelCastConfig() {
 
        Config config = new Config();
        config.setInstanceName("hazelcast-cache");

        return config;
    }
}

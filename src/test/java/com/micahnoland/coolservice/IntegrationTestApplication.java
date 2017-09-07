package com.micahnoland.coolservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegrationTestApplication.
 */
@ComponentScan(excludeFilters = { 
		@ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
})
@Configuration
public class IntegrationTestApplication extends SpringBootServletInitializer {
	
	 /**
     * The main method.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestApplication.class, args);
    }
    
	/* (non-Javadoc)	
	 * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IntegrationTestApplication.class);
	}
}
	

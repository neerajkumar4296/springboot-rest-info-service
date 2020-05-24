package com.weatherinfoservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@EnableEncryptableProperties
@EnableEurekaClient
@EnableScheduling
public class SpringBootRestInfoApplication {

	// run with default configuration
	  //public static void main(String[] args) {
	  //SpringApplication.run(SpringBootRestInfoApplication.class, args); }
	 

    // for building the Spring boot application with custom configuration properties file
	public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(SpringBootRestInfoApplication.class)
                 // for custom properties file name
                  //.properties("spring.config.name:application")
                //.properties("spring.cloud.config.uri", "http://cloud-config-server-dev1.ap-south-1.elasticbeanstalk.com")
                // for providing the secret pass to jasypt at application start up for decryption of properties
                .properties("jasypt.encryptor.password:topsecret")
                .build()
                .run(args);
        
       
    }
}

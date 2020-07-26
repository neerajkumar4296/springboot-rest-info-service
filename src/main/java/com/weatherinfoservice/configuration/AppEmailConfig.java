package com.weatherinfoservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class AppEmailConfig {
	
	
	@Bean
	public FreeMarkerConfigurationFactoryBean factoryBean() {
		FreeMarkerConfigurationFactoryBean fcb= new FreeMarkerConfigurationFactoryBean();
		fcb.setTemplateLoaderPath("classpath:/templates");
		return fcb;
	}

}

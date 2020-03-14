package com.agora.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AgoraUtility {
	
	@Autowired
	public Environment environment;
	
	public String getValue(String propertyName) {
		return environment.getProperty(propertyName);
	}

}

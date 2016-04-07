package com.sktechx.sso.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * TODO : @PropertySource 와 @ConfigurationProperties 의 정확한 차이를 확인해봐야 겠음
 *
 * 일단
 *
 * @PropertySource : Spring MVC
 * @ConfigurationProperties : Spring Boot.
 *
 *
 */

// @Configuration
// @PropertySource(value = "classpath:/config/application.properties")
@ConfigurationProperties(locations = { "classpath:/config/application.properties" })
@Data
public class PropertiesConfig {

	@Value("${auth.domain.full}")
	private String authDomain;

	@Value("${debug}")
	private String debug;

}

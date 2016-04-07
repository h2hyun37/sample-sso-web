package com.sktechx.sso;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sktechx.sso.config.PropertiesConfig;


/**
 * Application entry point
 *
 */

// 아래 어노테이션들은 @SpringBootApplication 을 선언함으로써 생략이 가능하다...
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
@SpringBootApplication
@EnableConfigurationProperties(value = { PropertiesConfig.class })
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("=========== CommandLineRunner::run()");
		for (String arg : arg0) {
			System.out.println("\targ : " + arg);
		}
		System.out.println("=========== System.getProperties() : " + System.getProperties());



	}

	// @Override
	// public void run(ApplicationArguments arg0) throws Exception {
	// System.out.println(arg0);
	// }

}

package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.run(args);
	}

	@Bean
	ApplicationRunner init() {
		return args -> {
			log.info("App runner!");
		};
	}

	@Bean
	ApplicationListener<ContextRefreshedEvent> refreshListener() {
		return event -> {
			log.info("Context refreshed!");
		};
	}

}

@RestController
@AllArgsConstructor
class Hello {

	private HelloService service;

	@GetMapping("/{name}")
	String sayHello(@PathVariable(name = "name") String name) {
		return service.greet(name);
	}
}

@Component
@AllArgsConstructor
@EnableConfigurationProperties(HelloConfig.class)
class HelloService {

	private HelloConfig config;

	String greet(String name) {
		return String.format(config.getGreeting() ,name);
	}
}

@Data
@ConfigurationProperties(prefix = "hello")
class HelloConfig {

	/**
	 * Greeting template.
	 */
	private String greeting;

}

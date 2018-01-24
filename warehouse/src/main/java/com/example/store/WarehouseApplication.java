package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	@Bean
	InfoContributor myInfo() {
		return builder -> {
			builder.withDetail("fromCode", "someValue");
		};
	}

	@Bean
	HealthIndicator myHealth() {
		return () -> {
			return Health.status(Status.UP).withDetail("myDetail", "OK").build();
		};
	}
}

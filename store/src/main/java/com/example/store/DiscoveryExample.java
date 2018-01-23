package com.example.store;

import com.example.store.item.ItemRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.cloud.config.enabled", havingValue = "true", matchIfMissing = true)
public class DiscoveryExample {

    @Bean
    public ApplicationRunner discoveryClientDemo(DiscoveryClient discovery) {
        return args -> {
            try {
                log.info("------------------------------");
                log.info("DiscoveryClient Example");

                discovery.getInstances("warehouse").forEach(instance -> {
                    log.info("Warehouse service: ");
                    log.info("  ID: {}", instance.getServiceId());
                    log.info("  URI: {}", instance.getUri());
                    log.info("  Meta: {}", instance.getMetadata());
                });

                log.info("------------------------------");
            } catch (Exception e) {
                log.error("DiscoveryClient Example Error!", e);
            }
        };
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApplicationRunner restTemplateDemo(RestTemplate rest) {
        return args -> {
            ParameterizedTypeReference<List<ItemRepresentation>> responseType =
                    new ParameterizedTypeReference<List<ItemRepresentation>>() {};

            ResponseEntity<List<ItemRepresentation>> response = rest.exchange(
                    "http://warehouse/items",
                    HttpMethod.GET,
                    null,
                    responseType);

            log.info("------------------------------");
            log.info("RestTemplate Example");

            log.info("Status: {}", response.getStatusCode());
            if (response.getStatusCode().is2xxSuccessful()) {
                response.getBody().forEach(item -> {
                    log.info("  {}", item);
                });
            }

            log.info("------------------------------");
        };
    }
}

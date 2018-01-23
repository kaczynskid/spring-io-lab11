package com.example.store.item;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "warehouse", fallback = ItemsFallback.class)
public interface ItemsClient {

    @GetMapping("/items")
    List<ItemRepresentation> findAll();

    @GetMapping("/items/{id}")
    ItemRepresentation findOne(@PathVariable("id") long id);
}

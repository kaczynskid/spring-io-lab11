package com.example.store.item;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("warehouse/items")
public interface ItemsClient {

    @GetMapping
    List<ItemRepresentation> findAll();

    @GetMapping("/{id}")
    ItemRepresentation findOne(@PathVariable("id") long id);
}

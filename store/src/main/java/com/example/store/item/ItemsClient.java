package com.example.store.item;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("warehouse/items")
public interface ItemsClient {

    @GetMapping
    List<ItemRepresentation> findAll();
}

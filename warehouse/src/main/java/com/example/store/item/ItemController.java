package com.example.store.item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
class ItemController {

    private ItemService items;

    @GetMapping
    List<ItemRepresentation> findAll() {
        return items.findAll().stream().map(ItemRepresentation::of).collect(toList());
    }

}

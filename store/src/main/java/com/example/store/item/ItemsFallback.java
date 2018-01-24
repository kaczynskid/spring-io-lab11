package com.example.store.item;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ItemsFallback implements ItemsClient {

    @Override
    public List<ItemRepresentation> findAll() {
        return Collections.singletonList(findOne(0));
    }

    @Override
    public ItemRepresentation findOne(long id) {
        return new ItemRepresentation("unknown product", BigDecimal.ZERO);
    }
}

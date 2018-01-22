package com.example.store

import com.example.store.item.Item
import com.example.store.item.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@AutoConfigureTestDatabase
@ActiveProfiles("test")
//@Import(SpecBaseConfig)
class SpecBase extends Specification {
}

@TestConfiguration
class SpecBaseConfig {

    @Autowired
    ItemRepository items

    @Bean
    ApplicationRunner itemTestData() {
        return {
            items.save(new Item(null, 'A', 100, 40.0))
            items.save(new Item(null, 'B', 100, 10.0))
            items.save(new Item(null, 'C', 100, 30.0))
            items.save(new Item(null, 'D', 100, 25.0))
        } as ApplicationRunner
    }
}

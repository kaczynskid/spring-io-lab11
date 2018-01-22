package com.example.store.item

import com.example.store.SpecBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ItemRepositorySpec extends SpecBase {

    @Autowired
    ItemRepository items

    def "Finds the most expensive item"() {
        expect:
            with(items.findTopByOrderByPriceDesc()) {
                id == 1L
                price == 40.0
            }
    }

    def "Finds by name prefix"() {
        given:
            items.save(new Item(null, 'Xero', 1, 100.0))
        expect:
            with(items.findByNamePrefix('X')) {
                size() == 1
                get(0).name == 'Xero'
            }
    }
}

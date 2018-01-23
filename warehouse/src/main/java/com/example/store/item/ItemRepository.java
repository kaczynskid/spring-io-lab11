package com.example.store.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findTopByOrderByPriceDesc();

    @Query("from Item where name like :prefix%")
    List<Item> findByNamePrefix(@Param("prefix") String namePrefix);
}

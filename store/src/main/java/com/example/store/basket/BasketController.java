package com.example.store.basket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/baskets")
public class BasketController {

	private final BasketService baskets;

	public BasketController(BasketService baskets) {
		this.baskets = baskets;
	}

	@PostMapping
	public BasketRepresentation create() {
		Basket basket = baskets.create();
		log.info("Created basket {}", basket.getId());
		return BasketRepresentation.of(basket);
	}

	@GetMapping("/{id}")
	public BasketRepresentation findOne(@PathVariable("id") long id) {
		return BasketRepresentation.of(baskets.findOne(id));
	}
}

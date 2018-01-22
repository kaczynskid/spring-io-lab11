package com.example.store.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
class ItemUpdate {

	private final long id;

	private final String name;

	private final BigDecimal price;

	@JsonCreator
	static ItemUpdate of(@JsonProperty("name") String name, @JsonProperty("price") BigDecimal price) {
		return new ItemUpdate(0, name, price);
	}

	ItemUpdate withId(long id) {
		return new ItemUpdate(id, name, price);
	}
}

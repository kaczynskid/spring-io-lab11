package com.example.store.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
@ToString
public class ItemStockUpdate {

	private final long id;

	private final int countDiff;

	@JsonCreator
	static ItemStockUpdate of(@JsonProperty("countDiff") int countDiff) {
		return new ItemStockUpdate(0, countDiff);
	}

	int applyFor(Item item) {
		int count = item.getCount();

		if (count + countDiff < 0) {
			throw new OutOfStock(item, countDiff);
		}

		return count + countDiff;
	}

	ItemStockUpdate withId(long id) {
		return new ItemStockUpdate(id, countDiff);
	}
}

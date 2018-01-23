package com.example.store.basket.item;

import com.example.store.MathProperties;
import com.example.store.item.ItemRepresentation;
import com.example.store.item.ItemsClient;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.math.BigDecimal.ZERO;

@Component
public class BasketItemService {

	private final BasketItemRepository basketItems;
	private final ItemsClient items;
	private final MathProperties math;

	public BasketItemService(BasketItemRepository basketItems, ItemsClient items, MathProperties math) {
		this.basketItems = basketItems;
		this.items = items;
		this.math = math;
	}

	public List<BasketItem> findAllItems(long basketId) {
		return basketItems.findByBasketId(basketId);
	}

	public BasketItem findOneItem(long basketId, long itemId) {
		return basketItems.findByBasketIdAndItemId(basketId, itemId).orElse(null);
	}

	public BasketUpdateDiff updateItem(long basketId, long itemId, int count) {
		BasketItem basketItem = basketItems.findByBasketIdAndItemId(basketId, itemId)
				.orElse(newBasketItem(basketId, itemId));

		int newUnitCount = basketItem.getUnitCount() + count;
		if (newUnitCount > 0) {
			return updateInBasket(basketItem, newUnitCount);
		} else {
			return removeFromBasket(basketItem);
		}
	}

	private BasketUpdateDiff updateInBasket(BasketItem basketItem, int newUnitCount) {
		ItemRepresentation changes = items.findOne(basketItem.getItemId());
		BasketUpdateDiff diff = basketItem.update(changes, newUnitCount, math);
		basketItems.save(basketItem);
		return diff;
	}

	private BasketUpdateDiff removeFromBasket(BasketItem basketItem) {
		BasketUpdateDiff diff = BasketUpdateDiff.ofItem(basketItem);
		basketItems.delete(basketItem);
		return diff;
	}

	private static BasketItem newBasketItem(long basketId, long itemId) {
		return new BasketItem(basketId, itemId);
	}
}

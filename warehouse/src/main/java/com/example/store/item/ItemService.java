package com.example.store.item;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ItemService {

	private final ItemRepository items;

	private final CounterService conuters;

	public List<Item> findAll() {
		return items.findAll();
	}

	public Item findOne(long id) {
		return items.getOne(id);
	}

	public Item create(Item item) {
		return items.save(item);
	}

	public Item update(ItemUpdate changes) {
		Assert.notNull(changes, "Changes cannot be null");

		Item item = findOne(changes.getId());
		item.update(changes);
		return items.save(item);
	}

	public Item updateStock(ItemStockUpdate changes) {
		Assert.notNull(changes, "Changes cannot be null");

		Item item = findOne(changes.getId());
		item.updateStock(changes);

		conuters.increment("item." + item.getId() + ".update");

		return items.save(item);
	}
}


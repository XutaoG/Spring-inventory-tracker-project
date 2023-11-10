package com.taoge.inventorymanagement.dao;

import com.taoge.inventorymanagement.entity.Item;

import java.util.List;

public interface ItemRepository
{
	List<Item> findAll();

	Item findById(int id);

	Item findByName(String itemName);

	void add(Item item);

	void save(Item item);

	void deleteById(int id);
}

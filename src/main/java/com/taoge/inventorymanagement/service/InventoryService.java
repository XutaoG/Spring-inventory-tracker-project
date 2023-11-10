package com.taoge.inventorymanagement.service;

import com.taoge.inventorymanagement.entity.Item;

import java.util.List;

public interface InventoryService
{
	List<Item> findAll();

	Item findById(int id);

	Item findByName(String name);

	void add(Item item);

	void save(Item item);

	void deleteById(int id);
}

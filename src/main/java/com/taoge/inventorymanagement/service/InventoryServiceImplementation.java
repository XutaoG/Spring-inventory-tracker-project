package com.taoge.inventorymanagement.service;

import com.taoge.inventorymanagement.dao.ItemRepository;
import com.taoge.inventorymanagement.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImplementation implements InventoryService
{
	private ItemRepository itemRepository;

	@Autowired
	public InventoryServiceImplementation(ItemRepository itemRepository)
	{
		this.itemRepository = itemRepository;
	}

	@Override
	public List<Item> findAll()
	{
		return itemRepository.findAll();
	}

	@Override
	public Item findById(int id)
	{
		return itemRepository.findById(id);
	}

	@Override
	@Transactional
	public Item findByName(String name)
	{
		return itemRepository.findByName(name);
	}

	@Override
	@Transactional
	public void add(Item item)
	{
		itemRepository.add(item);
	}

	@Override
	@Transactional
	public void save(Item item)
	{
		itemRepository.save(item);
	}

	@Override
	@Transactional
	public void deleteById(int id)
	{
		itemRepository.deleteById(id);
	}
}

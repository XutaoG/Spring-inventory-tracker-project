package com.taoge.inventorymanagement.dao;

import com.taoge.inventorymanagement.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImplementation implements ItemRepository
{
	private EntityManager entityManager;

	@Autowired
	public ItemRepositoryImplementation(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	@Override
	public List<Item> findAll()
	{
		TypedQuery<Item> typedQuery = entityManager.createQuery("From Item", Item.class);

		return typedQuery.getResultList();
	}

	@Override
	public Item findById(int id)
	{
		return entityManager.find(Item.class, id);
	}

	@Override
	public Item findByName(String itemName)
	{
		TypedQuery<Item> typedQuery = entityManager.createQuery("From Item WHERE itemName = :itemName", Item.class);

		typedQuery.setParameter("itemName", itemName);

		if (typedQuery.getResultList().isEmpty())
		{
			return null;
		}

		return typedQuery.getResultList().get(0);
	}

	@Override
	public void add(Item item)
	{
		if (item == null)
		{
			return;
		}

		Item foundItem = findByName(item.getItemName());

		if (foundItem != null)
		{
			foundItem.setQuantity(foundItem.getQuantity() + item.getQuantity());
			entityManager.merge(foundItem);
		}
		else
		{
			entityManager.merge(item);
		}
	}

	@Override
	public void save(Item item)
	{
		if (item == null)
		{
			return;
		}

		entityManager.merge(item);
	}

	@Override
	public void deleteById(int id)
	{
		if (findById(id) == null)
		{
			return;
		}

		entityManager.remove(findById(id));
	}

}

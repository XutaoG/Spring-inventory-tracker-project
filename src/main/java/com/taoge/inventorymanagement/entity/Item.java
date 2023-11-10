package com.taoge.inventorymanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "inventory")
public class Item
{
	// ***** Fields *****

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "Item name is required")
	@Size(min = 0, message = "Item name is required")
	@Column(name = "item_name")
	private String itemName;

	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity cannot be less than 0")
	@Column(name = "quantity")
	private Integer quantity;

	@NotNull(message = "Cost is required")
	@DecimalMin(value = "0.01", message = "Cost cannot be less than 0.01")
	@Column(name = "cost")
	private Double cost;

	@NotNull(message = "Unit is required")
	@Column(name = "unit")
	private String unit;

	// ***** Constructor *****

	public Item()
	{

	}

	public Item(String itemName, Integer quantity, Double cost, String unit)
	{
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
		this.unit = unit;
	}

	// ***** Getters/Setters *****

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public Double getCost()
	{
		return cost;
	}

	public void setCost(Double cost)
	{
		this.cost = cost;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	@Override
	public String toString()
	{
		return "Item{" +
				"id=" + id +
				", itemName='" + itemName + '\'' +
				", quantity=" + quantity +
				", cost=" + cost +
				", unit='" + unit + '\'' +
				'}';
	}
}

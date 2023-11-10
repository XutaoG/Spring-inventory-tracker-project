package com.taoge.inventorymanagement;

import com.taoge.inventorymanagement.entity.Item;
import com.taoge.inventorymanagement.service.InventoryService;
import com.taoge.inventorymanagement.service.InventoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class StoremanagementApplication
{
	private InventoryService inventoryService;

	public static void main(String[] args)
	{
		SpringApplication.run(StoremanagementApplication.class, args);
	}
}

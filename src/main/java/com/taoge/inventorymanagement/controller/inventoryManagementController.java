package com.taoge.inventorymanagement.controller;

import com.taoge.inventorymanagement.entity.Item;
import com.taoge.inventorymanagement.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class inventoryManagementController
{
	@Value("${units}")
	private List<String> units;

	private InventoryService inventoryService;

	public inventoryManagementController(InventoryService inventoryService)
	{
		this.inventoryService = inventoryService;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/inventory")
	public String showInventory(Model model)
	{
		model.addAttribute("items", inventoryService.findAll());

		return "inventory-list";
	}

	@GetMapping("/inventory/addItem")
	public String addItem(Model model)
	{
		model.addAttribute("item", new Item());
		model.addAttribute("action", "add");
		model.addAttribute("units", units);

		return "item-form";
	}

	@PostMapping("/inventory/save")
	public String saveItem(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, @ModelAttribute("action") String action, Model model)
	{
		if (bindingResult.hasErrors())
		{
			model.addAttribute("item", item);
			model.addAttribute("action", action);
			model.addAttribute("units", units);
			return "item-form";
		}

		if (action.compareTo("update") == 0)
		{
			inventoryService.save(item);
		}
		else if (action.compareTo("add") == 0)
		{
			inventoryService.add(item);
		}

		// Use a redirect to avoid duplicate submission
		return "redirect:/inventory";
	}

	@GetMapping("/inventory/updateItem")
	public String updateItem(@RequestParam("id") int id, Model model)
	{
		// Create model attribute to bind item retrieved from service
		model.addAttribute("item", inventoryService.findById(id));
		model.addAttribute("action", "update");
		model.addAttribute("units", units);

		return "item-form";
	}

	@GetMapping("/inventory/deleteItem")
	public String deleteItem(@RequestParam("id") int id)
	{
		// Delete item from database
		inventoryService.deleteById(id);

		// Return to inventory
		return "redirect:/inventory";
	}

	@GetMapping("/access-denied")
	public String showLoginPage()
	{
		return "redirect:/inventory";
	}
}

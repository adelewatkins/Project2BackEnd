package com.lbg.cczone.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.cczone.domain.Item;
import com.lbg.cczone.dtos.ItemDTO;
import com.lbg.cczone.service.ItemService;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

	private ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;
	}

	@GetMapping("/get")
	public List<ItemDTO> getItem() {
		return this.service.getItem();

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Item> getItem(@PathVariable int id) {
		return this.service.getItem(id);

	}

	@PostMapping("/create")
	public ResponseEntity<Item> createItem(@RequestBody Item item) {
		return this.service.createItem(item);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item item) {
		return this.service.updateItem(id, item);
	}

	@DeleteMapping("delete/{id}")
	public boolean deleteItem(@PathVariable int id) {
		return this.service.deleteItem(id);
	}

	@PatchMapping("/checkOut/{itemId}/{cartId}")
	public ResponseEntity<Item> checkOut(@PathVariable int itemId, @PathVariable int cartId) {
		return this.service.checkOut(itemId, cartId);
	}

	@PatchMapping("/checkIn/{itemId}")
	public ResponseEntity<Item> checkIn(@PathVariable int itemId) {
		return this.service.checkIn(itemId);
	}

}

package com.lbg.cczone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lbg.cczone.Repos.CartRepo;
import com.lbg.cczone.domain.Cart;

@Service
public class CartService {

	private CartRepo repo;

	public CartService(CartRepo repo) {
		super();
		this.repo = repo;
	}

	public List<Cart> getCart() {
		return this.repo.findAll();
	}

	public ResponseEntity<Cart> getCart(int id) {
		Optional<Cart> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}
		Cart body = found.get();
		return ResponseEntity.ok(body);

	}

	public ResponseEntity<Cart> createCart(Cart cart) {

		Cart created = this.repo.save(cart);
		return new ResponseEntity<Cart>(created, HttpStatus.CREATED);
	}

	public ResponseEntity<Cart> updateCart(int id, Cart cart) {
		Optional<Cart> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}
		Cart existing = found.get();
		if (cart.getItem() != null) {
			existing.setItem(cart.getItem());
		}
		if (cart.getCartItemQuantity() != null) {
			existing.setCartItemQuantity(cart.getCartItemQuantity());
		}

		Cart updated = this.repo.save(existing);
		return ResponseEntity.ok(updated);

	}

	public boolean deleteCart(@PathVariable int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}

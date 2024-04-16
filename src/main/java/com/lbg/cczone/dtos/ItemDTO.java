package com.lbg.cczone.dtos;

import com.lbg.cczone.domain.Item;

public class ItemDTO {

	private Integer id;

	private String itemName;

	private Double itemPrice;

	private Integer itemQuantity;
	private String image;

	private Integer cartId;

	public ItemDTO(Item item) {
		super();
		this.setId(item.getId());
		this.setItemName(item.getItemName());
		this.setItemPrice(item.getItemPrice());
		this.setImage(item.getImage());
		this.setItemQuantity(item.getItemQuantity());
		if (item.getCart() != null) {
			this.setCartId(item.getCart().getId());
		}
	}

	public ItemDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}

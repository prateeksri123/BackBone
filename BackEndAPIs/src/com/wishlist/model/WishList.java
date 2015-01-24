package com.wishlist.model;

import javax.ws.rs.QueryParam;

public class WishList {
	@QueryParam("userId")
	private int userId;
	
	@QueryParam("productId")
	private String productId;
	
	@QueryParam("wishListId")
	private int id;
	
	@QueryParam("myExpectedPrice")
	private int myExpectedPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getMyExpectedPrice() {
		return myExpectedPrice;
	}

	public void setMyExpectedPrice(int myExpectedPrice) {
		this.myExpectedPrice = myExpectedPrice;
	}

}

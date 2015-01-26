package com.wishlist.model;

/*This is class where we display the 
WishList item with the desired price. */
public class WishListItem extends Product {
private int wishListId;
private int desiredPrice;

public int getWishListId() {
	return wishListId;
}
public void setWishListId(int wishListId) {
	this.wishListId = wishListId;
}
public int getDesiredPrice() {
	return desiredPrice;
}
public void setDesiredPrice(int desiredPrice) {
	this.desiredPrice = desiredPrice;
}
}

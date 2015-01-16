package com.wishlist.model;

public class Product {
	private String id;
	private String productTitle;
	private String productDescription;
	private String imageUrls;
	private Number maximumRetailRrice;
	private Number sellingPrice;
	private String productUrl;
	private Boolean inStock;
	private Boolean codAvailable;
	private Boolean emiAvailable;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public Number getMaximumRetailRrice() {
		return maximumRetailRrice;
	}
	public void setMaximumRetailRrice(Number maximumRetailRrice) {
		this.maximumRetailRrice = maximumRetailRrice;
	}
	public Number getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Number sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public Boolean getInStock() {
		return inStock;
	}
	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}
	public Boolean getCodAvailable() {
		return codAvailable;
	}
	public void setCodAvailable(Boolean codAvailable) {
		this.codAvailable = codAvailable;
	}
	public Boolean getEmiAvailable() {
		return emiAvailable;
	}
	public void setEmiAvailable(Boolean emiAvailable) {
		this.emiAvailable = emiAvailable;
	}
}

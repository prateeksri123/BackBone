package com.wishlist.database.wishlist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.database.DataBaseConnection;
import com.test.database.product.ProductService;
import com.wishlist.model.Product;
import com.wishlist.model.WishList;
import com.wishlist.model.WishListItem;

public class WishListServiceImpl extends DataBaseConnection implements WishListService{

	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet;
	private ProductService productService = new ProductService(); 

	
	public void addWishList(WishList wishList) throws Exception {
		
		preparedStatement = connect
		  /* `userId` int(10) unsigned NOT NULL,
             `productId` varchar(255) NOT NULL,
             `id` int(10) unsigned NOT NULL AUTO_INCREMENT,*/
	      .prepareStatement("insert into  WishList.WishList(userId,productId,expectedPrice) values (?, ?, ?)");

	         preparedStatement.setInt(1, wishList.getUserId());
	         preparedStatement.setString(2, wishList.getProductId());
	         preparedStatement.setInt(3, wishList.getMyExpectedPrice());
	        
	         preparedStatement.executeUpdate();

		
	}

	public void updateWishList(WishList wishList) throws Exception {
		  preparedStatement = connect
		  /* `userId` int(10) unsigned NOT NULL,
          `productId` varchar(255) NOT NULL,
          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,*/
	      .prepareStatement("update WishList.WishList set userId=?,productId=? where id=?");


	         preparedStatement.setInt(1,wishList.getUserId());
	         preparedStatement.setString(2,wishList.getProductId());
	         preparedStatement.setInt(3,wishList.getId());
	        
	         preparedStatement.executeUpdate();

		
	}

	public List<WishListItem> getProductListFromUserId(int userId) throws Exception {

		return getProductList(userId);
	}
	
	private List<WishListItem> getProductList(int userId) throws Exception {
		try{
		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // resultSet gets the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from WishList.WishList where userId='" + userId + "'");
		      return writeWishListItem();
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    }
	}
	
	private List<WishListItem> writeWishListItem() {
		List<WishListItem> productList= new ArrayList<WishListItem>();
	    try {
			while (resultSet.next()) {
                String productId = "";
				productId = resultSet.getString("productId");
				WishListItem wishListItem = new WishListItem();
				wishListItem = getWishListItemOfProduct(productService.getProductById(productId));
				wishListItem.setDesiredPrice(resultSet.getInt("expectedPrice"));
				wishListItem.setWishListId(resultSet.getInt("id"));
				productList.add(wishListItem);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	
	private WishListItem getWishListItemOfProduct(Product product) {
		WishListItem wishListItem = new WishListItem();
		wishListItem.setCodAvailable(product.getCodAvailable());
		wishListItem.setEmiAvailable(product.getEmiAvailable());
		wishListItem.setImageUrls(product.getImageUrls());
		wishListItem.setInStock(product.getInStock());
		wishListItem.setMaximumRetailRrice(product.getMaximumRetailRrice());
		wishListItem.setProductDescription(product.getProductDescription());
		wishListItem.setProductTitle(product.getProductTitle());
		wishListItem.setProductUrl(product.getProductUrl());
		wishListItem.setSellingPrice(product.getSellingPrice());
		wishListItem.setCategoryId(product.getCategoryId());
		
		
		return wishListItem;
	}

	public void checkWishListItem() throws Exception {
		try{
		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // resultSet gets the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from WishList.WishList");
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    }
		
	}


	
	
	

}

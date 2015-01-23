package com.wishlist.database.wishlist;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.test.database.DataBaseConnection;
import com.wishlist.model.WishList;

public class WishListServiceImpl extends DataBaseConnection implements WishListService{

	private PreparedStatement preparedStatement;

	
	public void addWishList(WishList wishList) throws Exception {
		
		preparedStatement = connect
		  /* `userId` int(10) unsigned NOT NULL,
             `productId` varchar(255) NOT NULL,
             `id` int(10) unsigned NOT NULL AUTO_INCREMENT,*/
	      .prepareStatement("insert into  WishList.WishList(userId,productId) values (?, ?)");

	         preparedStatement.setInt(1, wishList.getUserId());
	         preparedStatement.setString(2, wishList.getProductId());
	        
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
	
	

}

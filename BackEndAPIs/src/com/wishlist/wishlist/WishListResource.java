package com.wishlist.wishlist;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wishlist.database.wishlist.WishListServiceImpl;
import com.wishlist.model.User;
import com.wishlist.model.WishList;

@Path("/wishlist")
public class WishListResource {
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public WishList create(WishList wishList) {
		//this.userString = userString;
		System.out.println("register wishlist post method");
		manageWishList(wishList);
		return wishList;
	}
	
	private void manageWishList(WishList wishList){
		WishListServiceImpl wishListService = new WishListServiceImpl();
		try {
			wishListService.addWishList(wishList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

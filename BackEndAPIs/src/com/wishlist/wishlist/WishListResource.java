package com.wishlist.wishlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.core.InjectParam;
import com.wishlist.database.wishlist.WishListServiceImpl;
import com.wishlist.model.Product;
import com.wishlist.model.User;
import com.wishlist.model.WishList;
import com.wishlist.model.WishListItem;

@Path("/wishlist")
public class WishListResource {
	
	
	private WishListServiceImpl wishListService = new WishListServiceImpl();
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public WishList create(WishList wishList) {
		//this.userString = userString;
		System.out.println("add wishlist" + wishList.getMyExpectedPrice());
		manageWishList(wishList);
		return wishList;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<WishListItem> getWishListItems(@InjectParam WishList wishList) throws Exception {
		System.out.println("get Wishlist Item " + wishList.getMyExpectedPrice());
		List<WishListItem> wishListItem = new ArrayList<WishListItem>();
		
		wishListItem = wishListService.getProductListFromUserId(wishList.getUserId());
		//User user = getUserDetails(userInpt.getUserName(), userInpt.getPassword());
		return wishListItem;
	}

	
	private void manageWishList(WishList wishList){
		
		try {
			wishListService.addWishList(wishList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

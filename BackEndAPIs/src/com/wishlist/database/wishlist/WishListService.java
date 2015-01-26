package com.wishlist.database.wishlist;

import java.util.List;

import com.wishlist.model.Product;
import com.wishlist.model.WishList;
import com.wishlist.model.WishListItem;

public interface WishListService {
   
   public void addWishList(WishList wishList) throws Exception;
   public void updateWishList(WishList wishList) throws Exception;
   public List<WishListItem> getProductListFromUserId(int userId) throws Exception;
   public void checkWishListItem() throws Exception;
}

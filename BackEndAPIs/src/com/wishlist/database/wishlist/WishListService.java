package com.wishlist.database.wishlist;

import com.wishlist.model.WishList;

public interface WishListService {
   
   public void addWishList(WishList wishList) throws Exception;
   public void updateWishList(WishList wishList) throws Exception;
}

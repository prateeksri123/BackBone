package com.wishlist.database.wishlist;

import java.util.List;

import com.wishlist.model.Product;
import com.wishlist.model.WishList;

public interface WishListService {
   
   public void addWishList(WishList wishList) throws Exception;
   public void updateWishList(WishList wishList) throws Exception;
   public List<Product> getProductListFromUserId(int userId) throws Exception;
}

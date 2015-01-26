package com.wishlist.schedular;

import java.util.List;

import com.test.database.category.ProductCategoryService;
import com.test.database.product.ProductService;

public class UpdateProductSchedular {
  public UpdateProductSchedular(){
	  ProductCategoryService pcs = new ProductCategoryService();
	  ProductService ps = new ProductService();
	  try {
		List<String> categoryIds = pcs.getProductCategoryIds();
		for( String id : categoryIds){
			ps.populateProducts(id);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}

package com.test.database.category;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.test.database.DataBaseConnection;
import com.wishlist.model.ProductCategory;

public class ProductCategoryService extends DataBaseConnection {
	
	public String getProductList() {
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();

//	    List<ProductCategory> cartList = new ArrayList<ProductCategory>();
//	    for(Product p : cartMap.keySet()) {
//	        cartList.add(p);
//	        JSONObject formDetailsJson = new JSONObject();
//	        formDetailsJson.put("id", "1");
//	        formDetailsJson.put("name", "name1");
//	       jsonArray.add(formDetailsJson);
//	    }
//	    responseDetailsJson.put("forms", jsonArray);//Here you can see the data in json format

	    return jsonArray.toString();
	}

}

package com.test.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.test.database.category.ProductCategoryService;
import com.wishlist.model.Product;
import com.wishlist.model.ProductCategory;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello1
// http://www.vogella.com/tutorials/REST/article.html

// Category Listing is available in https://affiliate-api.flipkart.net/affiliate/api/mywishlis.json
@Path("/hello1")
public class Hello1 {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello(@QueryParam("url") String url) {
	System.out.println(url);
    return getProductList(url);
  }


  private final String USER_AGENT = "Mozilla/5.0";
  private String getProductList(String productUrl) {
		String url = productUrl.replaceAll("~~", "&");
		/*headers : {
      	   'Fk-Affiliate-Id': 'mywishlis',
            'Fk-Affiliate-Token': '22ba4f9fe89f4007ab51f45a777d4c7a',*/
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
		try {
			ProductCategoryService pcs = new ProductCategoryService();
			List<Product> productList= pcs.getProductListByUrl(url);
			
		    for(Product p : productList) {
		        //cartList.add(p);
		        JSONObject formDetailsJson = new JSONObject();
		   
		        formDetailsJson.put("id", p.getId());
		        formDetailsJson.put("productTitle", p.getProductTitle());
		        formDetailsJson.put("productDescription", p.getProductDescription());
		        formDetailsJson.put("imageUrls", p.getImageUrls());
		        formDetailsJson.put("maximumRetailPrice", p.getMaximumRetailRrice());
		        formDetailsJson.put("sellingPrice", p.getSellingPrice());
		        formDetailsJson.put("productUrl", p.getProductUrl());
		        formDetailsJson.put("inStock", p.getInStock());
		        //formDetailsJson.put("productUrl", p.getProductUrl());
				
		       jsonArray.put(formDetailsJson);
		    }
		    responseDetailsJson.put("forms", jsonArray);
		    System.out.println(jsonArray.toString());
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toString();

	}


}
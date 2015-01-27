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

import com.test.database.PopulateDatabase;
import com.test.database.category.ProductCategoryService;
import com.test.database.product.ProductService;
import com.wishlist.model.Product;
import com.wishlist.model.ProductCategory;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.
// Create pages in wireframe similar to http://www.epagecity.com/web-development/website-plugins/product-catalog
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
	ProductService productService = new ProductService();
	String result = "";
    try {
		result = productService.getProductList(url);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
  }




}
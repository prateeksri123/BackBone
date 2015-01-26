package com.test.database.category;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.sql.*;
import com.test.database.DataBaseConnection;
import com.test.database.JSONDataParser;
import com.wishlist.model.Product;
import com.wishlist.model.ProductCategory;

public class ProductCategoryService extends DataBaseConnection {
	
	private Statement statement;
	private ResultSet resultSet;

	public String getProductList() throws Exception {
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    try {
	    List<ProductCategory> productList = getProductCategoryListFromDB();
	    for(ProductCategory p : productList) {
	        //cartList.add(p);
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("id", p.getId());
	        formDetailsJson.put("category_name", p.getProductCategory());
	        formDetailsJson.put("url", p.getUrl());
			
	       jsonArray.put(formDetailsJson);
	    }
	    responseDetailsJson.put("forms", jsonArray);//Here you can see the data in json format
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return jsonArray.toString();
	}
	
	private List<ProductCategory> getProductCategoryListFromDB() throws Exception {
		//List<ProductCategory> result = new ArrayList<ProductCategory>();
		 try{
		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // resultSet gets the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from WishList.product_category");
		     // return writeUserData(resultSet);	
		     return writeUserData(resultSet);
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    }	  
		
		//return result;
	}

	private List<ProductCategory> writeUserData(ResultSet resultSet) throws Exception {
		List<ProductCategory> result = new ArrayList<ProductCategory>();
		
		 while (resultSet.next()) {
		      // it is possible to get the columns via name
		      // also possible to get the columns via the column number
		      // which starts at 1
		      // e.g., resultSet.getSTring(2);
		    ProductCategory	pc = new ProductCategory();
		    pc.setId(resultSet.getString("id"));
		    pc.setProductCategory(resultSet.getString("category_name"));
		    pc.setUrl(resultSet.getString("category_url"));
		      result.add(pc);
		     
		    }
		return result;
	}
	
	public List<String> getProductCategoryIds() throws Exception{
		 try{
		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // resultSet gets the result of the SQL query
		      resultSet = statement
		          .executeQuery("select id from WishList.product_category");
		     // return writeUserData(resultSet);	
		     return writeProductCategory(resultSet);
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    }	  
		
	}
	
	private List<String> writeProductCategory(ResultSet resultSet) throws Exception {
		List<String> result = new ArrayList<String>();
		
		 while (resultSet.next()) {
		     
		    String categoryId = resultSet.getString("id");
		    
		      result.add(categoryId);
		     
		    }
		return result;
	}
	
	

}

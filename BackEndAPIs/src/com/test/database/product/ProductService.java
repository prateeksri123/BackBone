package com.test.database.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.test.database.DataBaseConnection;
import com.test.database.JSONDataParser;
import com.test.database.PopulateDatabase;
import com.test.database.category.ProductCategoryService;
import com.wishlist.model.Product;
import com.wishlist.model.ProductCategory;

public class ProductService extends DataBaseConnection {

	 private final String USER_AGENT = "Mozilla/5.0";
	private Statement statement;
	private ResultSet resultSet;
	  public String getProductList(String categoryId) throws Exception {
			String url = getCategoryURL(categoryId);
			/*headers : {
	      	   'Fk-Affiliate-Id': 'mywishlis',
	            'Fk-Affiliate-Token': '22ba4f9fe89f4007ab51f45a777d4c7a',*/
			JSONObject responseDetailsJson = new JSONObject();
		    JSONArray jsonArray = new JSONArray();
			try {
				//Call to update database should go in scheduler
				PopulateDatabase pdb = new PopulateDatabase();
				pdb.populateProduct(url,categoryId);
				
				List<Product> productList =  getProductListFromDB(categoryId);
				
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
	  
		private List<Product> getProductListFromDB(String categoryId) throws Exception {
			//List<ProductCategory> result = new ArrayList<ProductCategory>();
			 try{
			      // statements allow to issue SQL queries to the database
			      statement = connect.createStatement();
			      // resultSet gets the result of the SQL query
			      resultSet = statement
			          .executeQuery("select * from WishList.product where category_id='" + categoryId + "'");
			     // return writeUserData(resultSet);	
			     return writeUserData(resultSet);
			    } catch (Exception e) {
			      throw e;
			    } finally {
			      close();
			    }	  
			
			//return result;
		}
		
		private String getCategoryURL(String categoryId) throws Exception {
			String url = "";
			try{
			      // statements allow to issue SQL queries to the database
			      statement = connect.createStatement();
			      // resultSet gets the result of the SQL query
			      resultSet = statement
			          .executeQuery("select * from WishList.product_category where id='" + categoryId + "'");
			     // return writeUserData(resultSet);	
			      while (resultSet.next()) {
			    	 url =  resultSet.getString("category_url");
			      }
			    } catch (Exception e) {
			      throw e;
			    } finally {
			      close();
			    }
			    return url;
		}

		private List<Product> writeUserData(ResultSet resultSet) throws Exception {
			List<Product> result = new ArrayList<Product>();
			
			 while (resultSet.next()) {
			      // it is possible to get the columns via name
			      // also possible to get the columns via the column number
			      // which starts at 1
			      // e.g., resultSet.getSTring(2);
			    Product	pc = new Product();
			    pc.setId(resultSet.getString("id"));
			    pc.setProductTitle(resultSet.getString("product_title"));
			    pc.setProductDescription(resultSet.getString("product_description"));
			    pc.setImageUrls(resultSet.getString("image_urls"));
			    pc.setMaximumRetailRrice(resultSet.getInt("maximum_retail_price"));
			    pc.setSellingPrice(resultSet.getInt("selling_price"));
			    pc.setProductUrl(resultSet.getString("product_url"));
			    pc.setInStock(resultSet.getBoolean("in_stock"));
			    pc.setCodAvailable(resultSet.getBoolean("cod_available"));
			    pc.setEmiAvailable(resultSet.getBoolean("emi_available"));
			      result.add(pc);
			     
			    }
			return result;
		}
		
		public Product getProductById(String productId) throws Exception {
			Product product = new Product();
			try{
			      
			      statement = connect.createStatement();
			      
			      resultSet = statement
			          .executeQuery("select * from WishList.product where id='" + productId + "'");
			    	
			      
			      product =  writeProductData();
			      
			    } catch (Exception e) {
			      throw e;
			    } finally {
			      close();
			    }
			return product;
		}
		
		private Product writeProductData() throws Exception{
			Product product = null;
			try {
				while (resultSet.next()) {
					product = new Product();
					product.setId(resultSet.getString("id"));
					product.setProductTitle(resultSet.getString("product_title"));
					product.setProductDescription(resultSet.getString("product_description"));
					product.setImageUrls(resultSet.getString("image_urls"));
					product.setMaximumRetailRrice(resultSet.getInt("maximum_retail_price"));
					product.setSellingPrice(resultSet.getInt("selling_price"));
					product.setProductUrl(resultSet.getString("product_url"));
					product.setInStock(resultSet.getBoolean("in_stock"));
					product.setCodAvailable(resultSet.getBoolean("cod_available"));
					product.setEmiAvailable(resultSet.getBoolean("emi_available"));
					      
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
			return product;
		}
		

}

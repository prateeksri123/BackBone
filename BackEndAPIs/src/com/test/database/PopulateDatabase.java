package com.test.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.wishlist.model.Product;
import com.wishlist.model.ProductCategory;

public class PopulateDatabase {
	DataBaseConnection dao = new DataBaseConnection();
	private java.sql.Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private JSONDataParser jsonDataParser = new JSONDataParser("mywishlis",
	"22ba4f9fe89f4007ab51f45a777d4c7a");

	@SuppressWarnings("finally")
	public void populateCategory() throws Exception {
		try {
			
			List<ProductCategory> pcList = jsonDataParser
					.initializeProductDirectory();
			for (ProductCategory pc : pcList) {

				try {
					if (isCategoryExisting(pc.getId())) {
					
						preparedStatement = dao.connect
								.prepareStatement("insert into  WishList.product_category(category_name,category_url,id) values (?,?,?)");
					} else {
						preparedStatement = dao.connect
								.prepareStatement("update WishList.product_category set category_name=?,category_url=? where id=?");
						
					}

					preparedStatement.setString(1, pc.getProductCategory()); 
					preparedStatement.setString(2, pc.getUrl());
					preparedStatement.setString(3, pc.getId());
					preparedStatement.executeUpdate();
				}

				catch (MySQLIntegrityConstraintViolationException e) {
					// TODO: handle exception
					if (e instanceof MySQLIntegrityConstraintViolationException) {
						continue;
					} else {
						e.printStackTrace();
					}

				} finally {
					continue;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
	}

	private Boolean isCategoryExisting(String productId) throws Exception {
		try {
			// statements allow to issue SQL queries to the database
			statement = dao.connect.createStatement();
			// resultSet gets the result of the SQL query
			resultSet = statement
					.executeQuery("select * from WishList.product_category where id='"
							+ productId + "'");
			resultSet.last();
			
			return (resultSet.getRow() == 0);

		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();

		}
	}
	
	private Boolean isProductExisting(String productId) throws Exception {
		try {
			// statements allow to issue SQL queries to the database
			statement = dao.connect.createStatement();
			// resultSet gets the result of the SQL query
			resultSet = statement
					.executeQuery("select * from WishList.product where id='"
							+ productId + "'");
			resultSet.last();
			
			return (resultSet.getRow() == 0);

		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();

		}
	}

	@SuppressWarnings("finally")
	public void populateProduct(String url,String category_id) throws Exception {
		try {
			
			List<Product> productList = jsonDataParser.getProductList(url,category_id);
			for (Product product : productList) {

				try {
					if (isProductExisting(product.getId())) {
						
						preparedStatement = dao.connect
								.prepareStatement("insert into  WishList.product(product_title," +
										"product_description," +
										"image_urls," +
										"maximum_retail_price," +
										"selling_price," +
										"product_url," +
										"in_stock," +
										"cod_available,category_id," +
										"emi_available,id) values (?,?,?,?,?,?,?,?,?,?,?)");
					} else {
						preparedStatement = dao.connect
								.prepareStatement("update WishList.product set product_title=?," +
										"product_description=?," +
										"image_urls=?," +
										"maximum_retail_price=?," +
										"selling_price=?," +
										"product_url=?," +
										"in_stock=?," +
										"cod_available=?,category_id=?," +
										"emi_available=? where id=?");
					}

					preparedStatement.setString(1, product.getProductTitle());
					preparedStatement.setString(2, product.getProductDescription());
					preparedStatement.setString(3, product.getImageUrls());
					preparedStatement.setInt(4, product.getMaximumRetailRrice().intValue());
					preparedStatement.setInt(5, product.getSellingPrice().intValue());
					preparedStatement.setString(6, product.getProductUrl());
					preparedStatement.setBoolean(7, product.getInStock());
					preparedStatement.setBoolean(8, product.getCodAvailable());
					preparedStatement.setBoolean(10, product.getEmiAvailable());
					preparedStatement.setString(9, product.getCategoryId());
					preparedStatement.setString(11, product.getId());
					preparedStatement.executeUpdate();
				}

				catch (Exception e) {
					// TODO: handle exception
					if (e instanceof MySQLIntegrityConstraintViolationException) {
						continue;
					} else {
						e.printStackTrace();
					}

				} finally {
					continue;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}

	}

}

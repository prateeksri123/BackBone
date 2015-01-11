package com.test.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.wishlist.model.ProductCategory;

public class PopulateDatabase {
	DataBaseConnection dao = new DataBaseConnection();
	private java.sql.Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@SuppressWarnings("finally")
	public void populateCategory() throws Exception {
		try {
			JSONDataParser jsonDataParser = new JSONDataParser("mywishlis",
					"22ba4f9fe89f4007ab51f45a777d4c7a");
			List<ProductCategory> pcList = jsonDataParser
					.initializeProductDirectory();
			for (ProductCategory pc : pcList) {

				try {
					if (isCategoryExisting(pc.getId())) {
						System.out.println("insert");
						preparedStatement = dao.connect
								.prepareStatement("insert into  WishList.product_category(category_name,id,category_url) values (?,?,?)");
					} else {
						preparedStatement = dao.connect
								.prepareStatement("update WishList.product_category set category_name=?,category_url=? where id=?");
						System.out.println("update");
					}

					preparedStatement.setString(1, pc.getProductCategory());
					preparedStatement.setString(2, pc.getId());
					preparedStatement.setString(3, pc.getUrl());
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
			System.out.println(resultSet.getRow());
			return (resultSet.getRow() == 0);

		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();

		}
	}

}

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
			XMLDataParser xmlDataParser = new XMLDataParser("mywishlis",
					"22ba4f9fe89f4007ab51f45a777d4c7a");
			List<ProductCategory> pcList = xmlDataParser
					.initializeProductDirectory();
			for (ProductCategory pc : pcList) {

				try {
					preparedStatement = dao.connect
							/*
							 * VARCHAR(255) NOT NULL, `Password` VARCHAR(45) NOT
							 * NULL, `userId` INTEGER UNSIGNED NOT NULL
							 * AUTO_INCREMENT, `FirstName` VARCHAR(255) NOT
							 * NULL, `LastName` VARCHAR(255) NOT NULL, `email`
							 * VARCHAR(255) NOT NULL,
							 */
							.prepareStatement("insert into  WishList.product_category(category_name,id,category_url) values (?,?,?)");

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

}

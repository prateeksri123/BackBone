package com.test.database;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;*/
import java.sql.*;

import com.wishlist.model.User;

public class DataBaseConnection {
  public Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public DataBaseConnection() {
	  try {
	      // this will load the MySQL driver, each DB has its own driver
	    	//Driver a;
	      Class.forName("com.mysql.jdbc.Driver");
	      // setup the connection with the DB.
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/WishList?"
	              + "user=root");
	  }catch (Exception e) {
		// TODO: handle exception
	}

  }

  public User getUserDetails(String userName,String passWord) throws Exception{
	  try{
	      // statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // resultSet gets the result of the SQL query
	      resultSet = statement
	          .executeQuery("select * from WishList.users where UserName='" + userName + "' and Password='" + passWord + "'");
	      return writeUserData(resultSet);
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }
  }

  public User updateUser(User user) throws Exception {
	  preparedStatement = connect
	  /* VARCHAR(255) NOT NULL,
	  `Password` VARCHAR(45) NOT NULL,
	  `userId` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	  `FirstName` VARCHAR(255) NOT NULL,
	  `LastName` VARCHAR(255) NOT NULL,
	  `email` VARCHAR(255) NOT NULL,*/
      .prepareStatement("update WishList.users set FirstName=?,LastName=?,email=? where userId=?");


         preparedStatement.setString(1,user.getFirstName());
         preparedStatement.setString(2,user.getLastName());
         preparedStatement.setString(3,user.getEmail());
         preparedStatement.setInt(4,user.getUserId());
         preparedStatement.executeUpdate();

         return user;
  }

  public User createUser(User user) throws Exception {
	  preparedStatement = connect
	  /* VARCHAR(255) NOT NULL,
	  `Password` VARCHAR(45) NOT NULL,
	  `userId` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	  `FirstName` VARCHAR(255) NOT NULL,
	  `LastName` VARCHAR(255) NOT NULL,
	  `email` VARCHAR(255) NOT NULL,*/
      .prepareStatement("insert into  WishList.users(UserName,Password,FirstName,LastName,email) values (?, ?, ? , ?, ?)");

         preparedStatement.setString(1, user.getUserName());
         preparedStatement.setString(2, user.getPassword());
         preparedStatement.setString(3,user.getFirstName());
         preparedStatement.setString(4,user.getLastName());
         preparedStatement.setString(5,user.getEmail());
         preparedStatement.executeUpdate();

	  return user;
  }

  public void readDataBase() throws Exception {
    try{
      // statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // resultSet gets the result of the SQL query
      resultSet = statement
          .executeQuery("select * from WishList.users");
      writeResultSet(resultSet);

      // preparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("insert into  WishList.users values (default, ?, ?, ?, ? , ?, ?)");
      // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
      // parameters start with 1
      preparedStatement.setString(1, "Test");
      preparedStatement.setString(2, "TestEmail");
      preparedStatement.setString(3, "TestWebpage");
      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
      preparedStatement.setString(5, "TestSummary");
      preparedStatement.setString(6, "TestComment");
      preparedStatement.executeUpdate();

      preparedStatement = connect
          .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

      // remove again the insert comment
      preparedStatement = connect
      .prepareStatement("delete from FEEDBACK.COMMENTS where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();

      resultSet = statement
      .executeQuery("select * from FEEDBACK.COMMENTS");
      writeMetaData(resultSet);

    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    // now get some metadata from the database
    System.out.println("The columns in the table are: ");
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private User writeUserData(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	  User user = null;
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	    	user = new User();
	      user.setUserName(resultSet.getString("UserName"));
	      user.setUserId(resultSet.getInt("userId"));
	      user.setFirstName(resultSet.getString("FirstName"));
	      user.setLastName(resultSet.getString("LastName"));
	      user.setEmail(resultSet.getString("email"));
	      System.out.println("User: " + user.toString());

	    }
	    return user;
	  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // resultSet is initialised before the first data set
    while (resultSet.next()) {
      // it is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g., resultSet.getSTring(2);
      String user = resultSet.getString("UserName");
      String website = resultSet.getString("userId");
      String summary = resultSet.getString("FirstName");
      String date = resultSet.getString("LastName");
      String comment = resultSet.getString("email");
      System.out.println("User: " + user);
      System.out.println("Website: " + website);
      System.out.println("Summary: " + summary);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + comment);
    }
  }

  // you need to close all three to make sure
  public void close() {
    close(resultSet);
    close(statement);
    close(connect);
  }
  private void close(Connection connect2) {
	// TODO Auto-generated method stub

}

private void close(Statement statement2) {
	// TODO Auto-generated method stub

}

private void close(ResultSet resultSet2) {
    try {
      if (resultSet2 != null) {
        resultSet2.close();
      }
    } catch (Exception e) {
    // don't throw now as it might leave following closables in undefined state
    }
  }
}
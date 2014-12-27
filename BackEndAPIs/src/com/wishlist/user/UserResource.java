package com.wishlist.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.database.DataBaseConnection;
import com.wishlist.model.User;

@Path("/user")
public class UserResource {
	
	private String userString;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser() {
		System.out.println("check user Get method 2");     
		return getUserDetails("pra","pra").toString();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticateUser(String userString, String password) {
		this.userString = userString;
		System.out.println("check user post method 3");
        
		return getUserDetails("pra1","pra").toString();
	}
	
	private User getUserDetails(String userName, String passWord){
		User user = new User();
       DataBaseConnection dao = new DataBaseConnection();
		
	    try {
			user = dao.getUserDetails(userName,passWord);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return user;
	}

}

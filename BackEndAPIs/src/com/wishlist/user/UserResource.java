package com.wishlist.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.test.database.DataBaseConnection;
import com.wishlist.model.User;

@Path("/user")
public class UserResource {
	
	private String userString;
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String getUser(@QueryParam("userName") String userName,
			              @QueryParam("pwd") String pwd) {
		System.out.println("check user Get method 2");
		User user = getUserDetails(userName, pwd);
		return user != null ? user.toString() : "false";
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

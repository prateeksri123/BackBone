package com.wishlist.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.database.DataBaseConnection;
import com.wishlist.model.User;

@Path("/login")
public class UserResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello() {
		System.out.println("check user");
        
		return getUserDetails().toString();
	}
	
	private User getUserDetails(){
		User user = new User();
       DataBaseConnection dao = new DataBaseConnection();
		
	    try {
			dao.getUserDetails();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return user;
	}

}

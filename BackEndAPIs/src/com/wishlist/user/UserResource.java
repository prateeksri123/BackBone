package com.wishlist.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.core.InjectParam;
import com.test.database.DataBaseConnection;
import com.wishlist.model.User;

@Path("/user")
public class UserResource {

	private String userString;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User getUser(@InjectParam User userInpt) {
		System.out.println("check user Get method 222");
		User user = getUserDetails(userInpt.getUserName(), userInpt.getPassword());
		return user;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User create(User user) {
		//this.userString = userString;
		System.out.println("register user post method 3");

			manageUser(user);


		return user;
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

	private User manageUser(User user) {
		 DataBaseConnection dao = new DataBaseConnection();
		    try {
		    	if(user.getUserId() == 0) {
		    		user = dao.createUser(user);
		    	} else {
		    		user = dao.updateUser(user);
		    	}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return user;
	}

}

package org.vogella.jersey.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Driver;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.database.DataBaseConnection;
import com.test.database.PopulateDatabase;
import com.test.database.XMLDataParser;
import com.test.database.category.ProductCategoryService;
import com.wishlist.model.ProductCategory;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		
		return getProductList();
	}

	private final String USER_AGENT = "Mozilla/5.0";



	private String getProductList() {
		String url = "https://affiliate-api.flipkart.net/affiliate/api/mywishlis.json";
		StringBuffer response = new StringBuffer();
        URL obj;
		String result = "";
		try {
			PopulateDatabase pdb = new PopulateDatabase();
			//pdb.populateCategory();
			ProductCategoryService pcs = new ProductCategoryService();
			result = pcs.getProductList();
			System.out.println(result);
					} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return getProductList();
	}

	@GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	  }



}
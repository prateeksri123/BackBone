package com.test.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello1
// http://www.vogella.com/tutorials/REST/article.html

// Category Listing is available in https://affiliate-api.flipkart.net/affiliate/api/mywishlis.json
@Path("/hello1")
public class Hello1 {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello() {
	  JSONObject jsonObject = new JSONObject();
	  String s ="";
	  System.out.println("PLAIN");
	  try {
		InputStream crunchifyInputStream = new FileInputStream(
		"C:\\Important\\wishlist\\BackEndAPIs\\src\\com\\test\\example\\ProductList.json");
		 InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
         BufferedReader br = new BufferedReader(crunchifyReader);
         String line;
         while ((line = br.readLine()) != null) {
             s += line + "\n";
         }
         jsonObject = new JSONObject(s);
         System.out.println("PLain ->" + jsonObject);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return jsonObject.toString();
  }

  // This method is called if XML is request
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String sayXMLHello() {
	  JSONObject jsonObject = new JSONObject();
	  String s="";
	  System.out.println("XML");
	  try {
		InputStream crunchifyInputStream = new FileInputStream(
		  "C:\\Important\\wishlist\\BackEndAPIs\\src\\com\\test\\example\\ProductList.json");
		 InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
         BufferedReader br = new BufferedReader(crunchifyReader);
         String line;
         while ((line = br.readLine()) != null) {
             s += line + "\n";
         }
         jsonObject = new JSONObject(s);
         System.out.println(jsonObject);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return jsonObject.toString();
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
        + "<body><h1>" + "Hello REST API Call Started And it is ready for checkin HTML" + "</body></h1>" + "</html> ";
  }

}
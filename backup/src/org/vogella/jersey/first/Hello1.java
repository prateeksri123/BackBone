package org.vogella.jersey.first;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
  public String sayPlainTextHello(@QueryParam("url") String url) {
	System.out.println(url);
    return getProductList(url);
  }

  
  private final String USER_AGENT = "Mozilla/5.0";
  private String getProductList(String productUrl) {
		String url = productUrl.replaceAll("~~", "&");
		/*headers : {
      	   'Fk-Affiliate-Id': 'mywishlis',
            'Fk-Affiliate-Token': '22ba4f9fe89f4007ab51f45a777d4c7a',*/
		StringBuffer response = new StringBuffer();
		URL obj;
		try {
			obj = new URL(url);


			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			//HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Fk-Affiliate-Token","22ba4f9fe89f4007ab51f45a777d4c7a" );
            con.setRequestProperty("Fk-Affiliate-Id", "mywishlis");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con
					.getInputStream()));
			String inputLine;


			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();

	}


}
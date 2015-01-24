package com.test.database;
/***
 * The class to parse JSON data.
 * Please refer to the instructions.txt
 *
 * @author vijay.v@flipkart.com
 * @version 1.0
 * Copyright (c) Flipkart India Pvt. Ltd.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.wishlist.model.Product;
import com.wishlist.model.ProductCategory;

public class JSONDataParser extends DataParser {

    private String affiliateId;
    private String affiliateToken;
    private String affiliateBaseUrl;
    private Map<String, String> productDirectory;

    public JSONDataParser(String affiliateId, String affiliateToken) {
        this.affiliateId = affiliateId;
        this.affiliateToken = affiliateToken;
        this.affiliateBaseUrl = "https://affiliate-api.flipkart.net/affiliate/api/" + affiliateId + ".json";
        productDirectory = new HashMap<String, String>();
    }

    /***
     *  It gets the API Directory information from the API service and stores it locally.
     * @return true if initialization is successful.
     * @throws AffiliateAPIException
     */
    public List<ProductCategory> initializeProductDirectory() throws Exception {
        boolean return_value = true;
        List<ProductCategory> pclist = new ArrayList<ProductCategory>();
        try {
            // Query the API service and get back the result.
            String jsonData = queryService(affiliateBaseUrl);

            // Bookkeep the retrieved data in a local productDirectory Map.
            JSONObject obj = new JSONObject(jsonData);
            JSONObject listing = obj.getJSONObject("apiGroups").getJSONObject("affiliate").getJSONObject("apiListings");
            Iterator keys = listing.keys();

            while(keys.hasNext()) {
            	ProductCategory pc = new ProductCategory();
                String category_name = (String)keys.next();
                String catName = convertCategoryInCaps(category_name);
                pc.setProductCategory(catName);
                pc.setId(category_name);
                JSONObject variants = listing.getJSONObject(category_name).getJSONObject("availableVariants");

                // Sort the variants and get the latest version
                Iterator v_iterator = variants.keys();
                List<String> variant_keys = new ArrayList<String>();
                while(v_iterator.hasNext()) {
                    variant_keys.add((String)v_iterator.next());
                }
                Collections.sort(variant_keys, Collections.reverseOrder());

                String category_url = variants.getJSONObject(variant_keys.get(0)).getString("get");
                pc.setUrl(category_url);
                productDirectory.put(category_name, category_url);
                pclist.add(pc);
            }
        }
        catch(JSONException je) {
            return_value = false;
        }

        return pclist;
    }

    private String convertCategoryInCaps(String catName) {

    String resultString = Character.toUpperCase(catName.charAt(0)) + catName.substring(1);
    catName = resultString;
    while(catName.indexOf("_") != -1) {
      	int index = catName.indexOf('_');
      	char c = catName.charAt(index + 1);
      	resultString = catName.substring(0, index);
      	resultString = resultString + " " + Character.toUpperCase(c) + catName.substring(index + 2);
      	catName = resultString;

    }
    return resultString;
    }

    /***
     *
     * @return the locally stored product directory information (A list of categories and the corresponding URLs).
     * Originally updated using initializeProductDirectory() and it should be updated again if the URLs are expired.
     */
    public Map<String, String> getProductDirectory() {
        return productDirectory;
    }

    /***
     *
     * @param category
     * @return list of products for the given categery from the API service.
     * @throws Exception
     */
    public List<Product> getProductList(String url,String category_id) throws Exception {

        List<Product> plist = new ArrayList<Product>();

        try {
            String queryUrl = url;

            while(queryUrl != null && !queryUrl.isEmpty()) {
                String jsonData = queryService(queryUrl);

                JSONObject obj = new JSONObject(jsonData);
                JSONArray productArray = obj.getJSONArray("productInfoList");
                for(int i =0; i < productArray.length(); i++) {

                    Product pinfo = new Product();
                    JSONObject inner_obj = productArray.getJSONObject(i).getJSONObject("productBaseInfo");
                    pinfo.setId(inner_obj.getJSONObject("productIdentifier").getString("productId"));

                    JSONObject attributes = inner_obj.getJSONObject("productAttributes");
                    JSONObject imageUrls = attributes.getJSONObject("imageUrls");
                    pinfo.setProductTitle(attributes.getString("title"));
                    pinfo.setProductDescription(attributes.optString("productDescription", ""));
                    pinfo.setImageUrls(imageUrls.getString("100x100"));
                    pinfo.setMaximumRetailRrice(attributes.getJSONObject("maximumRetailPrice").getDouble("amount"));
                    //pinfo.setSellingPrice(attributes.getJSONObject("sellingPrice").getDouble("amount"));
                    pinfo.setProductUrl(attributes.getString("productUrl"));
                    pinfo.setInStock(attributes.getBoolean("inStock"));
                    pinfo.setEmiAvailable(attributes.getBoolean("emiAvailable"));
                    pinfo.setCategoryId(category_id);
                    plist.add(pinfo);
                }

                // Fetch the products from the next URL. Here we set the limit to 500 products.
                queryUrl = obj.optString("nextUrl", "");
                if(queryUrl != null && !queryUrl.isEmpty() && plist.size() > 500) { queryUrl = ""; }
            }
        }
        catch(JSONException je) {

        }
        return plist;
    }
    public String getAffiliateId() {
        return affiliateId;
    }
    public String getAffiliateToken() {
        return affiliateToken;
    }
}
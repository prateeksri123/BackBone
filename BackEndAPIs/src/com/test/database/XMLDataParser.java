package com.test.database;
/***
 * The class to parse XML data.
 * Please refer to the instructions.txt
 *
 * @author vijay.v@flipkart.com
 * @version 1.0
 * Copyright (c) Flipkart India Pvt. Ltd.
 */

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.wishlist.model.Product;
import com.wishlist.model.ProductCategory;


public class XMLDataParser extends DataParser {
    private String affiliateId;
    private String affiliateToken;
    private String affiliateBaseUrl;
    private Map<String, String> productDirectory;

    public XMLDataParser(String affiliateId, String affiliateToken) {
       this.affiliateId = affiliateId;
       this.affiliateToken = affiliateToken;
       this.affiliateBaseUrl = "https://affiliate-api.flipkart.net/affiliate/api/mywishlis.json";
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
            String xmlData = queryService(affiliateBaseUrl);

            // Bookkeep the retrieved data in a local productDirectory Map.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlData));
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("apiGroups");
            Node apiGroups = nList.item(0);
            Node apiListings = apiGroups.getFirstChild().getLastChild().getFirstChild();
            for(Node n = apiListings.getFirstChild(); n != null; n = n.getNextSibling()) {
            	ProductCategory pc = new ProductCategory();
                String category_name = n.getFirstChild().getTextContent();
                pc.setProductCategory(category_name.replaceAll("_", " "));
                pc.setId(category_name);
                Node availableVariants = n.getLastChild().getLastChild();
                String category_url = availableVariants.getLastChild().getLastChild().getFirstChild().getTextContent();
                pc.setUrl(category_url);
                category_url.replaceAll("&amp;", "&");
                productDirectory.put(category_name, category_url);
                pclist.add(pc);
            }
        }
        catch(ParserConfigurationException pce) {
            return_value = false;
        }
        catch(SAXException se) {
            return_value = false;
        }
        catch(IOException ioe) {
            return_value = false;
        }
        return pclist;
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
     * @throws AffiliateAPIException
     */
    public List<Product> getProductList(String url, String category) throws Exception {

        List<Product> plist = new ArrayList<Product>();
        try {
            String queryUrl = getProductDirectory().get(category);

            while(queryUrl != null && !queryUrl.isEmpty()) {
                String xmlData = queryService(queryUrl);

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xmlData));
                Document doc = dBuilder.parse(is);
                doc.getDocumentElement().normalize();
                Node products = doc.getElementsByTagName("products").item(0);

                for(Node productInfoList = products.getFirstChild(); productInfoList != null; productInfoList = productInfoList.getNextSibling()) {
                	Product pinfo = new Product();

                    pinfo.setId(productInfoList.getFirstChild().getNextSibling().getFirstChild().getLastChild().getTextContent());
                    //pinfo.setTitle(productInfoList.getFirstChild().getNextSibling().getLastChild().getLastChild().getTextContent());

                    Element productAttributes = (Element) productInfoList.getFirstChild().getNextSibling().getLastChild();
                    if(productAttributes.getElementsByTagName("productDescription").getLength() > 0) {
                       // pinfo.setDescription(productAttributes.getElementsByTagName("productDescription").item(0).getTextContent());
                    }
                    //pinfo.setMrp(Double.valueOf(productAttributes.getElementsByTagName("maximumRetailPrice").item(0).getFirstChild().getTextContent()));
                    //pinfo.setSellingPrice(Double.valueOf(productAttributes.getElementsByTagName("sellingPrice").item(0).getFirstChild().getTextContent()));

                    String productUrl = productAttributes.getElementsByTagName("productUrl").item(0).getTextContent();
                    productUrl.replaceAll("&amp;", "&");

                    //pinfo.setProductUrl(productUrl);
                   // pinfo.setInStock(Boolean.valueOf(productAttributes.getElementsByTagName("inStock").item(0).getTextContent()));
                    //pinfo.setEmiAvailable(Boolean.valueOf(productAttributes.getElementsByTagName("emiAvailable").item(0).getTextContent()));

                    plist.add(pinfo);
                }

                // Fetch the products from the nextUrl. Here we set the limit to 500 products.
                queryUrl = (doc.getElementsByTagName("nextUrl").getLength() > 0) ? doc.getElementsByTagName("nextUrl").item(0).getTextContent() : "";
                if(queryUrl != null && !queryUrl.isEmpty() && plist.size() > 500) { queryUrl = ""; }
            }
        }
        catch(ParserConfigurationException pce) {

        }
        catch(SAXException se) {

        }
        catch(IOException ioe) {

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

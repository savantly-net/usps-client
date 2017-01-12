package net.savantly.geo.usps.shippingAPI;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.savantly.geo.usps.AddressInfo;

public class ShippingAPIClient {
    private static final Logger log = LoggerFactory.getLogger(ShippingAPIClient.class);
    
    String baseUrl = "https://secure.shippingapis.com"; 
    String username = ""; 
    String password = "";
    String cityStateAPIPath = "/ShippingAPI.dll?API=CityStateLookup&XML=%s";
    
    public ShippingAPIClient(){}
    
    public ShippingAPIClient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AddressInfo CityStateLookup(String zipCode) throws IOException {
        
        String xml = 
                "<CityStateLookupRequest USERID=\""+ username + "\">" +
                    "<ZipCode ID=\"0\">" +
                        "<Zip5>"+ zipCode +"</Zip5>" +
                    "</ZipCode>" +
                "</CityStateLookupRequest>";
        
        String url = baseUrl + String.format(cityStateAPIPath, xml);
        
        Document doc = Jsoup.connect(url).get();
        
        log.debug("Response: {}", doc.outerHtml());
        
        Elements zips = doc.getElementsByTag("Zip5");
        Elements cities = doc.getElementsByTag("City");
        Elements states = doc.getElementsByTag("State");
        
        String zip5 = "", city = "", state ="";
        
        if(!zips.isEmpty()) zip5 = zips.first().text();
        if(!cities.isEmpty()) city = cities.first().text();
        if(!states.isEmpty()) state = states.first().text();
        
        return new AddressInfo(zip5, city, state);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

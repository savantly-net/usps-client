package net.savantly.geo.usps.shippingAPI;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import net.savantly.geo.usps.AddressInfo;

public class ShippingApiClientTest {
    
    String username = System.getenv().get("USPS_USERNAME");

    @Test
    public void test() throws IOException {
        ShippingAPIClient client = new ShippingAPIClient(username, "");
        AddressInfo addressInfo = client.CityStateLookup("76117");
        assertEquals("76117", addressInfo.getZip5());
        assertEquals("HALTOM CITY", addressInfo.getCity());
        assertEquals("TX", addressInfo.getState());
    }

}

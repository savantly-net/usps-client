package net.savantly.geo.usps;

public class AddressInfo {
    
    String zip5;
    String city;
    String state;
    
    public AddressInfo(){}
    
    public AddressInfo(String zip5, String city, String state) {
        this.zip5 = zip5;
        this.city = city;
        this.state = state;
    }
    public String getZip5() {
        return zip5;
    }
    public void setZip5(String zip5) {
        this.zip5 = zip5;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}

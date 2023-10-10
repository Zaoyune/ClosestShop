package com.orange.shop.model;



public class Shop {
    private double longitude;
    private double latitude;
    private String shopDescription;
    private int sunusng;
    private int ipom;
    private int weiwei;

    public boolean hasMobileInStock(String phoneName) {
        switch (phoneName) {
            case "sunusng":
                return sunusng > 0;
            case "ipom":
                return ipom > 0;
            case "weiwei":
                return weiwei > 0;
            default:
                return false; // Mobile name not recognized
        }
    }

    public int MobileStock(String phoneName) {
        switch (phoneName) {
            case "sunusng":
                return sunusng;
            case "ipom":
                return ipom;
            case "weiwei":
                return weiwei;
            default:
                return 0;
        }
    }

    public Shop(double longitude, double latitude, String shopDescription, int sunusng, int ipom, int weiwei) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.shopDescription = shopDescription;
        this.sunusng = sunusng;
        this.ipom = ipom;
        this.weiwei = weiwei;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public int getSunusng() {
        return sunusng;
    }

    public void setSunusng(int sunusng) {
        this.sunusng = sunusng;
    }

    public int getIpom() {
        return ipom;
    }

    public void setIpom(int ipom) {
        this.ipom = ipom;
    }

    public int getWeiwei() {
        return weiwei;
    }

    public void setWeiwei(int weiwei) {
        this.weiwei = weiwei;
    }
}

package com.orange.shop;

import com.orange.shop.model.Shop;
import com.orange.shop.service.OrangeShopFinderImpl;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        OrangeShopFinderImpl orangeShopFinder = new OrangeShopFinderImpl();
        double currentLongtitude = -1.46917;
        double currentLatitude = 47.18727;
        String nameMobile = "sunusng";
        double maxDistance = 5; // This distance is in Kilometer
        List<Shop> Shops = orangeShopFinder.readShopDataFromCSV();
        orangeShopFinder.findOrangeShopWithMobileAvailable(currentLongtitude,currentLatitude,nameMobile,maxDistance,Shops);
        //System.out.println(orangeShopFinder.calculateDistanceHaversine(0,0,1,1));
        //System.out.println(orangeShopFinder.distanceCalculator(0,0,1,1,"K"));
    }
}

package com.orange.shop.service;

import com.orange.shop.io.FileShopReader;
import com.orange.shop.model.Shop;
import com.orange.shop.exception.ShopNotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class OrangeShopFinderImpl implements OrangeShopFinder{

    public OrangeShopFinderImpl() {

    }

    @Override
    public String findOrangeShopWithMobileAvailable(double currentLongitude, double currentLatitude, String nameMobile,double maxDistance, List<Shop> ShopList) {
        // Filter shops with stock for the given mobile within the maximum distance
        List<Shop> shopsWithStockWithinDistance = ShopList.stream()
                .filter(shop -> shop.hasMobileInStock(nameMobile))
                .filter(shop -> {
                    double distance = distanceCalculator(currentLongitude, currentLatitude, shop.getLongitude(), shop.getLatitude(), "K");
                    return distance <= maxDistance;
                })
                .collect(Collectors.toList());

        if (shopsWithStockWithinDistance.isEmpty()) {
            throw new ShopNotFoundException("No nearby shops with the mobile "+ nameMobile +" in stock within " + maxDistance + " km ");
        }

        // Find the nearest shop among the filtered shops
        Shop nearestShop = shopsWithStockWithinDistance.stream()
                .min(Comparator.comparingDouble(shop -> distanceCalculator(currentLongitude, currentLatitude, shop.getLongitude(), shop.getLatitude(), "K")))
                .orElse(null);

        double closestDistance = distanceCalculator(currentLongitude, currentLatitude, nearestShop.getLongitude(), nearestShop.getLatitude(), "K");

        // Display the result in the console
        String result = "******* The nearest shop within a distance of " + maxDistance + " kilometers that has the phone " +nameMobile+ " available is : ********\n" +
                "Shop name and Description: " + nearestShop.getShopDescription() + "\n" +
                "Distance : " + closestDistance + " Km" + "\n" +
                "Stock Available: " + nearestShop.MobileStock(nameMobile);

        System.out.println(result);

        return result;
    }

    public List<Shop> readShopDataFromCSV() {
        URI uri = null;
        try {
            uri = ClassLoader.getSystemResource(FileShopReader.NAME_FILE).toURI();
            List<String> lines = Files.readAllLines(Paths.get(uri));
            return lines.stream()
                    .skip(1) // Skip the header line
                    .map(line -> line.split(";"))
                    .map(fields -> new Shop(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]), fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5])))
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public double calculateDistanceHaversine(double lon1, double lat1, double lon2, double lat2) {
        double radius = 6371; // Earth's radius in kilometers
        double dLon = Math.toRadians(lon2 - lon1);
        double dLat = Math.toRadians(lat2 - lat1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radius * c;//Multiply radius to c to get the value in Km
    }


    @Override
    public double distanceCalculator(double lon1, double lat1, double lon2, double lat2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344; // Converting miles to kilometers
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;   // Converting miles to nautical miles
            }

            return dist;
        }
    }


}

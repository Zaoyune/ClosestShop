package com.orange.shop;

import com.orange.shop.model.Shop;
import com.orange.shop.exception.ShopNotFoundException;
import com.orange.shop.service.OrangeShopFinder;
import com.orange.shop.service.OrangeShopFinderImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertThrows;

public class OrangeShopFinderImplTest {

    private OrangeShopFinder orangeShopFinder;

    @Before
    public void setUp() {
        orangeShopFinder = new OrangeShopFinderImpl();
    }

    @Test
    public void testValidMobileInStockWithinMaxDistanceScenario() {
        double currentLongitude = -1.46917;
        double currentLatitude = 47.18727;
        String nameMobile = "sunusng";
        double maxDistance = 10.0;


        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(-1.49383, 47.19164, "Shop1", 10, 5, 2));
        shops.add(new Shop(2.0, 2.0, "Shop2", 5, 0, 0));

        OrangeShopFinder finder = new OrangeShopFinderImpl();

        // Action
        String result = finder.findOrangeShopWithMobileAvailable(currentLongitude, currentLatitude, nameMobile, maxDistance, shops);

        // Assert
        assertTrue(result.contains("Shop name and Description: Shop1"));
        assertTrue(result.contains("Distance : 1.9256650304734224 Km"));
        assertTrue(result.contains("Stock Available: 10"));
    }

    @Test
    public void testNoShopsWithMobileInStockScenario() {
        double currentLongitude = -1.46917;
        double currentLatitude = 47.18727;
        String nameMobile = "ipom";
        double maxDistance = 10;

        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(-1.49383, 47.19164, "Shop1", 0, 0, 0));
        shops.add(new Shop(2.0, 2.0, "Shop2", 0, 0, 0));

        // Action and Assert
        assertThrows(ShopNotFoundException.class, () -> orangeShopFinder.findOrangeShopWithMobileAvailable(currentLongitude, currentLatitude, nameMobile, maxDistance, shops));
    }

    @Test
    public void testNoShopsWithinMaxDistanceScenario() {
        double currentLongitude = -1.46917;
        double currentLatitude = 47.18727;
        String nameMobile = "sunusng";
        double maxDistance = 1;

        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(-1.49383, 47.19164, "Shop1", 10, 5, 2));

        // Action and Assert
        assertThrows(ShopNotFoundException.class, () -> orangeShopFinder.findOrangeShopWithMobileAvailable(currentLongitude, currentLatitude, nameMobile, maxDistance, shops));
    }

    @Test
    public void testNoShopsAvailableScenario() {
        double currentLongitude = -1.46917;
        double currentLatitude = 47.18727;
        String nameMobile = "sunusng";
        double maxDistance = 10.0;

        List<Shop> shops = new ArrayList<>();

        // Action and Assert
        assertThrows(ShopNotFoundException.class, () -> orangeShopFinder.findOrangeShopWithMobileAvailable(currentLongitude, currentLatitude, nameMobile, maxDistance, shops));
    }

    @Test
    public void testCalculateDistanceHaversineScenario() {
        // Test the calculateDistance method using Haversine formula
        double lon1 = 0.0;
        double lat1 = 0.0;
        double lon2 = 1.0;
        double lat2 = 1.0;
        double distance = orangeShopFinder.calculateDistanceHaversine(lon1, lat1, lon2, lat2);

        // Expected value
        double expected = 157.249;
        System.out.println("expected : "+ expected +" distance : "+ distance);
        // Use an assertion to check if the actual result matches the expected result with a delta (tolerance) of 0.001
        assertEquals(expected, distance, 0.001);
    }

    @Test
    public void testDistanceCalculatorKilometerScenario() {
        // Test the distanceCalculator method with kilometer unity using Great Circle Distance formula
        double lon1 = 0.0;
        double lat1 = 0.0;
        double lon2 = 1.0;
        double lat2 = 1.0;
        String unit = "K";
        double distance = orangeShopFinder.distanceCalculator(lon1, lat1, lon2, lat2, unit);

        // Expected value
        double expected = 157.241;

        // Use an assertion to check if the actual result matches the expected result with a delta (tolerance) of 0.001
        assertEquals(expected, distance, 0.001);
    }

    @Test
    public void testDistanceCalculatorNauticalMilesScenario() {
        // Test the distanceCalculator method with Nautical Miles unity using Great Circle Distance formula
        double lon1 = 0.0;
        double lat1 = 0.0;
        double lon2 = 1.0;
        double lat2 = 1.0;
        String unit = "N";
        double distance = orangeShopFinder.distanceCalculator(lon1, lat1, lon2, lat2, unit);

        // Expected value
        double expected = 84.8474862424457;

        // Use an assertion to check if the actual result matches the expected result with a delta (tolerance) of 0.001
        assertEquals(expected, distance, 0.001);
    }
}

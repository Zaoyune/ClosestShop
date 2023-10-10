package com.orange.shop.service;

import com.orange.shop.model.Shop;

import java.util.List;

/**
 * Recherche de boutiques Orange.
 * 
 * Le point d'entrée doit implémenter cette interface.
 * 
 * 
 */
public interface OrangeShopFinder {
	/**
	 * Find the nearest shop with a mobile available within a specified maximum distance.
	 *
	 * @param currentLongitude Current longitude.
	 * @param currentLatitude  Current latitude.
	 * @param nameMobile       Name of the mobile to find.
	 * @param maxDistance      Maximum allowed distance in kilometers.
	 * @param ShopList         List of Shops
	 * @return A message or result indicating the nearest shop.
	 */
	String findOrangeShopWithMobileAvailable(double currentLongitude, double currentLatitude, String nameMobile,double maxDistance, List<Shop> ShopList);
	/**
	 * Calculate the distance between two geographic coordinates.
	 *
	 * @param lat1  Latitude of the first location.
	 * @param lon1 Longitude of the first location.
	 * @param lat2 Latitude of the second location.
	 * @param lon2 Longitude of the second location.
	 * @param unit  Unit of measurement for the distance ("N" for nautical miles, "K" for kilometers).
	 * @return The calculated distance.
	 * In this method we are using Great Circle Distance formula and we return the distance either with Kilometer or with Nautical Miles
	 */
	double distanceCalculator(double lat1, double lon1, double lat2, double lon2, String unit);
	/**
	 * Calculate the distance between two geographic coordinates.
	 *
	 * @param lat1 Latitude of the first location.
	 * @param lon1 Longitude of the first location.
	 * @param lat2 Latitude of the second location.
	 * @param lon2 Longitude of the second location.
	 * @return The calculated distance.
	 * In this method we are using Haversine formula
	 */
	double calculateDistanceHaversine(double lon1, double lat1, double lon2, double lat2);
}

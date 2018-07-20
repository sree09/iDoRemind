package com.location.reminder.model;


import android.location.Location;

public class LocationCalculator {

    public static boolean islocationnearby(double lat1, double lon1, double lat2, double lon2) {

        boolean nearby = false;
        double distance = distance(lat1, lon1, lat2, lon2);
        if (distance <= 150)
            nearby = true;

        return nearby;

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {


        Location loc1 = new Location("");
        loc1.setLatitude(lat1);
        loc1.setLongitude(lon1);

        Location loc2 = new Location("");
        loc2.setLatitude(lat2);
        loc2.setLongitude(lon2);

        double meters = loc1.distanceTo(loc2);
        return meters;
    }
}

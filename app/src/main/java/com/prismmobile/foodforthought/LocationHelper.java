package com.prismmobile.foodforthought;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by benjunya on 10/6/15.
 *
 * Do we really need this?
 */
public class LocationHelper {

    private static LocationManager locationManager;

    protected static Location getLocation(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    protected static boolean isNewerLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            return true;
        }

        int INTERVAL = 1000 * 60 * 2; // 2 minutes
        long timeDifference = currentBestLocation.getTime() - location.getTime();
        return timeDifference > INTERVAL;
    }

}

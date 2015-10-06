package com.prismmobile.foodforthought;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by benjunya on 10/6/15.
 *
 * Do we really need this?
 */
public class LocationHelper {

    protected static LocationManager locationManager;
    private static Location currentLocation;

    protected static Location getLocation(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onLocationChanged(Location location) {
                if(isNewerLocation(location, currentLocation)) {
                    currentLocation = location;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        if(currentLocation == null) {
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        return currentLocation;
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

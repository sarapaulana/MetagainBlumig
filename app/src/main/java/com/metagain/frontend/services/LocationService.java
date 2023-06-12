package com.metagain.frontend.services;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.metagain.frontend.controll.UpdateLocationController;
import com.metagain.frontend.controll.implementations.UpdateLocationControllerImpl;
import com.metagain.frontend.model.storage.ProfileDataStorage;
import com.metagain.frontend.network.NetworkConstants;

import java.security.Provider;

public class LocationService extends Service {

    private LocationManager locationManager;
    private LocationListener locationListener;

    private Activity callingActivity;
    private UpdateLocationController updateLocationController = new UpdateLocationControllerImpl();



    @Override
    public void onCreate() {
        super.onCreate();
        // Initialisierung des LocationManagers und LocationListeners
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Starte die Standortaktualisierungen
        startLocationUpdates();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stoppe die Standortaktualisierungen
        stopLocationUpdates();
        updateLocationController.updateLocationOnDestroy(new double[]{420,420});
    }

    // Methode zum Starten der Standortaktualisierungen
    private void startLocationUpdates() {
        try {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                  locationListener.onLocationChanged(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));

            }
        } catch (Exception e) {
            //TODO
        }
    }

    // Methode zum Stoppen der Standortaktualisierungen
    private void stopLocationUpdates() {
        locationManager.removeUpdates(locationListener);
    }

    // Implementiere deinen eigenen LocationListener, der auf Standortaktualisierungen reagiert
    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            ProfileDataStorage.setLatitude(latitude);
            ProfileDataStorage.setLongitude(longitude);
            updateLocationController.updateLocation(new double[] {latitude, longitude});

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


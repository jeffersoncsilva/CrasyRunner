package integrador.senac.com.crasyrunner;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

/**
 * Created by Jefferson on 30/05/2016.
 */
public class GPSLocation implements LocationListener {
    private LocationManager locationManager;
    private Activity act;
    private double latitude;
    private double longitude;

    public GPSLocation(Activity act) {
        this.act = act;
        locationManager = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 50, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 50, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
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

    public void removeUpdatesLocation() {
        if (ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        locationManager.removeUpdates(this);
    }

    public double getLatitude(){return this.latitude; }
    public double getLongitude(){return this.longitude;}

}

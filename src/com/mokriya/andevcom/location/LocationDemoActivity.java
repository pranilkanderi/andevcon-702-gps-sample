package com.mokriya.andevcom.location;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class LocationDemoActivity extends Activity {
	
	private TextView info;
	private DemoLocationListener locationListener;
    private LocationManager locationManager;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        info = (TextView) findViewById(R.id.info);
        
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Register the listener with the Location Manager to receive location updates
        locationListener = new DemoLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }
    
    class DemoLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			if (location.getProvider() == LocationManager.GPS_PROVIDER) {
				
			}
			StringBuffer locationInfo = new StringBuffer();
			locationInfo.append("Lat : " + new DecimalFormat("#.#####").format(location.getLatitude()) + "\n");
			locationInfo.append("Lon: "  + new DecimalFormat("#.#####").format(location.getLongitude()) + "\n");
			
			info.setText(locationInfo.toString());
			
		}

		@Override
		public void onProviderDisabled(String provider) {			
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
    	
    }
    
    @Override
    protected void onDestroy() {    	
    	super.onDestroy();
    	locationManager.removeUpdates(locationListener);
    }
}
package saurav.friends;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

public class LocationActivity extends ListActivity implements GooglePlayServicesClient.OnConnectionFailedListener, GooglePlayServicesClient.ConnectionCallbacks, LocationListener {
	
	// refresh when new update of location is available : Probably doing it in tpDB should work
	
	private static String username = "user"; // change it for every user or set global var for every user on first login
	JSONArray locations = null;
	ArrayList<LocationInfo> objects = new ArrayList<LocationInfo>();
	tpDB locationinfo;
	
	private static String response_url = "http://url/?user=" + username;
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_TIME = "time";
	private static final String TAG_LOCATIONS = "location";
	
	private LocationClient locationClient;
	private LocationRequest locationRequest;
	private Intent mIntentService;
	private PendingIntent mPendingIntent;
	
//	private String provider;
//	LocationManager locationManager;
	
	public String getHtml(String url1)
	  {
	  	HttpClient vClient = new DefaultHttpClient();
	  	HttpGet vGet = new HttpGet(url1);
	  	String response ="";
	  	try
	  	{
	  		ResponseHandler<String> vHandler = new BasicResponseHandler();
	  		response = vClient.execute(vGet, vHandler);
	  	}
	  	catch (Exception e)
	  	{
	  		e.printStackTrace();
	  	}
	  	return response;
	  	
	  }
	
@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		mIntentService = new Intent(this, LocationServices.class);
		mPendingIntent = PendingIntent.getService(this, 1, mIntentService, 0);
		
		int resp = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(resp == ConnectionResult.SUCCESS)
		{
			locationClient = new LocationClient(this, this, this);
					
			locationClient.connect();
			Log.v(TAG_NAME, locationClient.toString());
			
			
		}
		else
		{
			Toast.makeText(this, "Google Play Service Error",Toast.LENGTH_LONG).show();
		}
		
		ArrayList<String> locationItems = new ArrayList<String>();
		
		locationinfo = new tpDB(this);
		locationinfo.open();
		locationItems=locationinfo.getLocation();
		locationinfo.close();
		
		setListAdapter(new LocationArrayAdapter(this, locationItems));
		
		if(locationClient!=null && locationClient.isConnected())
		{
//			locationRequest = LocationRequest.create();
//			locationRequest.setInterval(20000);
//			locationClient.requestLocationUpdates(locationRequest, this);
			Location location = locationClient.getLastLocation();
			float lat = (float) (location.getLatitude());
		    Toast.makeText(this, String.valueOf(lat)+"hjg", Toast.LENGTH_LONG).show();
		    
		    
	//		onLocationChanged(location);			
		}
		
		/*
		  locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		    Criteria criteria = new Criteria();
		    provider = locationManager.getBestProvider(criteria, false);
		    Location location = locationManager.getLastKnownLocation(provider);
		    if(location != null)
		    {
		    	onLocationChanged(location);
		    }*/
		    
		   // new GetLocations().execute();
		    
		   
	}
	
	
	@Override
	  public void onLocationChanged(Location location) {
	    float lat = (float) (location.getLatitude());
	    float lng = (float) (location.getLongitude());
	    
	    String url; @SuppressWarnings("unused")
		String st="";
	    url = "http:url2/?name="+username+"&lg="+String.valueOf(lng)+"&lt="+String.valueOf(lat);
	    
		st = getHtml(url);
		// will be a blank response. Just update central server db
		// new GetLocations().execute(); // keep commented. halts the thought activity for no use. and hangs
	  }
	
	@SuppressWarnings("unused")
	private class GetLocations extends AsyncTask<Void, Void, Void> {
		 
		 ProgressDialog pDialog = new ProgressDialog(LocationActivity.this);
         
        @Override
        protected void onPreExecute() {
            
            // Showing progress dialog
            super.onPreExecute();
            pDialog.setMessage("Sending your location and receiving location updates");
            pDialog.setCancelable(true);
            pDialog.show();
            
         
        }

        
        @Override
        protected Void doInBackground(Void... arg0) {
        	
            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(response_url, ServiceHandler.GET);
 
            //Log.d("Response: ", "> " + jsonStr);
 
            if (jsonStr != null) {
            	
            	try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                     
                    // Getting JSON Array node
                    locations = jsonObj.getJSONArray(TAG_LOCATIONS);
 
                    // looping through All Locations
                    for (int i = 0; i < locations.length(); i++) {
                        JSONObject c = locations.getJSONObject(i);
                       
                        String name = c.getString(TAG_NAME);
                        String address = c.getString(TAG_ADDRESS);
                        String time = c.getString(TAG_TIME);
                        LocationInfo temp = new LocationInfo(name, address, time);
                        
                        objects.add(temp);
                        
                    }
                    
                    locationinfo.open();
                    locationinfo.insertLocation(objects);
                    locationinfo.close();
                } catch (JSONException e) {
                    //e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
 
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pDialog.dismiss();
          // sql executed by tpDB class. nothing to do here.
        }
 
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.layout.reload, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if(id == R.id.reload_button)
		{
			ArrayList<String> locationItems = new ArrayList<String>();
			
			locationinfo = new tpDB(this);
			locationinfo.open();
			locationItems=locationinfo.getLocation();
			locationinfo.close();
			setListAdapter(new LocationArrayAdapter(this, locationItems));
			return true;
			
		}
		else
		{
			return super.onOptionsItemSelected(item);
		}
		/*
		switch(item.getItemId())
		{
			case R.id.reload_button:
				ArrayList<String> locationItems = new ArrayList<String>();
				
				locationinfo = new tpDB(this);
				locationinfo.open();
				locationItems=locationinfo.getLocation();
				locationinfo.close();
				setListAdapter(new LocationArrayAdapter(this, locationItems));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		*/
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(locationClient!=null)
		{
			locationClient.disconnect();
		}
	}


	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		
	}


	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		
	}


	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
		
	}

}



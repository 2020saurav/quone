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
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class EntertainmentActivity extends ListActivity {
	
	JSONArray news = null;
	ArrayList<NewsInfo> objects = new ArrayList<NewsInfo>();
	tpDB newsinfo;
	ArrayList<NewsInfo> newsItems;
	EntertainmentArrayAdapter adapter;
	
	private static String response_url = "http://saurav.bl.ee/ent.php";
	private static final String TAG_NEWS = "news";
	private static final String TAG_HEAD = "headline";
	private static final String TAG_DETAIL = "description";	
	
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
		newsItems = new ArrayList<NewsInfo>();
		newsinfo = new tpDB(this);
		newsinfo.open();		
		newsItems=newsinfo.getEntertainmentNews();
		newsinfo.close();
		adapter = new EntertainmentArrayAdapter(this, newsItems);
		setListAdapter(adapter);
		new GetNews().execute();
	}
	
	private class GetNews extends AsyncTask<Void, Void, Void>
	{
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
		 }

		@Override
		protected Void doInBackground(Void... arg0) {
			
			ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(response_url, ServiceHandler.GET);
            if (jsonStr != null) {
            	
            	try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    news = jsonObj.getJSONArray(TAG_NEWS);
                    for (int i = 0; i < news.length(); i++) {
                        JSONObject c = news.getJSONObject(i);
                        String head = c.getString(TAG_HEAD);
                        String detail = c.getString(TAG_DETAIL);                        
                        NewsInfo temp = new NewsInfo(head,detail);                        
                        objects.add(temp);                        
                    }                    
                    newsinfo.open();
                    newsinfo.insertEntertainmentNews(objects);
                    newsinfo.close();
            	}
            	catch (JSONException e)
            	{
            		//e.printStackTrace();
            	}            	
		}
        else
        {
        	Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
            return null;
     }
		 @Override
	        protected void onPostExecute(Void result) {
	            super.onPostExecute(result);
	       }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.layout.reload, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if(id == R.id.reload_button)
		{
			newsItems = new ArrayList<NewsInfo>();
			newsinfo = new tpDB(this);
			newsinfo.open();		
			newsItems=newsinfo.getEntertainmentNews();
			newsinfo.close();
			adapter = new EntertainmentArrayAdapter(this, newsItems);
			setListAdapter(adapter);
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
				newsItems = new ArrayList<NewsInfo>();
				newsinfo = new tpDB(this);
				newsinfo.open();		
				newsItems=newsinfo.getEntertainmentNews();
				newsinfo.close();
				adapter = new EntertainmentArrayAdapter(this, newsItems);
				setListAdapter(adapter);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}*/
	}
}

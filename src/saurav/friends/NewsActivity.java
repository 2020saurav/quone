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


public class NewsActivity extends ListActivity {
	
	JSONArray news = null;
	ArrayList<NewsInfo> objects = new ArrayList<NewsInfo>();
	tpDB newsinfo;
	
	private static String response_url = "http://saurav.bl.ee/news.php";
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
		//setContentView(R.layout.activity_news);
		
		
		ArrayList<NewsInfo> newsItems = new ArrayList<NewsInfo>();
		
		newsinfo = new tpDB(this);
		// do sepearately for head and desc.
		newsinfo.open();		
		newsItems=newsinfo.getNews();
		newsinfo.close();
		
		NewsArrayAdapter adapter = new NewsArrayAdapter(this, newsItems);
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
 
            //Log.d("Response: ", "> " + jsonStr);
 
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
                    newsinfo.insertNews(objects);
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
	          // sql executed by tpDB class. nothing to do here.
	        }
		 
		 
	}

	@Override
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
			ArrayList<NewsInfo> newsItems = new ArrayList<NewsInfo>();
			
			newsinfo = new tpDB(this);
			// do sepearately for head and desc.
			newsinfo.open();		
			newsItems=newsinfo.getNews();
			newsinfo.close();
			
			NewsArrayAdapter adapter = new NewsArrayAdapter(this, newsItems);
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
				ArrayList<NewsInfo> newsItems = new ArrayList<NewsInfo>();
				
				newsinfo = new tpDB(this);
				// do sepearately for head and desc.
				newsinfo.open();		
				newsItems=newsinfo.getNews();
				newsinfo.close();
				
				NewsArrayAdapter adapter = new NewsArrayAdapter(this, newsItems);
				setListAdapter(adapter);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}*/
	}

}

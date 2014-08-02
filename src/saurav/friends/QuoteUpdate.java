package saurav.friends;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;

public class QuoteUpdate {
	
	private static String response_url = "http://saurav.bl.ee/getquote.php";
	private static final String TAG_QID = "quote_id";
	private static final String TAG_QUOTE = "quote";
	private static final String TAG_QUOTES = "quoteArray";
	
	ArrayList<QuotesInfo> objects = new ArrayList<QuotesInfo>();
	// need to be synced at first install too! user will do it.
	// will respond on manual click of 'Update Quote DB' from settings/actionbar of Quote Activity.
	tpDB qupdate;
	
	
	public void update()
	{
		new GetQuotes().execute();
		
	}


	private class GetQuotes extends AsyncTask<Void, Void, Void> 
	{
		
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          
        }
		@Override
		protected Void doInBackground(Void... params) 
		{
			ServiceHandler sh = new ServiceHandler();
			String jsonStr = sh.makeServiceCall(response_url, ServiceHandler.GET);
			
				if (jsonStr != null) {
            	
            	try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                     
                    // Getting JSON Array node
                    JSONArray quotesArray = jsonObj.getJSONArray(TAG_QUOTES);
 
                    // looping through All Locations
                    for (int i = 0; i < quotesArray.length(); i++) {
                        JSONObject c = quotesArray.getJSONObject(i);
                       
                        String id = c.getString(TAG_QID);
                        String qt = c.getString(TAG_QUOTE);
                        
                        QuotesInfo temp = new QuotesInfo(id,qt);
                        
                        objects.add(temp);
                        
                    }
                   
                    qupdate.open();
                 //   qupdate.insertQuotes(objects);
                    qupdate.close();
                    
                } 
            	
            	catch (JSONException e) 
            	{
                    e.printStackTrace();
                }
            } 
			
			else
			{
                Log.e("ServiceHandler", "Couldn't get any quote data from the url");
            }
			
			
			return null;
		}
		
		@Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
          // sql executed by tpDB class. nothing to do here.
        }
		
	}

}

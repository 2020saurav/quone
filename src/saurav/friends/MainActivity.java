package saurav.friends;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


@SuppressWarnings("deprecation")

public class MainActivity extends TabActivity {

	public TabHost mTabHost;
	public int tabId =0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		mTabHost = getTabHost();	
		setupTabs();
		mTabHost.setCurrentTab(tabId);
		
	}

	private void setupTabs() {
		Intent intent;
		TabHost.TabSpec spec;
		
		intent = new Intent(this, QuotesActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		spec = mTabHost.newTabSpec("quotes")
						.setIndicator("",getResources().getDrawable(R.drawable.quote))
						.setContent(intent);
		mTabHost.addTab(spec);
		
		intent = new Intent(this, NewsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		spec = mTabHost.newTabSpec("news")
						.setIndicator("",getResources().getDrawable(R.drawable.india))
						.setContent(intent);
		mTabHost.addTab(spec);
		
		intent = new Intent(this, WorldActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		spec = mTabHost.newTabSpec("world")
						.setIndicator("",getResources().getDrawable(R.drawable.world))
						.setContent(intent);
		mTabHost.addTab(spec);
		
		intent = new Intent(this, EntertainmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		spec = mTabHost.newTabSpec("ent")
						.setIndicator("",getResources().getDrawable(R.drawable.ent))
						.setContent(intent);
		mTabHost.addTab(spec);
		
		intent = new Intent(this, ScienceActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		spec = mTabHost.newTabSpec("snc")
						.setIndicator("",getResources().getDrawable(R.drawable.snc))
						.setContent(intent);
		mTabHost.addTab(spec);
		
		intent = new Intent(this, TechActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		spec = mTabHost.newTabSpec("tech")
						.setIndicator("",getResources().getDrawable(R.drawable.tech))
						.setContent(intent);
		mTabHost.addTab(spec);
		
		
		
//		intent = new Intent(this, LocationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		spec = mTabHost.newTabSpec("location")
//						.setIndicator("",getResources().getDrawable(R.drawable.loc))
//						.setContent(intent);
//		mTabHost.addTab(spec);
		
		/*intent = new Intent(this, ChatActivity.class);
		spec = mTabHost.newTabSpec("chat")
						.setIndicator("",getResources().getDrawable(R.drawable.wall))
						.setContent(intent);
		mTabHost.addTab(spec);*/
		
		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            mTabHost.getTabWidget().getChildAt(i).getLayoutParams().height /=1.5;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// For Android 11 + : Reload button will appear on top right.
		// For others, reload option separately given under option hardbutton.
		getMenuInflater().inflate(R.layout.reload, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		tabId = mTabHost.getCurrentTab();
		
		int id = item.getItemId();
		if(id == R.id.reload_button)
		{
			mTabHost.clearAllTabs();
			setupTabs();
			mTabHost.setCurrentTab(tabId);
			return true;
		}
		else
		{
			return super.onOptionsItemSelected(item);
		}
//		switch(item.getItemId())
//		{
//			case R.id.reload_button:
//
//				mTabHost.clearAllTabs();
//				setupTabs();
//				mTabHost.setCurrentTab(tabId);
//
//				return true;
//			default:
//				return super.onOptionsItemSelected(item);
//		}
		
				
	
	}
	

}

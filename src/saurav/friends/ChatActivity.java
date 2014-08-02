package saurav.friends;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ChatActivity extends Activity {
	// just after a push, update it in local db without time stamp. make pull request and refresh it. keep making pull req and refresh every 1 min. on load make a pull request.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

}

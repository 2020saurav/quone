package saurav.friends;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LocationArrayAdapter extends BaseAdapter {
	
	Context context;
	private ArrayList <String> location;
	
	public LocationArrayAdapter(Context context, ArrayList<String> location)
	{
		this.context = context;
		this.location = location;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return location.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return location.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.layout_row_location, parent, false);
		TextView tvNews = (TextView) rowView.findViewById(R.id.tvLocationItem);
		tvNews.setText(location.get(position));
		return rowView;
	}

}

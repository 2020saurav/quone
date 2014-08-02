package saurav.friends;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScienceArrayAdapter extends BaseAdapter {	

	private final Context context;
	private ArrayList <NewsInfo> news;
	
	public ScienceArrayAdapter(Context context, ArrayList<NewsInfo> news)
	{
		this.context = context;
		this.news = news;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context
									.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.layout_row_science, parent, false);
		TextView tvNewsHead = (TextView) rowView.findViewById(R.id.tvScienceHead);
		TextView tvNewsDesc = (TextView) rowView.findViewById(R.id.tvScienceDesc);
		
		String head = news.get(position).getHead();
		String desc = news.get(position).getDetail();
		head = head.replace("&nbsp;", " ");
		head = head.replace("&#39;", "\'");
		head = head.replace("\\\"", "\"");
		head = head.replace("&amp;", "&");
		head = head.replace("&middot;", "-");
		
		desc = desc.replace("&nbsp;", " ");
		desc = desc.replace("&#39;", "\'");
		desc = desc.replace("\\\"", "\"");
		desc = desc.replace("&amp;", "&");
		desc = desc.replace("&middot;", "-");		
		
		tvNewsHead.setText(head);
		tvNewsDesc.setText(desc);
		
		return rowView;
	}

	@Override
	public int getCount() {
		return news.size();
	}

	@Override
	public Object getItem(int position) {
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}

package com.devtf_l.app.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.devtf_l.app.R;
import com.devtf_l.app.entry.Icons;
import com.devtf_l.app.views.FontsTextView;

public class ToolbarMenudrawerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Icons> navDrawerItems;

	public ToolbarMenudrawerAdapter(Context context, ArrayList<Icons> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	public String getItemTitle(int position) {
		return navDrawerItems.get(position).getTitle();
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.toolbarmenudrawer_adapter, null);
		}
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.iconPicture);
		FontsTextView txtTitle = (FontsTextView) convertView.findViewById(R.id.MDText);
		imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
		txtTitle.setText(navDrawerItems.get(position).getTitle());
		return convertView;
	}
}

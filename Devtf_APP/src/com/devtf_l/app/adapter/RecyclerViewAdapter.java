package com.devtf_l.app.adapter;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devtf_l.app.R;
import com.devtf_l.app.entry.RssItem;

/**
 * @Desc: RecyclerView 适配器
 * @author ljh
 * @date 2015-4-30 下午5:23:21
 */
public class RecyclerViewAdapter extends Adapter<ViewHolder> {
	ArrayList<RssItem> itemList;
	
	public RecyclerViewAdapter(ArrayList<RssItem> itemList){
		this.itemList = itemList;
	}
	
	public void setItemList(ArrayList<RssItem> itemList) {
		if(null == itemList){
			this.itemList.clear();
		}else{
			this.itemList = itemList;
		}
	}
	
	@Override
	public int getItemCount() {
		return itemList.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.common_recycler_view_item, viewGroup, false);
		return new RecyclerViewHolder(view);
	}

	static class RecyclerViewHolder extends ViewHolder {
		
		
		public RecyclerViewHolder(View view) {
			super(view);
			
		}
	}
}

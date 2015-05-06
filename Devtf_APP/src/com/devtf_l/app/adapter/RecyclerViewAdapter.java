package com.devtf_l.app.adapter;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devtf_l.app.R;

/**
 * @Desc: android tab 列表适配器
 * @author ljh
 * @date 2015-4-30 下午5:23:21
 */
public class RecyclerViewAdapter extends BaseRecyclerAdapter {
	public <T> RecyclerViewAdapter(ArrayList<T> itemList) {
		super(itemList);
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

package com.devtf_l.app.adapter;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * @Desc: recyclerAdapter 基类，tab页面的列表均使用此adapter的子类
 * @author ljh
 * @date 2015-5-6 下午3:52:18
 */
public abstract class BaseRecyclerAdapter extends Adapter<ViewHolder> {
	ArrayList<?> itemList;

	public <T> BaseRecyclerAdapter(ArrayList<T> itemList) {
		this.itemList = itemList;
	}

	public <T> void setItemList(ArrayList<T> itemList) {
		if (null == itemList) {
			this.itemList.clear();
		} else {
			this.itemList = itemList;
		}
	}

	@Override
	public int getItemCount() {
		return itemList.size();
	}
}

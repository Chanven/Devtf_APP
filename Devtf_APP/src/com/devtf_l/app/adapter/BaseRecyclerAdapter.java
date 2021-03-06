package com.devtf_l.app.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * @Desc: recyclerAdapter 基类，tab页面的列表均使用此adapter的子类
 * @author ljh
 * @date 2015-5-6 下午3:52:18
 */
public abstract class BaseRecyclerAdapter extends Adapter<ViewHolder> {
	List<?> itemList;
	OnItemClickListener mOnItemClickListener;
	OnItemLongClickListener mOnItemLongClickListener;

	public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
		this.mOnItemLongClickListener = mOnItemLongClickListener;
	}

	public <T> BaseRecyclerAdapter(List<T> itemList) {
		this.itemList = itemList;
	}

	public <T> void setItemList(List<T> itemList) {
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

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	public interface OnItemLongClickListener {
		boolean onItemLongClick(View view, int position);
	}
	
}

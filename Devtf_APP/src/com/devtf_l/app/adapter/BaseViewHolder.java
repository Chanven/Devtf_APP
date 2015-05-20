package com.devtf_l.app.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import com.devtf_l.app.adapter.BaseRecyclerAdapter.OnItemClickListener;
import com.devtf_l.app.adapter.BaseRecyclerAdapter.OnItemLongClickListener;

/**
 * @desc ViewHolder基类
 * @author ljh lijunhuayc@sina.com 2015-5-20
 */
public abstract class BaseViewHolder extends ViewHolder implements OnClickListener, OnLongClickListener {
	OnItemClickListener mOnItemClickListener;
	OnItemLongClickListener mOnItemLongClickListener;

	public BaseViewHolder(View view) {
		super(view);
		findView(view);
	}

	public BaseViewHolder(View view, OnItemClickListener mItemClickListener, OnItemLongClickListener mItemLongClickListener) {
		this(view);
		this.mOnItemClickListener = mItemClickListener;
		this.mOnItemLongClickListener = mItemLongClickListener;
		view.setOnClickListener(this);
		view.setOnLongClickListener(this);
	}

	public BaseViewHolder(View view, OnItemClickListener mItemClickListener) {
		this(view);
		this.mOnItemClickListener = mItemClickListener;
		view.setOnClickListener(this);
	}
	
	/**
	 * 初始化view
	 */
	protected abstract void findView(View view);

	@Override
	public void onClick(View view) {
		if (null != mOnItemClickListener) {
			mOnItemClickListener.onItemClick(view, getPosition());
		}
	}

	@Override
	public boolean onLongClick(View view) {
		if (null != mOnItemLongClickListener) {
			mOnItemLongClickListener.onItemLongClick(view, getPosition());
		}
		return true;
	}
}

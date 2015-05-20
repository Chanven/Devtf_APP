package com.devtf_l.app.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devtf_l.app.R;
import com.devtf_l.app.entry.RssItem;

/**
 * @Desc: android tab 列表适配器
 * @author ljh
 * @date 2015-4-30 下午5:23:21
 */
public class AndroidRecyclerAdapter extends BaseRecyclerAdapter {
	public <T> AndroidRecyclerAdapter(List<T> itemList) {
		super(itemList);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		// 作者 : Mr.Simple 这里的显示效果较好 : 原文链接
		// 概述在工作初期，我们可能会经常会有这样的感觉，自己的代码接口设计混乱、代码耦合较..
		// <ahref="http://www.devtf.cn/?p=502">Read More</a>
		RecyclerViewHolder rvHolder = (RecyclerViewHolder) viewHolder;
		rvHolder.menuIV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 发送邮件、查看详细信息
			}
		});
		RssItem rssItem = (RssItem) itemList.get(position);
		rvHolder.titleTV.setText(rssItem.getTitle());
		rvHolder.descTV.setText(Html.fromHtml(rssItem.getDescription()));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_android_recycler_item, viewGroup, false);
		return new RecyclerViewHolder(view, mOnItemClickListener, mOnItemLongClickListener);
	}

	static class RecyclerViewHolder extends BaseViewHolder {
		ImageView menuIV;
		TextView titleTV;
		TextView descTV;

		public RecyclerViewHolder(View view, OnItemClickListener mOnItemClickListener, OnItemLongClickListener mOnItemLongClickListener) {
			super(view, mOnItemClickListener, mOnItemLongClickListener);
		}

		@Override
		protected void findView(View view) {
			menuIV = (ImageView) view.findViewById(R.id.menuIV);
			titleTV = (TextView) view.findViewById(R.id.titleTV);
			descTV = (TextView) view.findViewById(R.id.descTV);
		}
	}
}

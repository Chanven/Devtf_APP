package com.devtf_l.app.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devtf_l.app.R;
import com.devtf_l.app.entry.EmploymentItem;

/**
 * @Desc: employ tab 列表适配器
 * @author ljh
 * @date 2015-4-30 下午5:23:21
 */
public class EmployRecycleAdapter extends BaseRecyclerAdapter {
	public <T> EmployRecycleAdapter(List<T> itemList) {
		super(itemList);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		RecyclerViewHolder rvHolder = (RecyclerViewHolder) viewHolder;
		rvHolder.menuIV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//发送右键、详细信息
			}
		});
		EmploymentItem eItem = (EmploymentItem) itemList.get(position);
		rvHolder.companyName.setText(eItem.getCompanyName());
		rvHolder.jobName.setText(eItem.getJobName());
		rvHolder.postTemptTV.setText(eItem.getPostTempt());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_employ_recycler_item_cardview, viewGroup, false);
		return new RecyclerViewHolder(view, this.mOnItemClickListener, this.mOnItemLongClickListener);
	}

	static class RecyclerViewHolder extends ViewHolder implements OnClickListener, OnLongClickListener {
		ImageView menuIV;
		TextView companyName;
		TextView jobName;
		TextView postTemptTV;
		OnItemClickListener mOnItemClickListener;
		OnItemLongClickListener mOnItemLongClickListener;

		public RecyclerViewHolder(View view) {
			super(view);
			menuIV = (ImageView) view.findViewById(R.id.menuIV);
			companyName = (TextView) view.findViewById(R.id.companyNameTV);
			jobName = (TextView) view.findViewById(R.id.jobNameTV);
			postTemptTV = (TextView) view.findViewById(R.id.postTemptTV);
		}

		public RecyclerViewHolder(View view, OnItemClickListener mItemClickListener, OnItemLongClickListener mItemLongClickListener) {
			this(view);
			this.mOnItemClickListener = mItemClickListener;
			this.mOnItemLongClickListener = mItemLongClickListener;
			view.setOnClickListener(this);
			view.setOnLongClickListener(this);
		}

		public RecyclerViewHolder(View view, OnItemClickListener mItemClickListener) {
			this(view);
			this.mOnItemClickListener = mItemClickListener;
		}

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
}

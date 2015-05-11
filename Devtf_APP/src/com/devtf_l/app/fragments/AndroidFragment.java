package com.devtf_l.app.fragments;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.InjectView;

import com.devtf_l.app.R;
import com.devtf_l.app.adapter.AndroidRecyclerAdapter;
import com.devtf_l.app.base.BaseTabFragment;
import com.devtf_l.app.entry.RssItem;

/**
 * @Desc: android tab文章页
 * @author ljh
 * @date 2015-4-28 上午10:28:15
 */
public class AndroidFragment extends BaseTabFragment{
	@InjectView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	LinearLayoutManager linearLayoutManager;
	ArrayList<RssItem> itemList = new ArrayList<RssItem>();
	AndroidRecyclerAdapter rvAdapter;
	
	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_tab_android_layout;
	}

	@Override
	public void init() {
		linearLayoutManager = new LinearLayoutManager(context);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mRecyclerView.setHasFixedSize(true);
		rvAdapter = new AndroidRecyclerAdapter(itemList);
		mRecyclerView.setAdapter(rvAdapter);
		getData();
	}
	
	/**
	 * @Description: 获取数据
	 * @author (ljh) @date 2015-4-30 下午5:17:46  
	 * @return void
	 */
	@Override
	public void getData() {
		
	}

	@Override
	public void initPageViewListener() {

	}
	
	
}

package com.devtf_l.app.fragments;

import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import butterknife.InjectView;

import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.devtf_l.app.R;
import com.devtf_l.app.activity.WebViewActivity;
import com.devtf_l.app.adapter.AndroidRecyclerAdapter;
import com.devtf_l.app.adapter.BaseRecyclerAdapter.OnItemClickListener;
import com.devtf_l.app.adapter.BaseRecyclerAdapter.OnItemLongClickListener;
import com.devtf_l.app.base.BaseTabFragment;
import com.devtf_l.app.entry.RssItem;
import com.devtf_l.app.net.HtmlDocumentRequest;
import com.devtf_l.app.net.RSS2Request;
import com.devtf_l.app.net.WebAPI;
import com.devtf_l.app.util.Timber;
import com.github.johnpersano.supertoasts.SuperToast.Duration;

/**
 * @Desc: android tab文章页
 * @author ljh
 * @date 2015-4-28 上午10:28:15
 */
public class AndroidFragment extends BaseTabFragment{
	@InjectView(R.id.swipeRefreshLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;
	@InjectView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	@InjectView(R.id.errorViewStub)
	ViewStub mErrorViewStub;
	View errorView;
	LinearLayoutManager linearLayoutManager;
	AndroidRecyclerAdapter rvAdapter;
	ArrayList<RssItem> rssList = new ArrayList<RssItem>();
	
	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_tab_android_layout;
	}

	@Override
	public void init() {
		linearLayoutManager = new LinearLayoutManager(context);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mRecyclerView.setHasFixedSize(true);
		rvAdapter = new AndroidRecyclerAdapter(rssList);
		mRecyclerView.setAdapter(rvAdapter);
		rvAdapter.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(context, WebViewActivity.class);
				intent.putExtra("url", rssList.get(position).getLink());
				startActivity(intent);
			}
		});
		rvAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(View view, int position) {
				Timber.i("itemClick%s", position);
				return true;
			}
		});
		mSwipeRefreshLayout.setColorSchemeResources(R.color.light_green_a200, R.color.lime_a200, R.color.lime_a400, R.color.light_green_a400);
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				getData();
			}
		});
	}
	
	/**
	 * @Description: 获取数据
	 * @author (ljh) @date 2015-4-30 下午5:17:46  
	 * @return void
	 */
	@Override
	public void getData() {
		context.getRequestQueue().add(new RSS2Request(Method.GET, WebAPI.EMPLOY_URL, new Listener<InputStream>() {
			@Override
			public void onResponse(final InputStream inputStream) {
//				rssList = parseDocument(doc);
//				if (null != rssList && rssList.isEmpty()) {
//					mHandler.post(new Runnable() {
//						public void run() {
//							context.showToast("未获取到新数据");
//						}
//					});
//					return;
//				}
//				context.runOnUiThread(new Runnable() {
//					public void run() {
//						rvAdapter.setItemList(rssList);
//						rvAdapter.notifyDataSetChanged();
//						mErrorViewStub.setVisibility(View.GONE);
//						mSwipeRefreshLayout.setRefreshing(false);
//					}
//				});
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (rssList.isEmpty())
					if (null == errorView) 
						errorView = mErrorViewStub.inflate();
					else
						mErrorViewStub.setVisibility(View.VISIBLE);
				mSwipeRefreshLayout.setRefreshing(false);
				context.showToast("加载失败", Duration.VERY_SHORT);
			}
		}));
	}

	@Override
	public void initPageViewListener() {

	}
	
	
}

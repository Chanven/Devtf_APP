package com.devtf_l.app.fragments;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import butterknife.InjectView;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.devtf_l.app.R;
import com.devtf_l.app.activity.WebViewActivity;
import com.devtf_l.app.adapter.BaseRecyclerAdapter.OnItemClickListener;
import com.devtf_l.app.adapter.BaseRecyclerAdapter.OnItemLongClickListener;
import com.devtf_l.app.adapter.EmployRecycleAdapter;
import com.devtf_l.app.base.BaseTabFragment;
import com.devtf_l.app.entry.EmploymentItem;
import com.devtf_l.app.net.HtmlDocumentRequest;
import com.devtf_l.app.net.WebAPI;
import com.devtf_l.app.util.Timber;

/**
 * @Desc: 招聘 tab页
 * @author ljh
 * @date 2015-4-28 下午5:13:33
 */
public class EmployFragment extends BaseTabFragment {
	@InjectView(R.id.swipeRefreshLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;
	@InjectView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	@InjectView(R.id.errorViewStub)
	ViewStub mErrorViewStub;
	View errorView;
	// @InjectView(R.id.reloadBt)
	TextView mReloadBt;// 位于ViewStub中的View默认是不展开的，因此没法使用butterknife注解来初始化（bk是编译期注解）
	LinearLayoutManager linearLayoutManager;
	EmployRecycleAdapter rvAdapter;
	List<EmploymentItem> eiList = new ArrayList<EmploymentItem>();

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_tab_employ_layout;
	}

	@Override
	public void init() {
		linearLayoutManager = new LinearLayoutManager(context);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		rvAdapter = new EmployRecycleAdapter(eiList);
		mRecyclerView.setAdapter(rvAdapter);
		rvAdapter.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(context, WebViewActivity.class);
				intent.putExtra("url", eiList.get(position).getJobDescAddress());
				startActivity(intent);
			}
		});
		rvAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(View view, int position) {
				// TODO item click listener
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
//		mSwipeRefreshLayout.setRefreshing(true);
	}
	
	@Override
	public void getData() {
		context.getRequestQueue().add(new HtmlDocumentRequest(Method.GET, WebAPI.EMPLOY_URL, new Listener<Document>() {
			@Override
			public void onResponse(final Document doc) {
				eiList = parseDocument(doc);
				if (null != eiList && eiList.isEmpty()) {
					mHandler.post(new Runnable() {
						public void run() {
							context.showToast("未获取到新数据");
						}
					});
					return;
				}
				context.runOnUiThread(new Runnable() {
					public void run() {
						rvAdapter.setItemList(eiList);
						rvAdapter.notifyDataSetChanged();
						mErrorViewStub.setVisibility(View.GONE);
						mSwipeRefreshLayout.setRefreshing(false);
					}
				});
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if(null == errorView){
					errorView = mErrorViewStub.inflate();
				}
			}
		}));
	}

	@Override
	public void initPageViewListener() {
	}

	/**
	 * @Description: 解析document文档对象到EmploymentItem对象列表
	 * @author (ljh) @date 2015-5-7 上午11:11:57
	 * @param doc
	 * @return List<EmploymentItem>
	 */
	private List<EmploymentItem> parseDocument(Document doc) {
		List<EmploymentItem> eiList = new ArrayList<EmploymentItem>();
		try {
			Elements trs = doc.getElementsByTag("table").get(0).select("tbody").select("tr");
			for (Element tr : trs) {
				Elements tds = tr.select("td");
				EmploymentItem eItem = new EmploymentItem();
				eItem.setCompanyName(tds.get(0).text());
				Elements td = tds.get(1).select("a");
				eItem.setJobName(td.text());
				eItem.setJobDescAddress(td.attr("href"));
				eItem.setPostTempt(tds.get(2).text());
				eItem.setEmail(tds.get(3).text());
				eiList.add(eItem);
			}
		} catch (Exception e) {
			// Html页面结构变化可能导致解析NullPointException，这里catch一下不做处理
			// TODO ...后期可以考虑添加日志记录，在收集异常日志的时候可以及时发现并修正错误
		}
		return eiList;
	}
}

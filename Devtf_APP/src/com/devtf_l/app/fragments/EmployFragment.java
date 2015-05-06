package com.devtf_l.app.fragments;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;
import android.widget.TextView;
import butterknife.InjectView;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.devtf_l.app.R;
import com.devtf_l.app.adapter.AndroidRecyclerAdapter;
import com.devtf_l.app.adapter.EmployRecycleAdapter;
import com.devtf_l.app.base.BaseTabFragment;
import com.devtf_l.app.entry.EmploymentItem;
import com.devtf_l.app.net.HtmlInputRequest;
import com.devtf_l.app.net.WebAPI;

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
	ViewStub mViewStub;
//	@InjectView(R.id.reloadBt)
	TextView mReloadBt;//位于ViewStub中的View默认是不展开的，因此没法使用butterknife注解来初始化（bk是编译期注解）
	LinearLayoutManager linearLayoutManager;
	EmployRecycleAdapter rvAdapter;
	List<EmploymentItem> itemList = new ArrayList<EmploymentItem>();

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_tab_employ_layout;
	}

	@Override
	public void init() {
		linearLayoutManager = new LinearLayoutManager(context);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mRecyclerView.setHasFixedSize(true);
		rvAdapter = new EmployRecycleAdapter(itemList);
		mRecyclerView.setAdapter(rvAdapter);
		getData();
	}

	private void getData() {
		context.getRequestQueue().add(new HtmlInputRequest(Method.GET, WebAPI.EMPLOY_URL, new Listener<List<EmploymentItem>>() {
			@Override
			public void onResponse(final List<EmploymentItem> response) {
				context.runOnUiThread(new Runnable() {
					public void run() {
						rvAdapter.setItemList(response);
						rvAdapter.notifyDataSetChanged();
					}
				});
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
			}
		}));
		
		
		new Thread() {
			public void run() {
				try {
					Document doc = Jsoup.parse(new URL(WebAPI.EMPLOY_URL), 30000);
					Elements elements = doc.getElementsByTag("table");
					Elements els = doc.select("div.bio post-content");
					for (Element element : elements) {
						Elements tbody = element.select("tbody").select("tr");
						for (Element tr : tbody) {
							Elements tds = tr.select("td");
							EmploymentItem eItem = new EmploymentItem();
							eItem.setCompanyName(tds.get(0).text());
							Elements td = tds.get(1).select("a");
							eItem.setJobName(td.text());
							eItem.setJobDescAddress(td.attr("href"));
							eItem.setPostTempt(tds.get(2).text());
							eItem.setEmail(tds.get(3).text());
							System.out.println(eItem + "\n\n");
						}
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		};
//		.start();
	}

	@Override
	public void initPageViewListener() {
	}
	
}

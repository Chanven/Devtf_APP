package com.devtf_l.app.fragments;

import java.util.ArrayList;
import java.util.List;

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
import com.devtf_l.app.adapter.EmployRecycleAdapter;
import com.devtf_l.app.base.BaseTabFragment;
import com.devtf_l.app.entry.EmploymentItem;
import com.devtf_l.app.net.HtmlDocumentRequest;
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
	// @InjectView(R.id.reloadBt)
	TextView mReloadBt;// 位于ViewStub中的View默认是不展开的，因此没法使用butterknife注解来初始化（bk是编译期注解）
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
		context.getRequestQueue().add(new HtmlDocumentRequest(Method.GET, WebAPI.EMPLOY_URL, new Listener<Document>() {
			@Override
			public void onResponse(final Document doc) {
				final List<EmploymentItem> eiList = parseDocument(doc);
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
					}
				});
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
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

package com.devtf_l.app.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.devtf_l.app.entry.EmploymentItem;

/**
 * @Desc: 自定义 Request 将招聘信息 URL返回html解析成 EmploymentItem 的列表
 * @author ljh
 * @date 2015-5-6 下午6:11:35
 */
public class HtmlInputRequest extends Request<List<EmploymentItem>> {
	private final Listener<List<EmploymentItem>> mListener;

	public HtmlInputRequest(int method, String url, Listener<List<EmploymentItem>> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mListener = listener;
	}

	public HtmlInputRequest(String url, Listener<List<EmploymentItem>> listener, ErrorListener errorListener) {
		this(Method.GET, url, listener, errorListener);
	}

	@Override
	protected Response<List<EmploymentItem>> parseNetworkResponse(NetworkResponse response) {
		InputStream htmlIS = new ByteArrayInputStream(response.data);
		Document doc;
		List<EmploymentItem> eiList = new ArrayList<EmploymentItem>();
		try {
			doc = Jsoup.parse(htmlIS, "UTF-8", WebAPI.BASE_URL);
			doc = Jsoup.parse(new URL(WebAPI.EMPLOY_URL), 30000);
			Elements elements = doc.getElementsByTag("table");
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
					eiList.add(eItem);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return Response.success(eiList, HttpHeaderParser.parseCacheHeaders(response));
	}

	@Override
	protected void deliverResponse(List<EmploymentItem> response) {
		mListener.onResponse(response);
	}
}

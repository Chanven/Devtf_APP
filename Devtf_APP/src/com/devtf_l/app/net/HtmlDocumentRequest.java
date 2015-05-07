package com.devtf_l.app.net;

import java.io.ByteArrayInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * @Desc: 自定义 Request 将html请求结果用 Jsoup 转为 Document 返，回调中按需解析Document
 * @author ljh
 * @date 2015-5-6 下午6:11:35
 */
public class HtmlDocumentRequest extends Request<Document> {
	private final Listener<Document> mListener;

	public HtmlDocumentRequest(int method, String url, Listener<Document> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mListener = listener;
	}

	public HtmlDocumentRequest(String url, Listener<Document> listener, ErrorListener errorListener) {
		this(Method.GET, url, listener, errorListener);
	}

	@Override
	protected Response<Document> parseNetworkResponse(NetworkResponse response) {
		try {
			Document doc = Jsoup.parse(new ByteArrayInputStream(response.data), "UTF-8", WebAPI.BASE_URL);
			return Response.success(doc, HttpHeaderParser.parseCacheHeaders(response));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(Document response) {
		mListener.onResponse(response);
	}
}

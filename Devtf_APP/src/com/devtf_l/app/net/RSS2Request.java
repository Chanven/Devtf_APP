package com.devtf_l.app.net;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class RSS2Request extends Request<InputStream> {
	private final Listener<InputStream> mListener;

	public RSS2Request(int method, String url, Listener<InputStream> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mListener = listener;
	}

	public RSS2Request(String url, Listener<InputStream> listener, ErrorListener errorListener) {
		this(Method.GET, url, listener, errorListener);
	}

	@Override
	protected Response<InputStream> parseNetworkResponse(NetworkResponse response) {
		try {
			InputStream is = new ByteArrayInputStream(response.data);
			return Response.success(is, HttpHeaderParser.parseCacheHeaders(response));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(InputStream response) {
		mListener.onResponse(response);
	}
}

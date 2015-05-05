package com.devtf_l.app.net;

import java.io.InputStream;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;

public class RSS2Request extends Request<InputStream> {
	public RSS2Request(int method, String url, ErrorListener listener) {
		super(method, url, listener);
	}

	@Override
	protected Response<InputStream> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deliverResponse(InputStream response) {
		// TODO Auto-generated method stub
	}
}

package com.devtf_l.app.net;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;

public class RSS2Request extends Request<String>{

	public RSS2Request(int method, String url, ErrorListener listener) {
		super(method, url, listener);
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deliverResponse(String response) {
		// TODO Auto-generated method stub
		
	}
	
}

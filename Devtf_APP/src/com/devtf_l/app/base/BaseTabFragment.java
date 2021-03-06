package com.devtf_l.app.base;

import com.android.volley.Request;
import com.android.volley.RequestQueue.RequestFilter;
import com.devtf_l.app.R;
import com.devtf_l.app.fragments.AndroidFragment;
import com.devtf_l.app.fragments.ContactUsFragment;
import com.devtf_l.app.fragments.EmployFragment;
import com.devtf_l.app.fragments.IOSFragment;

import android.os.Bundle;

public abstract class BaseTabFragment extends BaseFragment{
	private static final String REQUEST_TAG = "tab";
	enum Type{
		ANDROID, IOS, EMPLOY, CONTACT_US
	}
	private static final String FRAGMENT_TYPE = "position";
	private int position;
	//固定至少3个颜色
	protected final int[] ColorSchemeArr = new int[]{R.color.light_green_a200, R.color.lime_a400, R.color.light_green_a400};

	public static BaseTabFragment newInstance(int mPosition) {
		BaseTabFragment fragment = null;
		switch (mPosition) {
			case 0:
				fragment = new AndroidFragment();
				break;
			case 1:
				fragment = new IOSFragment();
				break;
			case 2:
				fragment = new EmployFragment();
				break;
			case 3:
				fragment = new ContactUsFragment();
				break;
		}
		Bundle b = new Bundle();
		b.putInt(FRAGMENT_TYPE, mPosition);
		fragment.setArguments(b);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		position = getArguments().getInt(FRAGMENT_TYPE);
	}
	
	public int getPosition() {
		return position;
	}
	
	/**
	 * @Description: 获取数据的接口
	 * @author (ljh) @date 2015-5-11 上午11:21:47  
	 * @return void
	 */
	public abstract void getData();
	
	@Override
	public void onPause() {
		RequestFilter rf = new RequestFilter() {
			@Override
			public boolean apply(Request<?> request) {
				if(REQUEST_TAG.equals(request.getTag()))
					return true;
				return false;
			}
		};
		context.getRequestQueue().cancelAll(rf);
		super.onPause();
	}
	
}

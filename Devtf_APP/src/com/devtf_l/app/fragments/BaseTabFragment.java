package com.devtf_l.app.fragments;

import android.os.Bundle;

public abstract class BaseTabFragment extends BaseFragment{
	enum Type{
		ANDROID, IOS, EMPLOY, CONTACT_US
	}
	private static final String FRAGMENT_TYPE = "position";
	private int position;

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
	
}

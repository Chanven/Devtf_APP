package com.devtf_l.app.base;

import butterknife.ButterKnife;

import com.afollestad.materialdialogs.MaterialDialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Desc: fragment 公共基类，[Tips: 采用hide/show来管理fragment的话，周期方法不会重复执行]
 * @author ljh
 * @date 2015-5-5 上午11:55:37
 */
public abstract class BaseFragment extends Fragment {
	protected BaseActivity context = null;
	protected View fragmentRoot = null; // Fragment的根View,获取Fragment中的View需要使用
	private String TAG = "BaseFragment-TAG";
	protected MaterialDialog mDialog;	//MaterialDialog

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = (BaseActivity) activity;
		TAG = context.getClass().getSimpleName() + "-" + this.getClass().getSimpleName();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		fragmentRoot = inflater.inflate(getFragmentLayout(), null);
//		fragmentRoot.setBackgroundColor(getResources().getColor(android.R.color.white));
		ButterKnife.inject(this, fragmentRoot);
		return fragmentRoot;
	}

	public View getRootView() {
		return fragmentRoot;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		mDialog = context.createMaterialDialog();
		init();
		initPageViewListener();
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
	}

	public abstract int getFragmentLayout();
	public abstract void init();
	public abstract void initPageViewListener();


}

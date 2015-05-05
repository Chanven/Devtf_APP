package com.devtf_l.app.fragments;

import android.os.Handler;
import butterknife.InjectView;

import com.devtf_l.app.R;
import com.devtf_l.app.base.BaseFragment;
import com.devtf_l.app.views.FontsTextView;

/**
 * @Desc: 关于
 * @author ljh
 * @date 2015-4-29 下午5:02:47
 */
public class AboutFragment extends BaseFragment {
	@InjectView(R.id.labelTV)
	FontsTextView labelTV;
	@InjectView(R.id.descFontsTV)
	FontsTextView descFontsTV;
	@InjectView(R.id.thanksFontsTV)
	FontsTextView thanksFontsTV;
	Handler mHandler = new Handler();
	
	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_about_layout;
	}

	@Override
	public void init() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				labelTV.setText(R.string.about_label);
				descFontsTV.setText(R.string.about_desc);
				thanksFontsTV.setText(R.string.about_thanks);
			}
		}, 200);
		
	}

	@Override
	public void initPageViewListener() {
	}

}

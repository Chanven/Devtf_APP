package com.devtf_l.app.fragments;

import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;

import com.devtf_l.app.R;
import com.devtf_l.app.activity.EmailSendActivity;
import com.devtf_l.app.activity.WebViewActivity;
import com.devtf_l.app.base.BaseTabFragment;
import com.devtf_l.app.net.WebAPI;

/**
 * @Desc: 联系我们 tab页
 * @author ljh
 * @date 2015-4-28 下午5:14:18
 */
public class ContactUsFragment extends BaseTabFragment{
	@InjectView(R.id.joinTV)
	TextView joinTV;
	@InjectView(R.id.emailTV)
	TextView emailTV;
	@InjectView(R.id.qqTV)
	TextView qqTV;
	@InjectView(R.id.sinaTV)
	TextView sinaTV;
	
	Handler mHandler = new Handler();
	
	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_tab_contact_us_layout;
	}

	@Override
	public void init() {
		final String emailStr = "邮箱：<font color='#64FFDA'><u>simplecoder.h@gmail.com</u></font>";
		final String qqStr = "QQ：<font color='#64FFDA'>149419843</font>";
		final String sinaStr = "新浪微博：<font color='#64FFDA'><u>开发技术前线</u></font>";
		
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				joinTV.setText(R.string.about_desc);
				emailTV.setText(Html.fromHtml(emailStr));
				qqTV.setText(Html.fromHtml(qqStr));
				sinaTV.setText(Html.fromHtml(sinaStr));
			}
		}, 200);
	}

	@Override
	public void initPageViewListener() {
	}
	
	@OnClick({R.id.emailTV, R.id.sinaTV})
	public void onClick(View view){
		Intent intent = null;
		switch (view.getId()) {
			case R.id.emailTV:
				intent = new Intent(context, EmailSendActivity.class);
				startActivity(intent);
				break;
			case R.id.sinaTV:
				intent = new Intent(context, WebViewActivity.class);
				intent.putExtra("url", WebAPI.SINA_URL);
				startActivity(intent);
				break;
		}
	}
	
	@Override
	public void onDestroy() {
		mHandler.removeCallbacksAndMessages(null);
		super.onDestroy();
	}
	
}

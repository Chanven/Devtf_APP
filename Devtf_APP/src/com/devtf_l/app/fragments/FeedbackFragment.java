package com.devtf_l.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.devtf_l.app.R;

/**
 * @Desc: 反馈
 * @author ljh
 * @date 2015-4-27 下午1:09:51
 */
public class FeedbackFragment extends BaseFragment {
	String FEED_BACK_URL = "https://github.com/lijunhuayc/Devtf_APP/issues/new";
	Context context;
	WebView wv;

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_feedback_layout;
	}

	@Override
	public void init() {
		WebView wv = (WebView) fragmentRoot.findViewById(R.id.wv);
		wv.loadUrl("https://www.baidu.com");
		wv.getSettings().setJavaScriptEnabled(true);
		wv.clearCache(true);
		WebSettings webSettings = wv.getSettings();
		wv.getSettings().setPluginState(PluginState.ON);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		wv.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
			}
		});
		wv.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				{
					view.loadUrl(url);
					return true;
				}
			}
		});
	}

	@Override
	public void initPageViewListener() {
		// TODO Auto-generated method stub
		
	}
}
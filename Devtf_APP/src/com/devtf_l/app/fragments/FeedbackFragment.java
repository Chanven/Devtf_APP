package com.devtf_l.app.fragments;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.InjectView;

import com.devtf_l.app.R;

/**
 * @Desc: 反馈
 * @author ljh
 * @date 2015-4-27 下午1:09:51
 */
public class FeedbackFragment extends BaseFragment {
	@InjectView(R.id.webView)
	WebView webView;
	String FEED_BACK_URL = "https://github.com/lijunhuayc/Devtf_APP/issues/new";

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_feedback_layout;
	}

	@Override
	public void init() {
		webView.loadUrl(FEED_BACK_URL);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.clearCache(true);
		WebSettings webSettings = webView.getSettings();
		webView.getSettings().setPluginState(PluginState.ON);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
			}
		});
		webView.setWebViewClient(new WebViewClient() {
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
package com.devtf_l.app.fragments;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import butterknife.InjectView;

import com.devtf_l.app.R;

/**
 * @Desc: 投稿
 * @author ljh
 * @date 2015-4-29 下午4:53:21
 */
public class ContributeFragment extends BaseFragment {
	public static final String CONTRIBUTE_URL = "http://www.devtf.cn/?page_id=198";
	@InjectView(R.id.webView)
	WebView webView;

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_contribute_layout;
	}

	@Override
	public void init() {
		webView.loadUrl(CONTRIBUTE_URL);
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
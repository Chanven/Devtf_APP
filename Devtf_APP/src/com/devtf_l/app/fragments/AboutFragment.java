package com.devtf_l.app.fragments;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import butterknife.InjectView;

import com.devtf_l.app.R;

/**
 * @Desc: 关于
 * @author ljh
 * @date 2015-4-29 下午5:02:47
 */
public class AboutFragment extends BaseFragment {
	@InjectView(R.id.webView)
	WebView webView;
	String ABOUT_URL = "http://www.devtf.cn/?page_id=19";

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_about_layout;
	}

	@Override
	public void init() {
		webView.loadUrl(ABOUT_URL);
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

package com.devtf_l.app.fragments;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import butterknife.InjectView;
import butterknife.OnClick;

import com.devtf_l.app.R;

/**
 * @Desc: 关于
 * @author ljh
 * @date 2015-4-29 下午5:02:47
 */
public class AboutFragment extends BaseFragment {
	String ABOUT_URL = "http://www.devtf.cn/?page_id=19";
	@InjectView(R.id.webView)
	WebView webView;
	@InjectView(R.id.backIV)
	ImageView backIV;
	@InjectView(R.id.forwardIV)
	ImageView forwardIV;
	@InjectView(R.id.refreshIV)
	ImageView refreshIV;
	@InjectView(R.id.browserIV)
	ImageView browserIV;

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

			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
				return super.onJsAlert(view, url, message, result);
			}
		});
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webView.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
						webView.goBack();
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	public void initPageViewListener() {
		// TODO Auto-generated method stub
	}

	@OnClick({R.id.backIV, R.id.forwardIV, R.id.refreshIV, R.id.browserIV})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.backIV:
				if(webView.canGoBack())
					webView.goBack();
				break;
			case R.id.forwardIV:
				webView.goForward();
				break;
			case R.id.refreshIV:
				webView.reload();
				break;
			case R.id.browserIV:
				break;
		}
	}
}

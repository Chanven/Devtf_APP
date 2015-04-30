package com.devtf_l.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.InjectView;

import com.devtf_l.app.R;
import com.devtf_l.app.base.BaseActivity;
import com.devtf_l.app.entry.RssItem;

public class ShowContentActivity extends BaseActivity{
	RssItem rssItem;
	@InjectView(R.id.webView)
	WebView webView;
	
	@Override
	protected int initLayout() {
		return R.layout.common_webview_layout;
	}

	@Override
	protected void init() {
		if(null == rssItem){
			return;
		}
		webView.loadUrl(rssItem.getLink());
		webView.clearCache(true);
		WebSettings webSettings = webView.getSettings();
		webView.getSettings().setPluginState(WebSettings.PluginState.ON);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		webView.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
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
	protected void initListener() {
	}
	
}

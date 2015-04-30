package com.devtf_l.app.fragments;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.devtf_l.app.R;

/**
 * @Desc: 版权信息
 * @author ljh
 * @date 2015-4-29 下午5:02:47
 */
public class CopyrightFragment extends BaseFragment {
	WebView wv;
	String COPYRIGHT_URL = "http://www.devtf.cn/?page_id=6";

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_copyright_layout;
	}

	@Override
	public void init() {
		WebView wv = (WebView) fragmentRoot.findViewById(R.id.wv);
		/*
		 * Use this to make the webview link convert from Mobile to Desktop. ;)
		 * 
		 * wv.getSettings().setUserAgentString("Mozilla/5.0 " +
		 * "(Windows NT 6.2; " + "WOW64) AppleWebKit/537.31 " +
		 * "(KHTML, like Gecko) Chrome/20 " + "Safari/537.31");
		 */
		wv.loadUrl(COPYRIGHT_URL);
		wv.clearCache(true);
		WebSettings webSettings = wv.getSettings();
		wv.getSettings().setPluginState(WebSettings.PluginState.ON);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		wv.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
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

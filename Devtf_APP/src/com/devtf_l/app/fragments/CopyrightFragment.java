package com.devtf_l.app.fragments;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.InjectView;

import com.devtf_l.app.R;

/**
 * @Desc: 版权信息
 * @author ljh
 * @date 2015-4-29 下午5:02:47
 */
public class CopyrightFragment extends BaseFragment {
	@InjectView(R.id.webView)
	WebView webView;
	String COPYRIGHT_URL = "http://www.devtf.cn/?page_id=6";

	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_copyright_layout;
	}

	@Override
	public void init() {
		webView.loadUrl(COPYRIGHT_URL);
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
	public void initPageViewListener() {
		// TODO Auto-generated method stub
	}
}

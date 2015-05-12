package com.devtf_l.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import butterknife.InjectView;
import butterknife.OnClick;

import com.devtf_l.app.R;
import com.devtf_l.app.base.BaseActivity;

/**
 * @desc webview显示的模块均跳转到本处【暂时】
 * @author ljh lijunhuayc@sina.com 2015-5-4
 */
public class WebViewActivity extends BaseActivity {
	String url = "http://www.baidu.com";
	@InjectView(R.id.toolbar)
	Toolbar toolbar;
	@InjectView(R.id.webView)
	WebView webView;
	@InjectView(R.id.backIV)
	ImageView backIV;
	@InjectView(R.id.forwardIV)
	ImageView forwardIV;
	@InjectView(R.id.browserIV)
	ImageView browserIV;
	@InjectView(R.id.favoriteIV)
	ImageView favoriteIV;
	@InjectView(R.id.refreshIV)
	ImageView refreshIV;

	@Override
	protected int initLayout() {
		return R.layout.common_webview_layout;
	}

	@Override
	protected void init() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		String url = getIntent().getStringExtra("url");
		webView.loadUrl(url);
		webView.clearCache(true);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setPluginState(PluginState.ON);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				getSupportActionBar().setTitle(title);
			}
		});
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
		});
		webView.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initListener() {
	}

	@OnClick({R.id.backIV, R.id.forwardIV, R.id.refreshIV, R.id.browserIV})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.backIV:
				webView.goBack();
				break;
			case R.id.forwardIV:
				webView.goForward();
				break;
			case R.id.browserIV:
				break;
			case R.id.favoriteIV:
				break;
			case R.id.refreshIV:
				webView.reload();
				throw new RuntimeException("异常测试");
//				break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}

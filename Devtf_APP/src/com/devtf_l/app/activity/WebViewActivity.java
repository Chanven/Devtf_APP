package com.devtf_l.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Handler;
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
import com.devtf_l.app.views.ScrollerWebView;
import com.devtf_l.app.views.ScrollerWebView.OnScrollListener;

/**
 * @desc webview显示的模块均跳转到本处【暂时】
 * @author ljh lijunhuayc@sina.com 2015-5-4
 */
public class WebViewActivity extends BaseActivity {
	String url = "http://www.devtf.cn";
	@InjectView(R.id.toolbar)
	Toolbar toolbar;
	@InjectView(R.id.webView)
	ScrollerWebView mVebView;
	@InjectView(R.id.loadingIV)
	ImageView loadingIV;
	@InjectView(R.id.backIV)
	ImageView backIV;
	@InjectView(R.id.forwardIV)
	ImageView forwardIV;
	@InjectView(R.id.invokeIV)
	ImageView invokeIV;
	@InjectView(R.id.favoriteIV)
	ImageView favoriteIV;
	@InjectView(R.id.refreshIV)
	ImageView refreshIV;
	AnimationDrawable ad;
	Handler mHandler = new Handler();

	@Override
	protected int initLayout() {
		return R.layout.common_webview_layout;
	}

	@Override
	protected void init() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		url = getIntent().getStringExtra("url");
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				loadWeb();
			}
		}, 100);
	}

	private void loadWeb() {
		mVebView.loadUrl(url);
		mVebView.clearCache(true);
		WebSettings webSettings = mVebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setPluginState(PluginState.ON);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		mVebView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				getSupportActionBar().setTitle(title);
			}
		});
		mVebView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				loadingIV.setVisibility(View.VISIBLE);
				if(null == ad)
					ad = (AnimationDrawable) loadingIV.getBackground();
				ad.start();
				setMenuEnable();
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				setMenuEnable();
				if(null != ad){
					ad.stop();
				}
				loadingIV.setVisibility(View.GONE);
			}
			
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
		});
		mVebView.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		mVebView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(int l, int t) {
				
			}
		});
	}
	
	private void setMenuEnable() {
		if(mVebView.canGoBack()){
			backIV.setEnabled(true);
		}else{
			backIV.setEnabled(false);
		}
		if(mVebView.canGoForward()){
			forwardIV.setEnabled(true);
		}else{
			forwardIV.setEnabled(false);
		}
	}

	@Override
	protected void initListener() {
	}

	@OnClick({R.id.backIV, R.id.forwardIV, R.id.refreshIV, R.id.invokeIV, R.id.favoriteIV})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.backIV:
				if(mVebView.canGoBack()){
					mVebView.goBack();
				}else{
					onBackPressed();
				}
				break;
			case R.id.forwardIV:
				mVebView.goForward();
				break;
			case R.id.invokeIV:
				break;
			case R.id.favoriteIV:
				//收藏逻辑
				break;
			case R.id.refreshIV:
				mVebView.reload();
				break;
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
	
	@Override
	public void onBackPressed() {
		if(mVebView.canGoBack()){
			backIV.performClick();
		}else{
			super.onBackPressed();
		}
	}
	
}

package com.devtf_l.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * @Desc: 呆滚动监听的 WebView
 * @author ljh
 * @date 2015-5-14 下午3:34:21
 */
public class ScrollerWebView extends WebView {
	OnScrollListener mOnScrollListener;

	public ScrollerWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setOnScrollListener(OnScrollListener mOnScrollListener) {
		this.mOnScrollListener = mOnScrollListener;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (null != mOnScrollListener) {
			mOnScrollListener.onScroll(l, t);
		}
	}

	/**
	 * @Desc: 滚动回调接口
	 * @author ljh
	 * @date 2015-5-14 下午3:32:05
	 */
	public interface OnScrollListener {
		public void onScroll(int l, int t);
	}
}

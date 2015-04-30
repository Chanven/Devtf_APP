package com.devtf_l.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.InjectView;

import com.devtf_l.app.R;

/**
 * @Desc: android tab文章页
 * @author ljh
 * @date 2015-4-28 上午10:28:15
 */
public class AndroidFragment extends BaseTabFragment{
	@InjectView(R.id.textView)
	TextView textView;
	
	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_tab_android_layout;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void initPageViewListener() {
		textView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("你点击了android fragment");
				context.showToast("你点了MainFragment");
				Toast.makeText(context, "点击了android", 1).show();
			}
		});
	}
	
	
}

package com.devtf_l.app.fragments;

import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import butterknife.InjectView;

import com.devtf_l.app.R;
import com.devtf_l.app.adapter.MainPagerAdapter;
import com.devtf_l.app.base.BaseFragment;
import com.devtf_l.app.views.PagerSlidingTabStrip;
import com.devtf_l.app.views.jazzyviewpager.JazzyViewPager;
import com.devtf_l.app.views.jazzyviewpager.JazzyViewPager.SlideCallback;
import com.devtf_l.app.views.jazzyviewpager.JazzyViewPager.TransitionEffect;

/**
 * @Desc: 主Fragment, 包含多个tab页面
 * @author ljh
 * @date 2015-4-27 下午3:45:56
 */
public class MainFragment extends BaseFragment {
	@InjectView(R.id.mSlidingTabStrip)
	PagerSlidingTabStrip mSlidingTabStrip;
	@InjectView(R.id.mViewPager)
	JazzyViewPager mViewPager;
	private String[] tabTitles = {"Android","iOS","招聘信息","加入我们"};
	private MainPagerAdapter mPagerAdapter;
	int currentTab = 0;
	Handler mHandler = new Handler();
	
	
	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_main_layout;
	}

	@Override
	public void init() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPagerAdapter = new MainPagerAdapter(mViewPager, getChildFragmentManager(), tabTitles);
				mViewPager.setAdapter(mPagerAdapter);
				mViewPager.setOffscreenPageLimit(4);
				mViewPager.setCurrentItem(0);
				mViewPager.setFadeEnabled(true);
				mViewPager.setTransitionEffect(TransitionEffect.Stack);
				mViewPager.setSlideCallBack(new SlideCallback() {
					@Override
					public void callBack(int position, float positionOffset) {
						System.out.println(""+position+"--"+positionOffset);
						//TODO ...考虑在这里做tab 滑动动效
					}
				});
				mSlidingTabStrip.setViewPager(mViewPager);
				mSlidingTabStrip.setOnPageChangeListener(new OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						setCurrentTab(position);
					}
					@Override
					public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
					}
					@Override
					public void onPageScrollStateChanged(int state) {
					}
				});
			}
		}, 150);
		
	}

	@Override
	public void initPageViewListener() {
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}
	
	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
		mViewPager.setCurrentItem(currentTab, false);
	}
	
}

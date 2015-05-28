package com.devtf_l.app.fragments;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import butterknife.InjectView;

import com.devtf_l.app.R;
import com.devtf_l.app.adapter.MainPagerAdapter;
import com.devtf_l.app.base.BaseFragment;
import com.devtf_l.app.views.PagerSlidingTabStrip;

/**
 * @Desc: 主Fragment, 包含多个tab页面
 * @author ljh
 * @date 2015-4-27 下午3:45:56
 */
public class MainFragment extends BaseFragment {
	@InjectView(R.id.mSlidingTabStrip)
	PagerSlidingTabStrip mSlidingTabStrip;
	@InjectView(R.id.mViewPager)
	ViewPager mViewPager;
	private String[] tabTitles = {"Android","iOS","招聘信息","加入我们"};
	private MainPagerAdapter mPagerAdapter;
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
				mPagerAdapter = new MainPagerAdapter(getChildFragmentManager(), tabTitles);
				mViewPager.setAdapter(mPagerAdapter);
				mViewPager.setOffscreenPageLimit(4);
//				mViewPager.setCurrentItem(0);
				mSlidingTabStrip.setOnPageChangeListener(new OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
					}
					@Override
					public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
					}
					@Override
					public void onPageScrollStateChanged(int state) {
					}
				});
				mSlidingTabStrip.setShouldExpand(true);
				mSlidingTabStrip.setViewPager(mViewPager);
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
	
	public ViewPager getViewPager() {
		return mViewPager;
	}
	
	public void setCurrentPager(int currentPager) {
		mViewPager.setCurrentItem(currentPager, false);
	}
	
	public int getCurrentPager() {
		return mViewPager.getCurrentItem();
	}
	
}

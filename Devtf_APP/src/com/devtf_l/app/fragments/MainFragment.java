package com.devtf_l.app.fragments;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import butterknife.InjectView;

import com.devtf_l.app.R;
import com.devtf_l.app.adapter.MainPagerAdapter;
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
	protected String[] tabTitles = {"Android","iOS","招聘信息","加入我们"};
	MainPagerAdapter mPagerAdapter;
	
	@Override
	public int getFragmentLayout() {
		return R.layout.fragment_main_layout;
	}

	@Override
	public void init() {
		mPagerAdapter = new MainPagerAdapter(mViewPager, getChildFragmentManager(), tabTitles);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setFadeEnabled(true);
		mViewPager.setTransitionEffect(TransitionEffect.Stack);
		mViewPager.setSlideCallBack(new SlideCallback() {
			@Override
			public void callBack(int position, float positionOffset) {
				System.out.println(""+position+"--"+positionOffset);
			}
		});
		mSlidingTabStrip.setViewPager(mViewPager);
		
	}

	@Override
	public void initPageViewListener() {
		mSlidingTabStrip.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	
	
	
}

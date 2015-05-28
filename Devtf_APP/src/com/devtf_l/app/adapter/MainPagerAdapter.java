package com.devtf_l.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import com.devtf_l.app.base.BaseTabFragment;

/**
 * @Desc: MainFragment viewpager adapter
 * @author ljh
 * @date 2015-4-29 下午2:53:31
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
	private SparseArrayCompat<BaseTabFragment> mTabFragmentArray;
	private String[] mTitles;

	public MainPagerAdapter(FragmentManager fm, String[] mTitles) {
		super(fm);
		this.mTitles = mTitles;
		mTabFragmentArray = new SparseArrayCompat<BaseTabFragment>();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}

	@Override
	public int getCount() {
		return mTitles.length;
	}

	/**
	 * <p>
	 * Title: instantiateItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param container
	 * @param position
	 * @return
	 * @see com.devtf_l.app.adapter.FragmentPagerAdapter#instantiateItem(android.view.ViewGroup,
	 *      int)
	 */
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		return super.instantiateItem(container, position);
	}

	@Override
	public Fragment getItem(int position) {
		BaseTabFragment mFragment = mTabFragmentArray.get(position);
		if (null == mFragment) {
			mFragment = BaseTabFragment.newInstance(position);
			mTabFragmentArray.put(position, mFragment);
		}
		return mFragment;
	}

	public SparseArrayCompat<BaseTabFragment> getTabFragmentArray() {
		return mTabFragmentArray;
	}
}

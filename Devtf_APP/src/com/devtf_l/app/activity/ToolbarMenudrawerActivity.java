package com.devtf_l.app.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.InjectView;

import com.devtf_l.app.R;
import com.devtf_l.app.adapter.ToolbarMenudrawerAdapter;
import com.devtf_l.app.base.BaseActivity;
import com.devtf_l.app.base.BaseFragment;
import com.devtf_l.app.entry.Icons;
import com.devtf_l.app.fragments.AboutFragment;
import com.devtf_l.app.fragments.MainFragment;
import com.devtf_l.app.net.WebAPI;

/**
 * @desc main
 * @author ljh lijunhuayc@sina.com 2015-4-26
 */
public final class ToolbarMenudrawerActivity extends BaseActivity {
	@InjectView(R.id.toolbar)
	Toolbar mToolbar;
	@InjectView(R.id.leftDrawer)
	ListView mDrawerList;
	@InjectView(R.id.drawer_layout)
	DrawerLayout mDrawerLayout;
	protected ActionBarDrawerToggle mDrawerToggle;
	protected ArrayList<Icons> icons;
	protected ToolbarMenudrawerAdapter adapter;
	protected String[] MDTitles;
	BaseFragment mainFragment = new MainFragment();
	BaseFragment aboutFragment = new AboutFragment();
	FragmentManager mFragmentManager;
	FragmentTransaction mFTransaction;
	BaseFragment mCurrent = null;
	int containerViewId;

	@Override
	protected void init() {
		setSupportActionBar(mToolbar);
		getSupportActionBar().setTitle(R.string.app_name);
		containerViewId = R.id.content_frame;
		mFragmentManager = getSupportFragmentManager();
		final ViewGroup header = (ViewGroup) mInflater.inflate(R.layout.left_drawer_header, mDrawerList, false);
		final ViewGroup footer = (ViewGroup) mInflater.inflate(R.layout.left_drawer_footer, mDrawerList, false);
		mDrawerList.addHeaderView(header, null, true);
		mDrawerList.addFooterView(footer, null, true);
		//
		MDTitles = getResources().getStringArray(R.array.navigation_main_sections);
		TypedArray MDIcons = getResources().obtainTypedArray(R.array.navigation_main_sections_drawable_ids);
		icons = new ArrayList<Icons>();
		icons.add(new Icons(MDTitles[0], MDIcons.getResourceId(0, -1)));
		icons.add(new Icons(MDTitles[1], MDIcons.getResourceId(1, -2)));
		icons.add(new Icons(MDTitles[2], MDIcons.getResourceId(2, -3)));
		icons.add(new Icons(MDTitles[3], MDIcons.getResourceId(3, -4)));
		MDIcons.recycle();
		adapter = new ToolbarMenudrawerAdapter(getApplicationContext(), icons);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);
			}
		});
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu();
			}

			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				return super.onOptionsItemSelected(item);
			}
		};
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		selectItem(0);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		firstStart();
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int initLayout() {
		return R.layout.toolbarmenudrawer;
	}

	@Override
	protected void initListener() {
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		super.onPostCreate(savedInstanceState, persistentState);
		// 没执行，待咨询
	}

	/**
	 * @Description: 首次启动跳转到导航页
	 * @author (ljh) @date 2015-4-27 下午4:25:56
	 * @return void
	 */
	private void firstStart() {
		SharedPreferences first = PreferenceManager.getDefaultSharedPreferences(this);
		if (getIntent().getBooleanExtra("EXIT", false)) {
			super.finish();
		}
		if (!first.getBoolean("firstTime", false)) {
			Intent intent = new Intent(this, WelcomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			SharedPreferences.Editor editor = first.edit();
			editor.putBoolean("firstTime", true);
			editor.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.toolbarmenudrawer_menu, menu);
		menu.add(Menu.NONE, R.id.id_menu_update, 0, "检测新版本").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: onPrepareOptionsMenu
	 * </p>
	 * <p>
	 * Description: onPrepareOptions后需要刷新menu
	 * </p>
	 * <p>
	 * {@link BaseActivity#refreshOptionsMenu()}
	 * </p>
	 * 
	 * @param menu
	 * @return
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {// 屏蔽menu键
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
			case R.id.option1:
				Toast.makeText(this, "Option 1", Toast.LENGTH_SHORT).show();
				break;
			case R.id.option2:
				Toast.makeText(this, "Option 2", Toast.LENGTH_SHORT).show();
				break;
			case R.id.option3:
				Toast.makeText(this, "Option 3", Toast.LENGTH_SHORT).show();
				break;
			default:
		}
		return super.onOptionsItemSelected(item);
	}

	long exitTime = 0;

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerVisible(Gravity.START)) {
			mDrawerLayout.closeDrawer(Gravity.START);
			return;
		} else if (mFragmentManager.getBackStackEntryCount() != 0 ) {
			if(mCurrent != mainFragment)
				selectItem(0);// 返回键切换到 MainFragment
			else
				((MainFragment)mainFragment).setCurrentPager(0);
			return;
		} else {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				showToast("再点一次返回桌面");
				exitTime = System.currentTimeMillis();
			} else { // 两次点击后，实现home键效果
				Intent startMain = new Intent(Intent.ACTION_MAIN);
				startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startMain.addCategory(Intent.CATEGORY_HOME);
				startActivity(startMain);
			}
		}
	}

	/**
	 * @Description: 切换fragment
	 * @author (ljh) @date 2015-4-28 下午1:16:16
	 * @param mCurrent
	 * @param toFragment
	 * @return void
	 */
	private void switchFragment(BaseFragment toFragment) {
		if (mCurrent != toFragment) {
			if (toFragment.isAdded()) {
				mFTransaction.hide(mCurrent).show(toFragment);
			} else {
				if (null != mCurrent) {
					mFTransaction.hide(mCurrent);
				}
				mFTransaction.add(containerViewId, toFragment);
				mFTransaction.addToBackStack(null);
			}
			mCurrent = toFragment;
			hideInputMethod();
		}
	}

	private void selectItem(int position) {
		mFTransaction = mFragmentManager.beginTransaction();
		// .setCustomAnimations(enter, exit, popEnter, popExit); //添加fragment动画
		// material design
		Intent intent = new Intent(mContext, WebViewActivity.class);
		switch (position) {
			case 0:
				getSupportActionBar().setTitle(R.string.app_name);
				switchFragment(mainFragment);
				break;
			case 1:
				intent.putExtra("url", WebAPI.CONTRIBUTE_URL);
				startActivity(intent);
				return;
			case 2:
				intent.putExtra("url", WebAPI.FEED_BACK_URL);
				startActivity(intent);
				return;
			case 3:
				intent.putExtra("url", WebAPI.COPYRIGHT_URL);
				startActivity(intent);
				return;
			case 4:
				if (mainFragment.isHidden()) {
					getSupportActionBar().setTitle(R.string.app_name);
					switchFragment(mainFragment);
				}
				((MainFragment) mainFragment).setCurrentPager(3);
				break;
			case 5:
				getSupportActionBar().setTitle("关于");
				switchFragment(aboutFragment);
				break;
		}
		mFTransaction.commit();
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}

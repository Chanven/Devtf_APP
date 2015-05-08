package com.devtf_l.app.base;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import butterknife.ButterKnife;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.SuperToast.Animations;
import com.github.johnpersano.supertoasts.SuperToast.Background;
import com.github.johnpersano.supertoasts.SuperToast.Duration;
import com.github.johnpersano.supertoasts.SuperToast.TextSize;

/**
 * @desc 基类
 * @author ljh lijunhuayc@sina.com 2015-4-26
 */
public abstract class BaseActivity extends ActionBarActivity {
	protected Context mContext;
	protected DevtfApplication mAplication;
	protected LayoutInflater mInflater;
	protected MaterialDialog mDialog; // MaterialDialog
	protected RequestQueue mRequestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		android.os.Debug.startMethodTracing("Entertainment");
		mAplication = (DevtfApplication) getApplication();
		mContext = this;
		mInflater = getLayoutInflater();
		setContentView(initLayout());
		mRequestQueue = Volley.newRequestQueue(mContext);
		ButterKnife.inject(this);
		init();
		initListener();
		mAplication.addActivity(this);
	}

	protected abstract int initLayout(); // 布局

	protected abstract void init();

	protected abstract void initListener(); // 初始化监听

	protected SuperToast superToast;

	public void showToast(String text, int longint) {
		if (superToast == null) {
			superToast = new SuperToast(mContext);
			superToast.setBackground(Background.BLUE);
			superToast.setTextSize(TextSize.MEDIUM);
			superToast.setAnimations(Animations.FLYIN);
		}
		SuperToast.cancelAllSuperToasts();
		superToast.setDuration(longint);
		superToast.setText(text);
		superToast.show();
	}

	public void showToast(String text) {
		showToast(text, Duration.SHORT);
	}

	/**
	 * @Description: 创建一个基本的Dialog
	 * @author (ljh) @date 2015-4-27 下午3:27:33
	 * @return
	 * @return MaterialDialog
	 */
	public MaterialDialog createMaterialDialog() {
		if (null == mDialog) {
			mDialog = new MaterialDialog.Builder(mContext).build();
		}
		return mDialog;
	}

	/**
	 * @Description: 隐藏输入法
	 * @author (ljh) @date 2015-4-28 下午3:20:57
	 * @return void
	 */
	public void hideInputMethod() {
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm.isActive()) { // 为true表示输入法处于打开状态
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);// 关闭输入法
			}
		} catch (Exception e) {
		}
	}

	/**
	 * @Description: 刷新 option menu
	 * @author (ljh) @date 2015-5-5 下午2:10:09  
	 * @return void
	 */
	public void refreshOptionsMenu() {
		getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
	}
	
	/**
	 * 设置溢出菜单
	 * @deprecated
	 */
	protected void setOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
	
    public final Drawable getDrawable2(int id) {
        return getResources().getDrawable(id);
    }
	
	@Override
	protected void onDestroy() {
		mRequestQueue.cancelAll(null);
		super.onDestroy();
//		android.os.Debug.stopMethodTracing();
	}
}

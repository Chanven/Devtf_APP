package com.devtf_l.app.base;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;

import com.devtf_l.app.activity.ExceptionAlertActivity;
import com.devtf_l.app.activity.ToolbarMenudrawerActivity;
import com.devtf_l.app.crash.DevtfUncaughtExceptionHandler;
import com.devtf_l.app.util.Timber;

/**
 * @desc 自定义Application 做一些管理
 * @author ljh lijunhuayc@sina.com 2015-4-26
 */
public class DevtfApplication extends Application {
	List<BaseActivity> acList = null;

	@Override
	public void onCreate() {
		super.onCreate();
		DevtfUncaughtExceptionHandler.getInstance().init(getApplicationContext());
		acList = new LinkedList<BaseActivity>();
		Timber.plant(new Timber.DebugTree());
	}

	public void addActivity(BaseActivity mBaseActivity) {
		synchronized (acList) {
			//控制主界面在栈底，新建主界面activity并添加进acList之前须clear列表
			if(mBaseActivity.getClass() == ToolbarMenudrawerActivity.class){
				finishAll();
			}
			if(mBaseActivity.getClass() == ExceptionAlertActivity.class)
				return;	//异常处理界面采用singleInstance 模式，不做管理
			acList.add(mBaseActivity);
			Timber.i("当前activity数量：%s", acList.size());
		}
	}

	public void removeActivity(BaseActivity mBaseActivity) {
		synchronized (acList) {
			acList.remove(mBaseActivity);
		}
	}

	public void exitApp() {
		onTerminate();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		finishAll();
		System.exit(0);
	}

	public void finishAll() {
		for (Activity ac : acList) {
			ac.finish();
		}
		acList.clear();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}

	/**
	 * 利用以上两回调方法做一些必要的内存释放 1、OnLowMemory被回调时，已经没有后台进程；而onTrimMemory被回调时，还有后台进程。
	 * 2、OnLowMemory是在最后一个后台进程被杀时调用，一般情况是low memory killer
	 * 杀进程后触发；而OnTrimMemory的触发更频繁，每次计算进程优先级时，只要满足条件，都会触发。
	 * 3、通过一键清理后，OnLowMemory不会被触发，而OnTrimMemory会被触发一次。
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

}

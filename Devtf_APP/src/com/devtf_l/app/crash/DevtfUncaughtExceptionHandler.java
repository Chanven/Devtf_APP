package com.devtf_l.app.crash;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.devtf_l.app.activity.ExceptionAlertActivity;

/**
 * @desc 全局异常捕获器
 * @author ljh
 * lijunhuayc@sina.com 2015-4-26
 */
public class DevtfUncaughtExceptionHandler implements UncaughtExceptionHandler {
	private static DevtfUncaughtExceptionHandler crashHandler;// 程序只需要一个全局异常处理
	private Context mContext;
	private UncaughtExceptionHandler defaultExceptionHandler;
	private Map<String, String> infos = new HashMap<String, String>();// 用来存储设备信息和异常信息
	StringBuffer strBuffer = new StringBuffer();// 异常信息

	public static DevtfUncaughtExceptionHandler getInstance() {
		if (null == crashHandler) {
			synchronized (DevtfUncaughtExceptionHandler.class) {
				if (null == crashHandler) {
					crashHandler = new DevtfUncaughtExceptionHandler();
				}
			}
		}
		return crashHandler;
	}

	/**
	 * @Description: (设置默认异常处理)
	 * @author (ljh) @date 2015-1-30 上午10:28:34
	 * @return void
	 */
	public void init(Context appContext) {
		mContext = appContext;
		defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// 未处理则让系统默认处理
		if (!hadnleException(ex) && null != defaultExceptionHandler) {
			defaultExceptionHandler.uncaughtException(thread, ex);
		} else {
			Intent intent = new Intent(mContext, ExceptionAlertActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("causeInfo", strBuffer.toString());
			mContext.startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);// 非0表示非正常退出
		}
	}

	/**
	 * @Description: (自定义异常处理)
	 * @author (ljh) @date 2015-1-30 下午3:53:38
	 * @param ex
	 * @return boolean false表示未处理异常
	 */
	private boolean hadnleException(Throwable ex) {
		if (null == ex)
			return true;
//		mContext.getContentResolver().delete(ProviderConstants.RequestColumns.PROVIDER_Uri_REQUEST, "", null);
//		mContext.getContentResolver().delete(ProviderConstants.ResultColumns.PROVIDER_Uri_RESULT, "", null);
		collectDeviceInfo();
		String filePath = saveCrashInfo2File(ex);
		// TODO ...文件上传在哪里
		return true;
	}

	private void collectDeviceInfo() {
		try {
			PackageManager pm = mContext.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
			if (null != pi) {
				String versionName = pi.versionName == null ? "null" : pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
//			LogUtils.e("an error occured when collect package info", e);
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
			} catch (Exception e) {
//				LogUtils.e("an error occured when collect crash info", e);
			}
		}
	}

	private String saveCrashInfo2File(Throwable ex) {
		for (Entry<String, String> entry : infos.entrySet()) {
			strBuffer.append(entry.getKey() + " = " + entry.getValue() + "\n");
		}
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (null != cause) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
//		printWriter.close();
//		strBuffer.append(writer.toString());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_sss", Locale.CHINA);
//		String ecptPath = Constants.FILE_LOG_PATH + "/crash_" + sdf.format(new Date()) + ".txt";
//		File ecptFile = new File(ecptPath);
//		if (!ecptFile.exists()) {
//			ecptFile.getParentFile().mkdirs();
//		}
//		try {
//			DataOutputStream dos = new DataOutputStream(new FileOutputStream(ecptFile));
//			dos.writeChars(strBuffer.toString());
//			dos.close();
//		} catch (Exception e) {
//			LogUtils.e("an error occured while writing file...", e);
//		}
//		return ecptPath;
		return "";
	}
}

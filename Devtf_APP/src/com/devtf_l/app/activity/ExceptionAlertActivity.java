package com.devtf_l.app.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;

import com.devtf_l.app.R;
import com.devtf_l.app.base.BaseActivity;

public class ExceptionAlertActivity extends BaseActivity {
	@InjectView(R.id.checkName)
	CheckBox checkName;
	@InjectView(R.id.causeInfoTV)
	TextView causeInfoTV;
	String causeInfo;// 异常信息日志
	AlertDialog pd;
	Handler mHandler = new Handler();

	@Override
	public int initLayout() {
		return R.layout.activity_ecpt_layout;
	}

	@Override
	protected void init() {
		causeInfo = getIntent().getStringExtra("causeInfo");
		causeInfoTV.setText(causeInfo);
		pd = new ProgressDialog(mContext);
	}

	@Override
	public void initListener() {
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		init();
	}

	@OnClick({R.id.checkNameLayout, R.id.reStartBt, R.id.backBt})
	public void allClick(View view) {
		switch (view.getId()) {
			case R.id.checkNameLayout:
				checkName.toggle();
				break;
			case R.id.backBt:
				mAplication.exitApp();
				break;
			case R.id.reStartBt:
				if (checkName.isChecked()) {
					uploadEcptInfo();
				} else
					reStartApp();
				break;
		}
	}

	/**
	 * @Description: TODO(上传日志)
	 * @author (ljh) @date 2015-1-30 上午11:26:00
	 * @param causeInfo
	 * @return void
	 */
	private void uploadEcptInfo() {
		pd.setMessage("日志上传...");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		new Thread() {
			public void run() {
				try {
					// String urlStr = WebAPI.UpdateCauseLog_URL + "?dto=";//
					// 日志上传接口
					// String joStr = HttpRequest.getResponse(urlStr);
					// JSONObject jo = new JSONObject(joStr);
					// if ("1".equals(jo.getString("Status"))) {
					// pd.cancel();
					// showToast("日志上传成功");
					// reStartApp();
					// } else {
					// throw new Exception();
					// }
				} catch (Exception e) {
					// mHandler.post(new Runnable() {
					// @Override
					// public void run() {
					// pd.cancel();
					// showToast("日志上传失败");
					// }
					// });
				}
			};
		}.start();
	}

	/**
	 * @Description: TODO(重启App)
	 * @author (ljh) @date 2015-1-30 上午11:25:50
	 * @return void
	 */
	private void reStartApp() {
		mAplication.finishAll();
		Intent intent = new Intent(mContext, ToolbarMenudrawerActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.bottom_bar_in, R.anim.bottom_bar_in);
	}
}

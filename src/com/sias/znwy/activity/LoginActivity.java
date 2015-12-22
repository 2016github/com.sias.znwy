package com.sias.znwy.activity;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityJump;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.Util.WatingDialog;
import com.sias.znwy.WebUtil.LoginUtil;
import com.sias.znwy.demo.Tusbkey;
import com.sias.znwy.demo.WyAutoupdate;
import com.sias.znwy.web.util.OnResultListener;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登录
 * 
 * @author Administrator
 * 
 */
public class LoginActivity extends Activity implements OnClickListener {
	protected static final String TAG = "login";
	private Button btn_login;
	private EditText edit_name;
	private EditText edit_password;
	protected static RequestQueue mRequestQueue;
	private SharedPreferences sp;
	private WatingDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mRequestQueue = Volley.newRequestQueue(LoginActivity.this);
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		initView();
	}

	private void initView() {
		dialog = new WatingDialog(LoginActivity.this);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_password = (EditText) findViewById(R.id.edit_password);
		if (sp != null) {
			edit_name.setText(sp.getString("name", ""));
			edit_password.setText(sp.getString("password", ""));
		}
	}

	/**
	 * 获取手机的唯一标识码
	 */
	public String getDeviceId() {
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			login();
			break;

		default:
			break;
		}
	}

	/**
	 * 登录
	 */
	private void login() {
		if (TextUtils.isEmpty(edit_name.getText().toString())) {
			Toast.makeText(LoginActivity.this, "用户名不能为空", 0).show();
			return;
		}
		if (TextUtils.isEmpty(edit_password.getText().toString())) {
			Toast.makeText(LoginActivity.this, "密码不能为空", 0).show();
			return;
		}
		dialog.ShowWatingDialog();
		new LoginUtil().Login("zcdl", edit_name.getText().toString(),
				edit_password.getText().toString(), getDeviceId(), "aqyz",
				new OnResultListener() {

					@Override
					public void onResult(boolean isSuccess, int errorCode,
							Object obj) {
						if (isSuccess) {
							dialog.diaologdimiss();
							final SharedPreferences sharedPreferences = getSharedPreferences(
									"userInfo", Context.MODE_PRIVATE);
							Editor editor = sharedPreferences.edit();
							editor.putString("name", edit_name.getText()
									.toString());
							editor.putString("password", edit_password
									.getText().toString());
							editor.commit();// 提交修改
							Tusbkey tusbkey = ((JSONObject) obj).getObject(
									"t_usbkey", Tusbkey.class);
							WyAutoupdate wyautoupdate = ((JSONObject) obj)
									.getObject("wy_autoupdate",
											WyAutoupdate.class);
							UserInfo.setYhdh(tusbkey.getPin());
							UserInfo.setDeviceId(getDeviceId());
							Bundle bun = new Bundle();
							bun.putSerializable("wyautoupdate", wyautoupdate);
							bun.putSerializable("tusbkey", tusbkey);
							ActivityJump.JumpActivity(LoginActivity.this,
									HomeActivity.class, bun);

						} else {
							dialog.diaologdimiss();
							Toast.makeText(LoginActivity.this, (String)obj, 0)
									.show();
						}
					}
				});

	}
}

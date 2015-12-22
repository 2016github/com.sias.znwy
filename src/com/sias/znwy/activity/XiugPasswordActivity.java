package com.sias.znwy.activity;
import com.sias.znwy.R;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.WebUtil.ResetPassword;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class XiugPasswordActivity extends Activity implements OnClickListener {
	private String yhdh;
	private TextView text_return;
	private Button btn_sure;
	private EditText edit_jiupassword;
	private EditText edit_xinpassword;
	private EditText edit_xinpasswordto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiugpassword);
		initView();
	}
	private void initView() {
		yhdh=getIntent().getExtras().getString("yhdh");
		text_return=(TextView) findViewById(R.id.text_return);
		btn_sure=(Button) findViewById(R.id.btn_sure);
		text_return.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
		edit_jiupassword=(EditText) findViewById(R.id.edit_jiuPassword);
		edit_xinpassword=(EditText) findViewById(R.id.edit_xinPassword);
		edit_xinpasswordto=(EditText) findViewById(R.id.edit_xinPasswordto);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_return:
			finish();
			break;
		case R.id.btn_sure:
			ResetPassword();
			break;
		default:
			break;
		}
	}
	/**
	 * 重置密码
	 */
	private void ResetPassword() {
		if (TextUtils.isEmpty(edit_jiupassword.getText().toString())) {
			Toast.makeText(XiugPasswordActivity.this, "原始密码为空", 0).show();
			return;
		}
		if (!edit_xinpassword.getText().toString().equals(edit_xinpasswordto.getText().toString())) {
			Toast.makeText(XiugPasswordActivity.this, "两次密码输入不一致", 0).show();
			return;
		}
		new ResetPassword().Resetpassword("mmxg", yhdh, edit_jiupassword.getText().toString(), edit_xinpassword.getText().toString(), getDeviceId(), "aqyz", new OnResultListener() {
			
			@Override
			public void onResult(boolean isSuccess, int errorCode, Object obj) {
				if (isSuccess) {
					Toast.makeText(XiugPasswordActivity.this, "重置成功", 0).show();
				}else {
					Toast.makeText(XiugPasswordActivity.this, "重置密码失败", 0).show();
				}
			}
		});
	}
	/**
	 * 获取手机的唯一标识码
	 */
	public String getDeviceId() {
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;
	}
}

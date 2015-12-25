package com.sias.znwy.activity;

import com.sias.znwy.R;
import com.sias.znwy.Util.DialogUtil;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.WebUtil.QueryXchb;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 现场汇报
 * 
 * @author Administrator
 * 
 */
public class XCHBActivity extends Activity implements OnClickListener {
	private EditText edit_data;
	private Button btn_query;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xchb);
		initView();
	}

	private void initView() {
		edit_data = (EditText) findViewById(R.id.edit_data);
		btn_query = (Button) findViewById(R.id.btn_query);
		btn_query.setOnClickListener(this);
		queryXchb();
	}

	/**
	 * 查询现场汇报
	 */
	private void queryXchb() {
		new QueryXchb().queryXchb("xchbcx", UserInfo.getYhdh(),
				UserInfo.getDeviceId(), "aqyz", edit_data.getText().toString(),
				new OnResultListener() {
					
					@Override
					public void onResult(boolean isSuccess, int errorCode, Object obj) {
						if (isSuccess) {
							
						}else {
							new DialogUtil(XCHBActivity.this, obj);
						}
					}
				});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_query:
			queryXchb();
			break;
		default:
			break;
		}
	}
}

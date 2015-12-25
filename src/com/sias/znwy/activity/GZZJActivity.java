package com.sias.znwy.activity;

import com.sias.znwy.R;
import com.sias.znwy.Util.DialogUtil;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.WebUtil.QueryGzrb;
import com.sias.znwy.web.util.OnResultListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 工作总结
 * 
 * @author Administrator
 * 
 */
public class GZZJActivity extends Activity implements OnClickListener {
	private EditText edit_data;
	private Button btn_query;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gzzj);
		initView();

	}

	private void initView() {
		edit_data = (EditText) findViewById(R.id.edit_data);
		btn_query = (Button) findViewById(R.id.btn_query);
		btn_query.setOnClickListener(this);
		findViewById(R.id.activity_title_rightText).setOnClickListener(this);
		queryGzrb();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_query:
			queryGzrb();
			break;
		case R.id.activity_title_rightText:

			break;

		default:
			break;
		}
	}

	/**
	 * 查询工作日结
	 */
	private void queryGzrb() {
		
		new QueryGzrb().queryGzrb("gzrbcx", UserInfo.getYhdh(), UserInfo.getDeviceId(), "aqyz", edit_data.getText().toString(), new OnResultListener() {
			
			@Override
			public void onResult(boolean isSuccess, int errorCode, Object obj) {
				if (isSuccess) {
					
				}else {
					new DialogUtil(GZZJActivity.this, obj);
				}
			}
		});
		
	}
}

package com.sias.znwy.activity;
import com.sias.znwy.R;
import com.sias.znwy.Util.DialogUtil;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.WebUtil.QueryTZ;
import com.sias.znwy.web.util.OnResultListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 通知公告
 * 
 * @author Administrator
 * 
 */
public class TZTGActivity extends Activity implements OnClickListener {
	private TextView text_return;
	private EditText edit_data;
	private Button btn_query;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tztg);
		initView();
	}

	public void initView() {
		text_return = (TextView) findViewById(R.id.text_return);
		edit_data = (EditText) findViewById(R.id.edit_data);
		btn_query = (Button) findViewById(R.id.btn_query);
		text_return.setOnClickListener(this);
		btn_query.setOnClickListener(this);
		QueryTZ();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_return:
			finish();
			break;
		case R.id.btn_query:
			QueryTZ();
			break;
		default:
			break;
		}
	}
	/**
	 * 根据日期查询通知通告
	 */
	private void QueryTZ() {
		new QueryTZ().QueryTz("tztgcx", UserInfo.getYhdh(), UserInfo.getDeviceId(), "aqyz", edit_data.getText().toString(), new OnResultListener() {
			@Override
			public void onResult(boolean isSuccess, int errorCode, Object obj) {
				if (isSuccess) {
					//成功
					
				}else {
					new DialogUtil(TZTGActivity.this, obj);
				}
			}
		});
	}
}
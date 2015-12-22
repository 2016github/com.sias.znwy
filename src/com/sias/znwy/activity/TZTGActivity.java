package com.sias.znwy.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.sias.znwy.R;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.WebUtil.QueryTZ;
import com.sias.znwy.web.util.OnResultListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
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
	private Calendar c = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tztg);
		initView();
	}

	/**
	 * 获取当前日期
	 */
	public String getNowData() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	public void initView() {
		text_return = (TextView) findViewById(R.id.text_return);
		edit_data = (EditText) findViewById(R.id.edit_data);
		edit_data.setText(getNowData());
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edit_data.getWindowToken(), 0);
		edit_data.setInputType(0);
		btn_query = (Button) findViewById(R.id.btn_query);
		text_return.setOnClickListener(this);
		edit_data.setOnClickListener(this);
		btn_query.setOnClickListener(this);
		QueryTZ();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_return:
			finish();
			break;
		case R.id.edit_data:
			showDialog(0);
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
				}else {
					AlertDialog dialog= new AlertDialog.Builder(TZTGActivity.this).setTitle("提示").setMessage((String)obj).setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).create();
					dialog.show();
				}
			}
		});
	}
	/**
	 * 日期选择对话框
	 */
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		if (id == 0) {
			c = Calendar.getInstance();
			dialog = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
						public void onDateSet(DatePicker dp, int year,
								int month, int dayOfMonth) {
							edit_data.setText(year + "-" + (month + 1) + "-" 
									+ dayOfMonth);
						}
					}, 
					c.get(Calendar.YEAR), // 传入年份
					c.get(Calendar.MONTH), // 传入月份
					c.get(Calendar.DAY_OF_MONTH) // 传入天数
			);
		}
		return dialog;
	}
}

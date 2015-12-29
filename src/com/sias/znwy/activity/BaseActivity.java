package com.sias.znwy.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitle;
import com.sias.znwy.Util.RequestTools;
import com.sias.znwy.adapter.AbstractBaseAdapter;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.WebParam;

public abstract class BaseActivity extends Activity implements OnClickListener {
	public ActivityTitle title;
	public ListView dataListView;
	public Button search;
	public TextView searChDate;
	public LinearLayout quaryLayout;
	private Calendar c = null;
	public List datas = new ArrayList();
	public AbstractBaseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		title = (ActivityTitle) findViewById(R.id.activityTitle1);
		title.titleRightText.setOnClickListener(this);
		dataListView = (ListView) findViewById(R.id.dateList);
		searChDate = (TextView) findViewById(R.id.edit_data);
		search = (Button) findViewById(R.id.btn_query);
		quaryLayout = (LinearLayout) findViewById(R.id.quryLauout);
		search.setOnClickListener(this);
		searChDate.setOnClickListener(this);
		searChDate.setText(getNowData());
		adapter = getAdapter();
		setName();
		setRightTextVisiable();
		setRightText();
		getData();
		dataListView.setAdapter(adapter);
		dataListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				itemClick(position);
			}
		});
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

	private void getData() {

		new RequestTools().request(getParam(), new OnResultListener() {

			@Override
			public void onResult(boolean isSuccess, int errorCode, Object obj) {
				if (isSuccess) {
					System.out.println("相应结果:" + obj.toString());
					processDatas((JSONObject) obj);
					adapter.setDatas(datas);

				} else {
					Toast.makeText(BaseActivity.this, obj.toString(), Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_query:

			break;
		case R.id.edit_data:
			showDialog(0);
			break;
		case R.id.activity_title_rightText:
			rightTextclick();
			break;
		}

	}

	public abstract WebParam getParam();

	public abstract void setName();

	public abstract void setRightTextVisiable();

	public abstract void setRightText();

	public abstract AbstractBaseAdapter getAdapter();

	public abstract void rightTextclick();

	public abstract void processDatas(JSONObject json);

	public abstract void itemClick(int position);

	/**
	 * 日期选择对话框
	 */
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		if (id == 0) {
			c = Calendar.getInstance();
			dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
					searChDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
				}
			}, c.get(Calendar.YEAR), // 传入年份
					c.get(Calendar.MONTH), // 传入月份
					c.get(Calendar.DAY_OF_MONTH) // 传入天数
			);
		}
		return dialog;
	}
}

package com.sias.znwy.Util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sias.znwy.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
public class ActivityDateView extends LinearLayout {
	private EditText edit_data;
	private Calendar c = null;
	public ActivityDateView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ActivityDateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActivityTitleView);
		a.recycle();
	}
	public ActivityDateView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(final Context context) {
		View view = View.inflate(context, R.layout.activity_dataview, this);
		edit_data=(EditText) view.findViewById(R.id.edit_data);
		edit_data.setText(getNowData());
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edit_data.getWindowToken(), 0);
		edit_data.setInputType(0);
		edit_data.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(context);
			}
		});
	}
	protected void showDialog(Context context) {
			c = Calendar.getInstance();
			Dialog	dialog = new DatePickerDialog(context,
					new DatePickerDialog.OnDateSetListener() {
						public void onDateSet(DatePicker dp, int year,
								int month, int dayOfMonth) {
							edit_data.setText(year + "-" + (month + 1) + "-" 
									+ dayOfMonth);
						}
					}, 
					c.get(Calendar.YEAR), // 传入年份
					c.get(Calendar.MONTH), // 传入月份
					c.get(Calendar.DAY_OF_MONTH)//传入天数
					);
		dialog.show();
		 
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
	
}

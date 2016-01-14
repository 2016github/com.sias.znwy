package com.sias.znwy.activity;

import java.util.ArrayList;
import java.util.List;

import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitleView;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.adapter.WriteWorkPlanAdapter;
import com.sias.znwy.demo.WriteWorkPlanBean;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class XJHActivity extends Activity implements OnClickListener {
	private String[] weathers = { "晴", "多云", "阴", "小雨", "中雨", "大雨", "小雪", "中雪", "大雪" };
	private String[] winds = { "无风", "微风", "大风" };
	private Spinner mWeather, mWind;
	private TextView mDate;
	private ActivityTitleView mTitle;
	private ArrayAdapter adapter, adapter2;
	private Dialog longinDialog;
	private EditText unInstructionPlanMemo;
	private EditText unInstructionPlanLiang;
	private List<WriteWorkPlanBean> mdatas = new ArrayList<WriteWorkPlanBean>();
	private WriteWorkPlanAdapter planAdapter;
	private ListView plantListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xjh);
		initView();
	}

	private void initView() {
		mWeather = (Spinner) findViewById(R.id.jhWeather);
		mWind = (Spinner) findViewById(R.id.jhWindPower);
		mTitle = (ActivityTitleView) findViewById(R.id.activityXjhTitle);
		mDate = (TextView) findViewById(R.id.jhDate);
		plantListView = (ListView) findViewById(R.id.JHListview);
		findViewById(R.id.jhMandatoryPlan).setOnClickListener(this);
		findViewById(R.id.jhNoMandatoryPlan).setOnClickListener(this);
		mDate.setText(AppKit.getNowDataAndTime());
		planAdapter = new WriteWorkPlanAdapter(XJHActivity.this);
		planAdapter.setDatas(mdatas);
		plantListView.setAdapter(planAdapter);
		mTitle.titleRightText.setOnClickListener(this);
		// 将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weathers);

		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		mWeather.setAdapter(adapter);

		// 添加事件Spinner事件监听
		mWeather.setOnItemSelectedListener(new SpinnerSelectedListener());

		// 设置默认值
		mWeather.setVisibility(View.VISIBLE);
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, winds);

		// 设置下拉列表的风格
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		mWind.setAdapter(adapter2);

		// 添加事件Spinner事件监听
		mWind.setOnItemSelectedListener(new SpinnerSelectedListener());

		// 设置默认值
		mWind.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_title_rightText:
			// 提交
			break;

		case R.id.jhMandatoryPlan:
			// 指令计划
			MandatoryPlan(XJHActivity.this);
			break;
		case R.id.jhNoMandatoryPlan:
			// 非指令计划
			NoMandatoryPlanDialog(XJHActivity.this);
			break;
		}

	}

	/**
	 * 指令计划对话框
	 * 
	 * @param xjhActivity
	 * 
	 * @author lkx Created at 2016-1-14 下午5:19:14
	 */
	private void MandatoryPlan(Context context) {
		// 动态加载布局生成View对象
		LayoutInflater layoutInflater = LayoutInflater.from(context);

		View longinDialogView = layoutInflater.inflate(R.layout.dialog_instruction_plan, null);

		longinDialog = new AlertDialog.Builder(context, R.style.CustomDialog).create();
		longinDialog.show();
		longinDialog.getWindow().setContentView(longinDialogView);

		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		android.view.WindowManager.LayoutParams p = longinDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
		// p.height = (int) (d.getHeight() * 0.3); //高度设置为屏幕的0.3
		p.width = (int) (d.getWidth()); // 宽度设置为全屏
		longinDialog.getWindow().setAttributes(p); // 设置生效

	}

	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	@SuppressLint("InflateParams")
	public void NoMandatoryPlanDialog(Context context) {

		// 动态加载布局生成View对象
		LayoutInflater layoutInflater = LayoutInflater.from(context);

		View noMandatoryPlanView = layoutInflater.inflate(R.layout.dialog_un_instruction_plan, null);
		unInstructionPlanMemo = (EditText) noMandatoryPlanView.findViewById(R.id.writGzMemo);
		unInstructionPlanLiang = (EditText) noMandatoryPlanView.findViewById(R.id.writGzGaiLiang);
		noMandatoryPlanView.findViewById(R.id.dialogWritEnsure).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				WriteWorkPlanBean bean = new WriteWorkPlanBean();
				bean.setMemo(unInstructionPlanMemo.getText().toString().trim());
				bean.setType(0);
				mdatas.add(bean);
				planAdapter.notifyDataSetChanged();
				longinDialog.dismiss();
			}
		});

		longinDialog = new AlertDialog.Builder(context, R.style.CustomDialog).create();
		longinDialog.show();
		longinDialog.getWindow().setContentView(noMandatoryPlanView);

		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		android.view.WindowManager.LayoutParams p = longinDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
		// p.height = (int) (d.getHeight() * 0.3); //高度设置为屏幕的0.3
		p.width = (int) (d.getWidth()); // 宽度设置为全屏
		longinDialog.getWindow().setAttributes(p); // 设置生效
	}
}

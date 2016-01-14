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
	private String[] weathers = { "��", "����", "��", "С��", "����", "����", "Сѩ", "��ѩ", "��ѩ" };
	private String[] winds = { "�޷�", "΢��", "���" };
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
		// ����ѡ������ArrayAdapter��������
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weathers);

		// ���������б�ķ��
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ��ӵ�spinner��
		mWeather.setAdapter(adapter);

		// ����¼�Spinner�¼�����
		mWeather.setOnItemSelectedListener(new SpinnerSelectedListener());

		// ����Ĭ��ֵ
		mWeather.setVisibility(View.VISIBLE);
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, winds);

		// ���������б�ķ��
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ��ӵ�spinner��
		mWind.setAdapter(adapter2);

		// ����¼�Spinner�¼�����
		mWind.setOnItemSelectedListener(new SpinnerSelectedListener());

		// ����Ĭ��ֵ
		mWind.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_title_rightText:
			// �ύ
			break;

		case R.id.jhMandatoryPlan:
			// ָ��ƻ�
			MandatoryPlan(XJHActivity.this);
			break;
		case R.id.jhNoMandatoryPlan:
			// ��ָ��ƻ�
			NoMandatoryPlanDialog(XJHActivity.this);
			break;
		}

	}

	/**
	 * ָ��ƻ��Ի���
	 * 
	 * @param xjhActivity
	 * 
	 * @author lkx Created at 2016-1-14 ����5:19:14
	 */
	private void MandatoryPlan(Context context) {
		// ��̬���ز�������View����
		LayoutInflater layoutInflater = LayoutInflater.from(context);

		View longinDialogView = layoutInflater.inflate(R.layout.dialog_instruction_plan, null);

		longinDialog = new AlertDialog.Builder(context, R.style.CustomDialog).create();
		longinDialog.show();
		longinDialog.getWindow().setContentView(longinDialogView);

		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		android.view.WindowManager.LayoutParams p = longinDialog.getWindow().getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		// p.height = (int) (d.getHeight() * 0.3); //�߶�����Ϊ��Ļ��0.3
		p.width = (int) (d.getWidth()); // �������Ϊȫ��
		longinDialog.getWindow().setAttributes(p); // ������Ч

	}

	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	@SuppressLint("InflateParams")
	public void NoMandatoryPlanDialog(Context context) {

		// ��̬���ز�������View����
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
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		android.view.WindowManager.LayoutParams p = longinDialog.getWindow().getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		// p.height = (int) (d.getHeight() * 0.3); //�߶�����Ϊ��Ļ��0.3
		p.width = (int) (d.getWidth()); // �������Ϊȫ��
		longinDialog.getWindow().setAttributes(p); // ������Ч
	}
}

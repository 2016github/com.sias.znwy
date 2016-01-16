package com.sias.znwy.activity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitleView;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.adapter.WriteWorkPlanAdapter;
import com.sias.znwy.demo.WriteWorkPlanBean;
import com.sias.znwy.web.util.WebParam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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
	private EditText unInstructionPlanMemo;
	private EditText unInstructionPlanLiang;
	private List<WriteWorkPlanBean> mdatas = new ArrayList<WriteWorkPlanBean>();
	private WriteWorkPlanAdapter planAdapter;
	private ListView plantListView;
	private String weater, wind;
	private JSONArray array;
	private WebParam param;
	private AlertDialog mandatoryPlanDialog;
	private AlertDialog unInstructionPlanDialog;
	private EditText mandatoryPlanMemo;
	private EditText mandatoryPlanLiang;
	private EditText mandatoryPlanZH;
	private EditText mandatoryPlanMS;
	private EditText mandatoryPlanCDH;
	private EditText mandatoryPlanSXX;

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
		array = new JSONArray();
		plantListView.setAdapter(planAdapter);
		mTitle.titleRightText.setOnClickListener(this);
		// ����ѡ������ArrayAdapter��������
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weathers);

		// ���������б�ķ��
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ��ӵ�spinner��
		mWeather.setAdapter(adapter);

		// ����¼�Spinner�¼�����
		mWeather.setOnItemSelectedListener(new SpinnerSelectedListener(mWeather));

		// ����Ĭ��ֵ
		mWeather.setVisibility(View.VISIBLE);
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, winds);

		// ���������б�ķ��
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ��ӵ�spinner��
		mWind.setAdapter(adapter2);

		// ����¼�Spinner�¼�����
		mWind.setOnItemSelectedListener(new SpinnerSelectedListener(mWind));

		// ����Ĭ��ֵ
		mWind.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_title_rightText:
			// �ύ
			param = WebParam.create();
			param.put("jklx", "gzjhxr");
			param.put("yhdh", UserInfo.getYhdh());
			param.put("sjbs", UserInfo.getDeviceId());
			param.put("aqyz", "aqyz");
			if (array != null && array.size() > 0) {
				param.put("wy_gzjhmx", array.toJSONString());
			}

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

		View mandatoryPlanView = layoutInflater.inflate(R.layout.dialog_instruction_plan, null);

		mandatoryPlanDialog = new AlertDialog.Builder(context, R.style.CustomDialog).create();
		mandatoryPlanDialog.show();
		mandatoryPlanDialog.getWindow().setContentView(mandatoryPlanView);
		mandatoryPlanMemo = (EditText) mandatoryPlanView.findViewById(R.id.writGzMemo);
		mandatoryPlanLiang = (EditText) mandatoryPlanView.findViewById(R.id.writGzGaiLiang);
		mandatoryPlanZH = (EditText) mandatoryPlanView.findViewById(R.id.writGzZhuanghao);
		mandatoryPlanMS = (EditText) mandatoryPlanView.findViewById(R.id.writGzMIshu);
		mandatoryPlanCDH = (EditText) mandatoryPlanView.findViewById(R.id.writCDH);
		mandatoryPlanSXX = (EditText) mandatoryPlanView.findViewById(R.id.writShangXiaXing);
		mandatoryPlanView.findViewById(R.id.dialogWritEnsure).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				JSONObject obj = new JSONObject();
				WriteWorkPlanBean bean = new WriteWorkPlanBean();
				bean.setMemo(mandatoryPlanMemo.getText().toString().trim());
				if (!TextUtils.isEmpty(mandatoryPlanMemo.getText().toString().trim())) {
					obj.put("gzgy", mandatoryPlanMemo.getText().toString().trim());
				}
				if (!TextUtils.isEmpty(mandatoryPlanLiang.getText().toString().trim())) {
					obj.put("gzgl", mandatoryPlanLiang.getText().toString().trim());
				}
				if (!TextUtils.isEmpty(obj.toJSONString())) {
					array.add(obj);
				}
				bean.setType(1);
				mdatas.add(bean);
				planAdapter.notifyDataSetChanged();
				mandatoryPlanDialog.dismiss();
			}
		});
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		android.view.WindowManager.LayoutParams p = mandatoryPlanDialog.getWindow().getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		// p.height = (int) (d.getHeight() * 0.3); //�߶�����Ϊ��Ļ��0.3
		p.width = (int) (d.getWidth()); // �������Ϊȫ��
		mandatoryPlanDialog.getWindow().setAttributes(p); // ������Ч

	}

	class SpinnerSelectedListener implements OnItemSelectedListener {
		private View v;

		SpinnerSelectedListener() {
		}

		SpinnerSelectedListener(Spinner v) {
			this.v = v;
		}

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			if (v == mWeather) {
				weater = weathers[arg2];
			} else {
				wind = winds[arg2];
			}

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
				JSONObject obj = new JSONObject();
				WriteWorkPlanBean bean = new WriteWorkPlanBean();
				bean.setMemo(unInstructionPlanMemo.getText().toString().trim());
				if (!TextUtils.isEmpty(unInstructionPlanMemo.getText().toString().trim())) {
					obj.put("gzgy", unInstructionPlanMemo.getText().toString().trim());
				}
				if (!TextUtils.isEmpty(unInstructionPlanLiang.getText().toString().trim())) {
					obj.put("gzgl", unInstructionPlanLiang.getText().toString().trim());
				}
				if (!TextUtils.isEmpty(obj.toJSONString())) {
					array.add(obj);
				}
				bean.setType(0);
				mdatas.add(bean);
				planAdapter.notifyDataSetChanged();
				unInstructionPlanDialog.dismiss();
			}
		});

		unInstructionPlanDialog = new AlertDialog.Builder(context, R.style.CustomDialog).create();
		unInstructionPlanDialog.show();
		unInstructionPlanDialog.getWindow().setContentView(noMandatoryPlanView);

		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		android.view.WindowManager.LayoutParams p = unInstructionPlanDialog.getWindow().getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		// p.height = (int) (d.getHeight() * 0.3); //�߶�����Ϊ��Ļ��0.3
		p.width = (int) (d.getWidth()); // �������Ϊȫ��
		unInstructionPlanDialog.getWindow().setAttributes(p); // ������Ч
	}
}

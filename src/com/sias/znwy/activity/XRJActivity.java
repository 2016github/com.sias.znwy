package com.sias.znwy.activity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.sias.znwy.MyApplication;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitle;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.Util.RequestTools;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.demo.WriteWorkPlanBean;
import com.sias.znwy.service.LocationService;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.WebParam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * д�ս����
 * 
 */
public class XRJActivity extends Activity implements OnClickListener {
	private ActivityTitle title;
	private AlertDialog addRecordDialog;
	private LocationService locationService;
	private TextView locationAssress;
	private double zbx;
	private double zby;
	private WebParam param;
	private EditText managerName, addRecordSXX, addRecordZH, addRecordMS, addRecordCDH, addRecordJLDW, addRecordGCSL, addRecordSGQX,
			addRecordStareTime, addRecordEndTime, addRecordWorkTime, addRecordJLXM, addRecordNote;
	private JSONArray jsonArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xrj);

		intiView();
	}

	private void intiView() {
		title = (ActivityTitle) findViewById(R.id.xrjActivityTitle);
		title.setItemName("��д�����ս�");
		title.setRightNameVisiable(View.VISIBLE);
		title.setItemRightName("�ύ");
		title.titleRightText.setOnClickListener(this);
		locationAssress = (TextView) findViewById(R.id.locationAddress);
		managerName = (EditText) findViewById(R.id.xrj_manegerName);
		findViewById(R.id.locationBtn).setOnClickListener(this);
		findViewById(R.id.addRecord).setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_title_rightText:
			// �ύ�ս�
			param = WebParam.create();
			param.addParam("jklx", "jlxcxr");
			param.addParam("yhdh", UserInfo.getYhdh());
			param.addParam("sjbs", UserInfo.getDeviceId());
			param.addParam("aqyz", "aqyz");
			param.addParam("sbsj", AppKit.getNowData());
			commitRj();
			break;

		case R.id.locationBtn:
			// ���¶�λ
			locationService.start();// ��λSDK
			break;
		case R.id.addRecord:
			// ��Ӽ�¼
			addRecordDialog(XRJActivity.this);
			break;

		}

	}

	/**
	 * �ύ�ս�
	 * 
	 * 
	 */
	private void commitRj() {
		if (!TextUtils.isEmpty(managerName.getText().toString().trim()))
			param.addParam("jlgcs", managerName.getText().toString().trim());

		if (!TextUtils.isEmpty(String.valueOf(zbx)))
			param.addParam("zbx", String.valueOf(zbx));

		if (!TextUtils.isEmpty(String.valueOf(zby)))
			param.addParam("zby", String.valueOf(zby));
		if (jsonArray != null || jsonArray.size() > 0) {
			param.addParam("wy_gzrbmx", jsonArray.toJSONString());
		}
		new RequestTools().request(param, new OnResultListener() {

			@Override
			public void onResult(boolean isSuccess, int errorCode, Object obj) {
				if (isSuccess) {

				} else {
					Toast.makeText(XRJActivity.this, obj.toString(), Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		locationService = MyApplication.getContext().locationService;
		locationService.registerListener(mListener);
		locationService.setLocationOption(locationService.getDefaultLocationClientOption());
		locationService.start();// ��λSDK
	}

	@Override
	protected void onStop() {
		locationService.unregisterListener(mListener); // ע��������
		locationService.stop(); // ֹͣ��λ����
		super.onStop();
	}

	public void addRecordDialog(Context context) {

		// ��̬���ز�������View����
		LayoutInflater layoutInflater = LayoutInflater.from(context);

		View addRecordView = layoutInflater.inflate(R.layout.dialog_xrj_addrecor, null);
		addRecordZH = (EditText) addRecordView.findViewById(R.id.edt_stakeNumber);
		addRecordMS = (EditText) addRecordView.findViewById(R.id.edt_addRecordMishu);
		addRecordSXX = (EditText) addRecordView.findViewById(R.id.edt_addRecordSX);
		addRecordCDH = (EditText) addRecordView.findViewById(R.id.edt_addRecordLaneNumber);
		addRecordJLDW = (EditText) addRecordView.findViewById(R.id.edt_addRecordMeasureUnit);
		addRecordGCSL = (EditText) addRecordView.findViewById(R.id.edt_addRecordProjectNumber);
		addRecordSGQX = (EditText) addRecordView.findViewById(R.id.edt_addRecordConstructionMachinery);
		addRecordStareTime = (EditText) addRecordView.findViewById(R.id.edt_addRecordStartTime);

		addRecordEndTime = (EditText) addRecordView.findViewById(R.id.edt_addRecordEndTime);
		addRecordWorkTime = (EditText) addRecordView.findViewById(R.id.edt_addRecordWorkTime);
		addRecordJLXM = (EditText) addRecordView.findViewById(R.id.edt_addRecordManagerName);
		addRecordNote = (EditText) addRecordView.findViewById(R.id.edt_addRecordNote);

		addRecordView.findViewById(R.id.ImgBtnEnsure).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				if (!TextUtils.isEmpty(addRecordZH.getText().toString().trim()))
					jsonObject.put("zh", addRecordZH.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordMS.getText().toString().trim()))
					jsonObject.put("ms", addRecordMS.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordSXX.getText().toString().trim()))
					jsonObject.put("sxx", addRecordSXX.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordCDH.getText().toString().trim()))
					jsonObject.put("cdh", addRecordCDH.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordJLDW.getText().toString().trim()))
					jsonObject.put("jldw", addRecordJLDW.getText().toString().trim());

				if (!TextUtils.isEmpty(addRecordGCSL.getText().toString().trim()))
					jsonObject.put("gcsl", addRecordGCSL.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordSGQX.getText().toString().trim()))
					jsonObject.put("sgjx", addRecordSGQX.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordStareTime.getText().toString().trim()))
					jsonObject.put("kssj", addRecordStareTime.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordEndTime.getText().toString().trim()))
					jsonObject.put("jssj", addRecordEndTime.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordJLXM.getText().toString().trim()))
					jsonObject.put("jlxm", addRecordJLXM.getText().toString().trim());
				if (!TextUtils.isEmpty(addRecordNote.getText().toString().trim()))
					jsonObject.put("bx", addRecordNote.getText().toString().trim());
				jsonArray.add(jsonObject);
				addRecordDialog.dismiss();
			}
		});

		addRecordDialog = new AlertDialog.Builder(context, R.style.CustomDialog).create();
		addRecordDialog.show();
		addRecordDialog.getWindow().setContentView(addRecordView);

		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		android.view.WindowManager.LayoutParams p = addRecordDialog.getWindow().getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		// p.height = (int) (d.getHeight() * 0.3); //�߶�����Ϊ��Ļ��0.3
		p.width = (int) (d.getWidth()); // �������Ϊȫ��
		addRecordDialog.getWindow().setAttributes(p); // ������Ч
	}

	private BDLocationListener mListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if (null != location && location.getLocType() != BDLocation.TypeServerError) {
				locationAssress.setText(location.getAddrStr());
				StringBuffer sb = new StringBuffer(256);
				sb.append("time : ");
				/**
				 * ʱ��Ҳ����ʹ��systemClock.elapsedRealtime()���� ��ȡ�����Դӿ���������ÿ�λص���ʱ�䣻
				 * location.getTime() ��ָ����˳����ν����ʱ�䣬���λ�ò������仯����ʱ�䲻��
				 */
				sb.append(location.getTime());
				sb.append("\nerror code : ");
				sb.append(location.getLocType());
				sb.append("\nlatitude : ");
				sb.append(location.getLatitude());
				sb.append("\nlontitude : ");
				sb.append(location.getLongitude());
				zbx = location.getLatitude();
				zby = location.getLongitude();

				sb.append("\nradius : ");
				sb.append(location.getRadius());
				sb.append("\nCountryCode : ");
				sb.append(location.getCountryCode());
				sb.append("\nCountry : ");
				sb.append(location.getCountry());
				sb.append("\ncitycode : ");
				sb.append(location.getCityCode());
				sb.append("\ncity : ");
				sb.append(location.getCity());
				sb.append("\nDistrict : ");
				sb.append(location.getDistrict());
				sb.append("\nStreet : ");
				sb.append(location.getStreet());
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\nDescribe: ");
				sb.append(location.getLocationDescribe());
				sb.append("\nDirection(not all devices have value): ");
				sb.append(location.getDirection());
				sb.append("\nPoi: ");
				if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
					for (int i = 0; i < location.getPoiList().size(); i++) {
						Poi poi = (Poi) location.getPoiList().get(i);
						sb.append(poi.getName() + ";");
					}
				}
				if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS��λ���
					sb.append("\nspeed : ");
					sb.append(location.getSpeed());// ��λ��km/h
					sb.append("\nsatellite : ");
					sb.append(location.getSatelliteNumber());
					sb.append("\nheight : ");
					sb.append(location.getAltitude());// ��λ����
					sb.append("\ndescribe : ");
					sb.append("gps��λ�ɹ�");
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// ���綨λ���
					// ��Ӫ����Ϣ
					sb.append("\noperationers : ");
					sb.append(location.getOperators());
					sb.append("\ndescribe : ");
					sb.append("���綨λ�ɹ�");
				} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// ���߶�λ���
					sb.append("\ndescribe : ");
					sb.append("���߶�λ�ɹ������߶�λ���Ҳ����Ч��");
				} else if (location.getLocType() == BDLocation.TypeServerError) {
					sb.append("\ndescribe : ");
					sb.append("��������綨λʧ�ܣ����Է���IMEI�źʹ��嶨λʱ�䵽loc-bugs@baidu.com��������׷��ԭ��");
				} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
					sb.append("\ndescribe : ");
					sb.append("���粻ͬ���¶�λʧ�ܣ����������Ƿ�ͨ��");
				} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
					sb.append("\ndescribe : ");
					sb.append("�޷���ȡ��Ч��λ���ݵ��¶�λʧ�ܣ�һ���������ֻ���ԭ�򣬴��ڷ���ģʽ��һ���������ֽ�����������������ֻ�");
				}
				Log.e("Tag", sb.toString());
			}
		}

	};

}

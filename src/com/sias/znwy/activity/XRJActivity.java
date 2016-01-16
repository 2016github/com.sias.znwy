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
 * 写日结界面
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
		title.setItemName("填写工作日结");
		title.setRightNameVisiable(View.VISIBLE);
		title.setItemRightName("提交");
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
			// 提交日结
			param = WebParam.create();
			param.addParam("jklx", "jlxcxr");
			param.addParam("yhdh", UserInfo.getYhdh());
			param.addParam("sjbs", UserInfo.getDeviceId());
			param.addParam("aqyz", "aqyz");
			param.addParam("sbsj", AppKit.getNowData());
			commitRj();
			break;

		case R.id.locationBtn:
			// 重新定位
			locationService.start();// 定位SDK
			break;
		case R.id.addRecord:
			// 添加记录
			addRecordDialog(XRJActivity.this);
			break;

		}

	}

	/**
	 * 提交日结
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
		locationService.start();// 定位SDK
	}

	@Override
	protected void onStop() {
		locationService.unregisterListener(mListener); // 注销掉监听
		locationService.stop(); // 停止定位服务
		super.onStop();
	}

	public void addRecordDialog(Context context) {

		// 动态加载布局生成View对象
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
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		android.view.WindowManager.LayoutParams p = addRecordDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
		// p.height = (int) (d.getHeight() * 0.3); //高度设置为屏幕的0.3
		p.width = (int) (d.getWidth()); // 宽度设置为全屏
		addRecordDialog.getWindow().setAttributes(p); // 设置生效
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
				 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
				 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
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
				if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
					sb.append("\nspeed : ");
					sb.append(location.getSpeed());// 单位：km/h
					sb.append("\nsatellite : ");
					sb.append(location.getSatelliteNumber());
					sb.append("\nheight : ");
					sb.append(location.getAltitude());// 单位：米
					sb.append("\ndescribe : ");
					sb.append("gps定位成功");
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
					// 运营商信息
					sb.append("\noperationers : ");
					sb.append(location.getOperators());
					sb.append("\ndescribe : ");
					sb.append("网络定位成功");
				} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
					sb.append("\ndescribe : ");
					sb.append("离线定位成功，离线定位结果也是有效的");
				} else if (location.getLocType() == BDLocation.TypeServerError) {
					sb.append("\ndescribe : ");
					sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
				} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
					sb.append("\ndescribe : ");
					sb.append("网络不同导致定位失败，请检查网络是否通畅");
				} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
					sb.append("\ndescribe : ");
					sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
				}
				Log.e("Tag", sb.toString());
			}
		}

	};

}

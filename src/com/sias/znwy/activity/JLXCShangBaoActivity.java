package com.sias.znwy.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.Poi;
import com.sias.znwy.MyApplication;
import com.sias.znwy.R;
import com.sias.znwy.Util.ActivityTitleView;
import com.sias.znwy.Util.AppKit;
import com.sias.znwy.Util.RequestTools;
import com.sias.znwy.Util.UserInfo;
import com.sias.znwy.service.LocationService;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.WebParam;

/**
 * 监理巡查上报界面
 * 
 * @author lkx Created at 2015-12-26 上午11:06:47
 * @version 1.0
 */
public class JLXCShangBaoActivity extends Activity implements OnClickListener {
	private ActivityTitleView titleView;
	private TextView mLocation;
	private Spinner driverDirection, conditionParent, conditionSecond, conditionThird;
	private EditText miNumber, cartLineNumber, diseaseDiscription, manager, memo;
	private String fangXiang, conditionOne, conditionTwo, conditionThree;
	private String[] m_arr = { "上行", "下行" };
	/***
	 * 从Intent获取图片路径的KEY
	 */
	public static final String KEY_PHOTO_PATH = "photo_path";

	/***
	 * 使用照相机拍照获取图片
	 */
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
	private Intent lastIntent;
	private ImageView mImg;
	private Uri photoUri;
	private File tempFile;
	private Bitmap myBitmap;
	private String bitmapBate64;
	private double zbx, zby;
	// 定位
	private LocationService locationService;
	public LocationClient mLocationClient = null;
	private String address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jlxcshangbao);
		titleView = (ActivityTitleView) findViewById(R.id.jlxcShangbaoTitle);
		initView();
		titleView.titleRightText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 上报
				commit();

			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * 
	 * @author lkx Created at 2015-12-26 下午2:14:41
	 */
	private void initView() {
		driverDirection = (Spinner) findViewById(R.id.drivingDirection);
		conditionParent = (Spinner) findViewById(R.id.conditionParent);
		conditionSecond = (Spinner) findViewById(R.id.conditionSecond);
		conditionThird = (Spinner) findViewById(R.id.conditionThird);
		mImg = (ImageView) findViewById(R.id.photo);
		manager = (EditText) findViewById(R.id.management);
		cartLineNumber = (EditText) findViewById(R.id.cartLaneMiNumber);
		diseaseDiscription = (EditText) findViewById(R.id.pileNumber);
		miNumber = (EditText) findViewById(R.id.shangBaoMiNumber);
		memo = (EditText) findViewById(R.id.memo);
		mLocation = (TextView) findViewById(R.id.addressMsg);
		// driverDirection.setPrompt("请选择行驶方向");
		fangXiang = m_arr[0];
		// ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
		// R.array.spinnercolor,android.R.layout.simple_spinner_item);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m_arr);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		driverDirection.setAdapter(adapter);
		driverDirection.setSelection(1, true);
		driverDirection.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				fangXiang = m_arr[position];
				parent.setVisibility(View.VISIBLE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		findViewById(R.id.btn_takephoto).setOnClickListener(this);
		findViewById(R.id.location).setOnClickListener(this);

	}

	protected void commit() {

		// ,,,,,bhdl,bhxl,bhzl,bhms,
		WebParam param = WebParam.create();
		param.addParam("jklx", "jlxcxr");
		param.addParam("yhdh", UserInfo.getYhdh());
		param.addParam("sjbs", UserInfo.getDeviceId());
		param.addParam("aqyz", "aqyz");
		param.addParam("sbsj", AppKit.getNowData());
		if (!TextUtils.isEmpty(bitmapBate64)) {
			param.addParam("bhbwzp", this.bitmapBate64);
		}

		if (!TextUtils.isEmpty(manager.getText().toString())) {
			param.addParam("glbm", manager.getText().toString());
		}
		if (!TextUtils.isEmpty(miNumber.getText().toString())) {
			param.addParam("ms", miNumber.getText().toString());
		}
		if (!TextUtils.isEmpty(diseaseDiscription.getText().toString())) {
			param.addParam("zh", diseaseDiscription.getText().toString());
		}
		if (!TextUtils.isEmpty(cartLineNumber.getText().toString())) {
			param.addParam("cdh", cartLineNumber.getText().toString());
		}
		if (!TextUtils.isEmpty(memo.getText().toString())) {
			param.addParam("bhms", memo.getText().toString());
		}
		if (!TextUtils.isEmpty(fangXiang)) {
			param.addParam("xsfx", fangXiang);
		}
		if (!TextUtils.isEmpty(conditionOne)) {
			param.addParam("bhdl", conditionOne);
		}
		if (!TextUtils.isEmpty(conditionTwo)) {
			param.addParam("bhxl", conditionTwo);
		}
		if (!TextUtils.isEmpty(conditionThree)) {
			param.addParam("bhzl", conditionThree);
		}
		if (!TextUtils.isEmpty(String.valueOf(zbx))) {
			param.addParam("zbx", zbx);
		}
		if (!TextUtils.isEmpty(String.valueOf(zby))) {
			param.addParam("zby", zby);
		}

		new RequestTools().request(param, new OnResultListener() {

			@Override
			public void onResult(boolean isSuccess, int errorCode, Object obj) {

				if (isSuccess) {
					finish();
				} else {
					Toast.makeText(JLXCShangBaoActivity.this, obj.toString(), Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_takephoto:
			takePhoto();
			break;
		case R.id.location:
			locationService.start();// 定位SDK
			break;
		}

	}

	@Override
	protected void onStart() {
		locationService = MyApplication.getContext().locationService;
		locationService.registerListener(mListener);
		locationService.setLocationOption(locationService.getDefaultLocationClientOption());
		locationService.start();// 定位SDK
		super.onStart();
	}

	@Override
	protected void onStop() {
		locationService.unregisterListener(mListener); // 注销掉监听
		locationService.stop(); // 停止定位服务
		super.onStop();
	}

	private void takePhoto() {
		// 执行拍照前，应该先判断SD卡是否存在
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// 判断存储卡是否可以用，可用进行存储
		if (AppKit.hasSdcard()) {
			startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
		} else {
			Toast.makeText(JLXCShangBaoActivity.this, "暂无SD卡", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri uri = data.getData();
		if (uri != null) {
			this.myBitmap = BitmapFactory.decodeFile(uri.getPath());
		}
		if (this.myBitmap == null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				this.myBitmap = (Bitmap) bundle.get("data");
				mImg.setImageBitmap(myBitmap);
				this.bitmapBate64 = encode(myBitmap);
			} else {
				Toast.makeText(JLXCShangBaoActivity.this, "拍照失败", Toast.LENGTH_LONG).show();
				return;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 将图片编码
	 * 
	 * @param path
	 * 
	 * @author lkx Created at 2015-12-24 下午3:31:53
	 */
	private String encode(Bitmap bitmap) {
		// decode to bitmap

		// convert to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] bytes = baos.toByteArray();

		// base64 encode
		byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
		String encodeString = new String(encode);
		// mTvShow.setText(encodeString);
		return encodeString;
	}

	/*
	 * @see copy funtion to you project
	 * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
	 */
	private BDLocationListener mListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if (null != location && location.getLocType() != BDLocation.TypeServerError) {
				address = location.getAddrStr();
				mLocation.setText(address);
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
				zbx = location.getLatitude();
				sb.append("\nlontitude : ");
				sb.append(location.getLongitude());
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

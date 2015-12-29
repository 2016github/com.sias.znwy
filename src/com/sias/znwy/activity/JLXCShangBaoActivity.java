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
 * ����Ѳ���ϱ�����
 * 
 * @author lkx Created at 2015-12-26 ����11:06:47
 * @version 1.0
 */
public class JLXCShangBaoActivity extends Activity implements OnClickListener {
	private ActivityTitleView titleView;
	private TextView mLocation;
	private Spinner driverDirection, conditionParent, conditionSecond, conditionThird;
	private EditText miNumber, cartLineNumber, diseaseDiscription, manager, memo;
	private String fangXiang, conditionOne, conditionTwo, conditionThree;
	private String[] m_arr = { "����", "����" };
	/***
	 * ��Intent��ȡͼƬ·����KEY
	 */
	public static final String KEY_PHOTO_PATH = "photo_path";

	/***
	 * ʹ����������ջ�ȡͼƬ
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
	// ��λ
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
				// �ϱ�
				commit();

			}
		});
	}

	/**
	 * ��ʼ������
	 * 
	 * 
	 * @author lkx Created at 2015-12-26 ����2:14:41
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
		// driverDirection.setPrompt("��ѡ����ʻ����");
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
			locationService.start();// ��λSDK
			break;
		}

	}

	@Override
	protected void onStart() {
		locationService = MyApplication.getContext().locationService;
		locationService.registerListener(mListener);
		locationService.setLocationOption(locationService.getDefaultLocationClientOption());
		locationService.start();// ��λSDK
		super.onStart();
	}

	@Override
	protected void onStop() {
		locationService.unregisterListener(mListener); // ע��������
		locationService.stop(); // ֹͣ��λ����
		super.onStop();
	}

	private void takePhoto() {
		// ִ������ǰ��Ӧ�����ж�SD���Ƿ����
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// �жϴ洢���Ƿ�����ã����ý��д洢
		if (AppKit.hasSdcard()) {
			startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
		} else {
			Toast.makeText(JLXCShangBaoActivity.this, "����SD��", Toast.LENGTH_LONG).show();
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
				Toast.makeText(JLXCShangBaoActivity.this, "����ʧ��", Toast.LENGTH_LONG).show();
				return;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * ��ͼƬ����
	 * 
	 * @param path
	 * 
	 * @author lkx Created at 2015-12-24 ����3:31:53
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
	 * ��λ����ص�����дonReceiveLocation����������ֱ�ӿ������´��뵽�Լ��������޸�
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
				 * ʱ��Ҳ����ʹ��systemClock.elapsedRealtime()���� ��ȡ�����Դӿ���������ÿ�λص���ʱ�䣻
				 * location.getTime() ��ָ����˳����ν����ʱ�䣬���λ�ò������仯����ʱ�䲻��
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

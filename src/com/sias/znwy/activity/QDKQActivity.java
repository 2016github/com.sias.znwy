package com.sias.znwy.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ǩ������
 * 
 * @author Administrator
 */
public class QDKQActivity extends Activity implements OnClickListener {
	private TextView text_nameandtime;
	private TextView text_return;
	private Button btn_takePhoto, btn_dingwei;
	private LocationService locationService;
	public LocationClient mLocationClient = null;
	private String address;
	private TextView text_location;
	String picturename;
	private EditText signInMemo;
	private ImageView img_showphoto;
	private ActivityTitleView title;
	private String bitmapBate64;
	private double zbx, zby;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qdkq);
		initView();
	}

	private void initView() {
		text_return = (TextView) findViewById(R.id.text_return);
		title = (ActivityTitleView) findViewById(R.id.cxshTitle);
		text_return.setOnClickListener(this);
		text_nameandtime = (TextView) findViewById(R.id.text_nameandtime);
		text_nameandtime.setText("��ã�" + UserInfo.getYhdh() + " �����ǣ�" + getNowData());
		btn_takePhoto = (Button) findViewById(R.id.btn_takephoto);
		btn_dingwei = (Button) findViewById(R.id.btn_dingwei);
		signInMemo = (EditText) findViewById(R.id.signInMemo);
		btn_dingwei.setOnClickListener(this);
		btn_takePhoto.setOnClickListener(this);
		text_location = (TextView) findViewById(R.id.text_location);
		img_showphoto = (ImageView) findViewById(R.id.img_showphoto);
		title.titleRightText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ǩ��
				commit();

			}
		});
	}

	protected void commit() {
		// jklx:"sjqdxr",yhdh:"sj",sjbs:"ABCD",aqyz:"",qdsj,zp,zbx,zby,bq,rwdh,bz
		// ,glbm,blc
		WebParam param = WebParam.create();
		param.addParam("jklx", "sjqdxr");
		param.addParam("yhdh", UserInfo.getYhdh());
		param.addParam("sjbs", UserInfo.getDeviceId());
		param.addParam("aqyz", "aqyz");
		param.addParam("qdsj", AppKit.getNowData());
		param.addParam("zp", this.bitmapBate64);
		param.addParam("zbx", zbx);
		param.addParam("zby", zby);

		// param.addParam("bq", "");
		// param.addParam("rwdh", "");
		param.addParam("bz", signInMemo.getText().toString());
		// param.addParam("glbm",
		// SharedPreferencesKit.getString(QDKQActivity.this, Const.USERGLBM));
		// param.addParam("blc", "");
		new RequestTools().request(param, new OnResultListener() {

			@Override
			public void onResult(boolean isSuccess, int errorCode, Object obj) {
				if (isSuccess) {
					Toast.makeText(QDKQActivity.this, "ǩ���ɹ�", Toast.LENGTH_SHORT).show();
					finish();
				} else {
					Toast.makeText(QDKQActivity.this, obj.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		locationService = MyApplication.getContext().locationService;
		locationService.registerListener(mListener);
		locationService.setLocationOption(locationService.getDefaultLocationClientOption());
		locationService.start();// ��λSDK
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		locationService.unregisterListener(mListener); // ע��������
		locationService.stop(); // ֹͣ��λ����
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_return:
			finish();
			break;
		case R.id.btn_dingwei:
			// ��λ
			locationService.start();// ��λSDK

			break;
		case R.id.btn_takephoto:
			// ����
			picturename = Long.toString(SystemClock.currentThreadTimeMillis()) + ".jpg";
			Takephoto();
			break;

		default:
			break;
		}
	}

	/**
	 * ����
	 */
	private void Takephoto() {

		Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// �жϴ洢���Ƿ���ã��洢��Ƭ�ļ�
		if (hasSdcard()) {
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(Environment.getExternalStorageDirectory(), picturename)));
		}

		startActivityForResult(intentFromCapture, 1);

	}

	/**
	 * �ж�SD���Ƿ����
	 * 
	 * @return
	 */
	private boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// �д洢��SDCard
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ȡ��ǰ����
	 */
	public String getNowData() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��   HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
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
				text_location.setText(address);
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

	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			if (hasSdcard()) {
				File tempFile = new File(Environment.getExternalStorageDirectory(), picturename);
				Bitmap bm = BitmapFactory.decodeFile(tempFile.getAbsolutePath());
				img_showphoto.setImageBitmap(bm);
				// bm.recycle();
				this.bitmapBate64 = encode(bm);
			} else {
				Toast.makeText(QDKQActivity.this, "û��SDCard!", Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, intent);

	};

	/**
	 * ��ͼƬ����
	 * 
	 * @param path
	 * 
	 * 
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
}

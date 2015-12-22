package com.sias.znwy;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.sias.znwy.service.LocationService;
import com.sias.znwy.web.util.Web;
import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

public class MyApplication extends Application {

	private static MyApplication application;
	public LocationClient mLocationClient = null;
	public LocationService locationService;
	 public Vibrator mVibrator;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application = this;
		Web.init(this);
		initLocation();
	}

	/**
	 * 初始化百度地图定位SDK
	 */
	private void initLocation() {
		/***
		 * 初始化定位sdk，建议在Application中创建
		 */
		locationService = new LocationService(getApplicationContext());
		mVibrator = (Vibrator) getApplicationContext().getSystemService(
				Service.VIBRATOR_SERVICE);
		SDKInitializer.initialize(getApplicationContext());
	}

	public static MyApplication getContext() {
		return application;
	}
}

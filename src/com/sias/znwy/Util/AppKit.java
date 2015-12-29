package com.sias.znwy.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.telephony.TelephonyManager;

public class AppKit {
	/**
	 * 获取当前Android版本号
	 * 
	 * @return
	 * 
	 * @author lkx Created at 2015-12-24 上午10:36:57
	 */
	public static String getAppVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取 App VersionCode
	 * 
	 * @param context
	 * @return
	 * 
	 * @author lkx Created at 2015-12-24 上午10:36:57
	 */
	public static int getAppVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS).versionCode;
		} catch (NameNotFoundException e) {
		}
		return 1;
	}

	/**
	 * 是否存在SD卡
	 * 
	 * @return
	 * 
	 * @author lkx Created at 2015-12-24 下午3:18:06
	 */
	public static boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取手机的唯一标识码
	 */

	public static String getDeviceId(Context context) {
		TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;
	}

	/**
	 * 获取当前日期
	 */
	public static String getNowData() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

}

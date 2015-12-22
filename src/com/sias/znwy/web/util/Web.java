package com.sias.znwy.web.util;
import java.util.Map;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sias.znwy.MyApplication;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;

/**
 * Web请求抽象类
 * 
 * @author
 * 
 */
public abstract class Web implements IQuery {
	/**
	 * WEB请求对列
	 */
	protected static RequestQueue mRequestQueue;
	/**
	 * 根路径
	 */
	public static String programUrl = "http://218.28.186.204:8100/jywy/ws/dlgl";
	/**
	 * 初始化第三方插件
	 * 
	 * @param context
	 */
	private static boolean isNetCon;
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}

	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			System.out.println("context != null");
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	@Override
	public IReq query(final IWebKVParam param, final OnRespListener listener) {
		// 判断网络状态
		System.out.println("isNetCon"
				+ isNetworkConnected(MyApplication.getContext()));
		// 这句话 创建一个任务
		StringRequest stringRequest = new StringRequest(Method.POST, programUrl,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						  JSONObject obj = JSON.parseObject(response);
						  obj.getString("yzjg");
						  if (obj.getString("yzjg").equals("1")) {		
							  listener.onResponse(1, null, response);
						}else {
							 listener.onResponse(0, obj.getString("jgms"), null);
						}
						  
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						 listener.onResponse(0, error.getMessage(), null);
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return param.getMap();
			}
		};
		Req req = new Req();
		req.bindCustomRequest(stringRequest);
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mRequestQueue.add(stringRequest);
		return req;
	}
}

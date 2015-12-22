package com.sias.znwy.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public class CustomRequestMap extends CustomRequest {

	private Map<String, String> con;

	public CustomRequestMap(String url,  Map<String, String> con, final OnRespListener listener) {
		super(url,  listener, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				byte[] htmlBodyBytes = error.networkResponse.data;
				if(error instanceof TimeoutError){
					listener.onResponse( ERROE_TIMEOUT, error.getMessage(), null);
					return;
				}
				listener.onResponse( ERROE, error.getMessage(), null);
			}
		});
		this.con = con;
	}
	/**
	 * json «Î«Ûrequset –≈œ¢
	 */

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		
		return con;
	}
//	@Override
//	public byte[] getBody() {
//		Log.e("Tag", "getBody");
//		JSONObject jsonObject = new JSONObject();
////		jsonObject.put("head", new JSONObject(getHead()));
////		jsonObject.put("con", new JSONObject(con));
//		jsonObject.putAll( new JSONObject(con));
//		
//		String result = "";
//		try {
//			result = jsonObject.toJSONString();
//
//			try {
//				VolleyLog.d("json request:" + result);
//			} catch (Exception e) {
//				
//			}
//
//			return result.getBytes(PROTOCOL_CHARSET);
//		} catch (UnsupportedEncodingException uee) {
//			VolleyLog.wtf("Unsupported Encoding of %s using %s", result, PROTOCOL_CHARSET);
//			return null;
//		} catch (Exception e) {
//			VolleyLog.wtf("Parse to json string error-2");
//			return null;
//		}
//	}
}

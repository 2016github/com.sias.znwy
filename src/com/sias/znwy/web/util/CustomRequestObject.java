package com.sias.znwy.web.util;

import java.io.UnsupportedEncodingException;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public class CustomRequestObject extends CustomRequest {

	/** Use to make up JSON result. */
	private Object customBody;

	public CustomRequestObject(String url, final int cmd, Object customBody, final OnRespListener listener) {
		super(url, listener, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				if(error instanceof TimeoutError){
					listener.onResponse( ERROE_TIMEOUT, error.getMessage(), null);
					return;
				}
				listener.onResponse( ERROE, error.getMessage(), null);
			}
		});
		this.customBody = customBody;
	}

	@Override
	public byte[] getBody() {
		String result = "";
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("head", new JSONObject(getHead()));
			jsonObject.put("con", customBody);

			result = jsonObject.toJSONString();//JSON.toJSONString(jsonObject);

			VolleyLog.d("json request====:" + result);

			return result.getBytes(PROTOCOL_CHARSET);
		} catch (UnsupportedEncodingException uee) {
			VolleyLog.wtf("Unsupported Encoding of %s using %s", result, PROTOCOL_CHARSET);
			return null;
		} catch (Exception e) {
			VolleyLog.wtf("Parse to json string error-3");
			return null;
		}
	}
}

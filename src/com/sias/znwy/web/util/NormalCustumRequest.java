package com.sias.znwy.web.util;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;
public class NormalCustumRequest extends Request<JSONObject>{
    private  Map<String, String> map;
    private OnRespListener mlistener ;
	public NormalCustumRequest(String url,Map<String, String> map,OnRespListener listener, ErrorListener errorlistener) {
		super(Request.Method.POST, url, errorlistener);
		this.map=map;
		mlistener=listener;
		
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return map;
	}
	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		 try {
	            String jsonString = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
	            JSONObject object = JSON.parseObject(jsonString);
	            return Response.success(object,HttpHeaderParser.parseCacheHeaders(response));
	        } catch (UnsupportedEncodingException e) {
	            return Response.error(new ParseError(e));
	        } catch (JSONException je) {
	            return Response.error(new ParseError(je));
	        }
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		int state = 0;
		String msg = "";
		msg=response.getString("jgms");
		state = response.getInteger("yzjg");
		mlistener.onResponse(state, msg, response.toString());
	}


}

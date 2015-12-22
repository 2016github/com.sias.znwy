package com.sias.znwy.web.util;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

public class CustomRequest extends Request<JSONObject> {

	/** Callback interface for delivering parsed responses.
	 *  CumtomRequest·µ»ØÊý¾ÝµÄ½Ó¿Ú
	 **/
	public interface OnRespListener {
		/**
		 * Called when a response is received.
		 * 
		 * @param cmd
		 * @param state
		 *            include custom error code see {@link ERROR}
		 * @param msg
		 * @param body
		 */
		public void onResponse( int state, String msg, String body);
	}

	/** Custom error code: ï¿½ï¿½ï¿½ï¿½ï¿½ì³£ */
	public static final int ERROE = 0xFFFFFFFF;
	/** Custom error code: ï¿½ï¿½ï¿½ï¿½Î´ï¿½ï¿½ï¿½ï¿½ */
	public static final int ERROE_DISCONNECT = 0xFFFFFFFE;
	/** Custom error code: ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ì³£ */
	public static final int ERROE_SERVER = 0xFFFFFFFD;
	/** Custom error code: ï¿½ï¿½ï¿½Ó³ï¿½Ê± */
	public static final int ERROE_TIMEOUT = 0xFFFFFFFC;

	/** Custom Head Context
	 * ÇëÇóÍ·ÎÄ¼þ
	 *  */
	public static final String AID = "1and6uu";
	public static final String VER = "1.0";
	public static final String LN = "cn";
	public static final String MOD = "android";
	public static final String DE = "2013-11-11 00:00:00";
	public static final int SYNC = 1;

	/** Charset for request. */
	public static final String PROTOCOL_CHARSET = "utf-8";
	/** Content type for request. */
	private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

	protected int cmd;
	private OnRespListener listener;

	/*¹¹Ôì·½·¨
	 * 
	 */
	public CustomRequest(String url, final OnRespListener listener, ErrorListener errorListener) {
		super(Method.POST, url, errorListener);

		this.listener = listener;
	}
	/**
	 * ÇëÇó³É¹¦ºó
	 * ·µ»ØÊý¾Ý
	 */

	@Override
	protected void deliverResponse(JSONObject response) {
		if (listener != null) {
			int state = 0;
			String msg = "";
			String body = null;
			try {
				JSONObject head = response.getJSONObject("result");
				cmd = head.getInteger("cmd");
				state = head.getInteger("st");
				msg = head.getString("msg");
			} catch (Exception e) {
				listener.onResponse( ERROE, "parse head error", null);
				return;
			}
			try {
				JSONObject bodyObj = response.getJSONObject("body");
				if(bodyObj!=null)
					body = bodyObj.toString(); 
			} catch (Exception e) {
				body = null;
			}
			listener.onResponse( state, msg, body);
		}
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		String jsonString = "";
		try {
			jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			Log.d("jsonString",jsonString);
			JSONObject object = JSON.parseObject(jsonString);
			return Response.success(object, HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			VolleyLog.wtf("Unsupported Encoding of %s using %s", jsonString, PROTOCOL_CHARSET);
			return Response.error(new ParseError(e));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(jsonString);
			VolleyLog.wtf("Parse to json string error-1");
			return Response.error(new ParseError(e));
		}
	}

	@Override
	public String getBodyContentType() {
		return PROTOCOL_CONTENT_TYPE;
	}
	private static String chcode = "";
	private static int uid = 0;

	/**
	 * Set Check Code
	 * 
	 * @param mchcode
	 */
	public static void setChcode(String mchcode) {
		chcode = mchcode;
	}

	public static String getChcode() {
		return chcode;
	}

	/**
	 * Set UID
	 * 
	 * @param muid
	 */
	public static void setUID(int muid) {
		uid = muid;
	}

	public static int getUID() {
		return uid;
	}
	
	private Map<String, Object> head = null;
	protected Map<String, Object> getHead(){
		if(head == null){
			head = new HashMap<String, Object>();
			head.put("aid", AID);
			head.put("ver", VER);
			head.put("ln", LN);
			head.put("mod", MOD);
			head.put("de", DE);
			head.put("sync", SYNC);
			head.put("uuid", getUID()); // TODO
		}
		head.put("chcode", getChcode());
		head.put("cmd", cmd);
		return head;
	}
}
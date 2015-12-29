package com.sias.znwy.Util;

import java.io.File;
import java.util.ArrayList;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebKVParam;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.Web;
import com.sias.znwy.web.util.WebParam;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;

public class RequestTools extends Web {
	private Object value;

	public void request(WebParam param, final OnResultListener listener) {
		if (param != null || param.getMap().size() > 0) {
			for (String key : param.getMap().keySet()) {
				value = param.getMap().get(key);
				Log.d("RequestTools", "Param : " + key + " --> " + value);

			}
		}
		query(param, new OnRespListener() {

			@Override
			public void onResponse(int state, String msg, String body) {
				if (state == 1) {
					// 成功
					System.out.println("响应值：" + body);
					JSONObject obj = JSON.parseObject(body);
					listener.onResult(true, state, obj);
				} else {
					listener.onResult(false, state, msg);
				}
			}
		});

	}
}

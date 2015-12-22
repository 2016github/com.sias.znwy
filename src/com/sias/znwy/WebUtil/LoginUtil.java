package com.sias.znwy.WebUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.demo.Tusbkey;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;
import com.sias.znwy.web.util.IWebKVParam;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.Web;
import com.sias.znwy.web.util.WebParam;
public class LoginUtil extends Web {
	
	public void Login(String jklx,String yhdh,String usbmy,String sjbs,String aqyz, final OnResultListener listener) {
		IWebKVParam param = new WebParam();
		param.put("jklx", jklx);
		param.put("yhdh", yhdh);
		param.put("usbmy", usbmy);
		param.put("sjbs", sjbs);
		param.put("aqyz", aqyz);
		query(param, new OnRespListener() {
			
			@Override
			public void onResponse( int state, String msg, String body) {
				if (state==1) {
					//³É¹¦
					JSONObject obj = JSON.parseObject(body);
					Tusbkey  tusbkey=obj.getObject("t_usbkey", Tusbkey.class);
					listener.onResult(true, state, obj);
				}else {
					listener.onResult(false, state, msg);
				}
			}
		});
		
	}


}

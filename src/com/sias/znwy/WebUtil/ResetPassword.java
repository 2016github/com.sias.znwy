package com.sias.znwy.WebUtil;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebKVParam;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.Web;
import com.sias.znwy.web.util.WebParam;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;

public class ResetPassword extends Web {
	public void Resetpassword(String jklx, String yhdh, String oldpwd,
			String newpwd, String sjbs, String aqyz, final OnResultListener listener) {
		IWebKVParam param = new WebParam();
		param.put("jklx", jklx);
		param.put("yhdh", yhdh);
		param.put("oldPwd", oldpwd);
		param.put("newPwd", newpwd);
		param.put("sjbs", sjbs);
		param.put("aqyz", aqyz);
		query(param, new OnRespListener() {
			
			@Override
			public void onResponse( int state, String msg, String body) {
				if (state==1) {
					//³É¹¦
					JSONObject obj = JSON.parseObject(body);
					listener.onResult(true, state, obj);
				}else {
					listener.onResult(false, state, null);
				}
			}
		});
		
	}

}

package com.sias.znwy.WebUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;
import com.sias.znwy.web.util.IWebKVParam;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.Web;
import com.sias.znwy.web.util.WebParam;

public class QueryTZ extends Web {

	public void QueryTz(String jklx,String yhdh,String sjbs,String aqyz,String cxrq, final OnResultListener listener){
		IWebKVParam param=new WebParam();
		param.put("jklx", jklx);
		param.put("yhdh", yhdh);
		param.put("sjbs", sjbs);
		param.put("aqyz", aqyz);
		param.put("cxrq", cxrq);
		query(param, new OnRespListener() {
			
			@Override
			public void onResponse(int state, String msg, String body) {
				if (state==1) {
					JSONObject obj = JSON.parseObject(body);
					listener.onResult(true, state, obj);
				}else {
					listener.onResult(false, state, msg);
				}
			}
		});
		
	}	
	
}

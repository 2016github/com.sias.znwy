package com.sias.znwy.WebUtil;
import com.sias.znwy.web.util.CustomRequest.OnRespListener;
import com.sias.znwy.web.util.IWebKVParam;
import com.sias.znwy.web.util.OnResultListener;
import com.sias.znwy.web.util.Web;
import com.sias.znwy.web.util.WebParam;

public class QueryGzrb extends Web {
	public void queryGzrb(String jklx,String yhdh,String sjbs,String aqyz,String cxrq ,final OnResultListener listener){
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
					listener.onResult(true, state, body);
				}else {
					
					listener.onResult(false, state, msg);
				}
				
			}
		});
		
		
	}
}

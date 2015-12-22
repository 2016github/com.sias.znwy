package com.sias.znwy.web.util;

import com.android.volley.toolbox.StringRequest;

/**
 * 
 * 
 * @author zzh
 * 
 */
final class Req implements IReq {

	private StringRequest req;

	protected void bindCustomRequest(StringRequest req) {
		this.req = req;
	}

	public void cancel() {
		req.cancel();
	}
}
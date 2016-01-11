package com.sias.znwy.demo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebBeanParam;

public class CQSBBean extends AbstractBean implements IWebBeanParam, Serializable {
	private String cqryhdh;
	private String cqrxm;
	private String cqqk;
	private String cqkssj;
	private String cqjssj;
	private String rwbh;
	private String rwmc;
	private String rwlxdm;

	public CQSBBean(JSONObject json) {
		super(json);
	}

	public String getCqryhdh() {
		return cqryhdh;
	}

	public void setCqryhdh(String cqryhdh) {
		this.cqryhdh = cqryhdh;
	}

	public String getCqrxm() {
		return cqrxm;
	}

	public void setCqrxm(String cqrxm) {
		this.cqrxm = cqrxm;
	}

	public String getCqqk() {
		return cqqk;
	}

	public void setCqqk(String cqqk) {
		this.cqqk = cqqk;
	}

	public String getCqkssj() {
		return cqkssj;
	}

	public void setCqkssj(String cqkssj) {
		this.cqkssj = cqkssj;
	}

	public String getCqjssj() {
		return cqjssj;
	}

	public void setCqjssj(String cqjssj) {
		this.cqjssj = cqjssj;
	}

	public String getRwbh() {
		return rwbh;
	}

	public void setRwbh(String rwbh) {
		this.rwbh = rwbh;
	}

	public String getRwmc() {
		return rwmc;
	}

	public void setRwmc(String rwmc) {
		this.rwmc = rwmc;
	}

	public String getRwlxdm() {
		return rwlxdm;
	}

	public void setRwlxdm(String rwlxdm) {
		this.rwlxdm = rwlxdm;
	}

	@Override
	public void parse(JSONObject json) throws JSONException {
		this.cqryhdh = json.getString("cqryhdh");
		this.cqrxm = json.getString("cqrxm");
		this.cqqk = json.getString("cqqk");
		this.cqkssj = json.getString("cqkssj");
		this.cqjssj = json.getString("cqjssj");
		this.rwlxdm = json.getString("rwlxdm");
		this.rwbh = json.getString("rwbh");
		this.rwmc = json.getString("rwmc");

	}

}

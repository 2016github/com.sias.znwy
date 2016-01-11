package com.sias.znwy.demo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebBeanParam;

/**
 * 通知通告类
 * 
 * @author lkx Created at 2016-1-11 下午2:57:39
 * @version 1.0
 */
public class TZTGBean extends AbstractBean implements IWebBeanParam, Serializable {
	private String id;
	private String fbsj;
	private String qsbj;
	private String qsr;
	private String qssj;
	private TZzhuBiaoBean shhGsl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFbsj() {
		return fbsj;
	}

	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}

	public String getQsbj() {
		return qsbj;
	}

	public void setQsbj(String qsbj) {
		this.qsbj = qsbj;
	}

	public String getQsr() {
		return qsr;
	}

	public void setQsr(String qsr) {
		this.qsr = qsr;
	}

	public String getQssj() {
		return qssj;
	}

	public void setQssj(String qssj) {
		this.qssj = qssj;
	}

	public TZzhuBiaoBean getShhGsl() {
		return shhGsl;
	}

	public void setShhGsl(TZzhuBiaoBean shhGsl) {
		this.shhGsl = shhGsl;
	}

	public TZTGBean(JSONObject json) {
		super(json);
	}

	@Override
	public void parse(JSONObject json) throws JSONException {
		this.id = json.getString("id");
		this.fbsj = json.getString("fbsj");
		this.qsbj = json.getString("qsbj");
		this.qsr = json.getString("qsr");
		this.qssj = json.getString("qssj");
		this.shhGsl = new TZzhuBiaoBean(json.getJSONObject("shhGsl"));

	}

}

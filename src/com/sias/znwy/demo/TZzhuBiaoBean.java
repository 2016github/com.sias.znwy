package com.sias.znwy.demo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebBeanParam;

/**
 * 通知主表对象
 * 
 * @author lkx Created at 2016-1-11 下午2:56:33
 * @version 1.0
 */
public class TZzhuBiaoBean extends AbstractBean implements IWebBeanParam, Serializable {
	private String bt;
	private String nr;
	private String fbr;
	private String fzr;
	private String fzrlxdh;
	private String fbsj;

	public TZzhuBiaoBean(JSONObject json) {
		super(json);
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public String getFzrlxdh() {
		return fzrlxdh;
	}

	public void setFzrlxdh(String fzrlxdh) {
		this.fzrlxdh = fzrlxdh;
	}

	public String getFbsj() {
		return fbsj;
	}

	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}

	@Override
	public void parse(JSONObject json) throws JSONException {
		this.bt = json.getString("bt");
		this.fbr = json.getString("fbr");
		this.fbsj = json.getString("fbsj");
		this.fzr = json.getString("fzr");
		this.fzrlxdh = json.getString("fzrlxdh");
		this.nr = json.getString("nr");

	}

}

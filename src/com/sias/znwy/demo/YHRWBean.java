package com.sias.znwy.demo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebBeanParam;

public class YHRWBean extends AbstractBean implements IWebBeanParam, Serializable {

	private String bz;
	private String fzrbh;
	private String fzrxm;
	private String jhdscyhdh;
	private String jhzt;
	private String jszh;
	private String kszh;
	private String rwbh;
	private String rwmc;
	private String sgdwdm;
	private String sgdwmc;
	private String sprdh;
	private String sprxm;
	private String spsj;
	private String wxjhdXygwType;
	private String xcjlYwlxType;
	private String xcjlYwztType;
	private String xygw;
	private String ywlc;
	private String ywlx;
	private String ywzt;

	public YHRWBean(JSONObject json) {
		super(json);
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFzrbh() {
		return fzrbh;
	}

	public void setFzrbh(String fzrbh) {
		this.fzrbh = fzrbh;
	}

	public String getFzrxm() {
		return fzrxm;
	}

	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}

	public String getJhdscyhdh() {
		return jhdscyhdh;
	}

	public void setJhdscyhdh(String jhdscyhdh) {
		this.jhdscyhdh = jhdscyhdh;
	}

	public String getJhzt() {
		return jhzt;
	}

	public void setJhzt(String jhzt) {
		this.jhzt = jhzt;
	}

	public String getJszh() {
		return jszh;
	}

	public void setJszh(String jszh) {
		this.jszh = jszh;
	}

	public String getKszh() {
		return kszh;
	}

	public void setKszh(String kszh) {
		this.kszh = kszh;
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

	public String getSgdwdm() {
		return sgdwdm;
	}

	public void setSgdwdm(String sgdwdm) {
		this.sgdwdm = sgdwdm;
	}

	public String getSgdwmc() {
		return sgdwmc;
	}

	public void setSgdwmc(String sgdwmc) {
		this.sgdwmc = sgdwmc;
	}

	public String getSprdh() {
		return sprdh;
	}

	public void setSprdh(String sprdh) {
		this.sprdh = sprdh;
	}

	public String getSprxm() {
		return sprxm;
	}

	public void setSprxm(String sprxm) {
		this.sprxm = sprxm;
	}

	public String getSpsj() {
		return spsj;
	}

	public void setSpsj(String spsj) {
		this.spsj = spsj;
	}

	public String getWxjhdXygwType() {
		return wxjhdXygwType;
	}

	public void setWxjhdXygwType(String wxjhdXygwType) {
		this.wxjhdXygwType = wxjhdXygwType;
	}

	public String getXcjlYwlxType() {
		return xcjlYwlxType;
	}

	public void setXcjlYwlxType(String xcjlYwlxType) {
		this.xcjlYwlxType = xcjlYwlxType;
	}

	public String getXcjlYwztType() {
		return xcjlYwztType;
	}

	public void setXcjlYwztType(String xcjlYwztType) {
		this.xcjlYwztType = xcjlYwztType;
	}

	public String getXygw() {
		return xygw;
	}

	public void setXygw(String xygw) {
		this.xygw = xygw;
	}

	public String getYwlc() {
		return ywlc;
	}

	public void setYwlc(String ywlc) {
		this.ywlc = ywlc;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getYwzt() {
		return ywzt;
	}

	public void setYwzt(String ywzt) {
		this.ywzt = ywzt;
	}

	@Override
	public void parse(JSONObject json) throws JSONException {
		System.out.println("Êý¾Ý½âÎö");
		this.bz = json.getString("bz");
		this.fzrbh = json.getString("fzrbh");
		this.fzrxm = json.getString("fzrxm");
		this.jhdscyhdh = json.getString("jhdscyhdh");
		this.jhzt = json.getString("jhzt");
		this.jszh = json.getString("jszh");
		this.kszh = json.getString("kszh");
		this.rwbh = json.getString("rwbh");
		this.rwmc = json.getString("rwmc");
		this.sgdwdm = json.getString("sgdwdm");
		this.sgdwmc = json.getString("sgdwmc");
		this.sprdh = json.getString("sprdh");
		this.sprxm = json.getString("sprxm");
		this.spsj = json.getString("spsj");
		this.wxjhdXygwType = json.getString("wxjhdXygwType");
		this.xcjlYwlxType = json.getString("xcjlYwlxType");
		this.xcjlYwztType = json.getString("xcjlYwztType");
		this.xygw = json.getString("xygw");
		this.ywlc = json.getString("ywlc");
		this.ywlx = json.getString("ywlx");
		this.ywzt = json.getString("ywzt");

	}

}

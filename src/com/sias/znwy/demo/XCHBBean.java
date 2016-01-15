package com.sias.znwy.demo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebBeanParam;

public class XCHBBean extends AbstractBean implements IWebBeanParam, Serializable {

	private String rwlx;// 任务类型，
	private String rwbh;// 任务编号，
	private String rwmc;// 任务名称,
	private String bglx;// 报告类型，
	private String glbm;// 管理部门，
	private String bmmc;// 部门名称，
	private String yhdh;// 用户代号，
	private String xm;// 姓名，
	private String sbsj;// 上报时间,
	private String jlbh;// 记录编号，
	private String fldm;// 风力代码,
	private String tqdm;// 天气代码,
	private String zh;// 桩号、
	private String ms;// 米数、
	private String xsfx;// 行驶方向、
	private String cdh;// 车道号、
	private String bhdl;// 病害大类
	private String bhxl;// 病害小类
	private String bhzl;// 病害子类
	private String zbx;// 坐标x
	private String zby;// 坐标y
	private String zllx;// 、资料类型
	private String zlzp;// 、资料照片

	public String getRwlx() {
		return rwlx;
	}

	public void setRwlx(String rwlx) {
		this.rwlx = rwlx;
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

	public String getBglx() {
		return bglx;
	}

	public void setBglx(String bglx) {
		this.bglx = bglx;
	}

	public String getGlbm() {
		return glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getYhdh() {
		return yhdh;
	}

	public void setYhdh(String yhdh) {
		this.yhdh = yhdh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getJlbh() {
		return jlbh;
	}

	public void setJlbh(String jlbh) {
		this.jlbh = jlbh;
	}

	public String getFldm() {
		return fldm;
	}

	public void setFldm(String fldm) {
		this.fldm = fldm;
	}

	public String getTqdm() {
		return tqdm;
	}

	public void setTqdm(String tqdm) {
		this.tqdm = tqdm;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getXsfx() {
		return xsfx;
	}

	public void setXsfx(String xsfx) {
		this.xsfx = xsfx;
	}

	public String getCdh() {
		return cdh;
	}

	public void setCdh(String cdh) {
		this.cdh = cdh;
	}

	public String getBhdl() {
		return bhdl;
	}

	public void setBhdl(String bhdl) {
		this.bhdl = bhdl;
	}

	public String getBhxl() {
		return bhxl;
	}

	public void setBhxl(String bhxl) {
		this.bhxl = bhxl;
	}

	public String getBhzl() {
		return bhzl;
	}

	public void setBhzl(String bhzl) {
		this.bhzl = bhzl;
	}

	public String getZbx() {
		return zbx;
	}

	public void setZbx(String zbx) {
		this.zbx = zbx;
	}

	public String getZby() {
		return zby;
	}

	public void setZby(String zby) {
		this.zby = zby;
	}

	public String getZllx() {
		return zllx;
	}

	public void setZllx(String zllx) {
		this.zllx = zllx;
	}

	public String getZlzp() {
		return zlzp;
	}

	public void setZlzp(String zlzp) {
		this.zlzp = zlzp;
	}

	public XCHBBean(JSONObject json) {
		super(json);
	}

	@Override
	public void parse(JSONObject json) throws JSONException {
		this.rwlx = json.getString("rwlx");
		this.rwbh = json.getString("rwbh");
		this.rwmc = json.getString("rwmc");
		this.bglx = json.getString("bglx");
		this.glbm = json.getString("glbm");
		this.bmmc = json.getString("bmmc");
		this.yhdh = json.getString("yhdh");
		this.xm = json.getString("xm");
		this.sbsj = json.getString("sbsj");
		this.jlbh = json.getString("jlbh");
		this.fldm = json.getString("fldm");
		this.tqdm = json.getString("tqdm");
		this.zh = json.getString("zh");
		this.ms = json.getString("ms");
		this.xsfx = json.getString("xsfx");
		this.cdh = json.getString("cdh");
		this.bhdl = json.getString("bhdl");
		this.bhxl = json.getString("bhxl");
		this.bhzl = json.getString("bhzl");
		this.zbx = json.getString("zbx");
		this.zby = json.getString("zby");
		this.zllx = json.getString("zllx");
		this.zlzp = json.getString("zlzp");
	}

}

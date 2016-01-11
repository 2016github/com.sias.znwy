package com.sias.znwy.demo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sias.znwy.web.util.IWebBeanParam;

public class GZJHBean extends AbstractBean implements IWebBeanParam, Serializable {
	private String rwlx;// 任务类型
	private String rwbh;// 任务编号
	private String rwmc;// 任务名称
	private String dwbh;// 单位编号
	private String dwmc;// 单位编号
	private String yhdh;// 用户代号
	private String yhxm;// 用户姓名
	private String gwlx;// 岗位类型
	private String gw;// 岗位
	private String sbsj;// 上报时间
	private String tqdm;// 天气代码
	private String fldm;// 风力代码
	private String jhlx;// 计划类型（1指令性、2非指令性）
	private String jlbh;// 编号（有任务单的按计划任务记录编号）
	private String zh;// 桩号
	private String ms;// 米数
	private String xsfx;// 行驶方向
	private String cdh;// 车道号
	private String gzgy;// 工作概要
	private String gcgl;// 工程概量

	public GZJHBean(JSONObject json) {
		super(json);
	}

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

	public String getDwbh() {
		return dwbh;
	}

	public void setDwbh(String dwbh) {
		this.dwbh = dwbh;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getYhdh() {
		return yhdh;
	}

	public void setYhdh(String yhdh) {
		this.yhdh = yhdh;
	}

	public String getYhxm() {
		return yhxm;
	}

	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}

	public String getGwlx() {
		return gwlx;
	}

	public void setGwlx(String gwlx) {
		this.gwlx = gwlx;
	}

	public String getGw() {
		return gw;
	}

	public void setGw(String gw) {
		this.gw = gw;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getTqdm() {
		return tqdm;
	}

	public void setTqdm(String tqdm) {
		this.tqdm = tqdm;
	}

	public String getFldm() {
		return fldm;
	}

	public void setFldm(String fldm) {
		this.fldm = fldm;
	}

	public String getJhlx() {
		return jhlx;
	}

	public void setJhlx(String jhlx) {
		this.jhlx = jhlx;
	}

	public String getJlbh() {
		return jlbh;
	}

	public void setJlbh(String jlbh) {
		this.jlbh = jlbh;
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

	public String getGzgy() {
		return gzgy;
	}

	public void setGzgy(String gzgy) {
		this.gzgy = gzgy;
	}

	public String getGcgl() {
		return gcgl;
	}

	public void setGcgl(String gcgl) {
		this.gcgl = gcgl;
	}

	@Override
	public void parse(JSONObject json) throws JSONException {
		this.rwlx = json.getString("rwlx");
		this.rwbh = json.getString("rwbh");
		this.rwmc = json.getString("rwmc");
		this.dwbh = json.getString("dwbh");
		this.dwmc = json.getString("dwmc");

		this.yhdh = json.getString("yhdh");
		this.yhxm = json.getString("yhxm");
		this.gwlx = json.getString("gwlx");
		this.gw = json.getString("gw");

		this.sbsj = json.getString("sbsj");
		this.tqdm = json.getString("tqdm");
		this.fldm = json.getString("fldm");
		this.jhlx = json.getString("jhlx");
		this.jlbh = json.getString("jlbh");
		this.zh = json.getString("zh");
		this.ms = json.getString("ms");

		this.xsfx = json.getString("xsfx");
		this.cdh = json.getString("cdh");
		this.gzgy = json.getString("gzgy");

		this.gcgl = json.getString("gcgl");

	}

}

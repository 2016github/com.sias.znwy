package com.sias.znwy.demo;

import java.io.Serializable;

import com.sias.znwy.web.util.IWebBeanParam;

public class Tusbkey implements IWebBeanParam, Serializable{
	private String bmcc;
	private String pin;
	private String xm;
	private String gw;
	private String rywz;
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getGw() {
		return gw;
	}

	public void setGw(String gw) {
		this.gw = gw;
	}

	public String getRywz() {
		return rywz;
	}

	public void setRywz(String rywz) {
		this.rywz = rywz;
	}

	public String getBmcc() {
		return bmcc;
	}

	public void setBmcc(String bmcc) {
		this.bmcc = bmcc;
	}

	
}

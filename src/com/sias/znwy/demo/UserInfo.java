package com.sias.znwy.demo;
import java.io.Serializable;

import com.sias.znwy.web.util.IWebBeanParam;
public class UserInfo implements IWebBeanParam, Serializable {
	private String pin;
	private Tusbkey t_usbkey;
	public Tusbkey getT_usbkey() {
		return t_usbkey;
	}

	public void setT_usbkey(Tusbkey t_usbkey) {
		this.t_usbkey = t_usbkey;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	
}

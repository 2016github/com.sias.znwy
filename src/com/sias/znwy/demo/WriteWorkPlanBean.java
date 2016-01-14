package com.sias.znwy.demo;

import java.io.Serializable;

import com.sias.znwy.web.util.IWebBeanParam;

public class WriteWorkPlanBean implements IWebBeanParam, Serializable {
	private int type;
	private String memo;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}

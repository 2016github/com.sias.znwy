package com.sias.znwy.demo;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * ���ݶ���
 * 
 * 
 */
public abstract class AbstractBean implements java.io.Serializable {

	private static final long serialVersionUID = -2336283255860974476L;

	public AbstractBean(JSONObject jsonObject) throws JSONException {
		parse(jsonObject);
	}

	public AbstractBean() {
	}

	/**
	 * ����Json����
	 * 
	 * @param json
	 */
	public abstract void parse(JSONObject json) throws JSONException;
}

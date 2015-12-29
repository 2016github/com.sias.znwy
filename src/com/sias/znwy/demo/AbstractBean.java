package com.sias.znwy.demo;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 数据对象
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
	 * 解析Json对象
	 * 
	 * @param json
	 */
	public abstract void parse(JSONObject json) throws JSONException;
}

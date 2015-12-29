package com.sias.znwy.web.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebParam extends IWebKVParam {

	private Map<String, String> map = new HashMap<String, String>();

	public WebParam() {

	}

	public void put(String key, String value) {
		map.put(key, value);
	}

	public Map<String, String> getMap() {
		return map;
	}

	public WebParam addParam(String key, String value) {
		map.put(key, value);
		return this;
	}

	public WebParam addParam(String key, int value) {
		map.put(key, String.valueOf(value));
		return this;
	}

	public WebParam addParam(String key, long value) {
		map.put(key, String.valueOf(value));
		return this;
	}

	public WebParam addParam(String key, double value) {
		map.put(key, String.valueOf(value));
		return this;
	}

	public static WebParam create() {
		return new WebParam();
	}
}

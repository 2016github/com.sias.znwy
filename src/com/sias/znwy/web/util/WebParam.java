package com.sias.znwy.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebParam extends IWebKVParam {

	private Map<String, String> map = new HashMap<String, String>();

	public WebParam() {

	}

	public void put(String key, String value) {
		map.put(key, value);
	}


	protected Map<String, String> getMap() {
		return map;
	}

}

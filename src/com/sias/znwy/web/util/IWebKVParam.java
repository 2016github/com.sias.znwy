package com.sias.znwy.web.util;

import java.util.List;
import java.util.Map;

public abstract class IWebKVParam {

	public abstract void put(String key, String value);
	protected abstract Map<String, String> getMap();
}
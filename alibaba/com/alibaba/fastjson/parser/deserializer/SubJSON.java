package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;

public class SubJSON extends JSON {
	public static String toJSONString(Object obj){
		return JSON.toJSONString(obj);
	}
}

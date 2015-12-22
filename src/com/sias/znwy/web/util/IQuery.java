package com.sias.znwy.web.util;

import com.sias.znwy.web.util.CustomRequest.OnRespListener;



/**
 * 请求代理类
 * 
 * @author zzh
 * 
 */
public interface IQuery {
	
	public final static int ADDTYPE  = 1;//新增
	public final static int MODTYPE  = 2;//修改
	public final static int DELTYPE  = 3;//删除

	/**
	 * 以Map键值对的形式提交请求
	 * 
	 * @param cmd
	 *            命令值
	 * @param map
	 *            Map键值对
	 * @param listener
	 *            服务器响应监听器
	 * @return
	 */
	public IReq query( IWebKVParam param, OnRespListener listener);
}
package com.sias.znwy.Util;

/**
 * 签到规则
 * 
 */
public class SignRule {
	// yhdh:用户代号,sxsj:生效时间,qdkssj:签到开始时间,qdjssj:签到结束时间,jgxss:间隔小时数,yxpcfz:允许偏差分钟数}
	public static String yhdh;
	public static String sxsj;
	public static String qdkssj;
	public static String qdjssj;
	public static String jgxss;
	public static String yxpcfz;

	public static String getYhdh() {
		return yhdh;
	}

	public static void setYhdh(String yhdh) {
		SignRule.yhdh = yhdh;
	}

	public static String getSxsj() {
		return sxsj;
	}

	public static void setSxsj(String sxsj) {
		SignRule.sxsj = sxsj;
	}

	public static String getQdkssj() {
		return qdkssj;
	}

	public static void setQdkssj(String qdkssj) {
		SignRule.qdkssj = qdkssj;
	}

	public static String getQdjssj() {
		return qdjssj;
	}

	public static void setQdjssj(String qdjssj) {
		SignRule.qdjssj = qdjssj;
	}

	public static String getJgxss() {
		return jgxss;
	}

	public static void setJgxss(String jgxss) {
		SignRule.jgxss = jgxss;
	}

	public static String getYxpcfz() {
		return yxpcfz;
	}

	public static void setYxpcfz(String yxpcfz) {
		SignRule.yxpcfz = yxpcfz;
	}

}

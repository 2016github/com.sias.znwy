package com.sias.znwy.demo;

import java.io.Serializable;

import com.sias.znwy.web.util.IWebBeanParam;

public class WyAutoupdate implements IWebBeanParam ,Serializable {
	private String devicetype;
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public String getDownloadurl() {
		return downloadurl;
	}
	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public String getPubuser() {
		return pubuser;
	}
	public void setPubuser(String pubuser) {
		this.pubuser = pubuser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUpdatelevel() {
		return updatelevel;
	}
	public void setUpdatelevel(String updatelevel) {
		this.updatelevel = updatelevel;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	private String downloadurl;
	private String id;
	private String info;
	private String isuse;
	private String pubtime;
	private String pubuser;
	private String remark;
	private String updatelevel;
	private String version;
}

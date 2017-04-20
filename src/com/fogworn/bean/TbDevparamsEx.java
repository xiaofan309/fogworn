package com.fogworn.bean;

public class TbDevparamsEx extends TbDevparams{
	private String devno;//   设备编号
	private String devnm;//   设备名称
	private String devip;//   设备ip
	private String onlinetime;//   上线时间
	private String offlinetime;//   离线时间
	private String curstate;//   当前状态
	private String devtype;//   设备类型
	
	public String getDevno() {
	    return this.devno;
	}
	public void setDevno(String devno) {
	    this.devno=devno;
	}
	public String getDevnm() {
	    return this.devnm;
	}
	public void setDevnm(String devnm) {
	    this.devnm=devnm;
	}
	public String getDevip() {
	    return this.devip;
	}
	public void setDevip(String devip) {
	    this.devip=devip;
	}
	public String getOnlinetime() {
	    return this.onlinetime;
	}
	public void setOnlinetime(String onlinetime) {
	    this.onlinetime=onlinetime;
	}
	public String getOfflinetime() {
	    return this.offlinetime;
	}
	public void setOfflinetime(String offlinetime) {
	    this.offlinetime=offlinetime;
	}
	public String getCurstate() {
	    return this.curstate;
	}
	public void setCurstate(String curstate) {
	    this.curstate=curstate;
	}
	public String getDevtype() {
	    return this.devtype;
	}
	public void setDevtype(String devtype) {
	    this.devtype=devtype;
	}
}

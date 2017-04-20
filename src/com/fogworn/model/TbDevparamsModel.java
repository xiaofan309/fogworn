package com.fogworn.model;

import com.base.BaseModel;

public class TbDevparamsModel extends BaseModel {
	
	private String nightenable;//   
	
	private String devno;//   设备编号
	private String devnm;//   设备名称
	private String devip;//   设备ip
	private String onlinetime;//   上线时间
	private String offlinetime;//   离线时间
	private String curstate;//   当前状态
	private String devtype;//   设备类型
	
	public String getNightenable() {
		return nightenable;
	}
	public void setNightenable(String nightenable) {
		this.nightenable = nightenable;
	}
	public String getDevno() {
		return devno;
	}
	public void setDevno(String devno) {
		this.devno = devno;
	}
	public String getDevnm() {
		return devnm;
	}
	public void setDevnm(String devnm) {
		this.devnm = devnm;
	}
	public String getCurstate() {
		return curstate;
	}
	public void setCurstate(String curstate) {
		this.curstate = curstate;
	}
	public String getDevip() {
		return devip;
	}
	public void setDevip(String devip) {
		this.devip = devip;
	}
	public String getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}
	public String getOfflinetime() {
		return offlinetime;
	}
	public void setOfflinetime(String offlinetime) {
		this.offlinetime = offlinetime;
	}
	public String getDevtype() {
		return devtype;
	}
	public void setDevtype(String devtype) {
		this.devtype = devtype;
	}
	
}
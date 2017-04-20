package com.fogworn.bean;

import com.base.BaseBean;

public class TbDevparams extends BaseBean {
	
	
	private Integer guidlights;//   诱导灯台数
	private Integer nightenable;
	
	private Integer limitspeed;//   限速值
	private Integer sendtype;// 发送方式
	private Integer worktype;// 工作模式
	private Integer measuretype;// 浓见度测量方式
	private Integer measurenum;// 浓见度测量固定值
	private Integer lightopen;// 尾迹开启：1是0否
	private Integer lighttime;// 尾迹时间
	
	public Integer getNightenable() {
		return nightenable;
	}
	public void setNightenable(Integer nightenable) {
		this.nightenable = nightenable;
	}
	public Integer getLimitspeed() {
		return limitspeed;
	}
	public void setLimitspeed(Integer limitspeed) {
		this.limitspeed = limitspeed;
	}
	public Integer getSendtype() {
		return sendtype;
	}
	public void setSendtype(Integer sendtype) {
		this.sendtype = sendtype;
	}
	public Integer getWorktype() {
		return worktype;
	}
	public void setWorktype(Integer worktype) {
		this.worktype = worktype;
	}
	public Integer getMeasuretype() {
		return measuretype;
	}
	public void setMeasuretype(Integer measuretype) {
		this.measuretype = measuretype;
	}
	public Integer getMeasurenum() {
		return measurenum;
	}
	public void setMeasurenum(Integer measurenum) {
		this.measurenum = measurenum;
	}
	public Integer getLightopen() {
		return lightopen;
	}
	public void setLightopen(Integer lightopen) {
		this.lightopen = lightopen;
	}
	public Integer getLighttime() {
		return lighttime;
	}
	public void setLighttime(Integer lighttime) {
		this.lighttime = lighttime;
	}
}
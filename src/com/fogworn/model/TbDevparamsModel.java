package com.fogworn.model;

import com.base.BaseModel;

public class TbDevparamsModel extends BaseModel {
		private Integer id;//   	private String devid;//   关联设备	private String devstate;//   系统状态：开启系统、关闭系统	private Integer visibility;//   能见度值	private Integer guidlights;//   诱导灯台数	private Integer luminance;//   LED亮度等级	private Integer flickerfrequency;//   边界同步闪烁频率	private Integer luminancethreshold;//   夜间开启照度阈值	private Integer workpoint;//   产品工作数量
	private String nightenable;//   
	
	private String devno;//   设备编号
	private String devnm;//   设备名称
	private String devip;//   设备ip
	private String onlinetime;//   上线时间
	private String offlinetime;//   离线时间
	private String curstate;//   当前状态
	private String devtype;//   设备类型
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getDevid() {	    return this.devid;	}	public void setDevid(String devid) {	    this.devid=devid;	}	public String getDevstate() {	    return this.devstate;	}	public void setDevstate(String devstate) {	    this.devstate=devstate;	}	public Integer getVisibility() {	    return this.visibility;	}	public void setVisibility(Integer visibility) {	    this.visibility=visibility;	}	public Integer getGuidlights() {	    return this.guidlights;	}	public void setGuidlights(Integer guidlights) {	    this.guidlights=guidlights;	}	public Integer getLuminance() {	    return this.luminance;	}	public void setLuminance(Integer luminance) {	    this.luminance=luminance;	}	public Integer getFlickerfrequency() {	    return this.flickerfrequency;	}	public void setFlickerfrequency(Integer flickerfrequency) {	    this.flickerfrequency=flickerfrequency;	}	public Integer getLuminancethreshold() {	    return this.luminancethreshold;	}	public void setLuminancethreshold(Integer luminancethreshold) {	    this.luminancethreshold=luminancethreshold;	}	public Integer getWorkpoint() {	    return this.workpoint;	}	public void setWorkpoint(Integer workpoint) {	    this.workpoint=workpoint;	}
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

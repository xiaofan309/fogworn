package com.fogworn.bean;

import com.base.BaseBean;

public class TbDevparams extends BaseBean {
	
		private Integer id;//   	private Integer devid;//   关联设备	private Integer devstate;//   系统状态：开启系统、关闭系统	private Integer visibility;//   能见度值
	private Integer guidlights;//   诱导灯台数	private Integer luminance;//   LED亮度等级	private Integer flickerfrequency;//   边界同步闪烁频率	private Integer luminancethreshold;//   夜间开启照度阈值	private Integer workpoint;//   产品工作数量 
	private Integer nightenable;//	夜间自动开启关闭
	
	private Integer limitspeed;//   限速值
	private Integer sendtype;// 发送方式
	private Integer worktype;// 工作模式
	private Integer measuretype;// 能见度测量方式
	private Integer measurenum;// 能见度测量固定值
	private Integer lightopen;// 尾迹开启：1是0否
	private Integer lighttime;// 尾迹时间
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getDevid() {	    return this.devid;	}	public void setDevid(Integer devid) {	    this.devid=devid;	}	public Integer getDevstate() {	    return this.devstate;	}	public void setDevstate(Integer devstate) {	    this.devstate=devstate;	}	public Integer getVisibility() {	    return this.visibility;	}	public void setVisibility(Integer visibility) {	    this.visibility=visibility;	}	public Integer getGuidlights() {	    return this.guidlights;	}	public void setGuidlights(Integer guidlights) {	    this.guidlights=guidlights;	}	public Integer getLuminance() {	    return this.luminance;	}	public void setLuminance(Integer luminance) {	    this.luminance=luminance;	}	public Integer getFlickerfrequency() {	    return this.flickerfrequency;	}	public void setFlickerfrequency(Integer flickerfrequency) {	    this.flickerfrequency=flickerfrequency;	}	public Integer getLuminancethreshold() {	    return this.luminancethreshold;	}	public void setLuminancethreshold(Integer luminancethreshold) {	    this.luminancethreshold=luminancethreshold;	}	public Integer getWorkpoint() {	    return this.workpoint;	}	public void setWorkpoint(Integer workpoint) {	    this.workpoint=workpoint;	}
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

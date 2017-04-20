package com.fogworn.model;

import java.util.Date;

import com.base.BaseModel;

public class TbVisibHistoryModel extends BaseModel {
	
		private Integer devid;//   	private Integer visibility;//   	private Date beginDt;//   	private Date endDt;//   	public Integer getDevid() {	    return this.devid;	}	public void setDevid(Integer devid) {	    this.devid=devid;	}	public Integer getVisibility() {	    return this.visibility;	}	public void setVisibility(Integer visibility) {	    this.visibility=visibility;	}
	public Date getBeginDt() {
		return beginDt;
	}
	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}}

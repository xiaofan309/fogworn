package com.fogworn.model;

import java.util.Date;

import com.base.BaseModel;

public class TbVisibHistoryModel extends BaseModel {
	
	
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
	}
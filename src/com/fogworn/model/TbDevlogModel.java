package com.fogworn.model;

import com.base.BaseModel;

public class TbDevlogModel extends BaseModel {
	

	
	
	private String devnm;//设备名称
	private String devno;//设备编号
	
	public String getDevnm() {
		return devnm;
	}
	public void setDevnm(String devnm) {
		this.devnm = devnm;
	}
	public String getDevno() {
		return devno;
	}
	public void setDevno(String devno) {
		this.devno = devno;
	}
	
}
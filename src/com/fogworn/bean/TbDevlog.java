package com.fogworn.bean;

import com.base.BaseBean;

public class TbDevlog extends BaseBean {
	
	
	
	private String devnm;//设备名称
	private String devno;//设备编号
	
	private Integer flag = 1;//默认值为1
	
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
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
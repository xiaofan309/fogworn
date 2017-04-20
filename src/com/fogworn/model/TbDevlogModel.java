package com.fogworn.model;

import com.base.BaseModel;

public class TbDevlogModel extends BaseModel {
	

		private Integer id;//   	private Integer devid;//   所属设备id，关联设备表主键	private Integer pointid;//   故障或路障产品ID	private String commtime;//   通信时间	private String devonlinetime;//   设备上线时间	private String notes;//   备注
	
	private String devnm;//设备名称
	private String devno;//设备编号
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getDevid() {	    return this.devid;	}	public void setDevid(Integer devid) {	    this.devid=devid;	}	public Integer getPointid() {	    return this.pointid;	}	public void setPointid(Integer pointid) {	    this.pointid=pointid;	}	public String getCommtime() {	    return this.commtime;	}	public void setCommtime(String commtime) {	    this.commtime=commtime;	}	public String getDevonlinetime() {	    return this.devonlinetime;	}	public void setDevonlinetime(String devonlinetime) {	    this.devonlinetime=devonlinetime;	}	public String getNotes() {	    return this.notes;	}	public void setNotes(String notes) {	    this.notes=notes;	}
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

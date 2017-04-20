package com.fogworn.service;

import java.util.List;

import com.base.BaseModel;
import com.fogworn.bean.TbDev;

public interface TbDevService{
	
	TbDev queryById(Object id) throws Exception;
	
	TbDev queryByDevno(Object devno) throws Exception;
	
	void add(TbDev t)  throws Exception;
	
	void update(TbDev t)  throws Exception;
	
	void delete(Object... ids) throws Exception;
	
	List<TbDev> queryByList(BaseModel model) throws Exception;
	
	void updateBySelective(TbDev t);
	
	public void updateSelectiveByDevno(Object tbDev);
	
	public void updateSelectiveByAll(Object tbDev);
}

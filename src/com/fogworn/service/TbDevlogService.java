package com.fogworn.service;

import java.util.List;

import com.base.BaseModel;
import com.fogworn.bean.TbDevlog;


public interface TbDevlogService {

	TbDevlog queryById(Object id) throws Exception;
	
	void add(TbDevlog t)  throws Exception;
	
	void update(TbDevlog t)  throws Exception;
	
	void delete(Object... ids) throws Exception;
	
	List<TbDevlog> queryByList(BaseModel model) throws Exception;
}

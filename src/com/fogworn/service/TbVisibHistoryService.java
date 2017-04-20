package com.fogworn.service;

import java.util.List;

import com.base.BaseModel;
import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbVisibHistory;

public interface TbVisibHistoryService {
	
	TbVisibHistory queryById(Object id) throws Exception;
	void add(TbVisibHistory t)  throws Exception;
	void update(TbVisibHistory t)  throws Exception;
	void updateBySelective(TbVisibHistory t)  throws Exception;
	void delete(Object... ids) throws Exception;
	List<TbVisibHistory> queryByList(BaseModel model) throws Exception;
	List<TbVisibHistory> listByDevid(BaseModel model) throws Exception;
	
	List<TbDev> devList() throws Exception;
}

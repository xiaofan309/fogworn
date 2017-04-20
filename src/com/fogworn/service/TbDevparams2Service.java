package com.fogworn.service;

import java.util.List;

import com.base.BaseModel;
import com.fogworn.bean.TbDevparams;
import com.fogworn.bean.TbDevparams2;

public interface TbDevparams2Service {
	
	TbDevparams2 queryById(Object id) throws Exception;
	void add(TbDevparams2 t)  throws Exception;
	void update(TbDevparams2 t)  throws Exception;
	void updateBySelective(TbDevparams2 t)  throws Exception;
	void delete(Object... ids) throws Exception;
	List<TbDevparams2> queryByList(BaseModel model) throws Exception;
	
	void update2(TbDevparams t);
	TbDevparams2 queryByParamIdAndWorkType(Integer paramid, Integer worktype) throws Exception;
}

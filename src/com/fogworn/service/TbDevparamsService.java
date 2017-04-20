package com.fogworn.service;

import java.util.List;
import java.util.Map;

import com.base.BaseModel;
import com.fogworn.bean.TbDevparams;
import com.fogworn.bean.TbDevparamsEx;

public interface TbDevparamsService {
	
	TbDevparams queryById(Object id) throws Exception;
	
	TbDevparamsEx queryExById(Object id) throws Exception;
	
	void update(TbDevparams t)  throws Exception;
	
	void add(TbDevparams t)  throws Exception;
	
	void delete(Object... ids) throws Exception;
	
	List<TbDevparams> queryByList(BaseModel model) throws Exception;
	
	List<TbDevparamsEx> queryExByList(BaseModel model) throws Exception;
	
	/**
	 * 设置
	 * @param paramName
	 * @param type
	 * @param state
	 * @param devparamsEx
	 * @return 返回null表示成功。否则返回错误信息。
	 */
	String sendParam(String paramName, Integer type, Integer state, TbDevparamsEx devparamsEx);
	
	void updateSelectiveByDevId(Object t);
	
	Map<String,Object> getLogs(Integer devid) throws Exception;
	
	void clearLogs(Integer devid, String logIds) throws Exception;
}

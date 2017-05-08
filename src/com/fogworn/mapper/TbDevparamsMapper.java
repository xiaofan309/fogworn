package com.fogworn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.BaseMapper;
import com.base.BaseModel;
import com.fogworn.bean.TbDevlog;
import com.fogworn.bean.TbDevparamsEx;

/**
 * TbDevparams Mapper
 * @author Administrator
 *
 */
public interface TbDevparamsMapper<T> extends BaseMapper<T> {
	
	public void deleteByDevId(Object devid);
	
	public int queryExByCount(BaseModel model);
	
	public List<TbDevparamsEx> queryExByList(BaseModel model);
	
	public TbDevparamsEx queryExById(Object id);
	
	public TbDevparamsEx queryExByDevId(Object devid);
	
	public void updateSelectiveByDevId(Object t);
	
	List<TbDevlog> getLogs(Integer devid);
	
	int clearLogs(@Param("devid")Integer devid, @Param("logIds")String[] logIds);

	
}

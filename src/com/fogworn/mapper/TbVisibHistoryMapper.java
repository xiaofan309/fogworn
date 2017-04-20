package com.fogworn.mapper;

import java.util.List;

import com.base.BaseMapper;
import com.base.BaseModel;
import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbVisibHistory;

/**
 * TbVisibHistory Mapper
 * @author Administrator
 *
 */
public interface TbVisibHistoryMapper<T> extends BaseMapper<T> {
	
	List<TbVisibHistory> listByDevid(BaseModel model);
	
	List<TbDev> devList();
}

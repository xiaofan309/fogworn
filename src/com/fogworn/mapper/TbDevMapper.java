package com.fogworn.mapper;

import com.base.BaseMapper;

/**
 * TbDev Mapper
 * @author Administrator
 *
 */
public interface TbDevMapper<T> extends BaseMapper<T> {
	public T queryByDevno(Object devno);
	
	public void updateSelectiveByDevno(Object tbDev);
	
	public void updateSelectiveByAll(Object tbDev);
}

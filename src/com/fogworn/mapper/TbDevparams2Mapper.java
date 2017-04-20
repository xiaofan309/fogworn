package com.fogworn.mapper;

import org.apache.ibatis.annotations.Param;

import com.base.BaseMapper;
import com.fogworn.bean.TbDevparams;
import com.fogworn.bean.TbDevparams2;

/**
 * TbDevparams2 Mapper
 * @author Administrator
 *
 */
public interface TbDevparams2Mapper<T> extends BaseMapper<T> {
	
	void update2(TbDevparams t);
	TbDevparams2 queryByParamIdAndWorkType(@Param("id")Integer id, @Param("worktype")Integer worktype);
}

package com.fogworn.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.fogworn.bean.TbDevparams;
import com.fogworn.bean.TbDevparams2;
import com.fogworn.service.TbDevparams2Service;
import com.fogworn.mapper.TbDevparams2Mapper;

@Service("tbDevparams2Service")
public class TbDevparams2ServiceImpl extends BaseService<TbDevparams2> implements TbDevparams2Service {
	private final static Logger log= Logger.getLogger(TbDevparams2ServiceImpl.class);

	@Autowired
    private TbDevparams2Mapper<TbDevparams2> mapper;
		
	public TbDevparams2Mapper<TbDevparams2> getMapper() {
		return mapper;
	}

	public TbDevparams2 queryByParamIdAndWorkType(Integer paramid,
			Integer worktype) throws Exception {
		return mapper.queryByParamIdAndWorkType(paramid, worktype);
	}

	public void update2(TbDevparams t) {
		mapper.update2(t);
	}

}

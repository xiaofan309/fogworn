package com.fogworn.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbDevparams;
import com.fogworn.mapper.TbDevMapper;
import com.fogworn.mapper.TbDevparamsMapper;
import com.fogworn.service.TbDevService;

/**
 * 
 */
@Service("tbDevService")
public class TbDevServiceImpl extends BaseService<TbDev> implements TbDevService {

	@Autowired
    private TbDevMapper<TbDev> mapper;
	@Autowired
	private TbDevparamsMapper<TbDevparams> tbDevparamsMapper;
	
	private final static Logger log= Logger.getLogger(TbDevServiceImpl.class);
	
	public void add(TbDev t)  throws Exception{
		mapper.add(t);
		TbDevparams param = new TbDevparams();
		param.setDevid( t.getId());
		tbDevparamsMapper.add(param);
	}
	
	public void delete(Object... ids) throws Exception{
		if(ids == null || ids.length < 1){
			return;
		}
		for(Object id : ids ){
			tbDevparamsMapper.deleteByDevId(id);
			mapper.delete(id);
		}
	}
		
	public TbDevMapper<TbDev> getMapper() {
		return mapper;
	}

//	@Override
	public TbDev queryByDevno(Object devno) throws Exception {
		return mapper.queryByDevno(devno);
	}

//	@Override
	public void updateSelectiveByDevno(Object tbDev) {
		mapper.updateSelectiveByDevno(tbDev);
	}
	
//	@Override
	public void updateSelectiveByAll(Object tbDev) {
		mapper.updateSelectiveByAll(tbDev);
	}

}

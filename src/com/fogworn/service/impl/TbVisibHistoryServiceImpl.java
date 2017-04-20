package com.fogworn.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.BaseModel;
import com.base.BaseService;
import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbVisibHistory;
import com.fogworn.service.TbVisibHistoryService;
import com.fogworn.mapper.TbVisibHistoryMapper;

@Service("tbVisibHistoryService")
public class TbVisibHistoryServiceImpl extends BaseService<TbVisibHistory> implements TbVisibHistoryService {
	private final static Logger log= Logger.getLogger(TbVisibHistoryServiceImpl.class);

	@Autowired
    private TbVisibHistoryMapper<TbVisibHistory> mapper;
		
	public TbVisibHistoryMapper<TbVisibHistory> getMapper() {
		return mapper;
	}

//	@Override
	public List<TbVisibHistory> listByDevid(BaseModel model) throws Exception {
		return mapper.listByDevid(model);
	}

//	@Override
	public List<TbDev> devList() throws Exception {
		return mapper.devList();
	}

}

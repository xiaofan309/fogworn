package com.fogworn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.fogworn.bean.TbDevlog;
import com.fogworn.mapper.TbDevlogMapper;
import com.fogworn.service.TbDevlogService;

/**
 * 功能：TbDevlogService
 */
@Service("tbDevlogService")
public class TbDevlogServiceImpl extends BaseService<TbDevlog> implements
		TbDevlogService {
	@Autowired
	private TbDevlogMapper<TbDevlog> mapper;

	public TbDevlogMapper<TbDevlog> getMapper() {
		return mapper;
	}
}

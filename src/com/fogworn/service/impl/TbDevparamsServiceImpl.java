package com.fogworn.service.impl;

import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.BaseModel;
import com.base.BaseService;
import com.desun.protocal.DesunProtocal;
import com.desun.protocal.FogWarnExinterface;
import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbDevlog;
import com.fogworn.bean.TbDevparams;
import com.fogworn.bean.TbDevparamsEx;
import com.fogworn.mapper.TbDevMapper;
import com.fogworn.mapper.TbDevparamsMapper;
import com.fogworn.service.TbDevparams2Service;
import com.fogworn.service.TbDevparamsService;

@Service("tbDevparamsService")
public class TbDevparamsServiceImpl extends BaseService<TbDevparams> implements TbDevparamsService {
	private final static Logger log= Logger.getLogger(TbDevparams2ServiceImpl.class);
	@Autowired
    private TbDevparamsMapper<TbDevparams> mapper;
	@Autowired
	private TbDevMapper<TbDev> tbDevmapper;
	@Autowired
	private FogWarnExinterface fogWarnExinterface;
	@Autowired
	private TbDevparams2Service tbDevparams2Service;

	public TbDevparamsMapper<TbDevparams> getMapper() {
		return mapper;
	}
	
	public String sendParam(String paramName, Integer type, Integer state, TbDevparamsEx obj){
		String result = null;
		String suc = DesunProtocal.RESPONSE_SUCESS;
		if(obj.getId()==null || obj.getDevid()==null || paramName==null || "".equals(paramName)){
			return "缺少必要的参数";
		}
		TbDev dev = tbDevmapper.queryById(obj.getDevid());
		TbDevparams param = null;
		if("sendtype".equals(paramName)){//发送方式设置
			result = fogWarnExinterface.passDataTransType(dev, obj.getSendtype());
			if(suc.equals(result)){
				param = new TbDevparams();
				param.setId(obj.getId());
				param.setSendtype(obj.getSendtype());
				mapper.updateBySelective(param);
			}
		}else if("nightenable".equals(paramName)){//夜间自动启动
			result = fogWarnExinterface.nightAutoOn(dev, obj.getNightenable().intValue()==1 );
			if(suc.equals(result)){
				param = new TbDevparams();
				param.setId(obj.getId());
				param.setNightenable(obj.getNightenable());
				mapper.updateBySelective(param);
			}
		}else if("measuretype".equals(paramName)){//能见度测量方式
			result = fogWarnExinterface.visibilityDeal(dev, obj.getMeasuretype().intValue()==3 ? obj.getMeasurenum():obj.getMeasuretype());
			if(suc.equals(result)){
				param = new TbDevparams();
				param.setId(obj.getId());
				param.setMeasuretype(obj.getMeasuretype());
				param.setMeasurenum(obj.getMeasurenum());
				mapper.updateBySelective(param);
			}
		}else if("params".equals(paramName)){//
			if( obj.getWorktype().intValue() == 0){
				result = fogWarnExinterface.fogParam(dev, obj.getFlickerfrequency(), obj.getLuminance(), obj.getGuidlights(), obj.getLightopen().intValue()==1,
						obj.getLighttime());
				if(suc.equals(result)){
					param = new TbDevparams();
					param.setId(obj.getId());
					param.setWorktype(obj.getWorktype());
					param.setGuidlights(obj.getGuidlights());
					param.setFlickerfrequency(obj.getFlickerfrequency());
					param.setLuminance(obj.getLuminance());
					param.setLightopen(obj.getLightopen());
					param.setLighttime(obj.getLighttime());
					mapper.updateBySelective(param);
					tbDevparams2Service.update2(param);
				}
			}else if( obj.getWorktype().intValue() == 1){
				result = fogWarnExinterface.nightParam(dev, obj.getFlickerfrequency(), obj.getLuminance(), obj.getGuidlights(), obj.getLightopen().intValue()==1,
						obj.getLighttime());
				if(suc.equals(result)){
					param = new TbDevparams();
					param.setId(obj.getId());
					param.setWorktype(obj.getWorktype());
					param.setGuidlights(obj.getGuidlights());
					param.setFlickerfrequency(obj.getFlickerfrequency());
					param.setLuminance(obj.getLuminance());
					param.setLightopen(obj.getLightopen());
					param.setLighttime(obj.getLighttime());
					mapper.updateBySelective(param);
					tbDevparams2Service.update2(param);
				}
			}
		}else if("devstate".equals(paramName)){
			/*if(type.intValue() == 0){//正常开关机
				if(state.intValue() == 1){//kaiji
					if(obj.getNightenable()!=null && obj.getNightenable().intValue()==1){
						//夜间自动开启
						result = fogWarnExinterface.nightOn(dev, obj.getLuminance());
					}else{
						//非夜间自动开启
						result = fogWarnExinterface.standardOn(dev, obj.getFlickerfrequency(), obj.getLuminance(), obj.getGuidlights(),
								obj.getLightopen().intValue()==1, obj.getLighttime());
					}
				}else{
					result = fogWarnExinterface.packStandardOff(dev);
				}
			}else */if(obj.getSendtype().intValue() == 0){//传递式开关机
				if(state.intValue() == 1){
					result = fogWarnExinterface.passOn(dev, obj.getFlickerfrequency(), obj.getLuminance(), obj.getGuidlights(),
							obj.getLightopen().intValue()==1, obj.getLighttime());
				}else{
					result = fogWarnExinterface.passOff(dev);
				}
			}else if(obj.getSendtype().intValue() == 1){//覆盖式开关机
				if(state.intValue() == 1){
					result = fogWarnExinterface.coverOn(dev, obj.getFlickerfrequency(), obj.getLuminance(), obj.getGuidlights(),
							obj.getLightopen().intValue()==1, obj.getLighttime());
				}else{
					result = fogWarnExinterface.coverOff(dev);
				}
			}
			//System.out.println(result);
			if(suc.equals(result)){
				obj.setDevstate(state);
				mapper.updateBySelective((TbDevparams)obj);
				result = "操作成功";
			}else{
				result = "当前系统开关过于频繁，请稍后再操作";
			}
		}else if("ledcontrol".equals(paramName)){//LED控制方式
			result = fogWarnExinterface.ledControlOn(dev, obj.getLedcontrol().intValue()==1 );
			if(suc.equals(result)){
				param = new TbDevparams();
				param.setId(obj.getId());
				param.setLedcontrol(obj.getLedcontrol());
				mapper.updateBySelective(param);
			}
		}else if("ledspeed".equals(paramName)){//LED控制方式
			result = fogWarnExinterface.ledSpeed(dev, obj.getLedspeed().intValue() );
			if(suc.equals(result)){
				param = new TbDevparams();
				param.setId(obj.getId());
				param.setLedspeed(obj.getLedspeed());
				mapper.updateBySelective(param);
			}
		}
		if(result == null)
			result = "指令解析失败";
//		if(suc.equals(result))
//			result = null;
		return result;
	}

//	@Override
	public TbDevparamsEx queryExById(Object id) throws Exception {
		return mapper.queryExById(id);
	}
	
	public TbDevparamsEx queryExByDevId(Object devid) throws Exception {
		return mapper.queryExByDevId(devid);
	}

//	@Override
	public List<TbDevparamsEx> queryExByList(BaseModel model) throws Exception {
		Integer rowCount = mapper.queryExByCount(model);
		model.getPager().setRowCount(rowCount);
		return mapper.queryExByList(model);
	}
	
	public void updateSelectiveByDevId(Object t){
		mapper.updateSelectiveByDevId(t);
	}

//	@Override
	public void clearLogs(Integer devid, String logIds) throws Exception {
		if(logIds != null && logIds.length()>0){
			String[] ids = logIds.split(",");
			mapper.clearLogs(devid, ids);
		}
	}

//	@Override
	public Map<String, Object> getLogs(Integer devid) throws Exception {
		String logIds = "",
				logContent = "";
		List<TbDevlog> logList = mapper.getLogs(devid);
		for(int i=0; logList!=null && i<logList.size(); i++){
			TbDevlog e = logList.get(i);
			logIds += e.getId()+",";
			logContent += e.getNotes()+"；<br>";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("logIds", logIds);
		result.put("logContent", logContent);
		return result;
	}
}

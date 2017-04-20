package com.fogworn.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.BaseAction;
import com.base.utils.HtmlUtil;
import com.fogworn.bean.TbDev;
import com.fogworn.model.TbDevModel;
import com.fogworn.service.TbDevService;
import com.sys.service.SysLogService;
 
@Controller
@RequestMapping("/tbDev") 
public class TbDevAction extends BaseAction{
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TbDevService tbDevService; 
	@Autowired(required=false)
	private SysLogService sysLogService;
	
	
	/**
	 * 
	 * @param url
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(TbDevModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		return forword("fogworn/tbDevList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(TbDevModel model,HttpServletResponse response) throws Exception{
		//model.setDeleted(0);
		List<TbDev> dataList = tbDevService.queryByList(model);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(TbDev bean,Integer[] typeIds,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			tbDevService.add(bean);
			sysLogService.addLog(request, "tb_dev", bean.getId(), "新增", "添加一条设备信息["+bean.getDevnm()+"]");
		}else{
			tbDevService.update(bean);
			sysLogService.addLog(request, "tb_dev", bean.getId(), "修改", "修改一条设备信息["+bean.getDevnm()+"]");
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		TbDev bean  = tbDevService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		tbDevService.delete(id);
		sysLogService.addLog(request, "tb_dev", id, "删除", "删除一条设备信息");
		sendSuccessMessage(response, "删除成功");
	}

}

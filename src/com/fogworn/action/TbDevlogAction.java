package com.fogworn.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.BaseAction;
import com.base.utils.HtmlUtil;
import com.fogworn.bean.TbDevlog;
import com.fogworn.model.TbDevlogModel;
import com.fogworn.service.TbDevlogService;
 
@Controller
@RequestMapping("/tbDevlog") 
public class TbDevlogAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(TbDevlogAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TbDevlogService tbDevlogService; 
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(TbDevlogModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		return forword("fogworn/tbDevlogList",context); 
	}
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(TbDevlogModel model,HttpServletResponse response) throws Exception{
		//model.setDeleted(0);
		List<TbDevlog> dataList = tbDevlogService.queryByList(model);
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
	public void save(TbDevlog bean,Integer[] typeIds,HttpServletResponse response) throws Exception{
		//bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			tbDevlogService.add(bean);
		}else{
			tbDevlogService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		TbDevlog bean  = tbDevlogService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer id,HttpServletResponse response) throws Exception{
		tbDevlogService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}

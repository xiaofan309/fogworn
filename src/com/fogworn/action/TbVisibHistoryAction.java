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
import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbVisibHistory;
import com.fogworn.model.TbVisibHistoryModel;
import com.fogworn.service.TbVisibHistoryService;
 
@Controller
@RequestMapping("/tbVisibHistory") 
public class TbVisibHistoryAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(TbVisibHistoryAction.class);
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TbVisibHistoryService tbVisibHistoryService; 
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(TbVisibHistoryModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		return forword("fogworn/tbVisibHistory",context); 
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(TbVisibHistoryModel model, HttpServletResponse response) throws Exception{
		//model.setDeleted(0);
		List<TbVisibHistory> dataList = tbVisibHistoryService.queryByList(model);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	@RequestMapping("/listByDevid") 
	public void  listByDevid(TbVisibHistoryModel model, HttpServletResponse response) throws Exception{
		//model.setDeleted(0);
		List<TbVisibHistory> dataList = tbVisibHistoryService.listByDevid(model);
		//设置页面数据
		HtmlUtil.writerJson(response, dataList);
	}
	
	@RequestMapping("/devList") 
	public void  devList(HttpServletResponse response) throws Exception{
		//model.setDeleted(0);
		List<TbDev> dataList = tbVisibHistoryService.devList();
		//设置页面数据
		HtmlUtil.writerJson(response, dataList);
	}

}

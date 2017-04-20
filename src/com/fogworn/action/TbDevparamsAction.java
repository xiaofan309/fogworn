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
import com.base.utils.SessionUtils;
import com.desun.protocal.DesunProtocal;
import com.fogworn.bean.TbDevparams2;
import com.fogworn.bean.TbDevparamsEx;
import com.fogworn.model.TbDevparamsModel;
import com.fogworn.service.TbDevparams2Service;
import com.fogworn.service.TbDevparamsService;
import com.sys.bean.SysRoleRel;
import com.sys.service.SysLogService;
import com.sys.service.SysUserService;
 
@Controller
@RequestMapping("/tbDevparams") 
public class TbDevparamsAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(TbDevparamsAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TbDevparamsService tbDevparamsService; 
	@Autowired(required=false)
	private TbDevparams2Service tbDevparams2Service;
	
	@Autowired(required=false)
	private SysLogService sysLogService;
	@Autowired(required=false)
	private SysUserService sysUserService; 
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(TbDevparamsModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		List<SysRoleRel>  roles  =sysUserService.getUserRole(SessionUtils.getUserId(request));
		String roleIds = "";
		if(roles != null){
			for(SysRoleRel rel : roles ){
				roleIds += rel.getRoleId()+",";
			}
			roleIds = roleIds.substring(0, roleIds.length()-1);
		}
		context.put("roleIds", roleIds);
		return forword("fogworn/tbDevparamsList",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(TbDevparamsModel model,HttpServletResponse response) throws Exception{
		List<TbDevparamsEx> dataList = tbDevparamsService.queryExByList(model);
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
	public void save(TbDevparamsEx bean,Integer[] typeIds,HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			tbDevparamsService.add(bean);
			sysLogService.addLog(request, "tb_devparams", bean.getId(), "新增", "添加一条设备参数信息，编号["+bean.getDevid()+"]");
		}else{
			tbDevparamsService.update(bean);
			sysLogService.addLog(request, "tb_devparams", bean.getId(), "修改", "修改一条设备参数信息，编号["+bean.getDevid()+"]");
		}
		sendSuccessMessage(response, "保存成功");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		TbDevparamsEx bean  = tbDevparamsService.queryExById(id);
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
		tbDevparamsService.delete(id);
		sysLogService.addLog(request, "tb_devparams", id, "删除", "删除一条设备参数信息");
		sendSuccessMessage(response, "删除成功");
	}
	
	@RequestMapping("/sendParam")
	public void sendParam(String paramName, Integer type, Integer state, TbDevparamsEx devparamsEx, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String str = tbDevparamsService.sendParam(paramName, type, state, devparamsEx) ;
		if(DesunProtocal.RESPONSE_SUCESS.equals(str)){
			sysLogService.addLog(request, "tb_devparams", devparamsEx.getId(), "其他", "发送一条设备参数信息，并保存设备参数");
			sendSuccessMessage(response, "发送成功");
		}else{
			sendFailureMessage(response, str);
		}
	}

	@RequestMapping("/getLogs")
	public void getLogs(Integer devid,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		Map<String,Object> data  = tbDevparamsService.getLogs(devid);
		context.put(SUCCESS, true);
		context.put("data", data);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/clearLogs")
	public void clearLogs(Integer devid, String logIds, HttpServletRequest request, HttpServletResponse response) throws Exception{
		tbDevparamsService.clearLogs(devid, logIds);
		sendSuccessMessage(response, "操作成功");
	}

	@RequestMapping("/getParamByWorktype")
	public void getParamByWorktype(Integer id, Integer worktype, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TbDevparams2 obj = tbDevparams2Service.queryByParamIdAndWorkType(id, worktype);
		HtmlUtil.writerJson(response, obj);
	}
}

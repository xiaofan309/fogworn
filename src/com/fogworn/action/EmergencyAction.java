package com.fogworn.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.BaseAction;
import com.fogworn.model.TbDevModel;
 
@Controller
@RequestMapping("/emerg") 
public class EmergencyAction extends BaseAction{
	/**
	 * @param url
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/init") 
	public ModelAndView  list(TbDevModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		return forword("fogworn/emergency",context);
	}
}

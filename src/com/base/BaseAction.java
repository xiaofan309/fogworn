package com.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.base.utils.HtmlUtil;

public class BaseAction {
	public static final String SUCCESS = "success";
	public static final String MSG = "msg";
	public static final String DATA = "data";
	public static final String LOGOUT_FLAG = "logoutFlag";

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(Integer.TYPE, new MyEditor());
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public ModelAndView forword(String viewName, Map context) {
		return new ModelAndView(viewName, context);
	}

	public ModelAndView error(String errMsg) {
		return new ModelAndView("error");
	}

	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap();
		result.put("success", Boolean.valueOf(true));
		result.put("msg", message);
		HtmlUtil.writerJson(response, result);
	}

	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap();
		result.put("success", Boolean.valueOf(false));
		result.put("msg", message);
		HtmlUtil.writerJson(response, result);
	}

	public void sendFailureMessage(HttpServletResponse response,
			String message, int msgtype) {
		Map<String, Object> result = new HashMap();
		result.put("success", Boolean.valueOf(false));
		result.put("msg", message);
		result.put("type", Integer.valueOf(msgtype));
		HtmlUtil.writerJson(response, result);
	}

}
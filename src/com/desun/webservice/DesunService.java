package com.desun.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://webservice.desun.com/", name = "DesunService", serviceName = "DesunService")
public interface DesunService {
	@WebResult(name = "return", targetNamespace = "http://webservice.desun.com/")
	@WebMethod
	public String getVisibility(
			@WebParam(name = "arg0", targetNamespace = "http://webservice.desun.com/") String xmlStr);// 获取能见度

	public String getCurstate(String xmlStr);// 获取参数

	public String getParameter(String xmlStr);// 获取参数

	public String setParameter(String xmlStr);// 设置参数

	public String setSendtype(String xmlStr);// 设置发送方式

	public String setNightenable(String xmlStr);// 设置夜间自动开启、关闭

	public String setMeasuretype(String xmlStr);// 设置能见度测量方式

	public String setDevstate(String xmlStr);// 设置系统开启关闭
}

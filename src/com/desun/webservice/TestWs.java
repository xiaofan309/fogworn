package com.desun.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class TestWs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建WebService客户端代理工厂
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		// 注册WebService接口
		factory.setServiceClass(DesunService.class);
		// 设置WebService地址
		factory.setAddress("http://127.0.0.1:8080/fogworn/services/interface.ws?wsdl");
		DesunService desunService = (DesunService) factory.create();
		System.out.println(desunService
				.getVisibility("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<root>" + "<parameter>" + "<devId>2</devId>"
						+ "</parameter>" + "</root>"));

	}

}
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

		System.out.println(desunService.getVisibility("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "</parameter>" + "</root>"));

		System.out.println(desunService.getCurstate("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "</parameter>" + "</root>"));

		System.out.println(desunService.getParameter("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "</parameter>" + "</root>"));

		System.out.println(desunService.setParameter("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "<worktype>0</worktype>" + "<guidlights>3</guidlights>"
				+ "<flickerfrequency>3</flickerfrequency>" + "<luminance>5</luminance>" + "<lightopen>1</lightopen>"
				+ "<lighttime>5</lighttime>" + "</parameter>" + "</root>"));

		System.out.println(desunService.setSendtype("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "<sendtype>0</sendtype>" + "</parameter>" + "</root>"));

		System.out.println(desunService.setNightenable("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "<nightenable>1</nightenable>" + "</parameter>" + "</root>"));

		System.out.println(desunService.setMeasuretype("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "<measuretype>2</measuretype>" + "<measurenum>3</measurenum>"
				+ "</parameter>" + "</root>"));

		System.out.println(desunService.setDevstate("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<root>"
				+ "<parameter>" + "<devId>1</devId>" + "<devstate>0</devstate>" + "</parameter>" + "</root>"));

	}

}
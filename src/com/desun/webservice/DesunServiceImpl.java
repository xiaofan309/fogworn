package com.desun.webservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbDevparams;
import com.fogworn.bean.TbDevparamsEx;
import com.fogworn.service.TbDevService;
import com.fogworn.service.TbDevparamsService;

@WebService(targetNamespace = "http://webservice.desun.com/", name = "DesunService", serviceName = "DesunService")
public class DesunServiceImpl implements DesunService {

	@Autowired
	private TbDevparamsService tbDevparamsService;
	@Autowired
	private TbDevService tbDevService;

	// private TbDevparams tbDevparams;
	@WebResult(name = "return", targetNamespace = "http://webservice.desun.com/")
	@WebMethod
	public String getVisibility(
			@WebParam(name = "arg0", targetNamespace = "http://webservice.desun.com/") String xmlStr) {

		String devIdStr = parserVisibilityXml(xmlStr);
		TbDev tbDev;
		try {
			tbDev = this.tbDevService.queryByDevno(Integer.parseInt(devIdStr));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			return createVisibilityErrorXml();
		} catch (Exception e1) {
			e1.printStackTrace();
			return createVisibilityErrorXml();
		}
		int visibility = 0;
		try {
			visibility = this.tbDevparamsService.queryExByDevId(tbDev.getId()).getVisibility();
		} catch (Exception e) {
			e.printStackTrace();
			return createVisibilityErrorXml();
		}// tbDevparams.getVisibility();
		return createVisibilityXml(visibility + "");
	}

	// <?xml version="1.0" encoding="UTF-8"?>
	// <root>
	// <parameter>
	// <devId></devId>
	// </parameter >
	// </root>

	public static String parserVisibilityXml(String xmlStr) {
		String result = "";
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			Node devId = devIds.item(0);

			if (devId.getNodeType() == Node.ELEMENT_NODE) {
				if ("devId".equals(devId.getNodeName())) {
					result = devId.getTextContent();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	// <?xml version="1.0" encoding="UTF-8"?>
	// <root>
	// <head>
	// <code>0</code>
	// <message>获取能见度成功</message>
	// </head>
	// <body>
	// <visibility>
	// 2000
	// </visibility>
	// </body>
	// </root>

	public String createVisibilityXml(String visibility) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>获取能见度成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<visibility>" + visibility + "</visibility>");
		sb.append("<body>");
		sb.append("</root>");
		return sb.toString();
	}

	public String createVisibilityErrorXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>1</code>");
		sb.append("<message>获取能见度失败</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	/*
	 * measuretype//能见度测量方式 sendtype//获取发送方式 devstate//系统开启关闭
	 * nightenable//夜间自动开启、关闭 worktype//工作模式设置 guidlights//尾迹个数设置
	 * flickerfrequency//闪烁频率设置 luminance//亮度等级设置 lightopen//尾迹开关设置
	 * lighttime//尾迹延时设置
	 */
	public String getParameter(String xmlStr) {
		String devIdStr = parserGetParameterXml(xmlStr);
		Map<String, String> map = new HashMap<String, String>();
		TbDev tbDev;
		try {
			tbDev = this.tbDevService.queryByDevno(Integer.parseInt(devIdStr));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			return createVisibilityErrorXml();
		} catch (Exception e1) {
			e1.printStackTrace();
			return createVisibilityErrorXml();
		}
		try {
			TbDevparams tbDevparams = this.tbDevparamsService.queryExByDevId(tbDev.getId());
			map.put("visibility", tbDevparams.getVisibility() + "");
			map.put("measuretype", tbDevparams.getMeasuretype() + "");
			map.put("sendtype", tbDevparams.getSendtype() + "");
			map.put("nightenable", tbDevparams.getNightenable() + "");
			map.put("worktype", tbDevparams.getWorktype() + "");
			map.put("guidlights", tbDevparams.getGuidlights() + "");
			map.put("flickerfrequency", tbDevparams.getFlickerfrequency() + "");
			map.put("luminance", tbDevparams.getLuminance() + "");
			map.put("lightopen", tbDevparams.getLightopen() + "");
			map.put("lighttime", tbDevparams.getLighttime() + "");
		} catch (Exception e) {
			e.printStackTrace();
			return createGetParameterErrorXml();
		}

		return createGetParameterXml(map);
	}

	private String createGetParameterXml(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>获取参数成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<visibility>" + map.get("visibility") + "</visibility>");
		sb.append("<measuretype>" + map.get("measuretype") + "</measuretype>");
		sb.append("<sendtype>" + map.get("sendtype") + "</sendtype>");
		sb.append("<nightenable>" + map.get("nightenable") + "</nightenable>");
		sb.append("<worktype>" + map.get("worktype") + "</worktype>");
		sb.append("<guidlights>" + map.get("guidlights") + "</guidlights>");
		sb.append("<flickerfrequency>" + map.get("flickerfrequency") + "</flickerfrequency>");
		sb.append("<luminance>" + map.get("luminance") + "</luminance>");
		sb.append("<lightopen>" + map.get("lightopen") + "</lightopen>");
		sb.append("<lighttime>" + map.get("lighttime") + "</lighttime>");
		sb.append("<body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String createGetParameterErrorXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>1</code>");
		sb.append("<message>获取参数失败</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String parserGetParameterXml(String xmlStr) {
		String result = "";
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			Node devId = devIds.item(0);

			if (devId.getNodeType() == Node.ELEMENT_NODE) {
				if ("devId".equals(devId.getNodeName())) {
					result = devId.getTextContent();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public String setParameter(String xmlStr) {
		Map<String, String> map = parserSetParameterXml(xmlStr);
		String devId = map.get("devId");
		String worktype = map.get("worktype");
		String guidlights = map.get("guidlights");
		String flickerfrequency = map.get("flickerfrequency");
		String luminance = map.get("luminance");
		String lightopen = map.get("lightopen");
		String lighttime = map.get("lighttime");
		TbDev tbDev;
		try {
			tbDev = this.tbDevService.queryByDevno(Integer.parseInt(devId));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			return createSetParameterErrorXml(1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return createSetParameterErrorXml(1);
		}
		int state = 0;// 开关机
		try {
			TbDevparamsEx tbDevparamsEx = this.tbDevparamsService.queryExByDevId(tbDev.getId());
			tbDevparamsEx.setWorktype(Integer.parseInt(worktype));
			tbDevparamsEx.setGuidlights(Integer.parseInt(guidlights));
			tbDevparamsEx.setFlickerfrequency(Integer.parseInt(flickerfrequency));
			tbDevparamsEx.setLuminance(Integer.parseInt(luminance));
			tbDevparamsEx.setLightopen(Integer.parseInt(lightopen));
			tbDevparamsEx.setLighttime(Integer.parseInt(lighttime));
			String result = this.tbDevparamsService.sendParam("params", null, state, tbDevparamsEx);
			if (result.equals("通讯超时")) {
				return createSetParameterErrorXml(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createSetParameterErrorXml(1);
		}
		return createSetParameterXml();
	}

	private String createSetParameterXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>设置参数成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String createSetParameterErrorXml(int errorCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		if (errorCode == 1) {
			sb.append("<code>1</code>");
			sb.append("<message>设置参数失败</message>");
		}
		if (errorCode == 2) {
			sb.append("<code>2</code>");
			sb.append("<message>通讯超时</message>");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private Map<String, String> parserSetParameterXml(String xmlStr) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			// Node devId = devIds.item(0);

			for (int j = 0; j < devIds.getLength(); j++) {
				if ("devId".equals(devIds.item(j).getNodeName())) {
					map.put("devId", devIds.item(j).getTextContent());
				}
				if ("worktype".equals(devIds.item(j).getNodeName())) {
					map.put("worktype", devIds.item(j).getTextContent());
				}
				if ("guidlights".equals(devIds.item(j).getNodeName())) {
					map.put("guidlights", devIds.item(j).getTextContent());
				}
				if ("flickerfrequency".equals(devIds.item(j).getNodeName())) {
					map.put("flickerfrequency", devIds.item(j).getTextContent());
				}
				if ("luminance".equals(devIds.item(j).getNodeName())) {
					map.put("luminance", devIds.item(j).getTextContent());
				}
				if ("lightopen".equals(devIds.item(j).getNodeName())) {
					map.put("lightopen", devIds.item(j).getTextContent());
				}
				if ("lighttime".equals(devIds.item(j).getNodeName())) {
					map.put("lighttime", devIds.item(j).getTextContent());
				}
			}

			// if (devId.getNodeType() == Node.ELEMENT_NODE) {
			// if ("devId".equals(devId.getNodeName())) {
			// map.put("devId", devId.getTextContent());
			// }
			// }
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	public String setSendtype(String xmlStr) {
		Map<String, String> map = parserSetSendtypeXml(xmlStr);
		String devId = map.get("devId");
		String sendtype = map.get("sendtype");
		TbDev tbDev;
		try {
			tbDev = this.tbDevService.queryByDevno(Integer.parseInt(devId));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			return createSetSendtypeErrorXml(1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return createSetSendtypeErrorXml(1);
		}
		int state = 0;// 开关机
		try {
			TbDevparamsEx tbDevparamsEx = this.tbDevparamsService.queryExByDevId(tbDev.getId());
			tbDevparamsEx.setSendtype(Integer.parseInt(sendtype));
			String result = this.tbDevparamsService.sendParam("sendtype", null, state, tbDevparamsEx);
			if (result.equals("通讯超时")) {
				return createSetSendtypeErrorXml(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createSetSendtypeErrorXml(1);
		}
		return createSetSendtypeXml();
	}

	private String createSetSendtypeXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>设置发送方式成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String createSetSendtypeErrorXml(int errorCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		if (errorCode == 1) {
			sb.append("<code>1</code>");
			sb.append("<message>设置发送方式失败</message>");
		}
		if (errorCode == 2) {
			sb.append("<code>2</code>");
			sb.append("<message>通讯超时</message>");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private Map<String, String> parserSetSendtypeXml(String xmlStr) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			// Node devId = devIds.item(0);

			for (int j = 0; j < devIds.getLength(); j++) {
				if ("devId".equals(devIds.item(j).getNodeName())) {
					map.put("devId", devIds.item(j).getTextContent());
				}
				if ("sendtype".equals(devIds.item(j).getNodeName())) {
					map.put("sendtype", devIds.item(j).getTextContent());
				}

			}

			// if (devId.getNodeType() == Node.ELEMENT_NODE) {
			// if ("devId".equals(devId.getNodeName())) {
			// map.put("devId", devId.getTextContent());
			// }
			// }
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	public String setNightenable(String xmlStr) {
		Map<String, String> map = parserSetNightenableXml(xmlStr);
		String devId = map.get("devId");
		String nightenable = map.get("nightenable");
		TbDev tbDev;
		try {
			tbDev = this.tbDevService.queryByDevno(Integer.parseInt(devId));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			return createSetNightenableErrorXml(1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return createSetNightenableErrorXml(1);
		}
		int state = 0;// 开关机
		try {
			TbDevparamsEx tbDevparamsEx = this.tbDevparamsService.queryExByDevId(tbDev.getId());
			tbDevparamsEx.setNightenable(Integer.parseInt(nightenable));
			String result = this.tbDevparamsService.sendParam("nightenable", null, state, tbDevparamsEx);
			if (result.equals("通讯超时")) {
				return createSetNightenableErrorXml(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createSetNightenableErrorXml(1);
		}
		return createSetNightenableXml();
	}

	private String createSetNightenableXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>设置夜间自动开启、关闭成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String createSetNightenableErrorXml(int errorCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		if (errorCode == 1) {
			sb.append("<code>1</code>");
			sb.append("<message>设置夜间自动开启、关闭失败</message>");
		}
		if (errorCode == 2) {
			sb.append("<code>2</code>");
			sb.append("<message>通讯超时</message>");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private Map<String, String> parserSetNightenableXml(String xmlStr) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			// Node devId = devIds.item(0);

			for (int j = 0; j < devIds.getLength(); j++) {
				if ("devId".equals(devIds.item(j).getNodeName())) {
					map.put("devId", devIds.item(j).getTextContent());
				}
				if ("nightenable".equals(devIds.item(j).getNodeName())) {
					map.put("nightenable", devIds.item(j).getTextContent());
				}

			}

			// if (devId.getNodeType() == Node.ELEMENT_NODE) {
			// if ("devId".equals(devId.getNodeName())) {
			// map.put("devId", devId.getTextContent());
			// }
			// }
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	public String setMeasuretype(String xmlStr) {
		Map<String, String> map = parserSetMeasuretypeXml(xmlStr);
		String devId = map.get("devId");
		String measuretype = map.get("measuretype");
		String measurenum = map.get("measurenum");
		TbDev tbDev;
		try {
			tbDev = this.tbDevService.queryByDevno(Integer.parseInt(devId));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			return createSetMeasuretypeErrorXml(1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return createSetMeasuretypeErrorXml(1);
		}
		int state = 0;// 开关机
		try {
			TbDevparamsEx tbDevparamsEx = this.tbDevparamsService.queryExByDevId(tbDev.getId());
			tbDevparamsEx.setMeasuretype(Integer.parseInt(measuretype));
			if (Integer.parseInt(measuretype) == 3) {
				tbDevparamsEx.setMeasurenum(Integer.parseInt(measurenum));
			}
			String result = this.tbDevparamsService.sendParam("measuretype", null, state, tbDevparamsEx);
			if (result.equals("通讯超时")) {
				return createSetMeasuretypeErrorXml(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createSetMeasuretypeErrorXml(1);
		}
		return createSetMeasuretypeXml();
	}

	private String createSetMeasuretypeXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>设置能见度测量方式成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String createSetMeasuretypeErrorXml(int errorCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		if (errorCode == 1) {
			sb.append("<code>1</code>");
			sb.append("<message>设置能见度测量方式失败</message>");
		}
		if (errorCode == 2) {
			sb.append("<code>2</code>");
			sb.append("<message>通讯超时</message>");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private Map<String, String> parserSetMeasuretypeXml(String xmlStr) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			// Node devId = devIds.item(0);

			for (int j = 0; j < devIds.getLength(); j++) {
				if ("devId".equals(devIds.item(j).getNodeName())) {
					map.put("devId", devIds.item(j).getTextContent());
				}
				if ("measuretype".equals(devIds.item(j).getNodeName())) {
					map.put("measuretype", devIds.item(j).getTextContent());
				}
				if ("measurenum".equals(devIds.item(j).getNodeName())) {
					map.put("measurenum", devIds.item(j).getTextContent());
				}

			}

			// if (devId.getNodeType() == Node.ELEMENT_NODE) {
			// if ("devId".equals(devId.getNodeName())) {
			// map.put("devId", devId.getTextContent());
			// }
			// }
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	public String setDevstate(String xmlStr) {
		Map<String, String> map = parserSetDevstateXml(xmlStr);
		String devId = map.get("devId");
		String devstate = map.get("devstate");
		TbDev tbDev;
		try {
			tbDev = this.tbDevService.queryByDevno(Integer.parseInt(devId));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			return createSetMeasuretypeErrorXml(1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return createSetMeasuretypeErrorXml(1);
		}
		int state = 0;// 开关机
		try {
			TbDevparamsEx tbDevparamsEx = this.tbDevparamsService.queryExByDevId(tbDev.getId());
			tbDevparamsEx.setDevstate(Integer.parseInt(devstate));
			String result = this.tbDevparamsService.sendParam("devstate", null, state, tbDevparamsEx);
			if (result.equals("当前系统开关过于频繁，请稍后再操作")) {
				return createSetDevstateErrorXml(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createSetDevstateErrorXml(1);
		}
		return createSetDevstateXml();
	}

	private String createSetDevstateXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>系统开启关闭成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String createSetDevstateErrorXml(int errorCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		if (errorCode == 1) {
			sb.append("<code>1</code>");
			sb.append("<message>系统开启关闭失败</message>");
		}
		if (errorCode == 2) {
			sb.append("<code>2</code>");
			sb.append("<message>当前系统开关过于频繁，请稍后再操作</message>");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private Map<String, String> parserSetDevstateXml(String xmlStr) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			// Node devId = devIds.item(0);

			for (int j = 0; j < devIds.getLength(); j++) {
				if ("devId".equals(devIds.item(j).getNodeName())) {
					map.put("devId", devIds.item(j).getTextContent());
				}
				if ("devstate".equals(devIds.item(j).getNodeName())) {
					map.put("devstate", devIds.item(j).getTextContent());
				}

			}

			// if (devId.getNodeType() == Node.ELEMENT_NODE) {
			// if ("devId".equals(devId.getNodeName())) {
			// map.put("devId", devId.getTextContent());
			// }
			// }
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	public String getCurstate(String xmlStr) {
		String devIdStr = parserGetCurstateXml(xmlStr);
		String curstate;
		try {
			curstate = this.tbDevService.queryByDevno(Integer.parseInt(devIdStr)).getCurstate();
		} catch (Exception e) {
			e.printStackTrace();
			return createGetCurstateErrorXml();
		}// tbDevparams.getVisibility();
		return createGetCurstateXml(curstate);
	}

	private String createGetCurstateXml(String curstate) {

		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>0</code>");
		sb.append("<message>获取在线状态成功</message>");
		sb.append("</head>");
		sb.append("<body>");
		if (curstate.equals("在线")) {
			curstate = "1";
		} else {
			curstate = "0";
		}
		sb.append("<curstate>" + curstate + "</curstate>");
		sb.append("<body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String createGetCurstateErrorXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<root>");
		sb.append("<head>");
		sb.append("<code>1</code>");
		sb.append("<message>获取在线状态失败</message>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("</body>");
		sb.append("</root>");
		return sb.toString();
	}

	private String parserGetCurstateXml(String xmlStr) {
		String result = "";
		try {
			StringReader read = new StringReader(xmlStr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);

			NodeList roots = document.getChildNodes();
			Node root = roots.item(0);

			NodeList parameters = root.getChildNodes();
			Node parameter = parameters.item(0);

			NodeList devIds = parameter.getChildNodes();
			Node devId = devIds.item(0);

			if (devId.getNodeType() == Node.ELEMENT_NODE) {
				if ("devId".equals(devId.getNodeName())) {
					result = devId.getTextContent();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
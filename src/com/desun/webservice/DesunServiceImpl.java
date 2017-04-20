package com.desun.webservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

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

import com.fogworn.service.TbDevparamsService;

@WebService(endpointInterface = "com.desun.webservice.DesunService")
public class DesunServiceImpl implements DesunService {

	@Autowired
	private TbDevparamsService tbDevparamsService;

	// private TbDevparams tbDevparams;
	public String getVisibility(String xmlStr) {
		
		String devIdStr = parserXml(xmlStr);
		int visibility = 0;
		try {
			visibility = this.tbDevparamsService.queryById(Integer.parseInt(devIdStr)).getVisibility();
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
			return createErrorXml();
		}// tbDevparams.getVisibility();
		return createXml(visibility+"");

	}

	// <?xml version="1.0" encoding="UTF-8"?>
	// <root>
	// <parameter>
	// <devId></devId>
	// </parameter >
	// </root>

	public static String parserXml(String str) {
		String result = "";
		try {
			StringReader read = new StringReader(str);
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

	public String createXml(String visibility) {
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
	public String createErrorXml() {
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

}
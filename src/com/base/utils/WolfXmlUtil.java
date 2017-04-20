/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.FileInputStream;
/*   6:    */ import java.io.FileWriter;
/*   7:    */ import java.io.InputStreamReader;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.util.HashMap;
/*  10:    */ import java.util.List;
/*  11:    */ import java.util.Map;
/*  12:    */ import java.util.Map.Entry;
/*  13:    */ import org.dom4j.Document;
/*  14:    */ import org.dom4j.Element;
/*  15:    */ import org.dom4j.io.OutputFormat;
/*  16:    */ import org.dom4j.io.SAXReader;
/*  17:    */ import org.dom4j.io.XMLWriter;
/*  18:    */ 
/*  19:    */ public class WolfXmlUtil
/*  20:    */ {
/*  21:    */   private void getAddStrutsElemant(String filePath, String nodexPath)
/*  22:    */     throws Exception
/*  23:    */   {
/*  24: 23 */     Document document = getPath(filePath, "utf-8");
/*  25: 24 */     Element element = document.getRootElement();
/*  26: 25 */     Element nextElement = element.element("package");
/*  27: 26 */     Element newElement = nextElement.addElement("action");
/*  28: 27 */     newElement.addComment("系统自动创建");
/*  29: 28 */     newElement.addAttribute("name", "test");
/*  30: 29 */     newElement.addAttribute("class", "");
/*  31: 30 */     newElement.addAttribute("method", "");
/*  32: 31 */     newElement.addText("hello");
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void getAddNode(String filePath, String xPath, String newNode, Map<String, String> attrMap, String text)
/*  36:    */     throws Exception
/*  37:    */   {
/*  38: 43 */     if (getQueryNode(filePath, xPath, newNode, attrMap, text) < 1)
/*  39:    */     {
/*  40: 44 */       Document document = getPath(filePath, "UTF-8");
/*  41: 45 */       List<?> list = document.selectNodes(xPath);
/*  42: 46 */       System.out.println(xPath);
/*  43: 47 */       Element element = (Element)list.get(0);
/*  44: 48 */       Element newElement = element.addElement(newNode);
/*  45: 49 */       for (Map.Entry<String, String> entry : attrMap.entrySet()) {
/*  46: 50 */         newElement.addAttribute((String)entry.getKey(), (String)entry.getValue());
/*  47:    */       }
/*  48: 52 */       if ((text != null) && (text.trim().length() > 0)) {
/*  49: 53 */         newElement.addText(text);
/*  50:    */       }
/*  51: 55 */       getXMLWrite(document, filePath);
/*  52: 56 */       System.out.println("修改" + xPath + "成功");
/*  53:    */     }
/*  54:    */     else
/*  55:    */     {
/*  56: 58 */       System.out.println("已添�?");
/*  57:    */     }
/*  58:    */   }
/*  59:    */   
/*  60:    */   public int getQueryNode(String filePath, String xPath, String newNode, Map<String, String> attrMap, String text)
/*  61:    */     throws Exception
/*  62:    */   {
/*  63: 85 */     int count = 0;
/*  64: 86 */     Document document = getPath(filePath, "UTF-8");
/*  65: 87 */     StringBuffer sb = new StringBuffer();
/*  66: 88 */     for (Map.Entry<String, String> entry : attrMap.entrySet()) {
/*  67: 89 */       sb.append("[@" + (String)entry.getKey() + "='" + (String)entry.getValue() + "']");
/*  68:    */     }
/*  69: 91 */     xPath = xPath + "/" + newNode + sb.toString();
/*  70: 92 */     System.out.println("xPath=" + xPath);
/*  71: 93 */     document.selectNodes(xPath);
/*  72: 94 */     List<?> list = document.selectNodes(xPath);
/*  73: 95 */     for (int i = 0; i < list.size(); i++)
/*  74:    */     {
/*  75: 96 */       Element element = (Element)list.get(i);
/*  76: 97 */       if (element.getText().equals(text)) {
/*  77: 98 */         count++;
/*  78:    */       }
/*  79:    */     }
/*  80:103 */     return count;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void getXMLWrite(Document document, String filePath)
/*  84:    */     throws Exception
/*  85:    */   {
/*  86:115 */     OutputFormat of = new OutputFormat(" ", true);
/*  87:116 */     of.setEncoding("UTF-8");
/*  88:117 */     XMLWriter xw = new XMLWriter(new FileWriter(filePath), of);
/*  89:118 */     xw.setEscapeText(false);
/*  90:119 */     xw.write(document);
/*  91:120 */     xw.close();
/*  92:121 */     System.out.println(document.asXML());
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void getEditNode(String filePath, String xPath, Map<String, String> attrMap, String text)
/*  96:    */     throws Exception
/*  97:    */   {
/*  98:125 */     Document document = getPath(filePath, "UTF-8");
/*  99:126 */     List<?> list = document.selectNodes(xPath);
/* 100:127 */     Element element = (Element)list.get(0);
/* 101:128 */     if (attrMap != null) {
/* 102:129 */       for (Map.Entry<String, String> entry : attrMap.entrySet()) {
/* 103:130 */         element.addAttribute((String)entry.getKey(), (String)entry.getValue());
/* 104:    */       }
/* 105:    */     }
/* 106:134 */     List<?> nodelist = element.elements();
/* 107:135 */     for (int i = 0; i < nodelist.size(); i++)
/* 108:    */     {
/* 109:136 */       Element nodeElement = (Element)nodelist.get(i);
/* 110:137 */       nodeElement.getParent().remove(nodeElement);
/* 111:    */     }
/* 112:139 */     element.setText(text);
/* 113:140 */     getXMLWrite(document, filePath);
/* 114:    */   }
/* 115:    */   
/* 116:    */   public Document getPath(String filePath, String coding)
/* 117:    */   {
/* 118:151 */     SAXReader saxReader = new SAXReader();
/* 119:    */     
/* 120:153 */     Document document = null;
/* 121:    */     try
/* 122:    */     {
/* 123:155 */       saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/* 124:156 */       BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), coding));
/* 125:157 */       document = saxReader.read(read);
/* 126:    */     }
/* 127:    */     catch (Exception e)
/* 128:    */     {
/* 129:159 */       e.printStackTrace();
/* 130:    */     }
/* 131:161 */     return document;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public static void main(String[] args)
/* 135:    */   {
/* 136:164 */     WolfXmlUtil xml = new WolfXmlUtil();
/* 137:165 */     String filePath1 = "D:\\MyEclipse 8.5\\ssi\\src\\com\\wei\\ssi\\conf\\sqlmap\\ProUserSQL.xml";
/* 138:166 */     String filePath = "D:\\MyEclipse 8.5\\ssi\\src\\com\\wei\\ssi\\conf\\struts2\\struts2-ssi-proWbType.xml";
/* 139:    */     try
/* 140:    */     {
/* 141:173 */       Map<String, String> map = new HashMap();
/* 142:174 */       map.put("file", "no");
/* 143:175 */       xml.getEditNode(filePath1, "/sqlMap/select[@id='getProUserList']", map, "嘿嘿");
/* 144:    */     }
/* 145:    */     catch (Exception e)
/* 146:    */     {
/* 147:177 */       e.printStackTrace();
/* 148:    */     }
/* 149:    */   }
/* 150:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.WolfXmlUtil
 * JD-Core Version:    0.7.0.1
 */
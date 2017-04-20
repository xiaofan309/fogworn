/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import java.io.BufferedWriter;
/*  4:   */ import java.io.File;
/*  5:   */ import java.io.FileOutputStream;
/*  6:   */ import java.io.OutputStreamWriter;
/*  7:   */ import java.util.Properties;
/*  8:   */ import org.apache.commons.logging.Log;
/*  9:   */ import org.apache.commons.logging.LogFactory;
/* 10:   */ import org.apache.velocity.Template;
/* 11:   */ import org.apache.velocity.VelocityContext;
/* 12:   */ import org.apache.velocity.app.VelocityEngine;
/* 13:   */ 
/* 14:   */ public class CommonPageParser
/* 15:   */ {
/* 16:   */   private static VelocityEngine ve;
/* 17:   */   private static Properties properties;
/* 18:   */   private static final String CONTENT_ENCODING = "UTF-8";
/* 19:34 */   private static final Log log = LogFactory.getLog(CommonPageParser.class);
/* 20:37 */   private static boolean isReplace = true;
/* 21:40 */   private static String templateBasePath = Constant.WORK_TEMPLATE_PATH;
/* 22:   */   
/* 23:   */   public static void setTemplateBasePath(String path)
/* 24:   */   {
/* 25:46 */     templateBasePath = path;
/* 26:   */   }
/* 27:   */   
/* 28:   */   static
/* 29:   */   {
/* 30:   */     try
/* 31:   */     {
/* 32:51 */       Properties properties = new Properties();
/* 33:52 */       properties.setProperty("resource.loader", "file");
/* 34:53 */       properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
/* 35:54 */       properties.setProperty("file.resource.loader.path", templateBasePath);
/* 36:55 */       properties.setProperty("file.resource.loader.cache", "true");
/* 37:56 */       properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
/* 38:57 */       properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
/* 39:58 */       properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
/* 40:59 */       properties.setProperty("directive.set.null.allowed", "true");
/* 41:60 */       VelocityEngine velocityEngine = new VelocityEngine();
/* 42:61 */       velocityEngine.init(properties);
/* 43:62 */       ve = velocityEngine;
/* 44:   */     }
/* 45:   */     catch (Exception e)
/* 46:   */     {
/* 47:64 */       log.error(e);
/* 48:   */     }
/* 49:   */   }
/* 50:   */   
/* 51:   */   public static void WriterPage(VelocityContext context, String templateName, String fileDirPath, String targetFile)
/* 52:   */   {
/* 53:   */     try
/* 54:   */     {
/* 55:79 */       File file = new File(fileDirPath + targetFile);
/* 56:80 */       if (!file.exists()) {
/* 57:81 */         new File(file.getParent()).mkdirs();
/* 58:83 */       } else if (isReplace) {
/* 59:84 */         log.info("替换文件" + file.getAbsolutePath());
/* 60:   */       }
/* 61:89 */       Template template = ve.getTemplate(templateName, "UTF-8");
/* 62:90 */       FileOutputStream fos = new FileOutputStream(file);
/* 63:91 */       BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
/* 64:92 */       template.merge(context, writer);
/* 65:93 */       writer.flush();
/* 66:94 */       writer.close();
/* 67:95 */       fos.close();
/* 68:   */     }
/* 69:   */     catch (Exception e)
/* 70:   */     {
/* 71:98 */       log.error(e);
/* 72:   */     }
/* 73:   */   }
/* 74:   */   
/* 75:   */   public static void main(String[] args) {}
/* 76:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.CommonPageParser
 * JD-Core Version:    0.7.0.1
 */
/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import java.net.URL;
/*   6:    */ import java.net.URLDecoder;
/*   7:    */ import java.util.Map;
/*   8:    */ import java.util.ResourceBundle;
/*   9:    */ import org.apache.velocity.VelocityContext;
/*  10:    */ 
/*  11:    */ public class CreateJava
/*  12:    */ {
/*  13: 16 */   private static ResourceBundle res = ResourceBundle.getBundle("DataSourceConfig");
/*  14: 17 */   private static String url = res.getString("gpt.url");
/*  15: 18 */   private static String username = res.getString("gpt.username");
/*  16: 19 */   private static String passWord = res.getString("gpt.password");
/*  17: 22 */   private static String rootPath = "F:\\project\\";
/*  18:    */   
/*  19:    */   public static void main(String[] args)
/*  20:    */   {
/*  21: 26 */     CreateBean createBean = new CreateBean();
/*  22: 27 */     createBean.setMysqlInfo(url, username, passWord);
/*  23:    */     
/*  24: 29 */     String tableName = "tb_devparams2";
/*  25: 30 */     String codeName = "参数扩展";
/*  26: 31 */     String className = createBean.getTablesNameToClassName(tableName);
/*  27: 32 */     String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
/*  28:    */     
/*  29: 34 */     String pckName = "com.fogworn";
/*  30:    */     
/*  31: 36 */     String srcPath = rootPath + "src\\";
/*  32:    */     
/*  33: 38 */     String pckPath = rootPath + "src\\com\\fogworn\\";
/*  34:    */     
/*  35: 40 */     String webPath = rootPath + "WebRoot\\view\\fogworn\\";
/*  36:    */     
/*  37:    */ 
/*  38: 43 */     String modelPath = "model\\" + className + "Model.java";
/*  39: 44 */     String beanPath = "bean\\" + className + ".java";
/*  40: 45 */     String mapperPath = "mapper\\" + className + "Mapper.java";
/*  41: 46 */     String servicePath = "service\\" + className + "Service.java";
/*  42: 47 */     String serviceImplPath = "service\\impl\\" + className + "ServiceImpl.java";
/*  43: 48 */     String controllerPath = "action\\" + className + "Action.java";
/*  44: 49 */     String sqlMapperPath = "mapper\\" + className + "Mapper.xml";
/*  45:    */     
/*  46:    */ 
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50: 55 */     VelocityContext context = new VelocityContext();
/*  51: 56 */     context.put("pckName", pckName);
/*  52: 57 */     context.put("className", className);
/*  53: 58 */     context.put("lowerName", lowerName);
/*  54: 59 */     context.put("codeName", codeName);
/*  55: 60 */     context.put("tableName", tableName);
/*  56:    */     try
/*  57:    */     {
/*  58: 64 */       context.put("feilds", createBean.getBeanFeilds(tableName));
/*  59:    */     }
/*  60:    */     catch (Exception e)
/*  61:    */     {
/*  62: 66 */       e.printStackTrace();
/*  63:    */     }
/*  64:    */     try
/*  65:    */     {
/*  66: 71 */       Map<String, Object> sqlMap = createBean.getAutoCreateSql(tableName);
/*  67: 72 */       context.put("columnDatas", createBean.getColumnDatas(tableName));
/*  68: 73 */       context.put("SQL", sqlMap);
/*  69:    */     }
/*  70:    */     catch (Exception e)
/*  71:    */     {
/*  72: 75 */       e.printStackTrace();
/*  73: 76 */       return;
/*  74:    */     }
/*  75: 81 */     CommonPageParser.setTemplateBasePath(rootPath + "template\\");
/*  76: 82 */     CommonPageParser.WriterPage(context, "TempBean.java", pckPath, beanPath);
/*  77: 83 */     CommonPageParser.WriterPage(context, "TempModel.java", pckPath, modelPath);
/*  78: 84 */     CommonPageParser.WriterPage(context, "TempMapper.java", pckPath, mapperPath);
/*  79: 85 */     CommonPageParser.WriterPage(context, "TempService.java", pckPath, servicePath);
/*  80: 86 */     CommonPageParser.WriterPage(context, "TempServiceImpl.java", pckPath, serviceImplPath);
/*  81: 87 */     CommonPageParser.WriterPage(context, "TempMapper.xml", pckPath, sqlMapperPath);
/*  82: 88 */     CommonPageParser.WriterPage(context, "TempController.java", pckPath, controllerPath);
/*  83:    */     
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:    */ 
/*  91:    */ 
/*  92:    */ 
/*  93:    */ 
/*  94:    */ 
/*  95:    */ 
/*  96:    */ 
/*  97:    */ 
/*  98:    */ 
/*  99:    */ 
/* 100:106 */     System.out.println("==========结束=========");
/* 101:    */   }
/* 102:    */   
/* 103:    */   public static String getRootPath()
/* 104:    */   {
/* 105:115 */     String rootPath = "";
/* 106:    */     try
/* 107:    */     {
/* 108:117 */       File file = new File(CommonPageParser.class.getResource("/").getFile());
/* 109:118 */       rootPath = file.getParentFile().getParentFile().getParent() + "\\";
/* 110:119 */       rootPath = URLDecoder.decode(rootPath, "utf-8");
/* 111:120 */       System.out.println(rootPath);
/* 112:121 */       return rootPath;
/* 113:    */     }
/* 114:    */     catch (Exception e)
/* 115:    */     {
/* 116:123 */       e.printStackTrace();
/* 117:    */     }
/* 118:125 */     return rootPath;
/* 119:    */   }
/* 120:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.CreateJava
 * JD-Core Version:    0.7.0.1
 */
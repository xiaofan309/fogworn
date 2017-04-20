/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import java.util.ResourceBundle;
/*  4:   */ 
/*  5:   */ public class Constant
/*  6:   */ {
/*  7: 7 */   private static ResourceBundle res = ResourceBundle.getBundle("sysconfig");
/*  8: 9 */   public static String SSI_WEBSITE_NAME = res.getString("ssi.website.name");
/*  9:11 */   public static String SSI_WEBSITE_DOMAIN = res.getString("ssi.website.domain");
/* 10:13 */   public static String SSI_WEBSITE_URL = res.getString("ssi.website.url");
/* 11:17 */   public static String WORK_ROOT_PATH = res.getString("work.root.path");
/* 12:19 */   public static String WORK_TEMPLATE_PATH = res.getString("work.template.path");
/* 13:22 */   public static String UPLOAD_URL = res.getString("upload.url");
/* 14:24 */   public static String SEARCH_URL = res.getString("search.url");
/* 15:27 */   public static String LOOK_URL = res.getString("look.url");
/* 16:30 */   public static String SEARCH_SCORE = res.getString("search.score");
/* 17:32 */   public static String UPLOAD_PATH = res.getString("upload.path");
/* 18:34 */   public static String UPLOAD_PIC_SIZE = res.getString("upload.pic.size");
/* 19:37 */   public static String INDEX_BASE_DIR = res.getString("index.base.dir");
/* 20:   */   
/* 21:   */   public static enum SuperAdmin
/* 22:   */   {
/* 23:47 */     NO(0, "否"),  YES(1, "是");
/* 24:   */     
/* 25:   */     public int key;
/* 26:   */     public String value;
/* 27:   */     
/* 28:   */     private SuperAdmin(int key, String value)
/* 29:   */     {
/* 30:51 */       this.key = key;
/* 31:52 */       this.value = value;
/* 32:   */     }
/* 33:   */     
/* 34:   */     public static SuperAdmin get(int key)
/* 35:   */     {
/* 36:55 */       SuperAdmin[] values = values();
/* 37:56 */       for (SuperAdmin object : values) {
/* 38:57 */         if (object.key == key) {
/* 39:58 */           return object;
/* 40:   */         }
/* 41:   */       }
/* 42:61 */       return null;
/* 43:   */     }
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.Constant
 * JD-Core Version:    0.7.0.1
 */
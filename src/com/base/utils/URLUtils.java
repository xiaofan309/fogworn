/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.net.MalformedURLException;
/*   5:    */ import java.net.URL;
/*   6:    */ import java.util.List;
/*   7:    */ import java.util.Map;
/*   8:    */ import org.apache.commons.lang.StringUtils;
/*   9:    */ 
/*  10:    */ public class URLUtils
/*  11:    */ {
/*  12: 14 */   private static Map<String, String> urlsMap = null;
/*  13:    */   
/*  14:    */   public static String getReqUri(String reqUrl)
/*  15:    */   {
/*  16:    */     try
/*  17:    */     {
/*  18: 43 */       URL url = new URL(reqUrl);
/*  19:    */       
/*  20:    */ 
/*  21:    */ 
/*  22:    */ 
/*  23:    */ 
/*  24:    */ 
/*  25:    */ 
/*  26:    */ 
/*  27:    */ 
/*  28:    */ 
/*  29: 54 */       return url.getPath();
/*  30:    */     }
/*  31:    */     catch (MalformedURLException e)
/*  32:    */     {
/*  33: 56 */       e.printStackTrace();
/*  34:    */     }
/*  35: 58 */     return null;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public static void getBtnAccessUrls(String menuUrl, String actionUrls, List<String> accessUrls)
/*  39:    */   {
/*  40: 69 */     if ((menuUrl == null) || (actionUrls == null) || (accessUrls == null)) {
/*  41: 70 */       return;
/*  42:    */     }
/*  43: 72 */     String url = "save.do| action.do |/user/manger/abcd.do";
/*  44: 73 */     String[] actions = StringUtils.split(actionUrls, "|");
/*  45: 74 */     String menuUri = StringUtils.substringBeforeLast(menuUrl, "/");
/*  46: 75 */     for (String action : actions)
/*  47:    */     {
/*  48: 76 */       action = StringUtils.trim(action);
/*  49: 77 */       if (StringUtils.startsWith(action, "/")) {
/*  50: 78 */         accessUrls.add(action);
/*  51:    */       } else {
/*  52: 80 */         accessUrls.add(menuUri + "/" + action);
/*  53:    */       }
/*  54:    */     }
/*  55:    */   }
/*  56:    */   
/*  57:    */   public static void main(String[] args)
/*  58:    */   {
/*  59: 91 */     String menu = "/sysMneu/dataList.do";
/*  60: 92 */     String url = "save.do| action.do |/user/manger/abcd.do";
/*  61: 93 */     String[] actions = StringUtils.split(url, "|");
/*  62: 94 */     String menuUri = StringUtils.substringBeforeLast(menu, "/");
/*  63: 95 */     for (String action : actions)
/*  64:    */     {
/*  65: 96 */       action = StringUtils.trim(action);
/*  66: 97 */       if (StringUtils.startsWith(action, "/")) {
/*  67: 98 */         System.out.println(action);
/*  68:    */       } else {
/*  69:100 */         System.out.println(menuUri + "/" + action);
/*  70:    */       }
/*  71:    */     }
/*  72:    */   }
/*  73:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.URLUtils
 * JD-Core Version:    0.7.0.1
 */
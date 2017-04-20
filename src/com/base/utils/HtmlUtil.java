/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import com.base.utils.json.JSONUtil;
/*  4:   */ import java.io.IOException;
/*  5:   */ import java.io.PrintWriter;
/*  6:   */ import javax.servlet.http.HttpServletResponse;
/*  7:   */ import org.json.JSONException;
/*  8:   */ 
/*  9:   */ public class HtmlUtil
/* 10:   */ {
/* 11:   */   public static void writerJson(HttpServletResponse response, String jsonStr)
/* 12:   */   {
/* 13:24 */     writer(response, jsonStr);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public static void writerJson(HttpServletResponse response, Object object)
/* 17:   */   {
/* 18:   */     try
/* 19:   */     {
/* 20:29 */       response.setContentType("application/json");
/* 21:30 */       writer(response, JSONUtil.toJSONString(object));
/* 22:   */     }
/* 23:   */     catch (JSONException e)
/* 24:   */     {
/* 25:32 */       e.printStackTrace();
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static void writerHtml(HttpServletResponse response, String htmlStr)
/* 30:   */   {
/* 31:46 */     writer(response, htmlStr);
/* 32:   */   }
/* 33:   */   
/* 34:   */   private static void writer(HttpServletResponse response, String str)
/* 35:   */   {
/* 36:   */     try
/* 37:   */     {
/* 38:51 */       StringBuffer result = new StringBuffer();
/* 39:   */       
/* 40:53 */       response.setHeader("Pragma", "No-cache");
/* 41:54 */       response.setHeader("Cache-Control", "no-cache");
/* 42:55 */       response.setCharacterEncoding("UTF-8");
/* 43:56 */       PrintWriter out = null;
/* 44:57 */       out = response.getWriter();
/* 45:58 */       out.print(str);
/* 46:59 */       out.flush();
/* 47:60 */       out.close();
/* 48:   */     }
/* 49:   */     catch (IOException e)
/* 50:   */     {
/* 51:62 */       e.printStackTrace();
/* 52:   */     }
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.HtmlUtil
 * JD-Core Version:    0.7.0.1
 */
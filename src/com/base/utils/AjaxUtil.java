/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import javax.servlet.http.HttpServletRequest;
/*  4:   */ import org.springframework.web.context.request.WebRequest;
/*  5:   */ 
/*  6:   */ public class AjaxUtil
/*  7:   */ {
/*  8:   */   public static boolean isAjaxRequest(WebRequest webRequest)
/*  9:   */   {
/* 10:23 */     String requestedWith = webRequest.getHeader("X-Requested-With");
/* 11:24 */     return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public static boolean isAjaxUploadRequest(WebRequest webRequest)
/* 15:   */   {
/* 16:35 */     return webRequest.getParameter("ajaxUpload") != null;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public static boolean isAjaxRequest(HttpServletRequest request)
/* 20:   */   {
/* 21:46 */     String requestedWith = request.getHeader("X-Requested-With");
/* 22:47 */     return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.AjaxUtil
 * JD-Core Version:    0.7.0.1
 */
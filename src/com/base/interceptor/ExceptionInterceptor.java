/*  1:   */ package com.base.interceptor;
/*  2:   */ 
/*  3:   */ import com.base.exception.ServiceException;
/*  4:   */ import com.base.utils.HtmlUtil;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.util.Enumeration;
/*  7:   */ import java.util.HashMap;
/*  8:   */ import java.util.Map;
/*  9:   */ import javax.servlet.http.HttpServletRequest;
/* 10:   */ import javax.servlet.http.HttpServletResponse;
/* 11:   */ import org.apache.log4j.Logger;
/* 12:   */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/* 13:   */ 
/* 14:   */ public class ExceptionInterceptor
/* 15:   */   extends HandlerInterceptorAdapter
/* 16:   */ {
/* 17:23 */   private static final Logger log = Logger.getLogger(ExceptionInterceptor.class);
/* 18:   */   
/* 19:   */   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
/* 20:   */     throws Exception
/* 21:   */   {
/* 22:29 */     if (ex != null)
/* 23:   */     {
/* 24:30 */       String msg = "";
/* 25:31 */       if ((ex instanceof ServiceException)) {
/* 26:32 */         msg = ex.getMessage();
/* 27:33 */       } else if ((ex instanceof NullPointerException)) {
/* 28:34 */         msg = "空指针异常";
/* 29:35 */       } else if ((ex instanceof IOException)) {
/* 30:36 */         msg = "文件读写异常";
/* 31:   */       } else {
/* 32:38 */         msg = ex.toString();
/* 33:   */       }
/* 34:40 */       logger(request, handler, ex);
/* 35:41 */       response.setStatus(503);
/* 36:42 */       Map<String, Object> result = new HashMap();
/* 37:43 */       result.put("success", Boolean.valueOf(false));
/* 38:44 */       result.put("msg", msg);
/* 39:45 */       HtmlUtil.writerJson(response, result);
/* 40:   */     }
/* 41:   */     else
/* 42:   */     {
/* 43:47 */       super.afterCompletion(request, response, handler, ex);
/* 44:   */     }
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void logger(HttpServletRequest request, Object handler, Exception ex)
/* 48:   */   {
/* 49:58 */     StringBuffer msg = new StringBuffer();
/* 50:59 */     msg.append("异常拦截日志");
/* 51:60 */     msg.append("[uri＝").append(request.getRequestURI()).append("]");
/* 52:61 */     Enumeration<String> enumer = request.getParameterNames();
/* 53:62 */     while (enumer.hasMoreElements())
/* 54:   */     {
/* 55:63 */       String name = (String)enumer.nextElement();
/* 56:64 */       String[] values = request.getParameterValues(name);
/* 57:65 */       msg.append("[").append(name).append("=");
/* 58:66 */       if (values != null)
/* 59:   */       {
/* 60:67 */         int i = 0;
/* 61:68 */         for (String value : values)
/* 62:   */         {
/* 63:69 */           i++;
/* 64:70 */           msg.append(value);
/* 65:71 */           if (i < values.length) {
/* 66:72 */             msg.append("｜");
/* 67:   */           }
/* 68:   */         }
/* 69:   */       }
/* 70:77 */       msg.append("]");
/* 71:   */     }
/* 72:79 */     log.error(msg, ex);
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.interceptor.ExceptionInterceptor
 * JD-Core Version:    0.7.0.1
 */
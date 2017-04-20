/*  1:   */ package com.base.interceptor;
/*  2:   */ 
/*  3:   */ import com.base.annotation.Auth;
/*  4:   */ import com.base.utils.AjaxUtil;
/*  5:   */ import com.base.utils.HtmlUtil;
/*  6:   */ import com.base.utils.SessionUtils;
/*  7:   */ import com.sys.bean.SysUser;
/*  8:   */ import java.lang.reflect.Method;
/*  9:   */ import java.util.HashMap;
/* 10:   */ import java.util.Map;
/* 11:   */ import javax.servlet.http.HttpServletRequest;
/* 12:   */ import javax.servlet.http.HttpServletResponse;
/* 13:   */ import org.apache.commons.lang.StringUtils;
/* 14:   */ import org.apache.log4j.Logger;
/* 15:   */ import org.springframework.web.method.HandlerMethod;
/* 16:   */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/* 17:   */ 
/* 18:   */ public class AuthInterceptor
/* 19:   */   extends HandlerInterceptorAdapter
/* 20:   */ {
/* 21:27 */   private static final Logger log = Logger.getLogger(AuthInterceptor.class);
/* 22:   */   
/* 23:   */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
/* 24:   */     throws Exception
/* 25:   */   {
/* 26:32 */     HandlerMethod method = (HandlerMethod)handler;
/* 27:33 */     Auth auth = (Auth)method.getMethod().getAnnotation(Auth.class);
/* 28:35 */     if ((auth == null) || (auth.verifyLogin()))
/* 29:   */     {
/* 30:36 */       String baseUri = request.getContextPath();
/* 31:37 */       String path = request.getServletPath();
/* 32:38 */       SysUser user = SessionUtils.getUser(request);
/* 33:41 */       if (user == null)
/* 34:   */       {
/* 35:42 */         if (AjaxUtil.isAjaxRequest(request))
/* 36:   */         {
/* 37:43 */           response.setStatus(504);
/* 38:44 */           Map<String, Object> result = new HashMap();
/* 39:45 */           result.put("success", Boolean.valueOf(false));
/* 40:46 */           result.put("logoutFlag", Boolean.valueOf(true));
/* 41:47 */           result.put("msg", "登录超时.");
/* 42:48 */           HtmlUtil.writerJson(response, result);
/* 43:49 */           return false;
/* 44:   */         }
/* 45:51 */         response.setStatus(504);
/* 46:52 */         response.sendRedirect(baseUri + "/login.shtml");
/* 47:53 */         return false;
/* 48:   */       }
/* 49:   */     }
/* 50:71 */     if ((auth == null) || (auth.verifyURL())) {
/* 51:73 */       if (!SessionUtils.isAdmin(request))
/* 52:   */       {
/* 53:74 */         String menuUrl = StringUtils.remove(request.getRequestURI(), request.getContextPath());
/* 54:75 */         if (!SessionUtils.isAccessUrl(request, StringUtils.trim(menuUrl)))
/* 55:   */         {
/* 56:77 */           String userMail = SessionUtils.getUser(request).getEmail();
/* 57:78 */           String msg = "URL权限验证不通过:[url=" + menuUrl + "][email =" + userMail + "]";
/* 58:79 */           log.error(msg);
/* 59:   */           
/* 60:81 */           response.setStatus(403);
/* 61:82 */           Map<String, Object> result = new HashMap();
/* 62:83 */           result.put("success", Boolean.valueOf(false));
/* 63:84 */           result.put("msg", "没有权限访问,请联系管理员.");
/* 64:85 */           HtmlUtil.writerJson(response, result);
/* 65:86 */           return false;
/* 66:   */         }
/* 67:   */       }
/* 68:   */     }
/* 69:90 */     return super.preHandle(request, response, handler);
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.interceptor.AuthInterceptor
 * JD-Core Version:    0.7.0.1
 */
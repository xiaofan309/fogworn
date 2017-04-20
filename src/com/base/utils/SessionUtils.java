/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import com.sys.bean.SysUser;
/*   4:    */ import java.util.List;
/*   5:    */ import java.util.Map;
/*   6:    */ import javax.servlet.http.HttpServletRequest;
/*   7:    */ import javax.servlet.http.HttpSession;
/*   8:    */ import org.apache.log4j.Logger;
/*   9:    */ 
/*  10:    */ public final class SessionUtils
/*  11:    */ {
/*  12: 21 */   protected static final Logger logger = Logger.getLogger(SessionUtils.class);
/*  13:    */   private static final String SESSION_USER = "session_user";
/*  14:    */   private static final String SESSION_VALIDATECODE = "session_validatecode";
/*  15:    */   private static final String SESSION_ACCESS_URLS = "session_access_urls";
/*  16:    */   private static final String SESSION_MENUBTN_MAP = "session_menubtn_map";
/*  17:    */   
/*  18:    */   public static void setAttr(HttpServletRequest request, String key, Object value)
/*  19:    */   {
/*  20: 41 */     request.getSession(true).setAttribute(key, value);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public static Object getAttr(HttpServletRequest request, String key)
/*  24:    */   {
/*  25: 52 */     return request.getSession(true).getAttribute(key);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public static void removeAttr(HttpServletRequest request, String key)
/*  29:    */   {
/*  30: 61 */     request.getSession(true).removeAttribute(key);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public static void setUser(HttpServletRequest request, SysUser user)
/*  34:    */   {
/*  35: 70 */     request.getSession(true).setAttribute("session_user", user);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public static SysUser getUser(HttpServletRequest request)
/*  39:    */   {
/*  40: 80 */     return (SysUser)request.getSession(true).getAttribute("session_user");
/*  41:    */   }
/*  42:    */   
/*  43:    */   public static Integer getUserId(HttpServletRequest request)
/*  44:    */   {
/*  45: 89 */     SysUser user = getUser(request);
/*  46: 90 */     if (user != null) {
/*  47: 91 */       return user.getId();
/*  48:    */     }
/*  49: 93 */     return null;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public static void removeUser(HttpServletRequest request)
/*  53:    */   {
/*  54:103 */     removeAttr(request, "session_user");
/*  55:    */   }
/*  56:    */   
/*  57:    */   public static void setValidateCode(HttpServletRequest request, String validateCode)
/*  58:    */   {
/*  59:113 */     request.getSession(true).setAttribute("session_validatecode", validateCode);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static String getValidateCode(HttpServletRequest request)
/*  63:    */   {
/*  64:123 */     return (String)request.getSession(true).getAttribute("session_validatecode");
/*  65:    */   }
/*  66:    */   
/*  67:    */   public static void removeValidateCode(HttpServletRequest request)
/*  68:    */   {
/*  69:133 */     removeAttr(request, "session_validatecode");
/*  70:    */   }
/*  71:    */   
/*  72:    */   public static boolean isAdmin(HttpServletRequest request)
/*  73:    */   {
/*  74:142 */     SysUser user = getUser(request);
/*  75:143 */     if ((user == null) || (user.getSuperAdmin().intValue() != Constant.SuperAdmin.YES.key)) {
/*  76:144 */       return false;
/*  77:    */     }
/*  78:146 */     return true;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static void setAccessUrl(HttpServletRequest request, List<String> accessUrls)
/*  82:    */   {
/*  83:157 */     setAttr(request, "session_access_urls", accessUrls);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public static boolean isAccessUrl(HttpServletRequest request, String url)
/*  87:    */   {
/*  88:168 */     List<String> accessUrls = (List)getAttr(request, "session_access_urls");
/*  89:169 */     if ((accessUrls == null) || (accessUrls.isEmpty()) || (!accessUrls.contains(url))) {
/*  90:170 */       return false;
/*  91:    */     }
/*  92:172 */     return true;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public static void setMemuBtnMap(HttpServletRequest request, Map<String, List> btnMap)
/*  96:    */   {
/*  97:182 */     setAttr(request, "session_menubtn_map", btnMap);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public static List<String> getMemuBtnListVal(HttpServletRequest request, String menuUri)
/* 101:    */   {
/* 102:191 */     Map btnMap = (Map)getAttr(request, "session_menubtn_map");
/* 103:192 */     if ((btnMap == null) || (btnMap.isEmpty())) {
/* 104:193 */       return null;
/* 105:    */     }
/* 106:195 */     return (List)btnMap.get(menuUri);
/* 107:    */   }
/* 108:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.SessionUtils
 * JD-Core Version:    0.7.0.1
 */
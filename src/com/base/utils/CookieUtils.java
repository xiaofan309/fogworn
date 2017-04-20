/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.io.UnsupportedEncodingException;
/*   4:    */ import java.net.URLDecoder;
/*   5:    */ import java.net.URLEncoder;
/*   6:    */ import javax.servlet.http.Cookie;
/*   7:    */ import javax.servlet.http.HttpServletRequest;
/*   8:    */ import javax.servlet.http.HttpServletResponse;
/*   9:    */ import org.apache.log4j.Logger;
/*  10:    */ 
/*  11:    */ public final class CookieUtils
/*  12:    */ {
/*  13: 21 */   protected static final Logger logger = Logger.getLogger(CookieUtils.class);
/*  14:    */   
/*  15:    */   public static String getCookieValue(HttpServletRequest request, String cookieName)
/*  16:    */   {
/*  17: 32 */     return getCookieValue(request, cookieName, false);
/*  18:    */   }
/*  19:    */   
/*  20:    */   public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder)
/*  21:    */   {
/*  22: 44 */     Cookie[] cookieList = request.getCookies();
/*  23: 45 */     if ((cookieList == null) || (cookieName == null)) {
/*  24: 46 */       return null;
/*  25:    */     }
/*  26: 47 */     String retValue = null;
/*  27:    */     try
/*  28:    */     {
/*  29: 49 */       for (int i = 0; i < cookieList.length; i++) {
/*  30: 50 */         if (cookieList[i].getName().equals(cookieName))
/*  31:    */         {
/*  32: 51 */           if (isDecoder)
/*  33:    */           {
/*  34: 52 */             retValue = URLDecoder.decode(cookieList[i].getValue(), 
/*  35: 53 */               "utf-8"); break;
/*  36:    */           }
/*  37: 55 */           retValue = cookieList[i].getValue();
/*  38:    */           
/*  39: 57 */           break;
/*  40:    */         }
/*  41:    */       }
/*  42:    */     }
/*  43:    */     catch (UnsupportedEncodingException e)
/*  44:    */     {
/*  45: 61 */       logger.error("Cookie Decode Error.", e);
/*  46:    */     }
/*  47: 63 */     return retValue;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString)
/*  51:    */   {
/*  52: 75 */     Cookie[] cookieList = request.getCookies();
/*  53: 76 */     if ((cookieList == null) || (cookieName == null)) {
/*  54: 77 */       return null;
/*  55:    */     }
/*  56: 78 */     String retValue = null;
/*  57:    */     try
/*  58:    */     {
/*  59: 80 */       for (int i = 0; i < cookieList.length; i++) {
/*  60: 81 */         if (cookieList[i].getName().equals(cookieName))
/*  61:    */         {
/*  62: 83 */           retValue = URLDecoder.decode(cookieList[i].getValue(), 
/*  63: 84 */             encodeString);
/*  64:    */           
/*  65: 86 */           break;
/*  66:    */         }
/*  67:    */       }
/*  68:    */     }
/*  69:    */     catch (UnsupportedEncodingException e)
/*  70:    */     {
/*  71: 90 */       logger.error("Cookie Decode Error.", e);
/*  72:    */     }
/*  73: 92 */     return retValue;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue)
/*  77:    */   {
/*  78:100 */     setCookie(request, response, cookieName, cookieValue, -1);
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage)
/*  82:    */   {
/*  83:109 */     setCookie(request, response, cookieName, cookieValue, cookieMaxage, 
/*  84:110 */       false);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode)
/*  88:    */   {
/*  89:119 */     setCookie(request, response, cookieName, cookieValue, -1, isEncode);
/*  90:    */   }
/*  91:    */   
/*  92:    */   public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode)
/*  93:    */   {
/*  94:128 */     doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, 
/*  95:129 */       isEncode);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString)
/*  99:    */   {
/* 100:138 */     doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, 
/* 101:139 */       encodeString);
/* 102:    */   }
/* 103:    */   
/* 104:    */   public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName)
/* 105:    */   {
/* 106:147 */     doSetCookie(request, response, cookieName, "", -1, false);
/* 107:    */   }
/* 108:    */   
/* 109:    */   private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode)
/* 110:    */   {
/* 111:    */     try
/* 112:    */     {
/* 113:160 */       if (cookieValue == null) {
/* 114:161 */         cookieValue = "";
/* 115:162 */       } else if (isEncode) {
/* 116:163 */         cookieValue = URLEncoder.encode(cookieValue, "utf-8");
/* 117:    */       }
/* 118:165 */       Cookie cookie = new Cookie(cookieName, cookieValue);
/* 119:166 */       if (cookieMaxage > 0) {
/* 120:167 */         cookie.setMaxAge(cookieMaxage);
/* 121:    */       }
/* 122:168 */       if (request != null) {
/* 123:169 */         cookie.setDomain(getDomainName(request));
/* 124:    */       }
/* 125:170 */       cookie.setPath("/");
/* 126:171 */       response.addCookie(cookie);
/* 127:    */     }
/* 128:    */     catch (Exception e)
/* 129:    */     {
/* 130:173 */       logger.error("Cookie Encode Error.", e);
/* 131:    */     }
/* 132:    */   }
/* 133:    */   
/* 134:    */   private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString)
/* 135:    */   {
/* 136:    */     try
/* 137:    */     {
/* 138:187 */       if (cookieValue == null) {
/* 139:188 */         cookieValue = "";
/* 140:    */       } else {
/* 141:190 */         cookieValue = URLEncoder.encode(cookieValue, encodeString);
/* 142:    */       }
/* 143:192 */       Cookie cookie = new Cookie(cookieName, cookieValue);
/* 144:193 */       if (cookieMaxage > 0) {
/* 145:194 */         cookie.setMaxAge(cookieMaxage);
/* 146:    */       }
/* 147:195 */       if (request != null) {
/* 148:196 */         cookie.setDomain(getDomainName(request));
/* 149:    */       }
/* 150:197 */       cookie.setPath("/");
/* 151:198 */       response.addCookie(cookie);
/* 152:    */     }
/* 153:    */     catch (Exception e)
/* 154:    */     {
/* 155:200 */       logger.error("Cookie Encode Error.", e);
/* 156:    */     }
/* 157:    */   }
/* 158:    */   
/* 159:    */   private static final String getDomainName(HttpServletRequest request)
/* 160:    */   {
/* 161:208 */     String domainName = null;
/* 162:    */     
/* 163:210 */     String serverName = request.getRequestURL().toString();
/* 164:211 */     if ((serverName == null) || (serverName.equals("")))
/* 165:    */     {
/* 166:212 */       domainName = "";
/* 167:    */     }
/* 168:    */     else
/* 169:    */     {
/* 170:214 */       serverName = serverName.toLowerCase();
/* 171:215 */       serverName = serverName.substring(7);
/* 172:216 */       int end = serverName.indexOf("/");
/* 173:217 */       serverName = serverName.substring(0, end);
/* 174:218 */       String[] domains = serverName.split("\\.");
/* 175:219 */       int len = domains.length;
/* 176:220 */       if (len > 3) {
/* 177:222 */         domainName = "." + domains[(len - 3)] + "." + domains[(len - 2)] + "." + domains[(len - 1)];
/* 178:223 */       } else if ((len <= 3) && (len > 1)) {
/* 179:225 */         domainName = "." + domains[(len - 2)] + "." + domains[(len - 1)];
/* 180:    */       } else {
/* 181:227 */         domainName = serverName;
/* 182:    */       }
/* 183:    */     }
/* 184:231 */     if ((domainName != null) && (domainName.indexOf(":") > 0))
/* 185:    */     {
/* 186:232 */       String[] ary = domainName.split("\\:");
/* 187:233 */       domainName = ary[0];
/* 188:    */     }
/* 189:235 */     return domainName;
/* 190:    */   }
/* 191:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.CookieUtils
 * JD-Core Version:    0.7.0.1
 */
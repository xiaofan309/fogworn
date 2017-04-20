/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import com.base.exception.ServiceException;
/*  4:   */ import org.apache.commons.lang.StringUtils;
/*  5:   */ import org.apache.commons.lang.math.NumberUtils;
/*  6:   */ 
/*  7:   */ public class FormValidate
/*  8:   */ {
/*  9:   */   public static void validNull(String value, String msg)
/* 10:   */     throws ServiceException
/* 11:   */   {
/* 12:17 */     if (StringUtils.isBlank(value)) {
/* 13:18 */       throw new ServiceException(msg);
/* 14:   */     }
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static void validLen(String value, Integer minLen, Integer maxLen, String msg)
/* 18:   */     throws ServiceException
/* 19:   */   {
/* 20:31 */     int len = StringUtils.isNotBlank(value) ? value.length() : 0;
/* 21:32 */     if ((len < minLen.intValue()) || (len > maxLen.intValue())) {
/* 22:33 */       throw new ServiceException(msg);
/* 23:   */     }
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static void validNumber(String value, String msg)
/* 27:   */     throws ServiceException
/* 28:   */   {
/* 29:46 */     if (!NumberUtils.isNumber(value)) {
/* 30:47 */       throw new ServiceException(msg);
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public static void validInt(String value, String msg)
/* 35:   */     throws ServiceException
/* 36:   */   {
/* 37:   */     try
/* 38:   */     {
/* 39:62 */       Integer.parseInt(value);
/* 40:   */     }
/* 41:   */     catch (Exception e)
/* 42:   */     {
/* 43:64 */       throw new ServiceException(msg);
/* 44:   */     }
/* 45:   */   }
/* 46:   */   
/* 47:   */   public static void validFloat(String value, String msg)
/* 48:   */     throws ServiceException
/* 49:   */   {
/* 50:   */     try
/* 51:   */     {
/* 52:80 */       Float.parseFloat(value);
/* 53:   */     }
/* 54:   */     catch (Exception e)
/* 55:   */     {
/* 56:82 */       throw new ServiceException(msg);
/* 57:   */     }
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.FormValidate
 * JD-Core Version:    0.7.0.1
 */
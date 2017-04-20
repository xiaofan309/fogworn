/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.security.MessageDigest;
/*   4:    */ import org.apache.commons.lang.StringUtils;
/*   5:    */ 
/*   6:    */ public class MethodUtil
/*   7:    */ {
/*   8:    */   public static MethodUtil getInit()
/*   9:    */   {
/*  10: 13 */     return new MethodUtil();
/*  11:    */   }
/*  12:    */   
/*  13:    */   private static String byteArrayToHexString(byte[] b)
/*  14:    */   {
/*  15: 16 */     StringBuffer resultSb = new StringBuffer();
/*  16: 17 */     for (int i = 0; i < b.length; i++) {
/*  17: 18 */       resultSb.append(byteToHexString(b[i]));
/*  18:    */     }
/*  19: 20 */     return resultSb.toString();
/*  20:    */   }
/*  21:    */   
/*  22:    */   private static String byteToHexString(byte b)
/*  23:    */   {
/*  24: 24 */     int n = b;
/*  25: 25 */     if (n < 0) {
/*  26: 26 */       n += 256;
/*  27:    */     }
/*  28: 27 */     int d1 = n / 16;
/*  29: 28 */     int d2 = n % 16;
/*  30: 29 */     return hexDigits[d1] + hexDigits[d2];
/*  31:    */   }
/*  32:    */   
/*  33:    */   public static String MD5(String origin)
/*  34:    */   {
/*  35: 37 */     String resultString = MD5Encode(origin, "UTF-8");
/*  36: 38 */     if (StringUtils.isNotBlank(resultString)) {
/*  37: 39 */       return resultString.toUpperCase();
/*  38:    */     }
/*  39: 41 */     return resultString;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static boolean ecompareMD5(String origin, String md5)
/*  43:    */   {
/*  44: 52 */     String result = MD5(origin);
/*  45: 53 */     return md5.equals(result);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public static String MD5Encode(String origin, String charsetname)
/*  49:    */   {
/*  50: 61 */     String resultString = null;
/*  51:    */     try
/*  52:    */     {
/*  53: 63 */       resultString = new String(origin);
/*  54: 64 */       MessageDigest md = MessageDigest.getInstance("MD5");
/*  55: 65 */       if ((charsetname == null) || ("".equals(charsetname))) {
/*  56: 66 */         resultString = byteArrayToHexString(md.digest(resultString
/*  57: 67 */           .getBytes()));
/*  58:    */       } else {
/*  59: 69 */         resultString = byteArrayToHexString(md.digest(resultString
/*  60: 70 */           .getBytes(charsetname)));
/*  61:    */       }
/*  62:    */     }
/*  63:    */     catch (Exception localException) {}
/*  64: 74 */     return resultString;
/*  65:    */   }
/*  66:    */   
/*  67: 77 */   private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", 
/*  68: 78 */     "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
/*  69:    */   
/*  70:    */   public static String byteArr2HexStr(byte[] arrB)
/*  71:    */     throws Exception
/*  72:    */   {
/*  73: 91 */     int iLen = arrB.length;
/*  74:    */     
/*  75: 93 */     StringBuffer sb = new StringBuffer(iLen * 2);
/*  76: 94 */     for (int i = 0; i < iLen; i++)
/*  77:    */     {
/*  78: 95 */       int intTmp = arrB[i];
/*  79: 97 */       while (intTmp < 0) {
/*  80: 98 */         intTmp += 256;
/*  81:    */       }
/*  82:101 */       if (intTmp < 16) {
/*  83:102 */         sb.append("0");
/*  84:    */       }
/*  85:104 */       sb.append(Integer.toString(intTmp, 16));
/*  86:    */     }
/*  87:106 */     return sb.toString();
/*  88:    */   }
/*  89:    */   
/*  90:    */   public static byte[] hexStr2ByteArr(String strIn)
/*  91:    */     throws Exception
/*  92:    */   {
/*  93:121 */     byte[] arrB = strIn.getBytes();
/*  94:122 */     int iLen = arrB.length;
/*  95:    */     
/*  96:    */ 
/*  97:125 */     byte[] arrOut = new byte[iLen / 2];
/*  98:126 */     for (int i = 0; i < iLen; i += 2)
/*  99:    */     {
/* 100:127 */       String strTmp = new String(arrB, i, 2);
/* 101:128 */       arrOut[(i / 2)] = ((byte)Integer.parseInt(strTmp, 16));
/* 102:    */     }
/* 103:130 */     return arrOut;
/* 104:    */   }
/* 105:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.MethodUtil
 * JD-Core Version:    0.7.0.1
 */
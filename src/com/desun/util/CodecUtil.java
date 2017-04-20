/*  1:   */ package com.desun.util;
/*  2:   */ 
/*  3:   */ import java.io.PrintStream;
/*  4:   */ 
/*  5:   */ public final class CodecUtil
/*  6:   */ {
/*  7: 4 */   static CRC16 crc16 = new CRC16();
/*  8:   */   
/*  9:   */   public static byte[] short2bytes(short s)
/* 10:   */   {
/* 11: 8 */     byte[] bytes = new byte[2];
/* 12: 9 */     for (int i = 1; i >= 0; i--)
/* 13:   */     {
/* 14:10 */       bytes[i] = ((byte)(s % 256));
/* 15:11 */       s = (short)(s >> 8);
/* 16:   */     }
/* 17:13 */     return bytes;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static short bytes2short(byte[] bytes)
/* 21:   */   {
/* 22:16 */     short s = (short)(bytes[1] & 0xFF);
/* 23:17 */     s = (short)(s | bytes[0] << 8 & 0xFF00);
/* 24:18 */     return s;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public static byte[] crc16Bytes(byte[] data)
/* 28:   */   {
/* 29:24 */     return short2bytes(crc16Short(data));
/* 30:   */   }
/* 31:   */   
/* 32:   */   public static short crc16Short(byte[] data)
/* 33:   */   {
/* 34:30 */     return crc16.getCrc(data);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public static void main(String[] args)
/* 38:   */   {
/* 39:33 */     byte[] test = { 0, 1, 2, 3, 4 };
/* 40:34 */     byte[] crc = crc16Bytes(test);
/* 41:35 */     byte[] testc = new byte[test.length + 2];
/* 42:36 */     for (int i = 0; i < test.length; i++) {
/* 43:37 */       testc[i] = test[i];
/* 44:   */     }
/* 45:39 */     testc[test.length] = crc[0];
/* 46:40 */     testc[(test.length + 1)] = crc[1];
/* 47:41 */     System.out.println(crc16Short(testc));
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.desun.util.CodecUtil
 * JD-Core Version:    0.7.0.1
 */
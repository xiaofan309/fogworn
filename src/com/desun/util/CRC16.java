/*  1:   */ package com.desun.util;
/*  2:   */ 
/*  3:   */ public class CRC16
/*  4:   */ {
/*  5: 4 */   private short[] crcTable = new short[256];
/*  6: 5 */   private int gPloy = 4129;
/*  7:   */   
/*  8:   */   public CRC16()
/*  9:   */   {
/* 10: 7 */     computeCrcTable();
/* 11:   */   }
/* 12:   */   
/* 13:   */   private short getCrcOfByte(int aByte)
/* 14:   */   {
/* 15:10 */     int value = aByte << 8;
/* 16:11 */     for (int count = 7; count >= 0; count--) {
/* 17:12 */       if ((value & 0x8000) != 0) {
/* 18:13 */         value = value << 1 ^ this.gPloy;
/* 19:   */       } else {
/* 20:15 */         value <<= 1;
/* 21:   */       }
/* 22:   */     }
/* 23:18 */     value &= 0xFFFF;
/* 24:19 */     return (short)value;
/* 25:   */   }
/* 26:   */   
/* 27:   */   private void computeCrcTable()
/* 28:   */   {
/* 29:25 */     for (int i = 0; i < 256; i++) {
/* 30:26 */       this.crcTable[i] = getCrcOfByte(i);
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public short getCrc(byte[] data)
/* 35:   */   {
/* 36:30 */     int crc = 0;
/* 37:31 */     int length = data.length;
/* 38:32 */     for (int i = 0; i < length; i++) {
/* 39:33 */       crc = (crc & 0xFF) << 8 ^ this.crcTable[(((crc & 0xFF00) >> 8 ^ data[i]) & 0xFF)];
/* 40:   */     }
/* 41:35 */     crc &= 0xFFFF;
/* 42:36 */     return (short)crc;
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.desun.util.CRC16
 * JD-Core Version:    0.7.0.1
 */
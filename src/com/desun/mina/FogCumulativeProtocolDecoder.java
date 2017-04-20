/*  1:   */ package com.desun.mina;
/*  2:   */ 
/*  3:   */ import com.desun.protocal.BitConverter;
/*  4:   */ import org.apache.log4j.Logger;
/*  5:   */ import org.apache.mina.core.buffer.IoBuffer;
/*  6:   */ import org.apache.mina.core.session.IoSession;
/*  7:   */ import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
/*  8:   */ import org.apache.mina.filter.codec.ProtocolDecoderOutput;
/*  9:   */ 
/* 10:   */ public class FogCumulativeProtocolDecoder
/* 11:   */   extends CumulativeProtocolDecoder
/* 12:   */ {
/* 13:17 */   private final Logger logger = Logger.getLogger(FogCumulativeProtocolDecoder.class);
/* 14:   */   
/* 15:   */   protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
/* 16:   */     throws Exception
/* 17:   */   {
/* 18:22 */     in.mark();
/* 19:23 */     byte[] b = new byte[in.remaining()];
/* 20:24 */     in.get(b);
/* 21:25 */     in.reset();
/* 22:26 */     this.logger.warn("receiveData=" + BitConverter.bytes2HexStr(b, true));
/* 23:46 */     if (in.remaining() >= 6)
/* 24:   */     {
/* 25:47 */       in.mark();
/* 26:48 */       if ((in.get() == -91) && (in.get() == -18))
/* 27:   */       {
/* 28:49 */         int size = 6;
/* 29:50 */         in.reset();
/* 30:51 */         byte[] bytes = new byte[size];
/* 31:52 */         in.mark();
/* 32:53 */         in.get(bytes, 0, size);
/* 33:   */         
/* 34:55 */         out.write(IoBuffer.wrap(bytes));
/* 35:   */       }
/* 36:   */       else
/* 37:   */       {
/* 38:57 */         in.reset();
/* 39:   */       }
/* 40:   */     }
/* 41:63 */     if (in.remaining() >= 8)
/* 42:   */     {
/* 43:78 */       int size = 8;
/* 44:80 */       if (in.remaining() < size) {
/* 45:81 */         return false;
/* 46:   */       }
/* 47:83 */       in.mark();
/* 48:84 */       byte tempHeader = in.get();
/* 49:85 */       if ((-91 == tempHeader) || (85 == tempHeader) || (90 == tempHeader))
/* 50:   */       {
/* 51:86 */         in.reset();
/* 52:87 */         byte[] bytes = new byte[size];
/* 53:88 */         in.mark();
/* 54:89 */         in.get(bytes, 0, size);
/* 55:   */         
/* 56:91 */         out.write(IoBuffer.wrap(bytes));
/* 57:   */       }
/* 58:93 */       if (in.remaining() >= size) {
/* 59:94 */         return true;
/* 60:   */       }
/* 61:   */     }
/* 62:99 */     return false;
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.desun.mina.FogCumulativeProtocolDecoder
 * JD-Core Version:    0.7.0.1
 */
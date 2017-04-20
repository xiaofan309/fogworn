/*  1:   */ package com.desun.mina;
/*  2:   */ 
/*  3:   */ import org.apache.log4j.Logger;
/*  4:   */ import org.apache.mina.core.buffer.IoBuffer;
/*  5:   */ import org.apache.mina.core.session.IoSession;
/*  6:   */ import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
/*  7:   */ 
/*  8:   */ public class FogKeepAliveMessageFactory
/*  9:   */   implements KeepAliveMessageFactory
/* 10:   */ {
/* 11:10 */   private final Logger logger = Logger.getLogger(FogKeepAliveMessageFactory.class);
/* 12:   */   
/* 13:   */   public Object getRequest(IoSession session)
/* 14:   */   {
/* 15:14 */     return null;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public Object getResponse(IoSession session, Object request)
/* 19:   */   {
/* 20:20 */     return null;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean isRequest(IoSession session, Object message)
/* 24:   */   {
/* 25:26 */     if ((message instanceof IoBuffer))
/* 26:   */     {
/* 27:27 */       IoBuffer buffer = (IoBuffer)message;
/* 28:28 */       this.logger.warn(buffer);
/* 29:29 */       buffer.mark();
/* 30:30 */       if (buffer.remaining() == 5)
/* 31:   */       {
/* 32:31 */         byte[] b = new byte[buffer.remaining()];
/* 33:32 */         buffer.get(b);
/* 34:33 */         if ((b[0] == 72) && (b[1] == 69) && 
/* 35:34 */           (b[2] == 65) && (b[3] == 82) && 
/* 36:35 */           (b[4] == 84))
/* 37:   */         {
/* 38:36 */           this.logger.warn("---------------->>judeg to be heart");
/* 39:37 */           return true;
/* 40:   */         }
/* 41:39 */         buffer.reset();
/* 42:   */       }
/* 43:   */     }
/* 44:42 */     return false;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public boolean isResponse(IoSession session, Object message)
/* 48:   */   {
/* 49:48 */     return false;
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.desun.mina.FogKeepAliveMessageFactory
 * JD-Core Version:    0.7.0.1
 */
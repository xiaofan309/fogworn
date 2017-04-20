/*  1:   */ package com.desun.mina;
/*  2:   */ 
/*  3:   */ import org.apache.mina.core.session.IoSession;
/*  4:   */ import org.apache.mina.filter.codec.ProtocolCodecFactory;
/*  5:   */ import org.apache.mina.filter.codec.ProtocolDecoder;
/*  6:   */ import org.apache.mina.filter.codec.ProtocolEncoder;
/*  7:   */ 
/*  8:   */ public class FogCumulativeProtocolCodecFactory
/*  9:   */   implements ProtocolCodecFactory
/* 10:   */ {
/* 11:   */   public ProtocolEncoder getEncoder(IoSession session)
/* 12:   */     throws Exception
/* 13:   */   {
/* 14:23 */     return new FogCumulativeProtocolEncoder();
/* 15:   */   }
/* 16:   */   
/* 17:   */   public ProtocolDecoder getDecoder(IoSession session)
/* 18:   */     throws Exception
/* 19:   */   {
/* 20:32 */     return new FogCumulativeProtocolDecoder();
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.desun.mina.FogCumulativeProtocolCodecFactory
 * JD-Core Version:    0.7.0.1
 */
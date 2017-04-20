/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import org.apache.velocity.VelocityContext;
/*  4:   */ 
/*  5:   */ public class VelocityUtils
/*  6:   */ {
/*  7:   */   public static VelocityContext getContext()
/*  8:   */   {
/*  9: 8 */     VelocityContext context = new VelocityContext();
/* 10: 9 */     context.put("PathUtil", new PathUtil());
/* 11:10 */     context.put("DateUtil", new DateUtil());
/* 12:11 */     context.put("StringUtil", new StringUtil());
/* 13:12 */     context.put("basePath", PathUtil.getBasePath());
/* 14:13 */     return context;
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.VelocityUtils
 * JD-Core Version:    0.7.0.1
 */
/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import org.springframework.context.ApplicationContext;
/*  4:   */ import org.springframework.context.support.ClassPathXmlApplicationContext;
/*  5:   */ 
/*  6:   */ public class PublishPageUtil
/*  7:   */ {
/*  8:14 */   static ApplicationContext context = new ClassPathXmlApplicationContext(
/*  9:15 */     new String[] { "com/wei/ssi/conf/spring/*.xml" });
/* 10:17 */   private static final String FILE_DIR_PATH = PathUtil.getRootPath();
/* 11:   */   
/* 12:   */   public static void main(String[] args) {}
/* 13:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.PublishPageUtil
 * JD-Core Version:    0.7.0.1
 */
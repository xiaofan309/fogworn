/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.io.Reader;
/*  5:   */ import org.apache.ibatis.io.Resources;
/*  6:   */ import org.apache.ibatis.session.SqlSession;
/*  7:   */ import org.apache.ibatis.session.SqlSessionFactory;
/*  8:   */ import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*  9:   */ 
/* 10:   */ public class MyBatisDB
/* 11:   */ {
/* 12:   */   private static SqlSessionFactory sqlMapper;
/* 13:   */   
/* 14:   */   static
/* 15:   */   {
/* 16:16 */     String resource = "com/wei/ssi/conf/mybatis/mybatis-config.xml";
/* 17:17 */     Reader reader = null;
/* 18:   */     try
/* 19:   */     {
/* 20:19 */       reader = Resources.getResourceAsReader(resource);
/* 21:   */     }
/* 22:   */     catch (IOException e)
/* 23:   */     {
/* 24:21 */       e.printStackTrace();
/* 25:   */     }
/* 26:23 */     sqlMapper = new SqlSessionFactoryBuilder().build(reader);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static SqlSession getSqlSession()
/* 30:   */   {
/* 31:28 */     if (sqlMapper == null) {
/* 32:29 */       return null;
/* 33:   */     }
/* 34:31 */     return sqlMapper.openSession();
/* 35:   */   }
/* 36:   */   
/* 37:   */   public static void close()
/* 38:   */   {
/* 39:35 */     getSqlSession().close();
/* 40:   */   }
/* 41:   */   
/* 42:   */   public static void commit()
/* 43:   */   {
/* 44:40 */     getSqlSession().commit();
/* 45:   */   }
/* 46:   */   
/* 47:   */   public static void rollback()
/* 48:   */   {
/* 49:45 */     getSqlSession().rollback();
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.MyBatisDB
 * JD-Core Version:    0.7.0.1
 */
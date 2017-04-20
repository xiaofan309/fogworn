/*  1:   */ package com.base.utils;
/*  2:   */ 
/*  3:   */ import org.springframework.beans.BeansException;
/*  4:   */ import org.springframework.beans.factory.NoSuchBeanDefinitionException;
/*  5:   */ import org.springframework.context.ApplicationContext;
/*  6:   */ import org.springframework.context.ApplicationContextAware;
/*  7:   */ 
/*  8:   */ public class SpringContextUtil
/*  9:   */   implements ApplicationContextAware
/* 10:   */ {
/* 11:   */   private static ApplicationContext applicationContext;
/* 12:   */   
/* 13:   */   public void setApplicationContext(ApplicationContext applicationContext)
/* 14:   */     throws BeansException
/* 15:   */   {
/* 16:18 */     applicationContext = applicationContext;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public static ApplicationContext getApplicationContext()
/* 20:   */   {
/* 21:26 */     return applicationContext;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public static Object getBean(String name)
/* 25:   */     throws BeansException
/* 26:   */   {
/* 27:37 */     return applicationContext.getBean(name);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public static Object getBean(String name, Class requiredType)
/* 31:   */     throws BeansException
/* 32:   */   {
/* 33:49 */     return applicationContext.getBean(name, requiredType);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static boolean containsBean(String name)
/* 37:   */   {
/* 38:58 */     return applicationContext.containsBean(name);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public static boolean isSingleton(String name)
/* 42:   */     throws NoSuchBeanDefinitionException
/* 43:   */   {
/* 44:69 */     return applicationContext.isSingleton(name);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public static Class getType(String name)
/* 48:   */     throws NoSuchBeanDefinitionException
/* 49:   */   {
/* 50:78 */     return applicationContext.getType(name);
/* 51:   */   }
/* 52:   */   
/* 53:   */   public static String[] getAliases(String name)
/* 54:   */     throws NoSuchBeanDefinitionException
/* 55:   */   {
/* 56:88 */     return applicationContext.getAliases(name);
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.SpringContextUtil
 * JD-Core Version:    0.7.0.1
 */
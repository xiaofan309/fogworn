/*  1:   */ package com.sys.service.impl;
/*  2:   */ 
/*  3:   */ import com.base.BaseService;
/*  4:   */ import com.base.utils.SessionUtils;
/*  5:   */ import com.sys.bean.SysLog;
/*  6:   */ import com.sys.bean.SysUser;
/*  7:   */ import com.sys.mapper.SysLogMapper;
/*  8:   */ import com.sys.service.SysLogService;
/*  9:   */ import java.text.SimpleDateFormat;
/* 10:   */ import java.util.Date;
/* 11:   */ import javax.servlet.http.HttpServletRequest;
/* 12:   */ import org.springframework.beans.factory.annotation.Autowired;
/* 13:   */ import org.springframework.stereotype.Service;
/* 14:   */ 
/* 15:   */ @Service("sysLogService")
/* 16:   */ public class SysLogServiceImpl
/* 17:   */   extends BaseService<SysLog>
/* 18:   */   implements SysLogService
/* 19:   */ {
/* 20:   */   @Autowired
/* 21:   */   private SysLogMapper<SysLog> mapper;
/* 22:   */   
/* 23:   */   public SysLogMapper<SysLog> getMapper()
/* 24:   */   {
/* 25:28 */     return this.mapper;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void addLog(HttpServletRequest request, String table_name, Integer primary_id, String do_type, String description)
/* 29:   */     throws Exception
/* 30:   */   {
/* 31:33 */     SysLog log = new SysLog();
/* 32:34 */     log.setTable_name(table_name);
/* 33:35 */     log.setPrimary_id(primary_id);
/* 34:36 */     log.setDo_type(do_type);
/* 35:37 */     log.setDescription(description);
/* 36:38 */     log.setDo_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
/* 37:39 */     SysUser user = SessionUtils.getUser(request);
/* 38:40 */     Integer userId = null;
/* 39:41 */     String userName = "无法获取用户姓名";
/* 40:42 */     if (user != null)
/* 41:   */     {
/* 42:43 */       userId = user.getId();
/* 43:44 */       userName = user.getNickName();
/* 44:   */     }
/* 45:46 */     log.setDo_user_id(userId);
/* 46:47 */     log.setDo_user_name(userName);
/* 47:48 */     add(log);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void addLog(Integer do_user_id, String do_user_name, String table_name, Integer primary_id, String do_type, String description)
/* 51:   */     throws Exception
/* 52:   */   {
/* 53:53 */     SysLog log = new SysLog();
/* 54:54 */     log.setTable_name(table_name);
/* 55:55 */     log.setPrimary_id(primary_id);
/* 56:56 */     log.setDo_type(do_type);
/* 57:57 */     log.setDescription(description);
/* 58:58 */     log.setDo_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
/* 59:59 */     log.setDo_user_id(do_user_id);
/* 60:60 */     log.setDo_user_name(do_user_name);
/* 61:61 */     add(log);
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.impl.SysLogServiceImpl
 * JD-Core Version:    0.7.0.1
 */
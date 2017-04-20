/*  1:   */ package com.sys.service.impl;
/*  2:   */ 
/*  3:   */ import com.base.BaseService;
/*  4:   */ import com.sys.bean.SysMenuBtn;
/*  5:   */ import com.sys.mapper.SysMenuBtnMapper;
/*  6:   */ import com.sys.service.SysMenuBtnService;
/*  7:   */ import java.util.List;
/*  8:   */ import org.springframework.beans.factory.annotation.Autowired;
/*  9:   */ import org.springframework.stereotype.Service;
/* 10:   */ 
/* 11:   */ @Service("sysMenuBtnService")
/* 12:   */ public class SysMenuBtnServiceImpl
/* 13:   */   extends BaseService<SysMenuBtn>
/* 14:   */   implements SysMenuBtnService
/* 15:   */ {
/* 16:   */   @Autowired
/* 17:   */   private SysMenuBtnMapper<SysMenuBtn> mapper;
/* 18:   */   
/* 19:   */   public List<SysMenuBtn> queryByAll()
/* 20:   */   {
/* 21:22 */     return getMapper().queryByAll();
/* 22:   */   }
/* 23:   */   
/* 24:   */   public List<SysMenuBtn> queryByMenuid(Integer menuid)
/* 25:   */   {
/* 26:26 */     return getMapper().queryByMenuid(menuid);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public List<SysMenuBtn> queryByMenuUrl(String url)
/* 30:   */   {
/* 31:30 */     return getMapper().queryByMenuUrl(url);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void deleteByMenuid(Integer menuid)
/* 35:   */   {
/* 36:34 */     getMapper().deleteByMenuid(menuid);
/* 37:   */   }
/* 38:   */   
/* 39:   */   public List<SysMenuBtn> getMenuBtnByUser(Integer userid)
/* 40:   */   {
/* 41:38 */     return getMapper().getMenuBtnByUser(userid);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public SysMenuBtnMapper<SysMenuBtn> getMapper()
/* 45:   */   {
/* 46:45 */     return this.mapper;
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.impl.SysMenuBtnServiceImpl
 * JD-Core Version:    0.7.0.1
 */
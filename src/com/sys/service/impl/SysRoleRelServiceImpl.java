/*  1:   */ package com.sys.service.impl;
/*  2:   */ 
/*  3:   */ import com.base.BaseService;
/*  4:   */ import com.sys.bean.SysRoleRel;
/*  5:   */ import com.sys.mapper.SysRoleRelMapper;
/*  6:   */ import com.sys.service.SysRoleRelService;
/*  7:   */ import java.util.HashMap;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Map;
/* 10:   */ import org.springframework.beans.factory.annotation.Autowired;
/* 11:   */ import org.springframework.stereotype.Service;
/* 12:   */ 
/* 13:   */ @Service("sysRoleRelService")
/* 14:   */ public class SysRoleRelServiceImpl
/* 15:   */   extends BaseService<SysRoleRel>
/* 16:   */   implements SysRoleRelService
/* 17:   */ {
/* 18:   */   @Autowired
/* 19:   */   private SysRoleRelMapper<SysRoleRel> mapper;
/* 20:   */   
/* 21:   */   public List<SysRoleRel> queryByRoleId(Integer roleId, Integer relType)
/* 22:   */   {
/* 23:24 */     Map<String, Object> param = new HashMap();
/* 24:25 */     param.put("roleId", roleId);
/* 25:26 */     param.put("relType", relType);
/* 26:27 */     return getMapper().queryByRoleId(param);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public List<SysRoleRel> queryByObjId(Integer objId, Integer relType)
/* 30:   */   {
/* 31:31 */     Map<String, Object> param = new HashMap();
/* 32:32 */     param.put("objId", objId);
/* 33:33 */     param.put("relType", relType);
/* 34:34 */     return getMapper().queryByObjId(param);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void deleteByObjId(Integer objId, Integer relType)
/* 38:   */   {
/* 39:38 */     Map<String, Object> param = new HashMap();
/* 40:39 */     param.put("objId", objId);
/* 41:40 */     param.put("relType", relType);
/* 42:41 */     getMapper().deleteByObjId(param);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void deleteByRoleId(Integer roleId)
/* 46:   */   {
/* 47:45 */     deleteByRoleId(roleId, null);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void deleteByRoleId(Integer roleId, Integer relType)
/* 51:   */   {
/* 52:49 */     Map<String, Object> param = new HashMap();
/* 53:50 */     param.put("roleId", roleId);
/* 54:51 */     param.put("relType", relType);
/* 55:52 */     getMapper().deleteByRoleId(param);
/* 56:   */   }
/* 57:   */   
/* 58:   */   public SysRoleRelMapper<SysRoleRel> getMapper()
/* 59:   */   {
/* 60:59 */     return this.mapper;
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.impl.SysRoleRelServiceImpl
 * JD-Core Version:    0.7.0.1
 */
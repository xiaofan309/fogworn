/*  1:   */ package com.sys.service.impl;
/*  2:   */ 
/*  3:   */ import com.base.BaseService;
/*  4:   */ import com.sys.bean.SysRoleRel;
/*  5:   */ import com.sys.bean.SysRoleRel.RelType;
/*  6:   */ import com.sys.bean.SysUser;
/*  7:   */ import com.sys.mapper.SysUserMapper;
/*  8:   */ import com.sys.model.SysUserModel;
/*  9:   */ import com.sys.service.SysRoleRelService;
/* 10:   */ import com.sys.service.SysUserService;
/* 11:   */ import java.util.List;
/* 12:   */ import org.springframework.beans.factory.annotation.Autowired;
/* 13:   */ import org.springframework.stereotype.Service;
/* 14:   */ 
/* 15:   */ @Service("sysUserService")
/* 16:   */ public class SysUserServiceImpl
/* 17:   */   extends BaseService<SysUser>
/* 18:   */   implements SysUserService
/* 19:   */ {
/* 20:   */   @Autowired
/* 21:   */   private SysRoleRelService sysRoleRelService;
/* 22:   */   @Autowired
/* 23:   */   private SysUserMapper<SysUser> mapper;
/* 24:   */   
/* 25:   */   public SysUserMapper<SysUser> getMapper()
/* 26:   */   {
/* 27:31 */     return this.mapper;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void delete(Object... ids)
/* 31:   */     throws Exception
/* 32:   */   {
/* 33:35 */     super.delete(ids);
/* 34:36 */     for (Object id : ids) {
/* 35:37 */       this.sysRoleRelService.deleteByObjId((Integer)id, Integer.valueOf(SysRoleRel.RelType.USER.key));
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public SysUser queryLogin(String email, String pwd)
/* 40:   */   {
/* 41:42 */     SysUserModel model = new SysUserModel();
/* 42:43 */     model.setEmail(email);
/* 43:44 */     model.setPwd(pwd);
/* 44:45 */     return (SysUser)getMapper().queryLogin(model);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public int getUserCountByEmail(String email)
/* 48:   */   {
/* 49:49 */     return getMapper().getUserCountByEmail(email);
/* 50:   */   }
/* 51:   */   
/* 52:   */   public List<SysRoleRel> getUserRole(Integer userId)
/* 53:   */   {
/* 54:53 */     return this.sysRoleRelService.queryByObjId(userId, Integer.valueOf(SysRoleRel.RelType.USER.key));
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void addUserRole(Integer userId, Integer[] roleIds)
/* 58:   */     throws Exception
/* 59:   */   {
/* 60:57 */     if ((userId == null) || (roleIds == null) || (roleIds.length < 1)) {
/* 61:58 */       return;
/* 62:   */     }
/* 63:61 */     this.sysRoleRelService.deleteByObjId(userId, Integer.valueOf(SysRoleRel.RelType.USER.key));
/* 64:62 */     for (Integer roleId : roleIds)
/* 65:   */     {
/* 66:63 */       SysRoleRel rel = new SysRoleRel();
/* 67:64 */       rel.setRoleId(roleId);
/* 68:65 */       rel.setObjId(userId);
/* 69:66 */       rel.setRelType(Integer.valueOf(SysRoleRel.RelType.USER.key));
/* 70:67 */       this.sysRoleRelService.add(rel);
/* 71:   */     }
/* 72:   */   }
/* 73:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.impl.SysUserServiceImpl
 * JD-Core Version:    0.7.0.1
 */
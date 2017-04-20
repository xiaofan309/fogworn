/*  1:   */ package com.sys.service.impl;
/*  2:   */ 
/*  3:   */ import com.base.BaseBean;
import com.base.BaseBean.STATE;
/*  4:   */ import com.base.BaseService;
/*  5:   */ import com.sys.bean.SysRole;
/*  6:   */ import com.sys.bean.SysRoleRel;
/*  7:   */ import com.sys.bean.SysRoleRel.RelType;
/*  8:   */ import com.sys.mapper.SysRoleMapper;
/*  9:   */ import com.sys.service.SysRoleRelService;
/* 10:   */ import com.sys.service.SysRoleService;
/* 11:   */ import java.util.List;
/* 12:   */ import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/* 14:   */ 
/* 15:   */ @Service("sysRoleService")
/* 16:   */ public class SysRoleServiceImpl
/* 17:   */   extends BaseService<SysRole>
/* 18:   */   implements SysRoleService
/* 19:   */ {
/* 20:   */   @Autowired
/* 21:   */   private SysRoleRelService sysRoleRelService;
/* 22:   */   @Autowired
/* 23:   */   private SysRoleMapper<SysRole> mapper;
/* 24:   */   
/* 25:   */   public SysRoleMapper<SysRole> getMapper()
/* 26:   */   {
/* 27:31 */     return this.mapper;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void addRoleMenuRel(Integer roleId, Integer[] menuIds)
/* 31:   */     throws Exception
/* 32:   */   {
/* 33:35 */     if ((roleId == null) || (menuIds == null) || (menuIds.length < 1)) {
/* 34:36 */       return;
/* 35:   */     }
/* 36:38 */     for (Integer menuid : menuIds)
/* 37:   */     {
/* 38:39 */       SysRoleRel rel = new SysRoleRel();
/* 39:40 */       rel.setRoleId(roleId);
/* 40:41 */       rel.setObjId(menuid);
/* 41:42 */       rel.setRelType(Integer.valueOf(SysRoleRel.RelType.MENU.key));
/* 42:43 */       this.sysRoleRelService.add(rel);
/* 43:   */     }
/* 44:   */   }
/* 45:   */   
/* 46:   */   public void addRoleBtnRel(Integer roleId, Integer[] btnIds)
/* 47:   */     throws Exception
/* 48:   */   {
/* 49:48 */     if ((roleId == null) || (btnIds == null) || (btnIds.length < 1)) {
/* 50:49 */       return;
/* 51:   */     }
/* 52:51 */     for (Integer btnid : btnIds)
/* 53:   */     {
/* 54:52 */       SysRoleRel rel = new SysRoleRel();
/* 55:53 */       rel.setRoleId(roleId);
/* 56:54 */       rel.setObjId(btnid);
/* 57:55 */       rel.setRelType(Integer.valueOf(SysRoleRel.RelType.BTN.key));
/* 58:56 */       this.sysRoleRelService.add(rel);
/* 59:   */     }
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void add(SysRole role, Integer[] menuIds, Integer[] btnIds)
/* 63:   */     throws Exception
/* 64:   */   {
/* 65:61 */     super.add(role);
/* 66:62 */     addRoleMenuRel(role.getId(), menuIds);
/* 67:63 */     addRoleBtnRel(role.getId(), btnIds);
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void delete(Integer[] ids)
/* 71:   */     throws Exception
/* 72:   */   {
/* 73:67 */     super.delete(ids);
/* 74:68 */     for (Integer id : ids) {
/* 75:70 */       this.sysRoleRelService.deleteByRoleId(id);
/* 76:   */     }
/* 77:   */   }
/* 78:   */   
/* 79:   */   public void update(SysRole role, Integer[] menuIds, Integer[] btnIds)
/* 80:   */     throws Exception
/* 81:   */   {
/* 82:75 */     super.update(role);
/* 83:77 */     if (BaseBean.STATE.DISABLE.key == role.getState().intValue()) {
/* 84:78 */       this.sysRoleRelService.deleteByRoleId(role.getId(), Integer.valueOf(SysRoleRel.RelType.USER.key));
/* 85:   */     }
/* 86:81 */     this.sysRoleRelService.deleteByRoleId(role.getId(), Integer.valueOf(SysRoleRel.RelType.MENU.key));
/* 87:82 */     this.sysRoleRelService.deleteByRoleId(role.getId(), Integer.valueOf(SysRoleRel.RelType.BTN.key));
/* 88:83 */     addRoleMenuRel(role.getId(), menuIds);
/* 89:84 */     addRoleBtnRel(role.getId(), btnIds);
/* 90:   */   }
/* 91:   */   
/* 92:   */   public List<SysRole> queryAllList()
/* 93:   */   {
/* 94:89 */     return getMapper().queryAllList();
/* 95:   */   }
/* 96:   */   
/* 97:   */   public List<SysRole> queryByUserid(Integer userid)
/* 98:   */   {
/* 99:93 */     return getMapper().queryByUserid(userid);
/* :0:   */   }
/* :1:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.impl.SysRoleServiceImpl
 * JD-Core Version:    0.7.0.1
 */
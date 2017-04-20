/*   1:    */ package com.sys.service.impl;
/*   2:    */ 
/*   3:    */ import com.base.BaseService;
/*   4:    */ import com.sys.bean.SysMenu;
/*   5:    */ import com.sys.bean.SysMenuBtn;
import com.sys.bean.SysRoleRel;
/*   6:    */ import com.sys.bean.SysRoleRel.RelType;
/*   7:    */ import com.sys.mapper.SysMenuMapper;
/*   8:    */ import com.sys.service.SysMenuBtnService;
/*   9:    */ import com.sys.service.SysMenuService;
/*  10:    */ import com.sys.service.SysRoleRelService;
/*  11:    */ import java.util.HashMap;
/*  12:    */ import java.util.List;
/*  13:    */ import java.util.Map;
/*  14:    */ import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*  16:    */ 
/*  17:    */ @Service("sysMenuService")
/*  18:    */ public class SysMenuServiceImpl
/*  19:    */   extends BaseService<SysMenu>
/*  20:    */   implements SysMenuService
/*  21:    */ {
/*  22:    */   @Autowired
/*  23:    */   private SysRoleRelService sysRoleRelService;
/*  24:    */   @Autowired
/*  25:    */   private SysMenuBtnService sysMenuBtnService;
/*  26:    */   @Autowired
/*  27:    */   private SysMenuMapper<SysMenu> mapper;
/*  28:    */   
/*  29:    */   public void saveBtns(Integer menuid, List<SysMenuBtn> btns)
/*  30:    */     throws Exception
/*  31:    */   {
/*  32: 37 */     if ((btns == null) || (btns.isEmpty())) {
/*  33: 38 */       return;
/*  34:    */     }
/*  35: 40 */     for (SysMenuBtn btn : btns) {
/*  36: 41 */       if ((btn.getId() != null) && ("1".equals(btn.getDeleteFlag())))
/*  37:    */       {
/*  38: 42 */         this.sysMenuBtnService.delete(new Object[] { btn.getId() });
/*  39:    */       }
/*  40:    */       else
/*  41:    */       {
/*  42: 45 */         btn.setMenuid(menuid);
/*  43: 46 */         if (btn.getId() == null) {
/*  44: 47 */           this.sysMenuBtnService.add(btn);
/*  45:    */         } else {
/*  46: 49 */           this.sysMenuBtnService.update(btn);
/*  47:    */         }
/*  48:    */       }
/*  49:    */     }
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void add(SysMenu menu)
/*  53:    */     throws Exception
/*  54:    */   {
/*  55: 56 */     super.add(menu);
/*  56: 57 */     saveBtns(menu.getId(), menu.getBtns());
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void update(SysMenu menu)
/*  60:    */     throws Exception
/*  61:    */   {
/*  62: 61 */     super.update(menu);
/*  63: 62 */     saveBtns(menu.getId(), menu.getBtns());
/*  64:    */   }
/*  65:    */   
/*  66:    */   public List<SysMenu> queryByAll()
/*  67:    */   {
/*  68: 66 */     return this.mapper.queryByAll();
/*  69:    */   }
/*  70:    */   
/*  71:    */   public List<SysMenu> getRootMenu(Integer menuId)
/*  72:    */   {
/*  73: 70 */     Map<String, Integer> map = new HashMap();
/*  74: 71 */     map.put("menuId", menuId);
/*  75: 72 */     return this.mapper.getRootMenu(map);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public List<SysMenu> getChildMenu()
/*  79:    */   {
/*  80: 76 */     return this.mapper.getChildMenu();
/*  81:    */   }
/*  82:    */   
/*  83:    */   public List<SysMenu> getRootMenuByUser(Integer userId)
/*  84:    */   {
/*  85: 80 */     return getMapper().getRootMenuByUser(userId);
/*  86:    */   }
/*  87:    */   
/*  88:    */   public List<SysMenu> getChildMenuByUser(Integer userId)
/*  89:    */   {
/*  90: 84 */     return getMapper().getChildMenuByUser(userId);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public List<SysMenu> getMenuByRoleId(Integer roleId)
/*  94:    */   {
/*  95: 88 */     return getMapper().getMenuByRoleId(roleId);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void delete(Object... ids)
/*  99:    */     throws Exception
/* 100:    */   {
/* 101: 92 */     super.delete(ids);
/* 102: 94 */     for (Object id : ids)
/* 103:    */     {
/* 104: 95 */       this.sysRoleRelService.deleteByObjId((Integer)id, Integer.valueOf(SysRoleRel.RelType.MENU.key));
/* 105: 96 */       this.sysMenuBtnService.deleteByMenuid((Integer)id);
/* 106:    */     }
/* 107:    */   }
/* 108:    */   
/* 109:    */   public SysMenuMapper<SysMenu> getMapper()
/* 110:    */   {
/* 111:101 */     return this.mapper;
/* 112:    */   }
/* 113:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.impl.SysMenuServiceImpl
 * JD-Core Version:    0.7.0.1
 */
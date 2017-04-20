/*   1:    */ package com.sys.action;
/*   2:    */ 
/*   3:    */ import com.base.BaseAction;
/*   4:    */ import com.base.utils.HtmlUtil;
/*   5:    */ import com.base.utils.Pager;
/*   6:    */ import com.sys.bean.SysMenu;
/*   7:    */ import com.sys.bean.SysRole;
/*   8:    */ import com.sys.bean.SysRoleRel;
/*   9:    */ import com.sys.bean.SysRoleRel.RelType;
/*  10:    */ import com.sys.model.SysRoleModel;
/*  11:    */ import com.sys.service.SysMenuService;
/*  12:    */ import com.sys.service.SysRoleRelService;
/*  13:    */ import com.sys.service.SysRoleService;
/*  14:    */ import java.util.HashMap;
/*  15:    */ import java.util.List;
/*  16:    */ import java.util.Map;
/*  17:    */ import javax.servlet.http.HttpServletRequest;
/*  18:    */ import javax.servlet.http.HttpServletResponse;
/*  19:    */ import org.apache.commons.beanutils.BeanUtils;
/*  20:    */ import org.apache.log4j.Logger;
/*  21:    */ import org.springframework.beans.factory.annotation.Autowired;
/*  22:    */ import org.springframework.stereotype.Controller;
/*  23:    */ import org.springframework.web.bind.annotation.RequestMapping;
/*  24:    */ import org.springframework.web.servlet.ModelAndView;
/*  25:    */ 
/*  26:    */ @Controller
/*  27:    */ @RequestMapping({"/sysRole"})
/*  28:    */ public class SysRoleAction
/*  29:    */   extends BaseAction
/*  30:    */ {
/*  31: 32 */   private static final Logger log = Logger.getLogger(SysRoleAction.class);
/*  32:    */   @Autowired(required=false)
/*  33:    */   private SysRoleService sysRoleService;
/*  34:    */   @Autowired(required=false)
/*  35:    */   private SysMenuService sysMenuService;
/*  36:    */   @Autowired(required=false)
/*  37:    */   private SysRoleRelService sysRoleRelService;
/*  38:    */   
/*  39:    */   @RequestMapping({"/role"})
/*  40:    */   public ModelAndView list(SysRoleModel model, HttpServletRequest request)
/*  41:    */     throws Exception
/*  42:    */   {
/*  43: 49 */     Map<String, Object> context = new HashMap();
/*  44: 50 */     return forword("sys/sysRole", context);
/*  45:    */   }
/*  46:    */   
/*  47:    */   @RequestMapping({"/dataList"})
/*  48:    */   public void datalist(SysRoleModel model, HttpServletResponse response)
/*  49:    */     throws Exception
/*  50:    */   {
/*  51: 63 */     List<SysRole> dataList = this.sysRoleService.queryByList(model);
/*  52:    */     
/*  53: 65 */     Map<String, Object> jsonMap = new HashMap();
/*  54: 66 */     jsonMap.put("total", Integer.valueOf(model.getPager().getRowCount()));
/*  55: 67 */     jsonMap.put("rows", dataList);
/*  56: 68 */     HtmlUtil.writerJson(response, jsonMap);
/*  57:    */   }
/*  58:    */   
/*  59:    */   @RequestMapping({"/save"})
/*  60:    */   public void save(SysRole bean, Integer[] menuIds, Integer[] btnIds, HttpServletResponse response)
/*  61:    */     throws Exception
/*  62:    */   {
/*  63: 80 */     if (bean.getId() == null) {
/*  64: 81 */       this.sysRoleService.add(bean, menuIds, btnIds);
/*  65:    */     } else {
/*  66: 83 */       this.sysRoleService.update(bean, menuIds, btnIds);
/*  67:    */     }
/*  68: 85 */     sendSuccessMessage(response, "保存成功~");
/*  69:    */   }
/*  70:    */   
/*  71:    */   @RequestMapping({"/getId"})
/*  72:    */   public void getId(Integer id, HttpServletResponse response)
/*  73:    */     throws Exception
/*  74:    */   {
/*  75: 91 */     Map<String, Object> context = new HashMap();
/*  76: 92 */     SysRole bean = this.sysRoleService.queryById(id);
/*  77: 93 */     if (bean == null)
/*  78:    */     {
/*  79: 94 */       sendFailureMessage(response, "没有找到对应的记录!");
/*  80: 95 */       return;
/*  81:    */     }
/*  82: 98 */     Integer[] menuIds = (Integer[])null;
/*  83: 99 */     List<SysMenu> menuList = this.sysMenuService.getMenuByRoleId(id);
/*  84:100 */     if (menuList != null)
/*  85:    */     {
/*  86:101 */       menuIds = new Integer[menuList.size()];
/*  87:102 */       int i = 0;
/*  88:103 */       for (SysMenu item : menuList)
/*  89:    */       {
/*  90:104 */         menuIds[i] = item.getId();
/*  91:105 */         i++;
/*  92:    */       }
/*  93:    */     }
/*  94:109 */     Integer[] btnIds = (Integer[])null;
/*  95:110 */     List<SysRoleRel> btnList = this.sysRoleRelService.queryByRoleId(id, Integer.valueOf(SysRoleRel.RelType.BTN.key));
/*  96:111 */     if (btnList != null)
/*  97:    */     {
/*  98:112 */       btnIds = new Integer[btnList.size()];
/*  99:113 */       int i = 0;
/* 100:114 */       for (SysRoleRel item : btnList)
/* 101:    */       {
/* 102:115 */         btnIds[i] = item.getObjId();
/* 103:116 */         i++;
/* 104:    */       }
/* 105:    */     }
/* 106:121 */     Object data = BeanUtils.describe(bean);
/* 107:122 */     ((Map)data).put("menuIds", menuIds);
/* 108:123 */     ((Map)data).put("btnIds", btnIds);
/* 109:124 */     context.put("success", Boolean.valueOf(true));
/* 110:125 */     context.put("data", data);
/* 111:126 */     HtmlUtil.writerJson(response, context);
/* 112:    */   }
/* 113:    */   
/* 114:    */   @RequestMapping({"/delete"})
/* 115:    */   public void delete(Integer[] id, HttpServletResponse response)
/* 116:    */     throws Exception
/* 117:    */   {
/* 118:133 */     this.sysRoleService.delete(id);
/* 119:134 */     sendSuccessMessage(response, "删除成功");
/* 120:    */   }
/* 121:    */   
/* 122:    */   @RequestMapping({"/loadRoleList"})
/* 123:    */   public void loadRoleList(HttpServletResponse response)
/* 124:    */     throws Exception
/* 125:    */   {
/* 126:141 */     List<SysRole> roloList = this.sysRoleService.queryAllList();
/* 127:142 */     HtmlUtil.writerJson(response, roloList);
/* 128:    */   }
/* 129:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.action.SysRoleAction
 * JD-Core Version:    0.7.0.1
 */
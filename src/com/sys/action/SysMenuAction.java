/*   1:    */ package com.sys.action;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.BaseAction;
import com.base.BaseBean;
import com.base.TreeNode;
import com.base.utils.HtmlUtil;
import com.base.utils.TreeUtil;
import com.sys.bean.SysMenu;
import com.sys.bean.SysMenuBtn;
import com.sys.model.SysMenuModel;
import com.sys.service.SysMenuBtnService;
import com.sys.service.SysMenuService;
/*  27:    */ 
/*  28:    */ @Controller
/*  29:    */ @RequestMapping({"/sysMenu"})
/*  30:    */ public class SysMenuAction
/*  31:    */   extends BaseAction
/*  32:    */ {
/*  33: 34 */   private static final Logger log = Logger.getLogger(SysMenuAction.class);
/*  34:    */   @Autowired(required=false)
/*  35:    */   private SysMenuService sysMenuService;
/*  36:    */   @Autowired
/*  37:    */   private SysMenuBtnService sysMenuBtnService;
/*  38:    */   
/*  39:    */   @RequestMapping({"/menu"})
/*  40:    */   public ModelAndView menu(SysMenuModel model, HttpServletRequest request)
/*  41:    */     throws Exception
/*  42:    */   {
/*  43: 50 */     Map<String, Object> context = new HashMap();
/*  44: 51 */     model.setDeleted(Integer.valueOf(BaseBean.DELETED.NO.key));
/*  45: 52 */     List<SysMenu> dataList = this.sysMenuService.queryByList(model);
/*  46:    */     
/*  47: 54 */     context.put("dataList", dataList);
/*  48: 55 */     return forword("sys/sysMenu", context);
/*  49:    */   }
/*  50:    */   
/*  51:    */   @RequestMapping({"/rootMenuJson"})
/*  52:    */   public void rootMenu(Integer menuId, HttpServletResponse response)
/*  53:    */     throws Exception
/*  54:    */   {
/*  55: 66 */     List<SysMenu> dataList = this.sysMenuService.getRootMenu(menuId);
/*  56: 67 */     if (dataList == null) {
/*  57: 68 */       dataList = new ArrayList();
/*  58:    */     }
/*  59: 70 */     HtmlUtil.writerJson(response, dataList);
/*  60:    */   }
/*  61:    */   
/*  62:    */   @RequestMapping({"/dataList"})
/*  63:    */   public void dataList(SysMenuModel model, HttpServletResponse response)
/*  64:    */     throws Exception
/*  65:    */   {
/*  66: 83 */     List<SysMenu> dataList = this.sysMenuService.queryByList(model);
/*  67:    */     
/*  68: 85 */     Map<String, Object> jsonMap = new HashMap();
/*  69: 86 */     jsonMap.put("total", Integer.valueOf(model.getPager().getRowCount()));
/*  70: 87 */     jsonMap.put("rows", dataList);
/*  71: 88 */     HtmlUtil.writerJson(response, jsonMap);
/*  72:    */   }
/*  73:    */   
/*  74:    */   @RequestMapping({"/save"})
/*  75:    */   public void save(SysMenu bean, HttpServletRequest request, HttpServletResponse response)
/*  76:    */     throws Exception
/*  77:    */   {
/*  78:103 */     List<SysMenuBtn> btns = getReqBtns(request);
/*  79:104 */     bean.setBtns(btns);
/*  80:105 */     if (bean.getId() == null)
/*  81:    */     {
/*  82:106 */       bean.setDeleted(Integer.valueOf(BaseBean.DELETED.NO.key));
/*  83:107 */       this.sysMenuService.add(bean);
/*  84:    */     }
/*  85:    */     else
/*  86:    */     {
/*  87:109 */       this.sysMenuService.update(bean);
/*  88:    */     }
/*  89:111 */     sendSuccessMessage(response, "保存成功~");
/*  90:    */   }
/*  91:    */   
/*  92:    */   @RequestMapping({"/getId"})
/*  93:    */   public void getId(Integer id, HttpServletResponse response)
/*  94:    */     throws Exception
/*  95:    */   {
/*  96:116 */     Map<String, Object> context = new HashMap();
/*  97:117 */     SysMenu bean = this.sysMenuService.queryById(id);
/*  98:118 */     if (bean == null)
/*  99:    */     {
/* 100:119 */       sendFailureMessage(response, "没有找到对应的记录!");
/* 101:120 */       return;
/* 102:    */     }
/* 103:122 */     List<SysMenuBtn> btns = this.sysMenuBtnService.queryByMenuid(id);
/* 104:123 */     bean.setBtns(btns);
/* 105:124 */     context.put("success", Boolean.valueOf(true));
/* 106:125 */     context.put("data", bean);
/* 107:126 */     HtmlUtil.writerJson(response, context);
/* 108:    */   }
/* 109:    */   
/* 110:    */   @RequestMapping({"/delete"})
/* 111:    */   public void delete(Integer[] id, HttpServletResponse response)
/* 112:    */     throws Exception
/* 113:    */   {
/* 114:131 */     if ((id != null) && (id.length > 0))
/* 115:    */     {
/* 116:132 */       this.sysMenuService.delete(id);
/* 117:133 */       sendSuccessMessage(response, "删除成功");
/* 118:    */     }
/* 119:    */     else
/* 120:    */     {
/* 121:135 */       sendFailureMessage(response, "未选中记录");
/* 122:    */     }
/* 123:    */   }
/* 124:    */   
/* 125:    */   @RequestMapping({"/getMenuTree"})
/* 126:    */   public void getMenuTree(Integer id, HttpServletResponse response)
/* 127:    */     throws Exception
/* 128:    */   {
/* 129:142 */     List<TreeNode> menuTree = treeMenu();
/* 130:143 */     HtmlUtil.writerJson(response, menuTree);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public List<TreeNode> treeMenu()
/* 134:    */   {
/* 135:151 */     List<SysMenu> rootMenus = this.sysMenuService.getRootMenu(null);
/* 136:152 */     List<SysMenu> childMenus = this.sysMenuService.getChildMenu();
/* 137:153 */     List<SysMenuBtn> childBtns = this.sysMenuBtnService.queryByAll();
/* 138:154 */     TreeUtil util = new TreeUtil(rootMenus, childMenus, childBtns);
/* 139:155 */     return util.getTreeNode();
/* 140:    */   }
/* 141:    */   
/* 142:    */   public List<SysMenuBtn> getReqBtns(HttpServletRequest request)
/* 143:    */   {
/* 144:164 */     List<SysMenuBtn> btnList = new ArrayList();
/* 145:165 */     String[] btnId = request.getParameterValues("btnId");
/* 146:166 */     String[] btnName = request.getParameterValues("btnName");
/* 147:167 */     String[] btnType = request.getParameterValues("btnType");
/* 148:168 */     String[] actionUrls = request.getParameterValues("actionUrls");
/* 149:169 */     String[] deleteFlag = request.getParameterValues("deleteFlag");
/* 150:170 */     if ((btnId == null) || (btnId.length < 1)) {
/* 151:171 */       return null;
/* 152:    */     }
/* 153:174 */     for (int i = 0; i < btnId.length; i++) {
/* 154:175 */       if ((StringUtils.isNotBlank(btnName[i])) && (StringUtils.isNotBlank(btnType[i])))
/* 155:    */       {
/* 156:176 */         SysMenuBtn btn = new SysMenuBtn();
/* 157:177 */         if ((StringUtils.isNotBlank(btnId[i])) && (NumberUtils.isNumber(btnId[i]))) {
/* 158:178 */           btn.setId(Integer.valueOf(NumberUtils.toInt(btnId[i])));
/* 159:    */         }
/* 160:180 */         btn.setBtnName(btnName[i]);
/* 161:181 */         btn.setBtnType(btnType[i]);
/* 162:182 */         btn.setActionUrls(actionUrls[i]);
/* 163:183 */         btn.setDeleteFlag(deleteFlag[i]);
/* 164:184 */         btnList.add(btn);
/* 165:    */       }
/* 166:    */     }
/* 167:187 */     return btnList;
/* 168:    */   }
/* 169:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.action.SysMenuAction
 * JD-Core Version:    0.7.0.1
 */
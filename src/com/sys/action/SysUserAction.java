/*   1:    */ package com.sys.action;
/*   2:    */ 
/*   3:    */ import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.BaseAction;
import com.base.BaseBean;
import com.base.exception.ServiceException;
import com.base.utils.HtmlUtil;
import com.base.utils.MethodUtil;
import com.base.utils.SessionUtils;
import com.sys.bean.SysRole;
import com.sys.bean.SysRoleRel;
import com.sys.bean.SysUser;
import com.sys.model.SysUserModel;
import com.sys.service.SysRoleService;
import com.sys.service.SysUserService;
/*  27:    */ 
/*  28:    */ @Controller
/*  29:    */ @RequestMapping({"/sysUser"})
/*  30:    */ public class SysUserAction
/*  31:    */   extends BaseAction
/*  32:    */ {
/*  33:    */   @Autowired(required=false)
/*  34:    */   private SysUserService sysUserService;
/*  35:    */   @Autowired(required=false)
/*  36:    */   private SysRoleService sysRoleService;
/*  37:    */   
/*  38:    */   @RequestMapping({"/list"})
/*  39:    */   public ModelAndView list(SysUserModel model, HttpServletRequest request)
/*  40:    */     throws Exception
/*  41:    */   {
/*  42: 45 */     Map<String, Object> context = new HashMap();
/*  43: 46 */     List<SysUser> dataList = this.sysUserService.queryByList(model);
/*  44:    */     
/*  45: 48 */     context.put("dataList", dataList);
/*  46: 49 */     return forword("sys/sysUser", context);
/*  47:    */   }
/*  48:    */   
/*  49:    */   @RequestMapping({"/dataList"})
/*  50:    */   public void dataList(SysUserModel model, HttpServletResponse response)
/*  51:    */     throws Exception
/*  52:    */   {
/*  53: 62 */     List<SysUser> dataList = this.sysUserService.queryByList(model);
/*  54: 63 */     for (SysUser user : dataList)
/*  55:    */     {
/*  56: 64 */       List<SysRole> list = this.sysRoleService.queryByUserid(user.getId());
/*  57: 65 */       user.setRoleStr(rolesToStr(list));
/*  58:    */     }
/*  59: 68 */     Map<String, Object> jsonMap = new HashMap();
/*  60: 69 */     jsonMap.put("total", Integer.valueOf(model.getPager().getRowCount()));
/*  61: 70 */     jsonMap.put("rows", dataList);
/*  62: 71 */     HtmlUtil.writerJson(response, jsonMap);
/*  63:    */   }
/*  64:    */   
/*  65:    */   private String rolesToStr(List<SysRole> list)
/*  66:    */   {
/*  67: 80 */     if ((list == null) || (list.isEmpty())) {
/*  68: 81 */       return null;
/*  69:    */     }
/*  70: 83 */     StringBuffer str = new StringBuffer();
/*  71: 84 */     for (int i = 0; i < list.size(); i++)
/*  72:    */     {
/*  73: 85 */       SysRole role = (SysRole)list.get(i);
/*  74: 86 */       str.append(role.getRoleName());
/*  75: 87 */       if (i + 1 < list.size()) {
/*  76: 88 */         str.append(",");
/*  77:    */       }
/*  78:    */     }
/*  79: 91 */     return str.toString();
/*  80:    */   }
/*  81:    */   
/*  82:    */   @RequestMapping({"/save"})
/*  83:    */   public void save(SysUser bean, HttpServletResponse response)
/*  84:    */     throws Exception
/*  85:    */   {
/*  86:103 */     Map<String, Object> context = new HashMap();
/*  87:104 */     int count = this.sysUserService.getUserCountByEmail(bean.getEmail());
/*  88:105 */     if (bean.getId() == null)
/*  89:    */     {
/*  90:106 */       if (count > 0) {
/*  91:107 */         throw new ServiceException("用户已存在.");
/*  92:    */       }
/*  93:109 */       bean.setDeleted(Integer.valueOf(BaseBean.DELETED.NO.key));
/*  94:110 */       this.sysUserService.add(bean);
/*  95:    */     }
/*  96:    */     else
/*  97:    */     {
/*  98:112 */       if (count > 1) {
/*  99:113 */         throw new ServiceException("用户已存在.");
/* 100:    */       }
/* 101:115 */       this.sysUserService.updateBySelective(bean);
/* 102:    */     }
/* 103:117 */     sendSuccessMessage(response, "保存成功~");
/* 104:    */   }
/* 105:    */   
/* 106:    */   @RequestMapping({"/getId"})
/* 107:    */   public void getId(Integer id, HttpServletResponse response)
/* 108:    */     throws Exception
/* 109:    */   {
/* 110:122 */     Map<String, Object> context = new HashMap();
/* 111:123 */     SysUser bean = this.sysUserService.queryById(id);
/* 112:124 */     if (bean == null)
/* 113:    */     {
/* 114:125 */       sendFailureMessage(response, "没有找到对应的记录!");
/* 115:126 */       return;
/* 116:    */     }
/* 117:128 */     context.put("success", Boolean.valueOf(true));
/* 118:129 */     context.put("data", bean);
/* 119:130 */     HtmlUtil.writerJson(response, context);
/* 120:    */   }
/* 121:    */   
/* 122:    */   @RequestMapping({"/delete"})
/* 123:    */   public void delete(Integer[] id, HttpServletResponse response)
/* 124:    */     throws Exception
/* 125:    */   {
/* 126:135 */     this.sysUserService.delete(id);
/* 127:136 */     sendSuccessMessage(response, "删除成功");
/* 128:    */   }
/* 129:    */   
/* 130:    */   @RequestMapping({"/updatePwd"})
/* 131:    */   public void updatePwd(Integer id, String oldPwd, String newPwd, HttpServletRequest request, HttpServletResponse response)
/* 132:    */     throws Exception
/* 133:    */   {
/* 134:149 */     boolean isAdmin = SessionUtils.isAdmin(request);
/* 135:150 */     SysUser bean = this.sysUserService.queryById(id);
/* 136:151 */     if ((bean.getId() == null) || (BaseBean.DELETED.YES.key == bean.getDeleted().intValue()))
/* 137:    */     {
/* 138:152 */       sendFailureMessage(response, "Sorry ,User is not exists.");
/* 139:153 */       return;
/* 140:    */     }
/* 141:155 */     if (StringUtils.isBlank(newPwd))
/* 142:    */     {
/* 143:156 */       sendFailureMessage(response, "Password is required.");
/* 144:157 */       return;
/* 145:    */     }
/* 146:160 */     if ((!isAdmin) && (!MethodUtil.ecompareMD5(oldPwd, bean.getPwd())))
/* 147:    */     {
/* 148:161 */       sendFailureMessage(response, "Wrong old password.");
/* 149:162 */       return;
/* 150:    */     }
/* 151:164 */     bean.setPwd(MethodUtil.MD5(newPwd));
/* 152:165 */     this.sysUserService.update(bean);
/* 153:166 */     sendSuccessMessage(response, "保存成功~");
/* 154:    */   }
/* 155:    */   
/* 156:    */   @RequestMapping({"/userRole"})
/* 157:    */   public ModelAndView userRole(HttpServletRequest request)
/* 158:    */     throws Exception
/* 159:    */   {
/* 160:179 */     Map<String, Object> context = new HashMap();
/* 161:180 */     return forword("/sys/sysUserRole", context);
/* 162:    */   }
/* 163:    */   
/* 164:    */   @RequestMapping({"/userList"})
/* 165:    */   public void userList(SysUserModel model, HttpServletResponse response)
/* 166:    */     throws Exception
/* 167:    */   {
/* 168:191 */     model.setState(Integer.valueOf(BaseBean.STATE.ENABLE.key));
/* 169:192 */     dataList(model, response);
/* 170:    */   }
/* 171:    */   
/* 172:    */   @RequestMapping({"/getUser"})
/* 173:    */   public void getUser(Integer id, HttpServletResponse response)
/* 174:    */     throws Exception
/* 175:    */   {
/* 176:203 */     Map<String, Object> context = new HashMap();
/* 177:204 */     SysUser bean = this.sysUserService.queryById(id);
/* 178:205 */     if (bean == null)
/* 179:    */     {
/* 180:206 */       sendFailureMessage(response, "没有找到对应的记录!");
/* 181:207 */       return;
/* 182:    */     }
/* 183:209 */     Integer[] roleIds = (Integer[])null;
/* 184:210 */     List<SysRoleRel> roles = this.sysUserService.getUserRole(bean.getId());
/* 185:211 */     if (roles != null)
/* 186:    */     {
/* 187:212 */       roleIds = new Integer[roles.size()];
/* 188:213 */       int i = 0;
/* 189:214 */       for (SysRoleRel rel : roles)
/* 190:    */       {
/* 191:215 */         roleIds[i] = rel.getRoleId();
/* 192:216 */         i++;
/* 193:    */       }
/* 194:    */     }
/* 195:220 */     Map<String, Object> data = new HashMap();
/* 196:221 */     data.put("id", bean.getId());
/* 197:222 */     data.put("email", bean.getEmail());
/* 198:223 */     data.put("roleIds", roleIds);
/* 199:224 */     context.put("success", Boolean.valueOf(true));
/* 200:225 */     context.put("data", data);
/* 201:226 */     HtmlUtil.writerJson(response, context);
/* 202:    */   }
/* 203:    */   
/* 204:    */   @RequestMapping({"/addUserRole"})
/* 205:    */   public void addUserRole(Integer id, Integer[] roleIds, HttpServletResponse response)
/* 206:    */     throws Exception
/* 207:    */   {
/* 208:239 */     this.sysUserService.addUserRole(id, roleIds);
/* 209:240 */     sendSuccessMessage(response, "保存成功");
/* 210:    */   }
/* 211:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.action.SysUserAction
 * JD-Core Version:    0.7.0.1
 */
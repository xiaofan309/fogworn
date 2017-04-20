/*   1:    */ package com.sys.action;
/*   2:    */ 
/*   3:    */ import com.base.BaseAction;
import com.base.BaseBean;
/*   4:    */ import com.base.BaseBean.DELETED;
/*   5:    */ import com.base.BaseBean.STATE;
/*   6:    */ import com.base.TreeNode;
/*   7:    */ import com.base.annotation.Auth;
import com.base.utils.Constant;
/*   8:    */ import com.base.utils.Constant.SuperAdmin;
/*   9:    */ import com.base.utils.DateUtil;
/*  10:    */ import com.base.utils.HtmlUtil;
/*  11:    */ import com.base.utils.MethodUtil;
/*  12:    */ import com.base.utils.SessionUtils;
/*  13:    */ import com.base.utils.TreeUtil;
/*  14:    */ import com.base.utils.URLUtils;
/*  15:    */ import com.sys.bean.SysMenu;
/*  16:    */ import com.sys.bean.SysMenuBtn;
/*  17:    */ import com.sys.bean.SysUser;
/*  18:    */ import com.sys.service.SysLogService;
/*  19:    */ import com.sys.service.SysMenuBtnService;
/*  20:    */ import com.sys.service.SysMenuService;
/*  21:    */ import com.sys.service.SysUserService;
/*  22:    */ import java.util.ArrayList;
/*  23:    */ import java.util.HashMap;
/*  24:    */ import java.util.List;
/*  25:    */ import java.util.Map;
/*  26:    */ import javax.servlet.http.HttpServletRequest;
/*  27:    */ import javax.servlet.http.HttpServletResponse;
/*  28:    */ import org.apache.commons.lang.StringUtils;
/*  29:    */ import org.apache.log4j.Logger;
/*  30:    */ import org.springframework.beans.factory.annotation.Autowired;
/*  31:    */ import org.springframework.stereotype.Controller;
/*  32:    */ import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/*  34:    */ 
/*  35:    */ @Controller
/*  36:    */ public class MainAction
/*  37:    */   extends BaseAction
/*  38:    */ {
/*  39: 42 */   private static final Logger log = Logger.getLogger(MainAction.class);
/*  40:    */   @Autowired(required=false)
/*  41:    */   private SysMenuService sysMenuService;
/*  42:    */   @Autowired(required=false)
/*  43:    */   private SysUserService sysUserService;
/*  44:    */   @Autowired(required=false)
/*  45:    */   private SysMenuBtnService sysMenuBtnService;
/*  46:    */   @Autowired(required=false)
/*  47:    */   private SysLogService sysLogService;
/*  48:    */   
/*  49:    */   @Auth(verifyLogin=false, verifyURL=false)
/*  50:    */   @RequestMapping({"/login"})
/*  51:    */   public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
/*  52:    */     throws Exception
/*  53:    */   {
/*  54: 65 */     Map<String, Object> context = new HashMap();
/*  55: 66 */     return forword("login", context);
/*  56:    */   }
/*  57:    */   
/*  58:    */   @Auth(verifyLogin=false, verifyURL=false)
/*  59:    */   @RequestMapping({"/toLogin"})
/*  60:    */   public void toLogin(String email, String pwd, String verifyCode, HttpServletRequest request, HttpServletResponse response)
/*  61:    */     throws Exception
/*  62:    */   {
/*  63: 82 */     String vcode = SessionUtils.getValidateCode(request);
/*  64: 83 */     SessionUtils.removeValidateCode(request);
/*  65: 85 */     if (StringUtils.isBlank(verifyCode))
/*  66:    */     {
/*  67: 86 */       sendFailureMessage(response, "验证码不能为空.", 1);
/*  68: 87 */       return;
/*  69:    */     }
/*  70: 90 */     if (!verifyCode.toLowerCase().equals(vcode))
/*  71:    */     {
/*  72: 91 */       sendFailureMessage(response, "验证码输入错误.", 1);
/*  73: 92 */       return;
/*  74:    */     }
/*  75: 94 */     if (StringUtils.isBlank(email))
/*  76:    */     {
/*  77: 95 */       sendFailureMessage(response, "账号不能为空.", 2);
/*  78: 96 */       return;
/*  79:    */     }
/*  80: 98 */     if (StringUtils.isBlank(pwd))
/*  81:    */     {
/*  82: 99 */       sendFailureMessage(response, "密码不能为空.", 3);
/*  83:100 */       return;
/*  84:    */     }
/*  85:102 */     String msg = "用户登录日志:";
/*  86:103 */     SysUser user = this.sysUserService.queryLogin(email, MethodUtil.MD5(pwd));
/*  87:104 */     if (user == null)
/*  88:    */     {
/*  89:106 */       log.debug(msg + "[" + email + "]" + "账号或者密码输入错误.");
/*  90:107 */       sendFailureMessage(response, "账号或者密码输入错误.", 2);
/*  91:108 */       return;
/*  92:    */     }
/*  93:110 */     if (BaseBean.STATE.DISABLE.key == user.getState().intValue())
/*  94:    */     {
/*  95:111 */       sendFailureMessage(response, "账号已被禁用.", 2);
/*  96:112 */       return;
/*  97:    */     }
/*  98:115 */     int loginCount = 0;
/*  99:116 */     if (user.getLoginCount() != null) {
/* 100:117 */       loginCount = user.getLoginCount().intValue();
/* 101:    */     }
/* 102:119 */     user.setLoginCount(Integer.valueOf(loginCount + 1));
/* 103:120 */     user.setLoginTime(DateUtil.getDateByString(""));
/* 104:121 */     this.sysUserService.update(user);
/* 105:    */     
/* 106:123 */     SessionUtils.setUser(request, user);
/* 107:    */     
/* 108:125 */     log.debug(msg + "[" + email + "]" + "登录成功");
/* 109:126 */     this.sysLogService.addLog(user.getId(), user.getNickName(), "", user.getId(), "登录", "用户登录成功");
/* 110:127 */     sendSuccessMessage(response, "登录成功.");
/* 111:    */   }
/* 112:    */   
/* 113:    */   @Auth(verifyLogin=false, verifyURL=false)
/* 114:    */   @RequestMapping({"/logout"})
/* 115:    */   public void logout(HttpServletRequest request, HttpServletResponse response)
/* 116:    */     throws Exception
/* 117:    */   {
/* 118:140 */     this.sysLogService.addLog(request, "", null, "退出", "用户退出系统");
/* 119:141 */     SessionUtils.removeUser(request);
/* 120:142 */     response.sendRedirect(request.getContextPath() + "/login.shtml");
/* 121:    */   }
/* 122:    */   
/* 123:    */   @Auth(verifyURL=false)
/* 124:    */   @RequestMapping({"/getActionBtn"})
/* 125:    */   public void getActionBtn(String url, HttpServletRequest request, HttpServletResponse response)
/* 126:    */     throws Exception
/* 127:    */   {
/* 128:154 */     Map<String, Object> result = new HashMap();
/* 129:155 */     List<String> actionTypes = new ArrayList();
/* 130:157 */     if (SessionUtils.isAdmin(request))
/* 131:    */     {
/* 132:158 */       result.put("allType", Boolean.valueOf(true));
/* 133:    */     }
/* 134:    */     else
/* 135:    */     {
/* 136:160 */       String menuUrl = URLUtils.getReqUri(url);
/* 137:161 */       menuUrl = StringUtils.remove(menuUrl, request.getContextPath());
/* 138:    */       
/* 139:163 */       actionTypes = SessionUtils.getMemuBtnListVal(request, StringUtils.trim(menuUrl));
/* 140:164 */       result.put("allType", Boolean.valueOf(false));
/* 141:165 */       result.put("types", actionTypes);
/* 142:    */     }
/* 143:167 */     result.put("success", Boolean.valueOf(true));
/* 144:168 */     HtmlUtil.writerJson(response, result);
/* 145:    */   }
/* 146:    */   
/* 147:    */   @Auth(verifyURL=false)
/* 148:    */   @RequestMapping({"/modifyPwd"})
/* 149:    */   public void modifyPwd(String oldPwd, String newPwd, HttpServletRequest request, HttpServletResponse response)
/* 150:    */     throws Exception
/* 151:    */   {
/* 152:182 */     SysUser user = SessionUtils.getUser(request);
/* 153:183 */     if (user == null)
/* 154:    */     {
/* 155:184 */       sendFailureMessage(response, "对不起,登录超时.");
/* 156:185 */       return;
/* 157:    */     }
/* 158:187 */     SysUser bean = this.sysUserService.queryById(user.getId());
/* 159:188 */     if ((bean.getId() == null) || (BaseBean.DELETED.YES.key == bean.getDeleted().intValue()))
/* 160:    */     {
/* 161:189 */       sendFailureMessage(response, "对不起,用户不存在.");
/* 162:190 */       return;
/* 163:    */     }
/* 164:192 */     if (StringUtils.isBlank(newPwd))
/* 165:    */     {
/* 166:193 */       sendFailureMessage(response, "密码不能为空.");
/* 167:194 */       return;
/* 168:    */     }
/* 169:197 */     if (!MethodUtil.ecompareMD5(oldPwd, bean.getPwd()))
/* 170:    */     {
/* 171:198 */       sendFailureMessage(response, "旧密码输入不匹配.");
/* 172:199 */       return;
/* 173:    */     }
/* 174:201 */     bean.setPwd(MethodUtil.MD5(newPwd));
/* 175:202 */     this.sysUserService.update(bean);
/* 176:203 */     sendSuccessMessage(response, "Save success.");
/* 177:    */   }
/* 178:    */   
/* 179:    */   @Auth(verifyURL=false)
/* 180:    */   @RequestMapping({"/main"})
/* 181:    */   public ModelAndView main(HttpServletRequest request)
/* 182:    */   {
/* 183:215 */     Map<String, Object> context = new HashMap();
/* 184:216 */     SysUser user = SessionUtils.getUser(request);
/* 185:217 */     List<SysMenu> rootMenus = null;
/* 186:218 */     List<SysMenu> childMenus = null;
/* 187:219 */     List<SysMenuBtn> childBtns = null;
/* 188:221 */     if ((user != null) && (Constant.SuperAdmin.YES.key == user.getSuperAdmin().intValue()))
/* 189:    */     {
/* 190:222 */       rootMenus = this.sysMenuService.getRootMenu(null);
/* 191:223 */       childMenus = this.sysMenuService.getChildMenu();
/* 192:    */     }
/* 193:    */     else
/* 194:    */     {
/* 195:225 */       rootMenus = this.sysMenuService.getRootMenuByUser(user.getId());
/* 196:226 */       childMenus = this.sysMenuService.getChildMenuByUser(user.getId());
/* 197:227 */       childBtns = this.sysMenuBtnService.getMenuBtnByUser(user.getId());
/* 198:228 */       buildData(childMenus, childBtns, request);
/* 199:    */     }
/* 200:230 */     context.put("user", user);
/* 201:231 */     context.put("menuList", treeMenu(rootMenus, childMenus));
/* 202:232 */     return forword("main/main", context);
/* 203:    */   }
/* 204:    */   
/* 205:    */   private List<TreeNode> treeMenu(List<SysMenu> rootMenus, List<SysMenu> childMenus)
/* 206:    */   {
/* 207:240 */     TreeUtil util = new TreeUtil(rootMenus, childMenus);
/* 208:241 */     return util.getTreeNode();
/* 209:    */   }
/* 210:    */   
/* 211:    */   private void buildData(List<SysMenu> childMenus, List<SysMenuBtn> childBtns, HttpServletRequest request)
/* 212:    */   {
/* 213:251 */     List<String> accessUrls = new ArrayList();
/* 214:    */     
/* 215:253 */     Map<String, List> menuBtnMap = new HashMap();
/* 216:254 */     for (SysMenu menu : childMenus) {
/* 217:256 */       if (StringUtils.isNotBlank(menu.getUrl()))
/* 218:    */       {
/* 219:257 */         List<String> btnTypes = new ArrayList();
/* 220:258 */         for (SysMenuBtn btn : childBtns) {
/* 221:259 */           if (menu.getId().equals(btn.getMenuid()))
/* 222:    */           {
/* 223:260 */             btnTypes.add(btn.getBtnType());
/* 224:261 */             URLUtils.getBtnAccessUrls(menu.getUrl(), btn.getActionUrls(), accessUrls);
/* 225:    */           }
/* 226:    */         }
/* 227:264 */         menuBtnMap.put(menu.getUrl(), btnTypes);
/* 228:265 */         URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(), accessUrls);
/* 229:266 */         accessUrls.add(menu.getUrl());
/* 230:    */       }
/* 231:    */     }
/* 232:269 */     SessionUtils.setAccessUrl(request, accessUrls);
/* 233:270 */     SessionUtils.setMemuBtnMap(request, menuBtnMap);
/* 234:    */   }
/* 235:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.action.MainAction
 * JD-Core Version:    0.7.0.1
 */
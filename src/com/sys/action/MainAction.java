package com.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.BaseAction;
import com.base.BaseBean;
import com.base.TreeNode;
import com.base.annotation.Auth;
import com.base.utils.Constant;
import com.base.utils.DateUtil;
import com.base.utils.HtmlUtil;
import com.base.utils.MethodUtil;
import com.base.utils.SessionUtils;
import com.base.utils.TreeUtil;
import com.base.utils.URLUtils;
import com.sys.bean.SysMenu;
import com.sys.bean.SysMenuBtn;
import com.sys.bean.SysUser;
import com.sys.service.SysLogService;
import com.sys.service.SysMenuBtnService;
import com.sys.service.SysMenuService;
import com.sys.service.SysUserService;

@Controller
public class MainAction extends BaseAction {
	private static final Logger log = Logger.getLogger(MainAction.class);
	@Autowired(required = false)
	private SysMenuService sysMenuService;
	@Autowired(required = false)
	private SysUserService sysUserService;
	@Autowired(required = false)
	private SysMenuBtnService sysMenuBtnService;
	@Autowired(required = false)
	private SysLogService sysLogService;

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping({ "/login" })
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		return forword("login", context);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping({ "/toLogin" })
	public void toLogin(String email, String pwd, String verifyCode,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String vcode = SessionUtils.getValidateCode(request);
		SessionUtils.removeValidateCode(request);
		if (StringUtils.isBlank(verifyCode)) {
			sendFailureMessage(response, "验证码不能为空.", 1);
			return;
		}
		if (!verifyCode.toLowerCase().equals(vcode)) {
			sendFailureMessage(response, "验证码输入错误.", 1);
			return;
		}
		if (StringUtils.isBlank(email)) {
			sendFailureMessage(response, "账号不能为空.", 2);
			return;
		}
		if (StringUtils.isBlank(pwd)) {
			sendFailureMessage(response, "密码不能为空.", 3);
			return;
		}
		String msg = "用户登录日志:";
		SysUser user = this.sysUserService.queryLogin(email,
				MethodUtil.MD5(pwd));
		if (user == null) {
			log.debug(msg + "[" + email + "]" + "账号或者密码输入错误.");
			sendFailureMessage(response, "账号或者密码输入错误.", 2);
			return;
		}
		if (BaseBean.STATE.DISABLE.key == user.getState().intValue()) {
			sendFailureMessage(response, "账号已被禁用.", 2);
			return;
		}
		int loginCount = 0;
		if (user.getLoginCount() != null) {
			loginCount = user.getLoginCount().intValue();
		}
		user.setLoginCount(Integer.valueOf(loginCount + 1));
		user.setLoginTime(DateUtil.getDateByString(""));
		this.sysUserService.update(user);

		SessionUtils.setUser(request, user);

		log.debug(msg + "[" + email + "]" + "登录成功");
		this.sysLogService.addLog(user.getId(), user.getNickName(), "",
				user.getId(), "登录", "用户登录成功");
		sendSuccessMessage(response, "登录成功.");
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping({ "/logout" })
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.sysLogService.addLog(request, "", null, "退出", "用户退出系统");
		SessionUtils.removeUser(request);
		response.sendRedirect(request.getContextPath() + "/login.shtml");
	}

	@Auth(verifyURL = false)
	@RequestMapping({ "/getActionBtn" })
	public void getActionBtn(String url, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap();
		List<String> actionTypes = new ArrayList();
		if (SessionUtils.isAdmin(request)) {
			result.put("allType", Boolean.valueOf(true));
		} else {
			String menuUrl = URLUtils.getReqUri(url);
			menuUrl = StringUtils.remove(menuUrl, request.getContextPath());

			actionTypes = SessionUtils.getMemuBtnListVal(request,
					StringUtils.trim(menuUrl));
			result.put("allType", Boolean.valueOf(false));
			result.put("types", actionTypes);
		}
		result.put("success", Boolean.valueOf(true));
		HtmlUtil.writerJson(response, result);
	}

	@Auth(verifyURL = false)
	@RequestMapping({ "/modifyPwd" })
	public void modifyPwd(String oldPwd, String newPwd,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = SessionUtils.getUser(request);
		if (user == null) {
			sendFailureMessage(response, "对不起,登录超时.");
			return;
		}
		SysUser bean = this.sysUserService.queryById(user.getId());
		if ((bean.getId() == null)
				|| (BaseBean.DELETED.YES.key == bean.getDeleted().intValue())) {
			sendFailureMessage(response, "对不起,用户不存在.");
			return;
		}
		if (StringUtils.isBlank(newPwd)) {
			sendFailureMessage(response, "密码不能为空.");
			return;
		}
		if (!MethodUtil.ecompareMD5(oldPwd, bean.getPwd())) {
			sendFailureMessage(response, "旧密码输入不匹配.");
			return;
		}
		bean.setPwd(MethodUtil.MD5(newPwd));
		this.sysUserService.update(bean);
		sendSuccessMessage(response, "Save success.");
	}

	@Auth(verifyURL = false)
	@RequestMapping({ "/main" })
	public ModelAndView main(HttpServletRequest request) {
		Map<String, Object> context = new HashMap();
		SysUser user = SessionUtils.getUser(request);
		List<SysMenu> rootMenus = null;
		List<SysMenu> childMenus = null;
		List<SysMenuBtn> childBtns = null;
		if ((user != null)
				&& (Constant.SuperAdmin.YES.key == user.getSuperAdmin()
						.intValue())) {
			rootMenus = this.sysMenuService.getRootMenu(null);
			childMenus = this.sysMenuService.getChildMenu();
		} else {
			rootMenus = this.sysMenuService.getRootMenuByUser(user.getId());
			childMenus = this.sysMenuService.getChildMenuByUser(user.getId());
			childBtns = this.sysMenuBtnService.getMenuBtnByUser(user.getId());
			buildData(childMenus, childBtns, request);
		}
		context.put("user", user);
		context.put("menuList", treeMenu(rootMenus, childMenus));
		return forword("main/main", context);
	}

	private List<TreeNode> treeMenu(List<SysMenu> rootMenus,
			List<SysMenu> childMenus) {
		TreeUtil util = new TreeUtil(rootMenus, childMenus);
		return util.getTreeNode();
	}

	private void buildData(List<SysMenu> childMenus,
			List<SysMenuBtn> childBtns, HttpServletRequest request) {
		List<String> accessUrls = new ArrayList();

		Map<String, List> menuBtnMap = new HashMap();
		for (SysMenu menu : childMenus) {
			if (StringUtils.isNotBlank(menu.getUrl())) {
				List<String> btnTypes = new ArrayList();
				for (SysMenuBtn btn : childBtns) {
					if (menu.getId().equals(btn.getMenuid())) {
						btnTypes.add(btn.getBtnType());
						URLUtils.getBtnAccessUrls(menu.getUrl(),
								btn.getActionUrls(), accessUrls);
					}
				}
				menuBtnMap.put(menu.getUrl(), btnTypes);
				URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(),
						accessUrls);
				accessUrls.add(menu.getUrl());
			}
		}
		SessionUtils.setAccessUrl(request, accessUrls);
		SessionUtils.setMemuBtnMap(request, menuBtnMap);
	}
}

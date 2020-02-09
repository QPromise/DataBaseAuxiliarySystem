package cn.edu.ouc.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.ouc.pojo.dto.Role;
import cn.edu.ouc.pojo.dto.User;
import cn.edu.ouc.pojo.dto.UserCheck;
import cn.edu.ouc.service.RoleService;
import cn.edu.ouc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	// 用户登录
	@RequestMapping("/login")
	public @ResponseBody String login(String userName, String password) {
		Subject currentUser = SecurityUtils.getSubject();

		try {
			if (!currentUser.isAuthenticated()) {
				// 把用户名和密码封装为token对象
				UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
				token.setRememberMe(true);
				currentUser.login(token);
			}
			// return "indexQD";
			// 省市县全面的
			return "index";
			// 天津的
			// return "indexTJ";
			// 强国
			// return "indexQG";
		} catch (IncorrectCredentialsException e) {
			return "error";
		} catch (UnknownAccountException e) {
			return "none";
		} catch (DisabledAccountException E) {
			return "isNotEnable";
		}

	}

	// 联查所有用户，进入“用户管理”界面
	@RequestMapping("/getAllUser")
	public String getAllUser(Map<String, Object> map) throws Exception {

		// 获取当前的登录用户
		String userName = (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
		String roleName = userService.getRoleByUserName(userName);

		// 查询所有用户
		List<UserCheck> users = null;
		if ("developer".equals(roleName) || "boss".equals(roleName)) {
			userName = null;
			users = userService.listUsers(userName);
		} else {
			users = userService.listUsers(userName);
		}
		// 查询所有用户
		map.put("users", users);
		// 查询所有角色
		List<Role> roles = roleService.listRoles();
		map.put("roles", roles);
		return "pages/user/user";
	}

	// 添加新用户
	@RequestMapping("/saveUser")
	public String saveUser(User user, RedirectAttributes attr) {
		user.setIsEnable(0);// 预设用户状态
		String result = null;
		if (userService.saveUser(user)) {
			result = "操作成功";
		} else {
			result = "操作失败";
		}
		attr.addFlashAttribute("result", result);
		return "redirect:/getAllUser.action";
	}

	// 更新用户
	@RequestMapping("/updateUser")
	public String updateUser(User user, String old, RedirectAttributes attr) {
		user.setIsEnable(0);//
		String result = null;
		if (userService.updateUser(user, old)) {
			// 修改用户 。
			result = "操作执行成功";
			attr.addFlashAttribute("result", result);
		} else {
			result = "操作失败";
			attr.addFlashAttribute("result", result);
		}
		return "redirect:/getAllUser.action";
	}

	// 删除用户
	@RequestMapping("/deleteUser")
	public String deleteUser(Integer id, RedirectAttributes attr) {
		User user = new User();
		user.setId(id);
		String result = null;
		try {
			userService.deleteUser(user);
			result = "操作执行成功";
		} catch (Exception e) {
			result = "操作失败";
			attr.addFlashAttribute("result", result);
			return "redirect:/getAllUser.action";
		}
		attr.addFlashAttribute("result", result);
		return "redirect:/getAllUser.action";
	}

}

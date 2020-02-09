package cn.edu.ouc.auth;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.ouc.pojo.dto.User;
import cn.edu.ouc.service.UserService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)  
	 * @Function: ShiroRealm.java
	 * @Description: 用户认证方法
	 *
	 * @version: v1.0.0
	 * @author: AN
	 * @date: 2017年11月18日 下午7:22:28 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		String userName = upToken.getUsername();
		
		
		User user = null;
		user = userService.getUserByUserName(userName);
		
		 if (user == null) {
             throw new UnknownAccountException();
         }
         if (user.getIsEnable() == 0) {
			 throw new DisabledAccountException();
		 }

		
		Object principals = upToken.getUsername();
		Object credentials = user.getPassWord();
		String realmName = getName();
		
		//用户名加入session
		SecurityUtils.getSubject().getSession(true).setAttribute("userName", principals);
		
		//根据用户信息构建AuthenticationToken
		//密码的比对由shrio执行
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principals, credentials, realmName);
		
		return info;
	}
	
	/**
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)  
	 * @Function: ShiroRealm.java
	 * @Description:用于授权的方法
	 *
	 * @version: v1.0.0
	 * @author: AN
	 * @date: 2017年11月18日 下午7:23:44 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		Object principal = principals.getPrimaryPrincipal();
		
		try {
			
			//当前认证用户应有的权限
			Set<String> permission = new HashSet<String>();
//			List<String> power = userService.getPowerByUserName((String)principal);
//			
//			for (String string : power) {
//				permission.add(string);
//				info.addRole(string);
//			}
			//permission.add("delivery:manage");
		
			//将权限添加到构造器
			//info.addRole("delivery:manage");
			//将当前认证用户拥有的权限添加到构造器
			info.addStringPermissions(permission);
			//roles.add("user:admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	
	/**
     * 清除缓存,在其他代码中注入realm即可使用。
     * 更改权限时需要清除缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
	
}

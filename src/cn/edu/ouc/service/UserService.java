package cn.edu.ouc.service;

import java.util.List;

import cn.edu.ouc.pojo.dto.User;
import cn.edu.ouc.pojo.dto.UserCheck;

public interface UserService {
	// 登录验证
	User getUserByUserName(String name);
	
	 /**
     * 联查所有用户，界面显示
     * @return
     */
	List<UserCheck> listUsers(String userName);

	/**
     * 新增用户
     * @return
     */
	boolean saveUser(User user);

	/**
     * 更新用户
     * @return
     */
	boolean updateUser(User user, String old);


	/**
     * 删除用户
     * @return
     */
	void deleteUser(User user);
	
	/**
	 * 
	 * @Function: RoleService.java
	 * @Description: 根据用户名查询角色名称
	 *
	 * @version: v1.0.0
	 * @author: AN
	 * @date: 2017年11月18日 下午7:46:18 
	 */
	String getRoleByUserName(String userName) throws Exception;
	
}

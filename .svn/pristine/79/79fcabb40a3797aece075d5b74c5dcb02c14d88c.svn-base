package cn.edu.ouc.mapper.province;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.ouc.pojo.dto.User;
import cn.edu.ouc.pojo.dto.UserCheck;

public interface UserMapper {

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @return
	 */
	User getUserByUserName(String name);
	
	/**
     * 联查所有用户，界面显示
     * @return
     */
	List<UserCheck> listUsers(@Param("userName") String userName);
	
	/**
     * 新增用户
     * @return
     */
	void saveUser(User user);
	
	/**
     * 更新用户
     * @return
     */
	void updateUser(User user);
	
	/**
     * 删除用户
     * @return
     */
	void deleteUser(User user);
	
	/**
     * 根据用户名查找用户，注册时判断用户名是否唯一
     * @return
     */
	User findUserByName(String name);
	
	/**
	 * 
	 * @Function: UserMapper.java
	 * @Description: 根据用户名查询当期角色
	 *
	 * @version: v1.0.0
	 * @author: AN
	 * @date: 2017年11月18日 下午7:35:29 
	 */
	String getRoleByUserName(String userName);
	

}

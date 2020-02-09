package cn.edu.ouc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ouc.mapper.province.UserMapper;
import cn.edu.ouc.pojo.dto.User;
import cn.edu.ouc.pojo.dto.UserCheck;
import cn.edu.ouc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	// 注入mapper
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByUserName(String name) {
		User user = userMapper.getUserByUserName(name);
		return user;
	}

	// 联查所有用户，界面显示
	@Override
	public List<UserCheck> listUsers(String userName) {
		return userMapper.listUsers(userName);
	}

	// 添加用户
	@Override
	public boolean saveUser(User user) {
		if (userMapper.findUserByName(user.getUserName()) != null) {
			return false;
		} else {
			userMapper.saveUser(user);
			return true;
		}
	}

	// 更新用户
	@Override
	public boolean updateUser(User user, String old) {
		User found = userMapper.findUserByName(user.getUserName());
		if (old.equals("")) {
			user.setPassWord(found.getPassWord());
			userMapper.updateUser(user);
			return true;
		} else {
			if (found != null) {
				if (found.getPassWord().equals(old)) {
					userMapper.updateUser(user);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	// 删除用户
	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}
	
	@Override
	public String getRoleByUserName(String userName) throws Exception {
		return userMapper.getRoleByUserName(userName);
	}

}

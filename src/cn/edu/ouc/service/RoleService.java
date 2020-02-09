package cn.edu.ouc.service;

import java.util.List;

import cn.edu.ouc.pojo.dto.Role;

public interface RoleService {

	/**
	 * 查询所有角色
	 * @return
	 */
	List<Role> listRoles();

}

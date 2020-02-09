package cn.edu.ouc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ouc.mapper.province.RoleMapper;
import cn.edu.ouc.pojo.dto.Role;
import cn.edu.ouc.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	//注入mapper
    @Autowired
    private RoleMapper roleMapper;

 	//查询角色
	@Override
	public List<Role> listRoles() {
		return roleMapper.listRoles();
	}


}

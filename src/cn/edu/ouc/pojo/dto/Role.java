package cn.edu.ouc.pojo.dto;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	// id
	private Integer id;
	// 角色名
	private String roleName;
	// 备注
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", remark=" + remark + "]";
	}

}

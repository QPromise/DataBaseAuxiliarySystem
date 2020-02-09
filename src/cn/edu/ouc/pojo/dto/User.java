package cn.edu.ouc.pojo.dto;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//用户名
    private String userName;
    //密码
    private String passWord;
    //角色Id
    private Integer fkRoleId;
    //是否启用 1启用 0不启用
    private Integer isEnable;
    //备注
    private String remark;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getFkRoleId() {
		return fkRoleId;
	}
	public void setFkRoleId(Integer fkRoleId) {
		this.fkRoleId = fkRoleId;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", fkRoleId=" + fkRoleId + ", isEnable="
				+ isEnable + ", remark=" + remark + "]";
	}
    
}

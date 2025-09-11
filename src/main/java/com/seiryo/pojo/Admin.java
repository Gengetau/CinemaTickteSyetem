package com.seiryo.pojo;

/**
 * @author 11567
 * @version v1.0
 * @ClassName Admin
 * @Description 管理员实体类
 * @dateTime 2025/9/9 19:19
 */
public class Admin {
	// 来自 ADMIN_INFO表
	private Integer adminId;//管理员编号
	private String adminName;//管理员账号
	private String adminPass;//管理员密码
	private Integer adminPermission;//管理员权限等级
	private String adminState;//管理员账号状态
	
	public Admin() {
	}
	
	public Admin(Integer adminId, String adminName, String adminPass, Integer adminPermission, String adminState) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPass = adminPass;
		this.adminPermission = adminPermission;
		this.adminState = adminState;
	}
	
	public Integer getAdminId() {
		return adminId;
	}
	
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminPass() {
		return adminPass;
	}
	
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	public Integer getAdminPermission() {
		return adminPermission;
	}
	
	public void setAdminPermission(Integer adminPermission) {
		this.adminPermission = adminPermission;
	}
	
	public String getAdminState() {
		return adminState;
	}
	
	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}
	
	@Override
	public String toString() {
		return "AdminInfo{" +
				"adminId=" + adminId +
				", adminName='" + adminName + '\'' +
				", adminPass='" + adminPass + '\'' +
				", adminPermission=" + adminPermission +
				", adminState=" + adminState +
				'}';
	}
}

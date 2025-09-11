package com.seiryo.pojo;

import java.math.BigDecimal;

/**
 * @author 11567
 * @version v1.0
 * @ClassName User
 * @Description 用户实体类
 * @dateTime 2025/9/9 17:31
 */
public class User {
	// 来自 MY_USER表
	private Long userid;// 用户编号
	private String username;// 用户账号
	private String userpass;// 用户密码
	
	// 来自 MY_USER_INFO表
	private BigDecimal userMoney;// 用户余额
	private Long userPoint;// 用户积分
	private String userVip;// 用户会员等级
	private String userPhone;// 用户手机号
	private String userState;// 用户状态
	
	public User() {
	}
	
	public User(Long userid, String username, String userpass, BigDecimal userMoney, Long userPoint, String userVip, String userPhone, String userState) {
		this.userid = userid;
		this.username = username;
		this.userpass = userpass;
		this.userMoney = userMoney;
		this.userPoint = userPoint;
		this.userVip = userVip;
		this.userPhone = userPhone;
		this.userState = userState;
	}
	
	public Long getUserid() {
		return userid;
	}
	
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserpass() {
		return userpass;
	}
	
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	
	public BigDecimal getUserMoney() {
		return userMoney;
	}
	
	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}
	
	public Long getUserPoint() {
		return userPoint;
	}
	
	public void setUserPoint(Long userPoint) {
		this.userPoint = userPoint;
	}
	
	public String getUserVip() {
		return userVip;
	}
	
	public void setUserVip(String userVip) {
		this.userVip = userVip;
	}
	
	public String getUserPhone() {
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getUserState() {
		return userState;
	}
	
	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	@Override
	public String toString() {
		return "userid=" + userid +
				", username='" + username + '\'' +
				", userpass='" + userpass + '\'' +
				", userMoney=" + userMoney +
				", userPoint=" + userPoint +
				", userVip='" + userVip + '\'' +
				", userPhone='" + userPhone + '\'' +
				", userState='" + userState + '\'';
	}
}

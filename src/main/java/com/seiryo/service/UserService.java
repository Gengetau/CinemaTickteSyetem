package com.seiryo.service;

import com.seiryo.pojo.Admin;
import com.seiryo.pojo.User;

/**
 * @author 11567
 * @version v1.0
 * @ClassName UserService
 * @Description 用户服务层接口
 * @dateTime 2025/9/9 20:03
 */
public interface UserService {
	
	/**
	 * @param inputName 用户输入的账号
	 * @param inputPass 用户输入的密码
	 * @return 返回登录成功的用户
	 * @MethodName: userLogin
	 * @Description: 用户登录逻辑方法
	 */
	User userLogin(String inputName, String inputPass);
	
	/**
	 * @param inputName 用户输入的账号
	 * @return 成功返回true,失败返回false
	 * @MethodName: userRegister
	 * @Description: 用户注册逻辑方法
	 */
	boolean userRegister(String inputName);
	
	/**
	 * @param user 注册的新用户
	 * @MethodName: addUser
	 * @Description: 添加新用户
	 */
	void addUser(User user);
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserMoneyAndPoints
	 * @Description: 更新用户余额和积分
	 */
	void updateUserMoneyAndPoints(User user);
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserMoney
	 * @Description: 更新用户余额
	 */
	void updateUserMoney(User user);
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserVipName
	 * @Description: 更新用户会员等级
	 */
	void updateUserVipName(User user);
	
	/**
	 * @param user 当前用户
	 * @MethodName: updateUserPass
	 * @Description: 更新用户密码
	 */
	void updateUserPass(User user);
	
	/**
	 * @param user 当前用户
	 * @MethodName: updateUserPhone
	 * @Description: 更新用户手机号
	 */
	void updateUserPhone(User user);
	
	// ================== 由管理员发起的对用户的操作 ==================
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: insertUserByAdmin
	 * @Description: 管理员增加用户
	 */
	void insertUserByAdmin(Admin admin);
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: deleteUserByAdmin
	 * @Description: 管理员删除用户
	 */
	void deleteUserByAdmin(Admin admin);
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: updateUserByAdmin
	 * @Description: 管理员修改用户
	 */
	void updateUserByAdmin(Admin admin);
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: queryUserByAdmin
	 * @Description: 管理员查询用户
	 */
	void queryUserByAdmin(Admin admin);
}

package com.seiryo.service;

import com.seiryo.pojo.Admin;

/**
 * @author 11567
 * @version v1.0
 * @ClassName AdminService
 * @Description 管理员服务层接口
 * @dateTime 2025/9/9 21:53
 */
public interface AdminService {
	/**
	 * @param inputName 输入的账号
	 * @param inputPass 输入的密码
	 * @return 返回管理员对象
	 * @MethodName: adminLogin
	 * @Description: 管理员登录逻辑
	 */
	Admin adminLogin(String inputName, String inputPass);
	
	/**
	 * @MethodName: insertUser
	 * @Description: 增加用户
	 */
	void insertUser(Admin admin);
	
	/**
	 * @MethodName: deleteUser
	 * @Description: 删除用户
	 */
	void deleteUser(Admin admin);
	
	/**
	 * @MethodName: updateUser
	 * @Description: 修改用户
	 */
	void updateUser(Admin admin);
	
	/**
	 * @MethodName: queryUser
	 * @Description: 查询用户
	 */
	void queryUser(Admin admin);
	
	/**
	 * @MethodName: insertMovie
	 * @Description: 增加电影
	 */
	void insertMovie(Admin admin);
	
	/**
	 * @MethodName: deleteMovie
	 * @Description: 删除电影
	 */
	void deleteMovie(Admin admin);
	
	/**
	 * @MethodName: updateMovie
	 * @Description: 修改电影
	 */
	void updateMovie(Admin admin);
	
	/**
	 * @MethodName: queryMovie
	 * @Description: 查询电影
	 */
	void queryMovie(Admin admin);
	
	/**
	 * @MethodName: insertVip
	 * @Description: 增加VIP等级
	 */
	void insertVip(Admin admin);
	
	/**
	 * @MethodName: deleteVip
	 * @Description: 删除VIP等级
	 */
	void deleteVip(Admin admin);
	
	/**
	 * @MethodName: updateVip
	 * @Description: 修改VIP等级
	 */
	void updateVip(Admin admin);
	
	/**
	 * @MethodName: queryVip
	 * @Description: 查询VIP等级
	 */
	void queryVip(Admin admin);
	
	/**
	 * @MethodName: updateOrder
	 * @Description: 修改订单
	 */
	void updateOrder(Admin admin);
	
	/**
	 * @MethodName: deleteOrder
	 * @Description: 删除订单
	 */
	void deleteOrder(Admin admin);
	
	/**
	 * @MethodName: queryOrder
	 * @Description: 查询订单
	 */
	void queryOrder(Admin admin);
}

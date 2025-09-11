package com.seiryo.mapper;

import com.seiryo.pojo.User;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName UserMapper
 * @Description 用户数据层映射接口
 * @dateTime 2025/9/9 20:11
 */
public interface UserMapper {
	/**
	 * @return 返回用户列表
	 * @MethodName: selectAllUsers
	 * @Description: 查询所有用户信息
	 */
	List<User> selectAllUsers();
	
	/**
	 * @param user 新用户
	 * @return 返回影响行数
	 * @MethodName: insertNewUser
	 * @Description: 添加新用户
	 */
	int insertNewUser(User user);
	
	/**
	 * @param user 新用户
	 * @return 返回影响行数
	 * @MethodName: insertNewUserInfo
	 * @Description: 添加新用户信息
	 */
	int insertNewUserInfo(User user);
	
	/**
	 * @param userid 需要更新的用户id
	 * @return 返回受影响的行数
	 * @MethodName: updateUserMoneyAndPoints
	 * @Description: 更新用户余额和积分
	 */
	int updateUserMoneyAndPoints(Long userid);
	
	/**
	 * @param userid 需要更新的用户id
	 * @return 返回受影响的行数
	 * @MethodName: updateUserVipName
	 * @Description: 更新用户会员等级
	 */
	int updateUserVipName(Long userid);
	
	/**
	 * @param user 当前用户
	 * @return 返回受影响的行数
	 * @MethodName: updateUserPass
	 * @Description: 修改密码
	 */
	int updateUserPass(User user);
	
	/**
	 * @param user 当前用户
	 * @return 返回受影响的行数
	 * @MethodName: updateUserPhone
	 * @Description: 修改手机号
	 */
	int updateUserPhone(User user);
	
	/**
	 * @param user 需要注销的用户
	 * @return 返回受影响的行数
	 * @MethodName: updateUserState
	 * @Description: 更新用户账号状态
	 */
	int updateUserState(User user);
	
	/**
	 * @param username 用户账号
	 * @return 返回用户对象
	 * @MethodName: selectUserByUsername
	 * @Description: 根据账号查询用户
	 */
	User selectUserByUsername(String username);
	
	/**
	 * @param user 要修改的用户
	 * @return 返回受影响的行数
	 * @MethodName: updateUserByAdmin
	 * @Description: 管理员修改用户信息
	 */
	int updateUserByAdmin(User user);
}

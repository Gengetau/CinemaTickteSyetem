package com.seiryo.serviceImpl;

import com.seiryo.mapper.UserMapper;
import com.seiryo.mapper.VipMapper;
import com.seiryo.pojo.Admin;
import com.seiryo.pojo.User;
import com.seiryo.service.LogService;
import com.seiryo.service.UserService;
import com.seiryo.util.MyBatisUtil;
import com.seiryo.util.ScannerUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName UserServiceImpl
 * @Description 用户服务层接口实现类
 * @dateTime 2025/9/9 20:05
 */
public class UserServiceImpl implements UserService {
	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	/**
	 * @param inputName 用户输入的账号
	 * @param inputPass 用户输入的密码
	 * @return 返回登录成功的用户
	 * @MethodName: userLogin
	 * @Description: 用户登录逻辑方法
	 */
	public User userLogin(String inputName, String inputPass) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			User user = userMapper.selectUserByUsername(inputName);
			if (user != null && user.getUserpass().equals(inputPass)) {
				return user;
			}
		}
		System.out.println("账号或密码错误，请重新输入");
		return null;
	}
	
	/**
	 * @param inputName 用户输入的账号
	 * @return 成功返回true,失败返回false
	 * @MethodName: userRegister
	 * @Description: 用户注册逻辑方法
	 */
	public boolean userRegister(String inputName) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.selectUserByUsername(inputName);
			// 如果查不到用户(user == null)，说明可以注册，返回true
			return user == null;
		}
	}
	
	/**
	 * @param user 注册的新用户
	 * @MethodName: addUser
	 * @Description: 添加新用户
	 */
	public void addUser(User user) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			// 第一步：插入基本用户信息
			int userRows = userMapper.insertNewUser(user);
			
			// 第二步：如果第一步成功，newUser对象现在应该已经自动获得了数据库生成的ID
			if (userRows > 0 && user.getUserid() != null) {
				// 第三步：插入详细信息
				int userInfoRows = userMapper.insertNewUserInfo(user);
				if (userInfoRows > 0) {
					// 两步都成功，提交事务
					sqlSession.commit();
					System.out.println("新用户及信息添加成功！");
					return; // 成功后直接返回
				}
			}
			
			// 如果有任何一步失败，则回滚
			sqlSession.rollback();
			System.out.println("新用户添加失败！");
			
		} catch (Exception e) {
			System.out.println("添加用户时发生数据库错误！");
			// noinspection CallToPrintStackTrace
			e.printStackTrace();
		}
	}
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserMoneyAndPoints
	 * @Description: 更新用户余额和积分
	 */
	public void updateUserMoneyAndPoints(User user) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			int rows = userMapper.updateUserMoneyAndPoints(user.getUserid());
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("用户余额和积分更新成功！");
			} else {
				sqlSession.rollback();
				System.out.println("余额和积分修改失败！");
			}
		}
	}
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserMoney
	 * @Description: 更新用户余额
	 */
	public void updateUserMoney(User user) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			int rows = userMapper.updateUserMoneyAndPoints(user.getUserid());
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("充值成功！当前余额为：" + user.getUserMoney());
			} else {
				sqlSession.rollback();
				System.out.println("充值失败！");
			}
		}
	}
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserVipName
	 * @Description: 更新用户会员等级
	 */
	public void updateUserVipName(User user) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			VipMapper vipMapper = sqlSession.getMapper(VipMapper.class);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			String newVipName = vipMapper.selectVipNameByPoints1(user.getUserPoint());
			
			if (newVipName != null && !newVipName.equals(user.getUserVip())) {
				user.setUserVip(newVipName);
				int rows = userMapper.updateUserVipName(user.getUserid());
				if (rows > 0) {
					sqlSession.commit();
					System.out.println("会员等级提升！当前等级为：" + user.getUserVip());
				} else {
					sqlSession.rollback();
					System.out.println("会员等级提升失败");
				}
			} else {
				Integer newVipPoints = vipMapper.selectVipNameByPoints2(user.getUserPoint());
				if (newVipPoints != null) {
					System.out.println("距离下一会员等级还差" + (newVipPoints - user.getUserPoint()) + "积分");
				} else {
					System.out.println("您已是我们影城最高级的黑钻会员！");
				}
			}
		}
	}
	
	/**
	 * @param user 当前用户
	 * @MethodName: updateUserPass
	 * @Description: 更新用户密码
	 */
	public void updateUserPass(User user) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			int rows = userMapper.updateUserPass(user);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("密码修改成功！");
			} else {
				sqlSession.rollback();
				System.out.println("密码修改失败！");
			}
		}
	}
	
	/**
	 * @param user 当前用户
	 * @MethodName: updateUserPhone
	 * @Description: 更新用户手机号
	 */
	public void updateUserPhone(User user) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			int rows = userMapper.updateUserPhone(user);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("手机号更新成功！");
			} else {
				sqlSession.rollback();
				System.out.println("手机号更新失败！");
			}
		}
	}
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: insertUserByAdmin
	 * @Description: 管理员插入用户
	 */
	public void insertUserByAdmin(Admin admin) {
		System.out.println("========== 增加用户 ==========");
		String inputName = ScannerUtil.nameCheck("请输入要注册的账号【输入back退出增加用户】：");
		if (inputName.equalsIgnoreCase("back")) {
			System.out.println("已退出增加用户!");
			return;
		}
		String inputPass = ScannerUtil.passwordCheck("请输入要注册的密码【输入back退出增加用户】：");
		if (inputPass.equalsIgnoreCase("back")) {
			System.out.println("已退出增加用户!");
			return;
		}
		
		if (userRegister(inputName)) {
			User newUser = new User();
			newUser.setUsername(inputName);
			newUser.setUserpass(inputPass);
			newUser.setUserMoney(BigDecimal.valueOf(0.00));
			newUser.setUserPoint(0L);
			newUser.setUserVip("无");
			newUser.setUserPhone(null);
			newUser.setUserState("正常");
			
			addUser(newUser);
			logService.insertLog(admin, "增加新用户" + newUser.getUsername() + "成功");
		} else {
			logService.insertLog(admin, "尝试增加已存在的用户 " + inputName + " 失败");
			System.out.println("增加失败！账号已存在，请重新输入账号");
		}
	}
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: deleteUserByAdmin
	 * @Description: 管理员删除用户
	 */
	public void deleteUserByAdmin(Admin admin) {
		System.out.println("========== 删除用户 ==========");
		String pd = ScannerUtil.nextLine("是否继续进行删除操作（Y/N）：");
		if (pd.equalsIgnoreCase("N")) {
			System.out.println("正在返回用户管理页面");
			return;
		}
		
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<User> users = userMapper.selectAllUsers();
			System.out.println("所有用户信息如下：");
			users.forEach(System.out::println);
			
			int userId = ScannerUtil.getValidIntegerInput("请输入要删除的用户id：");
			User selectedUser = users.stream()
					.filter(u -> u.getUserid() == userId)
					.findFirst()
					.orElse(null);
			
			if (selectedUser == null) {
				System.out.println("用户ID不存在！");
				return;
			}
			
			selectedUser.setUserState("注销");
			int rows = userMapper.updateUserState(selectedUser);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("用户删除成功！");
				logService.insertLog(admin, "删除用户" + selectedUser.getUsername() + "成功");
			} else {
				sqlSession.rollback();
				System.out.println("用户删除失败！");
				logService.insertLog(admin, "删除用户" + selectedUser.getUsername() + "失败");
			}
		}
	}
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: updateUserByAdmin
	 * @Description: 管理员修改用户信息
	 */
	public void updateUserByAdmin(Admin admin) {
		System.out.println("========== 修改用户 ==========");
		String pd = ScannerUtil.nextLine("是否继续进行修改操作（Y/N）：");
		if (pd.equalsIgnoreCase("N")) {
			System.out.println("正在返回用户管理页面");
			return;
		}
		
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<User> users = userMapper.selectAllUsers();
			System.out.println("所有用户信息如下：");
			users.forEach(System.out::println);
			
			int userId = ScannerUtil.getValidIntegerInput("请输入要修改的用户ID：");
			User userToUpdate = users.stream().filter(u -> u.getUserid() == userId).findFirst().orElse(null);
			
			if (userToUpdate == null) {
				System.out.println("未找到该用户，请重新输入！");
				return;
			}
			
			System.out.println("您将修改用户：" + userToUpdate.getUsername());
			System.out.println("请选择要修改的属性：\n1. 密码\n2. 余额\n3. 积分\n4. 手机号\n5. 用户状态\n6. 返回");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1-6】进行操作：", 1, 6);
			
			switch (choice) {
				case 1:
					String newPass = ScannerUtil.passwordCheck("请输入新密码：");
					userToUpdate.setUserpass(newPass);
					logService.insertLog(admin, "修改用户" + userToUpdate.getUsername() + "密码");
					break;
				case 2:
					BigDecimal newMoney = ScannerUtil.moneyCheck("请输入新余额：");
					userToUpdate.setUserMoney(newMoney);
					logService.insertLog(admin, "修改用户" + userToUpdate.getUsername() + "余额为" + userToUpdate.getUserMoney());
					break;
				case 3:
					long newPoint = ScannerUtil.getValidIntegerInput("请输入新积分：");
					userToUpdate.setUserPoint(newPoint);
					logService.insertLog(admin, "修改用户" + userToUpdate.getUsername() + "积分为" + userToUpdate.getUserPoint());
					break;
				case 4:
					String newPhone = ScannerUtil.phoneCheck("请输入新手机号：");
					userToUpdate.setUserPhone(newPhone);
					logService.insertLog(admin, "修改用户" + userToUpdate.getUsername() + "手机号为" + userToUpdate.getUserPhone());
					break;
				case 5:
					String newState = ScannerUtil.nextLine("请输入新用户状态（例如：正常，注销）：");
					userToUpdate.setUserState(newState);
					logService.insertLog(admin, "修改用户" + userToUpdate.getUsername() + "状态为" + userToUpdate.getUserState());
					break;
				case 6:
					return;
			}
			
			int rows = userMapper.updateUserByAdmin(userToUpdate);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("用户" + userToUpdate.getUsername() + "信息修改成功！");
			} else {
				sqlSession.rollback();
				System.out.println("用户信息修改失败！");
			}
		}
	}
	
	/**
	 * @param admin 操作的管理员 (用于日志记录)
	 * @MethodName: queryUserByAdmin
	 * @Description: 管理员查询用户
	 */
	public void queryUserByAdmin(Admin admin) {
		System.out.println("========== 查询用户 ==========");
		String pd = ScannerUtil.nextLine("是否继续进行查询操作（Y/N）：");
		if (pd.equalsIgnoreCase("N")) {
			System.out.println("正在返回用户管理页面");
			return;
		}
		
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<User> users = userMapper.selectAllUsers();
			System.out.println("所有用户信息如下：");
			users.forEach(System.out::println);
			System.out.println("查询完成！");
			logService.insertLog(admin, "查询所有用户信息");
		}
	}
}

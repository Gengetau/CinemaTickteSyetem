package com.seiryo.serviceImpl;

import com.seiryo.mapper.AdminMapper;
import com.seiryo.mapper.UserMapper;
import com.seiryo.pojo.Admin;
import com.seiryo.pojo.User;
import com.seiryo.service.*;
import com.seiryo.util.MyBatisUtil;
import com.seiryo.util.ScannerUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName AdminServiceImpl
 * @Description 管理员服务层接口实现类
 * @dateTime 2025/9/9 21:56
 */
public class AdminServiceImpl implements AdminService {
	// 获取SqlSession对象和Mapper代理对象
	private final SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private final AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
	private final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	// 定义依赖的Service层接口
	private UserService userService;
	private CinemaService cinemaService;
	private VipService vipService;
	private OrderService orderService;
	private LogService logService;
	
	// Setter方法用于依赖注入
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setCinemaService(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}
	
	public void setVipService(VipService vipService) {
		this.vipService = vipService;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	/**
	 * @param inputName 输入的账号
	 * @param inputPass 输入的密码
	 * @return 返回管理员对象
	 * @MethodName: adminLogin
	 * @Description: 管理员登录逻辑
	 */
	public Admin adminLogin(String inputName, String inputPass) {
		// 1.获取管理员列表
		List<Admin> admins = adminMapper.selectAllAdmins();
		
		// 2.对比账号密码
		for (Admin admin : admins) {
			if (admin.getAdminName().equals(inputName)) {
				if (admin.getAdminPass().equals(inputPass)) {
					return admin;
				}
			}
		}
		System.out.println("账号或密码错误，请重新输入");
		return null;
	}
	
	/**
	 * @MethodName: insertUser
	 * @Description: 增加用户
	 */
	public void insertUser(Admin admin) {
		System.out.println("========== 增加用户 ==========");
		User newUser = new User();
		// 1.增加循环
		while (true) {
			// 2.获取管理员输入的账号密码
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
			
			// 3.调用服务层方法进行注册逻辑验证
			boolean registerSusses = userService.userRegister(inputName);
			
			// 4.判断注册是否成功
			if (registerSusses) {
				// 5.创建对象初始化值
				
				newUser.setUsername(inputName);
				newUser.setUserpass(inputPass);
				newUser.setUserMoney(BigDecimal.valueOf(0.00));
				newUser.setUserPoint(0L);
				newUser.setUserVip("无");
				newUser.setUserPhone(null);
				newUser.setUserState("正常");
				
				// 6.更新数据库
				userService.addUser(newUser);
				userService.addUserInfo(newUser);
				System.out.println("增加用户成功！");
				
				// 7.插入日志
				logService.insertLog(admin, "增加新用户" + newUser.getUsername() + "成功");
				return;
			}
			logService.insertLog(admin, "增加新用户" + newUser.getUsername() + "失败");
			System.out.println("增加失败！账号已存在，请重新输入账号");
			System.out.println("==========================");
		}
	}
	
	/**
	 * @MethodName: deleteUser
	 * @Description: 删除用户
	 */
	public void deleteUser(Admin admin) {
		System.out.println("========== 删除用户 ==========");
		// 删除循环
		while (true) {
			String pd = ScannerUtil.nextLine("是否继续进行删除操作（Y/N）：");
			if (pd.equalsIgnoreCase("N")) {
				System.out.println("正在返回用户管理页面");
				return;
			}
			
			// 1.获取所有用户信息
			List<User> users = userMapper.selectAllUsers();
			
			// 2.展示所有用户信息
			for (User user : users) {
				System.out.println(user);
			}
			
			// 3.选择要删除的用户
			int userid = ScannerUtil.getValidIntegerInput("请输入要删除的用户id：", 1, users.size());
			User selectedUser = users.get(userid - 1);
			
			// 4.执行删除
			selectedUser.setUserState("注销");
			int cows = userMapper.updateUserState(selectedUser);
			if (cows != 0) {
				System.out.println("用户删除成功！");
				
				// 5.插入日志
				logService.insertLog(admin, "删除用户" + selectedUser.getUsername() + "成功");
			} else {
				System.out.println("用户删除失败！");
				// 5.插入日志
				logService.insertLog(admin, "删除用户" + selectedUser.getUsername() + "失败");
			}
		}
	}
	
	/**
	 * @MethodName: updateUser
	 * @Description: 修改用户
	 */
	public void updateUser(Admin admin) {
		System.out.println("========== 修改用户 ==========");
		while (true) {
			String pd = ScannerUtil.nextLine("是否继续进行修改操作（Y/N）：");
			if (pd.equalsIgnoreCase("N")) {
				System.out.println("正在返回用户管理页面");
				return;
			}
			
			// 1. 获取所有用户信息
			List<User> users = userMapper.selectAllUsers();
			// 2. 展示所有用户信息
			users.forEach(System.out::println);
			
			// 3. 选择要修改的用户
			int userId = ScannerUtil.getValidIntegerInput("请输入要修改的用户ID：");
			User userToUpdate = users.stream().filter(u -> u.getUserid() == userId).findFirst().orElse(null);
			
			if (userToUpdate == null) {
				System.out.println("未找到该用户，请重新输入！");
				continue;
			}
			
			// 4. 选择要修改的属性
			System.out.println("您将修改用户：" + userToUpdate.getUsername());
			System.out.println("请选择要修改的属性：");
			System.out.println("1. 密码");
			System.out.println("2. 余额");
			System.out.println("3. 积分");
			System.out.println("4. 手机号");
			System.out.println("5. 用户状态");
			System.out.println("6. 返回");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1-6】进行操作：", 1, 6);
			
			switch (choice) {
				case 1:
					String newPass = ScannerUtil.passwordCheck("请输入新密码：");
					userToUpdate.setUserpass(newPass);
					logService.insertLog(admin, "修改用户" + userToUpdate.getUsername() + "密码为" + userToUpdate.getUserpass());
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
			
			// 5. 调用服务层更新数据库
			userService.updateUserByAdmin(userToUpdate);
			System.out.println("用户" + userToUpdate.getUsername() + "信息修改成功！");
			return;
		}
	}
	
	/**
	 * @MethodName: queryUser
	 * @Description: 查询用户
	 */
	public void queryUser(Admin admin) {
		System.out.println("========== 查询用户 ==========");
		while (true) {
			String pd = ScannerUtil.nextLine("是否继续进行查询操作（Y/N）：");
			if (pd.equalsIgnoreCase("N")) {
				System.out.println("正在返回用户管理页面");
				return;
			}
			
			// 1. 获取所有用户信息
			List<User> users = userMapper.selectAllUsers();
			// 2. 展示所有用户信息
			System.out.println("所有用户信息如下：");
			users.forEach(System.out::println);
			System.out.println("查询完成！");
			logService.insertLog(admin, "查询所有用户信息");
		}
	}
	
	/**
	 * @MethodName: insertMovie
	 * @Description: 增加电影
	 */
	public void insertMovie(Admin admin) {
		System.out.println("========== 增加电影 ==========");
		cinemaService.addCinema(admin);
	}
	
	/**
	 * @MethodName: deleteMovie
	 * @Description: 删除电影
	 */
	public void deleteMovie(Admin admin) {
		System.out.println("========== 删除电影 ==========");
		cinemaService.deleteCinema(admin);
	}
	
	/**
	 * @MethodName: updateMovie
	 * @Description: 修改电影
	 */
	public void updateMovie(Admin admin) {
		System.out.println("========== 修改电影 ==========");
		cinemaService.updateCinema(admin);
	}
	
	/**
	 * @MethodName: queryMovie
	 * @Description: 查询电影
	 */
	public void queryMovie(Admin admin) {
		System.out.println("========== 查询电影 ==========");
		cinemaService.queryCinema(admin);
	}
	
	/**
	 * @MethodName: insertVip
	 * @Description: 增加VIP等级
	 */
	public void insertVip(Admin admin) {
		System.out.println("========== 增加VIP等级 ==========");
		vipService.addVip(admin);
	}
	
	/**
	 * @MethodName: deleteVip
	 * @Description: 删除VIP等级
	 */
	public void deleteVip(Admin admin) {
		System.out.println("========== 删除VIP等级 ==========");
		vipService.deleteVip(admin);
	}
	
	/**
	 * @MethodName: updateVip
	 * @Description: 修改VIP等级
	 */
	public void updateVip(Admin admin) {
		System.out.println("========== 修改VIP等级 ==========");
		vipService.updateVip(admin);
	}
	
	/**
	 * @MethodName: queryVip
	 * @Description: 查询VIP等级
	 */
	public void queryVip(Admin admin) {
		System.out.println("========== 查询VIP等级 ==========");
		vipService.queryVip(admin);
	}
	
	/**
	 * @MethodName: updateOrder
	 * @Description: 修改订单
	 */
	public void updateOrder(Admin admin) {
		System.out.println("========== 修改订单 ==========");
		orderService.updateOrder(admin);
	}
	
	/**
	 * @MethodName: deleteOrder
	 * @Description: 删除订单
	 */
	public void deleteOrder(Admin admin) {
		System.out.println("========== 删除订单 ==========");
		orderService.deleteOrder(admin);
	}
	
	/**
	 * @MethodName: queryOrder
	 * @Description: 查询订单
	 */
	public void queryOrder(Admin admin) {
		System.out.println("========== 查询订单 ==========");
		orderService.queryOrder(admin);
	}
}

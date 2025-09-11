package com.seiryo.ctrl;

import com.seiryo.pojo.Admin;
import com.seiryo.pojo.Cinema;
import com.seiryo.pojo.User;
import com.seiryo.service.CinemaService;
import com.seiryo.service.UserService;
import com.seiryo.util.CaptchaUtil;
import com.seiryo.util.ScannerUtil;
import com.seiryo.view.AdminView;
import com.seiryo.view.CinemaView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName UserCtrl
 * @Description 用户操作类
 * @dateTime 2025/9/9 19:44
 */
public class UserCtrl {
	private UserService userService;
	private CinemaService cinemaService;
	private AdminCtrl adminCtrl;
	private CinemaView cinemaView;
	private AdminView adminView;
	
	/**
	 * @param userService 用户服务
	 * @MethodName: setUserService
	 * @Description: 设置UserService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setCinemaService(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}
	
	/**
	 * @param adminView 管理员视图
	 * @MethodName: setAdminView
	 * @Description: 设置AdminView
	 */
	public void setAdminView(AdminView adminView) {
		this.adminView = adminView;
	}
	
	/**
	 * @param adminCtrl 管理员控制器
	 * @MethodName: setAdminCtrl
	 * @Description: 设置AdminCtrl
	 */
	public void setAdminCtrl(AdminCtrl adminCtrl) {
		this.adminCtrl = adminCtrl;
	}
	
	/**
	 * @param cinemaView 影院视图
	 * @MethodName: setCinemaView
	 * @Description: 设置CinemaView
	 */
	public void setCinemaView(CinemaView cinemaView) {
		this.cinemaView = cinemaView;
	}
	
	
	/**
	 * @param choice 用户输入值
	 * @MethodName: UserChoice
	 * @Description: 主界面用户操作方法
	 */
	public boolean userCtrl(int choice) {
		switch (choice) {
			case 1:// 用户登录
				User loginUser = userLogin();
				// 进入影城主界面
				if (loginUser != null) {
					cinemaView.cinemaView(loginUser);
				}
				ScannerUtil.pressAnyKeyToReturn();
				break;
			case 2:// 用户注册
				register();
				ScannerUtil.pressAnyKeyToReturn();
				break;
			case 3:// 预览
				preview();
				ScannerUtil.pressAnyKeyToReturn();
				break;
			case 4:// 管理员登录
				Admin loginAdmin = adminCtrl.adminLogin();
				// 进入管理员页面
				if (loginAdmin != null) {
					adminView.adminView(loginAdmin);
				}
				ScannerUtil.pressAnyKeyToReturn();
				break;
			case 5:// 退出系统
				System.out.println("欢迎下次光临neko影城");
				System.exit(0);
		}
		return true;
	}
	
	/**
	 * @return 返回登录用户
	 * @MethodName: login
	 * @Description: 用户登录方法
	 */
	private User userLogin() {
		int loginAttempts = 0;// 记录失败次数
		System.out.println("========== 登录界面 ==========");
		// 登录循环
		while (true) {
			// 1.获取用户输入的账号和密码
			String inputName = ScannerUtil.nextLine("请输入您的账号：");
			String inputPass = ScannerUtil.nextLine("请输入您的密码：");
			
			// 2.调用服务层方法进行登录逻辑验证,获取登录的用户
			User loginUser = userService.userLogin(inputName, inputPass);
			
			// 3.进行判断
			if (loginUser != null) {
				if (loginUser.getUserState().equals("正常")) {
					System.out.println(loginUser.getUsername() + "登录成功!");
					return loginUser;
				}
			}
			
			// 4.登录失败
			loginAttempts++;// 增加失败次数
			
			// 5.失败超过3次，进入验证环节
			if (loginAttempts >= 3) {
				System.out.println("您已连续失败3次，需要进行安全验证！");
				if (CaptchaUtil.checkCaptcha()) { // 调用验证码工具
					System.out.println("验证成功！您可以继续尝试登录。");
					loginAttempts = 0; // 验证码通过后，重置失败次数，给他新的机会！
				} else {
					System.out.println("验证码输入错误！已退出登录流程。");
					return null;
				}
			} else {
				System.out.println("您还有 " + (3 - loginAttempts) + " 次尝试机会。");
			}
			System.out.println("==========================");
		}
	}
	
	/**
	 * @MethodName: register
	 * @Description: 新用户注册
	 */
	private void register() {
		System.out.println("========== 注册界面 ==========");
		
		// 1.注册循环
		while (true) {
			// 2.获取用户输入的账号密码
			String inputName = ScannerUtil.nameCheck("请输入要注册的账号【输入back退出注册】：");
			if (inputName.equalsIgnoreCase("back")) {
				System.out.println("已退出账号注册!");
				return;
			}
			String inputPass = ScannerUtil.passwordCheck("请输入要注册的密码【输入back退出注册】：");
			if (inputPass.equalsIgnoreCase("back")) {
				System.out.println("已退出账号注册!");
				return;
			}
			
			// 3.调用服务层方法进行注册逻辑验证
			boolean registerSusses = userService.userRegister(inputName);
			
			// 4.判断注册是否成功
			if (registerSusses) {
				// 5.创建对象初始化值
				User newUser = new User();
				newUser.setUsername(inputName);
				newUser.setUserpass(inputPass);
				newUser.setUserMoney(BigDecimal.valueOf(0.00));
				newUser.setUserPoint(0L);
				newUser.setUserVip("无");
				newUser.setUserPhone(null);
				newUser.setUserState("正常");
				
				// 6.更新数据库
				userService.addUser(newUser);
				System.out.println("注册成功！");
				return;
			}
			
			System.out.println("注册失败！账号已存在，请重新输入账号");
			System.out.println("==========================");
		}
	}
	
	
	/**
	 * @param price 票价
	 * @param user  购票用户
	 * @return 购票成功返回true,失败返回false
	 * @MethodName: payMoney
	 * @Description: 用户支付方法
	 */
	public boolean payMoney(BigDecimal price, User user) {
		// 1.获取用户余额
		BigDecimal userMoney = user.getUserMoney();
		Long priceLong = price.longValue();
		
		// 2.支付判断
		// 余额不足
		if (userMoney.compareTo(price) >= 0) {
			// 余额充足，进行支付
			user.setUserMoney(userMoney.subtract(price));
			user.setUserPoint(user.getUserPoint() + priceLong);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @MethodName: preview
	 * @Description: 电影菜单预览
	 */
	public void preview() {
		System.out.println("========== 电影列表 ==========");
		
		// 2.打印标题行
		System.out.printf("%-10s \t %-18s \t %-19s \t %-16s \t %-10s\n", "电影编号", "电影名称", "上映时间", "电影价格", "观影时间");
		
		// 3.获取电影列表
		List<Cinema> cinemas = cinemaService.getAllCinemas();
		
		if (cinemas.isEmpty()) {
			System.out.println("现在影院没有电影");
			return;
		}
		
		// 4.打印电影列表
		for (Cinema cinema : cinemas) {
			System.out.println(cinema);
		}
	}
}

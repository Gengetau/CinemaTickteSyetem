package com.seiryo.ctrl;

import com.seiryo.pojo.Admin;
import com.seiryo.service.AdminService;
import com.seiryo.service.LogService;
import com.seiryo.util.CaptchaUtil;
import com.seiryo.util.ScannerUtil;
import com.seiryo.view.AdminView;
import com.seiryo.view.CinemaView;
import com.seiryo.view.InformationView;
import com.seiryo.view.OrderView;

/**
 * @author 11567
 * @version v1.0
 * @ClassName AdminCtrl
 * @Description 管理员操作类
 * @dateTime 2025/9/10 11:14
 */
public class AdminCtrl {
	private AdminService adminService;
	private LogService logService;
	private AdminView adminView;
	private CinemaView cinemaView;
	private OrderView orderView;
	private InformationView informationView;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public AdminView getAdminView() {
		return adminView;
	}
	
	public void setAdminView(AdminView adminView) {
		this.adminView = adminView;
	}
	
	public CinemaView getCinemaView() {
		return cinemaView;
	}
	
	public void setCinemaView(CinemaView cinemaView) {
		this.cinemaView = cinemaView;
	}
	
	public OrderView getOrderView() {
		return orderView;
	}
	
	public void setOrderView(OrderView orderView) {
		this.orderView = orderView;
	}
	
	public InformationView getInformationView() {
		return informationView;
	}
	
	public void setInformationView(InformationView informationView) {
		this.informationView = informationView;
	}
	
	/**
	 * @return 返回登录成功的管理员对象
	 * @MethodName: adminLogin
	 * @Description: 管理员登录方法
	 */
	public Admin adminLogin() {
		int loginAttempts = 0;// 记录失败次数
		System.out.println("========== 管理员登录界面 ==========");
		// 登录循环
		while (true) {
			// 1.获取用户输入的账号和密码
			String inputName = ScannerUtil.nextLine("请输入您的管理员账号：");
			String inputPass = ScannerUtil.nextLine("请输入您的管理员密码：");
			
			// 2.调用服务层方法进行登录逻辑验证,获取登录的管理员
			Admin loginAdmin = adminService.adminLogin(inputName, inputPass);
			
			// 3.进行判断
			if (loginAdmin != null) {
				System.out.println("管理员" + loginAdmin.getAdminName() + "登录成功!");
				return loginAdmin;
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
			System.out.println("==================================");
		}
	}
	
	/**
	 * @param option 执行菜单名
	 * @param admin  操作人
	 * @return 返回true或false
	 * @MethodName: adminCtrl
	 * @Description: 管理员主界面操作
	 */
	public boolean adminCtrl(String option, Admin admin) {
		switch (option) {
			case "管理用户":
				adminView.userManageView(admin);
				break;
			case "管理电影":
				adminView.movieManageView(admin);
				break;
			case "管理VIP":
				adminView.vipManageView(admin);
				break;
			case "管理订单":
				adminView.orderManageView(admin);
				break;
			case "操作日志":
				adminView.loginManageView(admin);
				break;
			case "退出系统":
				System.out.println("正在返回登录界面");
				return false;
		}
		return true;
	}
	
	/**
	 * @param choice 执行的操作
	 * @param admin  操作人
	 * @return 返回true或false
	 * @MethodName: userManageCtrl
	 * @Description: 用户管理界面操作
	 */
	public boolean userManageCtrl(int choice, Admin admin) {
		switch (choice) {
			case 1:// 增加用户
				adminService.insertUser(admin);
				break;
			case 2:// 删除用户
				adminService.deleteUser(admin);
				break;
			case 3:// 修改用户
				adminService.updateUser(admin);
				break;
			case 4:// 查询用户
				adminService.queryUser(admin);
				break;
			case 5:// 返回管理界面
				System.out.println("正在返回管理界面");
				return false;
		}
		return true;
	}
	
	/**
	 * @param choice 执行的操作
	 * @param admin  操作人
	 * @return 返回true或false
	 * @MethodName: movieManageCtrl
	 * @Description: 电影管理界面操作
	 */
	public boolean movieManageCtrl(int choice, Admin admin) {
		switch (choice) {
			case 1:// 增加电影
				adminService.insertMovie(admin);
				break;
			case 2:// 删除电影
				adminService.deleteMovie(admin);
				break;
			case 3:// 修改电影
				adminService.updateMovie(admin);
				break;
			case 4:// 查询电影
				adminService.queryMovie(admin);
				break;
			case 5:// 返回管理界面
				System.out.println("正在返回管理界面");
				return false;
		}
		return true;
	}
	
	/**
	 * @param choice 执行的操作
	 * @param admin  操作人
	 * @return 返回true或false
	 * @MethodName: vipManageCtrl
	 * @Description: VIP管理界面操作
	 */
	public boolean vipManageCtrl(int choice, Admin admin) {
		switch (choice) {
			case 1:// 增加VIP等级
				adminService.insertVip(admin);
				break;
			case 2:// 删除VIP等级
				adminService.deleteVip(admin);
				break;
			case 3:// 修改VIP等级
				adminService.updateVip(admin);
				break;
			case 4:// 查询VIP等级
				adminService.queryVip(admin);
				break;
			case 5:// 返回管理界面
				System.out.println("正在返回管理界面");
				return false;
		}
		return true;
	}
	
	/**
	 * @param choice 执行的操作
	 * @param admin  操作人
	 * @return 返回true或false
	 * @MethodName: orderManageCtrl
	 * @Description: 订单管理界面操作
	 */
	public boolean orderManageCtrl(int choice, Admin admin) {
		switch (choice) {
			case 1:// 查询订单
				adminService.queryOrder(admin);
				break;
			case 2:// 修改订单状态
				adminService.updateOrder(admin);
				break;
			case 3:// 删除订单
				adminService.deleteOrder(admin);
				break;
			case 4:// 返回管理界面
				System.out.println("正在返回管理界面");
				return false;
		}
		return true;
	}
	
	/**
	 * @param choice 管理员操作
	 * @param admin  操作人
	 * @return 返回true或false
	 * @MethodName: loginManageCtrl
	 * @Description: 日志页面操作
	 */
	public boolean logManageCtrl(int choice, Admin admin) {
		switch (choice) {
			case 1:
				logService.selectAllLog(admin);
				break;
			case 2:// 返回管理界面
				System.out.println("正在返回管理界面");
				return false;
		}
		return true;
	}
}

package com.seiryo.view;

import com.seiryo.ctrl.AdminCtrl;
import com.seiryo.pojo.Admin;
import com.seiryo.util.ScannerUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 11567
 * @version v1.0
 * @ClassName AdminView
 * @Description 管理员界面
 * @dateTime 2025/9/10 17:58
 */
public class AdminView {
	private AdminCtrl adminCtrl;
	
	public void setAdminCtrl(AdminCtrl adminCtrl) {
		this.adminCtrl = adminCtrl;
	}
	
	/**
	 * @MethodName: adminView
	 * @Description: 管理员视图层
	 */
	public void adminView(Admin admin) {
		boolean flag = true;
		while (flag) {
			System.out.println("========= 欢迎" + admin.getAdminName() + "管理员进入后台管理界面 =========");
			System.out.println("您当前的权限等级是: " + admin.getAdminPermission() + " 级");
			
			// 1.获取动态菜单
			List<String> availableOptions = getAvailableOptions(admin);
			
			// 2.打印动态菜单
			for (int i = 0; i < availableOptions.size(); i++) {
				System.out.println((i + 1) + ".  " + availableOptions.get(i));
			}
			
			// 3.获取输入值
			int choice = ScannerUtil.getValidIntegerInput("请输入你要操作的菜单序号：", 1, availableOptions.size());
			
			// 4.获取序号对应的菜单文字
			String option = availableOptions.get(choice - 1);
			
			// 5.进入管理员页面操作层
			flag = adminCtrl.adminCtrl(option, admin);
		}
	}
	
	/**
	 * @param admin 当前管理员
	 * @return 返回动态菜单
	 * @MethodName: getAvailableOptions
	 * @Description: 根据权限获取动态菜单
	 */
	private List<String> getAvailableOptions(Admin admin) {
		Map<String, Integer> allOptions = new LinkedHashMap<>();
		allOptions.put("管理用户", 1);
		allOptions.put("管理电影", 2);
		allOptions.put("管理VIP", 3);
		allOptions.put("管理订单", 4);
		allOptions.put("操作日志", 4); // 操作日志只能让最高级管理员查看
		allOptions.put("退出系统", 1); // 退出系统是所有管理员都应该有的权限
		
		// 2. 创建一个动态的列表，用来存放当前管理员能看到的菜单
		List<String> availableOptions = new ArrayList<>();
		
		// 3.判断权限，添加菜单
		for (Map.Entry<String, Integer> entry : allOptions.entrySet()) {
			if (admin.getAdminPermission() >= entry.getValue()) {
				availableOptions.add(entry.getKey());
			}
		}
		return availableOptions;
	}
	
	/**
	 * @MethodName: userManageView
	 * @Description: 用户管理页面
	 */
	public void userManageView(Admin admin) {
		boolean flag = true;
		while (flag) {
			System.out.println("========== 用户管理界面 ==========");
			System.out.println("1.	增加用户");
			System.out.println("2.	删除用户");
			System.out.println("3.	修改用户");
			System.out.println("4.	查询用户");
			System.out.println("5.	返回管理页面");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3/4/5】进行操作:", 1, 5);
			
			// 进入用户管理操作层
			flag = adminCtrl.userManageCtrl(choice, admin);
		}
	}
	
	/**
	 * @MethodName: movieManageView
	 * @Description: 电影管理页面
	 */
	public void movieManageView(Admin admin) {
		boolean flag = true;
		while (flag) {
			System.out.println("========== 电影管理界面 ==========");
			System.out.println("1.	增加电影");
			System.out.println("2.	删除电影");
			System.out.println("3.	修改电影");
			System.out.println("4.	查询电影");
			System.out.println("5.	返回管理页面");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3/4/5】进行操作:", 1, 5);
			
			// 进入电影管理操作层
			flag = adminCtrl.movieManageCtrl(choice, admin);
		}
	}
	
	/**
	 * @MethodName: vipManageView
	 * @Description: VIP管理页面
	 */
	public void vipManageView(Admin admin) {
		boolean flag = true;
		while (flag) {
			System.out.println("========== VIP管理界面 ==========");
			System.out.println("1.	增加VIP等级");
			System.out.println("2.	删除VIP等级");
			System.out.println("3.	修改VIP等级");
			System.out.println("4.	查询VIP等级");
			System.out.println("5.	返回管理页面");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3/4/5】进行操作:", 1, 5);
			
			// 进入VIP管理操作层
			flag = adminCtrl.vipManageCtrl(choice, admin);
		}
	}
	
	/**
	 * @MethodName: orderManageView
	 * @Description: 订单管理页面
	 */
	public void orderManageView(Admin admin) {
		boolean flag = true;
		while (flag) {
			System.out.println("========== 订单管理界面 ==========");
			System.out.println("1.	查询订单");
			System.out.println("2.	修改订单状态");
			System.out.println("3.	删除订单");
			System.out.println("4.	返回管理页面");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3/4】进行操作:", 1, 4);
			
			// 进入订单管理操作层
			flag = adminCtrl.orderManageCtrl(choice, admin);
		}
	}
	
	/**
	 * @MethodName: loginManageView
	 * @Description: 日志操作界面
	 */
	public void loginManageView(Admin admin) {
		boolean flag = true;
		while (flag) {
			System.out.println("========== 操作日志界面 ==========");
			System.out.println("1.	查看日志");
			System.out.println("2.	返回管理页面");
			
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2】进行操作:", 1, 2);
			
			// 进入日志操作层
			flag = adminCtrl.logManageCtrl(choice, admin);
		}
	}
}

package com.seiryo.ctrl;

import com.seiryo.pojo.User;
import com.seiryo.service.UserService;
import com.seiryo.util.ScannerUtil;

import java.math.BigDecimal;

/**
 * @author 11567
 * @version v1.0
 * @ClassName InformationCtrl
 * @Description 信息操作类
 * @dateTime 2025/9/10 17:04
 */
public class InformationCtrl {
	// 服务对象
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * @param choice 用户操作
	 * @MethodName: myInformationCtrl
	 * @Description:
	 */
	public boolean myInformationCtrl(int choice, User user) {
		switch (choice) {
			case 1:// 修改密码
				updatePassword(user);
				break;
			case 2:// 充值余额
				chargeMoney(user);
				break;
			case 3:// 修改手机号
				updateUserPhone(user);
			case 4:// 退出
				System.out.println("正在返回主界面");
				return false;
		}
		return true;
	}
	
	/**
	 * @param user 当前用户
	 * @MethodName: updatePassword
	 * @Description: 修改密码
	 */
	private void updatePassword(User user) {
		System.out.println("========== 修改密码 ===========");
		while (true) {
			System.out.println("修改密码需要验证手机号后四位！");
			// 1.验证用户手机号是否为空
			if (user.getUserPhone().isEmpty()) {
				System.out.println("手机号为空，请先添加手机号！");
				return;
			}
			
			// 1.获取用户输入的4位验证字符串
			String phoneCode = ScannerUtil.nextLine("请输入您的手机号后四位：");
			
			// 2.进行验证
			if (user.getUserPhone().substring(8).equals(phoneCode)) {
				// 3.获取新的密码
				String newPass = ScannerUtil.passwordCheck("验证成功！请输入新的密码【输入back退出】：");
				
				if (newPass.equalsIgnoreCase("back")) {
					System.out.println("已退出密码修改!");
					return;
				}
				
				// 4.更改密码
				user.setUserpass(newPass);
				
				// 5.更新数据库
				userService.updateUserPass(user);
				return;
			} else {
				System.out.println("验证失败，请重新输入！");
			}
		}
	}
	
	/**
	 * @param user 当前用户
	 * @MethodName: chargeMoney
	 * @Description: 充值余额
	 */
	private void chargeMoney(User user) {
		System.out.println("========== 余额充值 ===========");
		// 1.充值
		BigDecimal money = ScannerUtil.moneyCheck("请输入你要充值的金额【输入back退出】：");
		
		// 2.判断
		if (money == null) {
			return;
		}
		
		// 3.更新余额
		user.setUserMoney(money);
		
		// 4.更新数据库
		userService.updateUserMoney(user);
	}
	
	/**
	 * @param user 当前用户
	 * @MethodName: updateUserPhone
	 * @Description: 修改手机号
	 */
	private void updateUserPhone(User user) {
		System.out.println("========== 修改手机号 ===========");
		while (true) {
			System.out.println("修改手机号需要验证密码！");
			
			// 1.获取用户输入的密码
			String passCheck = ScannerUtil.nextLine("请输入您的密码：");
			
			// 2.进行验证
			if (user.getUserpass().equals(passCheck)) {
				// 3.获取新的手机号
				String newPhone = ScannerUtil.phoneCheck("验证成功！请输入新的手机号：");
				
				// 4.更改手机号
				user.setUserPhone(newPhone);
				
				// 5.更新数据库
				userService.updateUserPhone(user);
				return;
			} else {
				System.out.println("验证失败，请重新输入！");
			}
		}
	}
}

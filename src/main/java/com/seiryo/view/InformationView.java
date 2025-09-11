package com.seiryo.view;

import com.seiryo.ctrl.InformationCtrl;
import com.seiryo.pojo.User;
import com.seiryo.util.ScannerUtil;

/**
 * @author 11567
 * @version v1.0
 * @ClassName InformationView
 * @Description 信息界面
 * @dateTime 2025/9/10 16:52
 */
public class InformationView {
	private InformationCtrl informationCtrl;
	
	public void setInformationCtrl(InformationCtrl informationCtrl) {
		this.informationCtrl = informationCtrl;
	}
	
	/**
	 * @MethodName: myInformationView
	 * @Description: 我的信息界面
	 */
	public void myInformationView(User user) {
		boolean flag = true;
		while (flag) {
			System.out.println("========== 欢迎" + user.getUsername() + "进入信息界面 ==========");
			
			// 1.输出信息
			System.out.println("我的账号：" + "\t" + user.getUsername());
			System.out.println("会员等级：" + "\t" + user.getUsername());
			System.out.println("我的余额：" + "\t" + user.getUsername());
			System.out.println("我的积分：" + "\t" + user.getUsername());
			// 手机号隐藏后四位
			if (user.getUserPhone().isEmpty()) {
				System.out.println("我的手机号：" + "\t" + "null");
			} else {
				System.out.println("我的手机号：" + "\t" + user.getUsername().substring(0, 7) + "****");
			}
			System.out.println("\n=========== 信息操作 ==========");
			System.out.println("1.	修改密码：");
			System.out.println("2.	充值余额：");
			System.out.println("3.	修改手机号：");
			System.out.println("4.	退出：");
			
			// 2.获取用户输入值
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3/4】进行操作", 1, 4);
			
			// 3.进入信息操作层
			flag = informationCtrl.myInformationCtrl(choice, user);
		}
	}
}

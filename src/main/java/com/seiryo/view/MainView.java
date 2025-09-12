package com.seiryo.view;

import com.seiryo.ctrl.UserCtrl;
import com.seiryo.util.ScannerUtil;

/**
 * @author 11567
 * @version v1.0
 * @ClassName MainView
 * @Description 主视图层
 * @dateTime 2025/9/9 19:26
 */
public class MainView {
	private UserCtrl userCtrl;
	
	/**
	 * @param userCtrl 用户控制器
	 * @MethodName: setUserCtrl
	 * @Description: 设置UserCtrl
	 */
	public void setUserCtrl(UserCtrl userCtrl) {
		this.userCtrl = userCtrl;
	}
	
	/**
	 * @MethodName: mainView
	 * @Description: 主界面视图层
	 */
	public void mainView() {
		boolean flag = true;
		while (flag) {
			System.out.println("========== 欢迎来到neko影城 ==========");
			System.out.println("1.	登录");
			System.out.println("2. 	注册");
			System.out.println("3. 	预览");
			System.out.println("4. 	管理员登录");
			System.out.println("5. 	退出");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3/4/5】进行操作：", 1, 5);
			// 调用用户操作方法，传入用户输入值
			flag = userCtrl.userCtrl(choice);
		}
	}
}

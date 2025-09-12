package com.seiryo.util;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author 11567
 * @version v1.0
 * @ClassName ScannerUtil
 * @Description 安全输入工具
 * @dateTime 2025/9/9 19:27
 */
public class ScannerUtil {
	private static final Scanner SC = new Scanner(System.in);
	
	/**
	 * @param prompt 提示用户输入数据的字符串
	 * @return 返回用户输入的字符串
	 * @MethodName: nextLine
	 * @Description: 安全输入字符串
	 */
	public static String nextLine(String prompt) {
		System.out.print(prompt);
		return SC.nextLine();
	}
	
	
	/**
	 * @MethodName: pressAnyKeyToReturn
	 * @Description: 专属的“按任意键返回”方法
	 */
	public static void pressAnyKeyToReturn() {
		System.out.println("\n(按任意键以继续...)");
		SC.nextLine();
	}
	
	/**
	 * @param prompt 提示语句
	 * @return 返回正确的手机号
	 * @MethodName: phoneCheck
	 * @Description: 手机号安全输入工具
	 */
	public static String phoneCheck(String prompt) {
		while (true) {
			String phone = nextLine(prompt);
			if (phone.matches("[0-9]{11}")) {
				return phone;
			}
			System.out.println("输入错误，手机号应为11位纯数字组成，请重新输入！");
		}
	}
	
	/**
	 * @param prompt 提示用户输入的信息
	 * @return 编号返回Long类型的值,名称返回String类型的值
	 * @MethodName: getCinemaIdOrName
	 * @Description: 识别用户输入的是编号还是名称并返回相应的值
	 */
	public static Object getCinemaIdOrName(String prompt) {
		String input = nextLine(prompt);
		
		try {
			// 如果成功，就返回Long类型的编号
			return Long.parseLong(input);
		} catch (NumberFormatException e) {
			// 异常，返回字符串
			return input;
		}
	}
	
	/**
	 * @param prompt 提示用户输入账号的字符串
	 * @return 返回用户输入的账号
	 * @MethodName: nameCheck
	 * @Description: 账号输入验证工具
	 */
	public static String nameCheck(String prompt) {
		// 正则表达式
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$";
		while (true) {
			String inputName = nextLine(prompt);
			if (inputName.equalsIgnoreCase("back")) {
				return inputName;
			}
			if (inputName.matches(regex)) {
				return inputName;
			}
			System.out.println("账号格式错误，请重新输入！");
			System.out.println("账号必须以数字和英文结合，必须包含大写字母和特殊符号，长度在8位以上！");
		}
	}
	
	/**
	 * @param prompt 提示用户输入密码的字符串
	 * @return 返回用户输入的密码
	 * @MethodName: passwordCheck
	 * @Description: 密码输入验证工具
	 */
	public static String passwordCheck(String prompt) {
		String regex = "^[0-9]{6}$";
		while (true) {
			String inputName = nextLine(prompt);
			if (inputName.equalsIgnoreCase("back")) {
				return inputName;
			}
			if (inputName.matches(regex)) {
				return inputName;
			}
			System.out.println("密码格式错误，请重新输入！");
			System.out.println("密码必须是由6位数字组成！");
		}
	}
	
	/**
	 * @param prompt 提示语句
	 * @return 返回BigDecimal类型的数据
	 * @MethodName: moneyCheck
	 * @Description: 充值金额输入工具
	 */
	public static BigDecimal moneyCheck(String prompt) {
		// 输入循环
		while (true) {
			String input = nextLine(prompt);
			
			if (input.equalsIgnoreCase("back")) {
				System.out.println("已退出充值!");
				return null;
			}
			
			try {
				// 1. 尝试转换，如果输入的不是数字，这里就会抛出异常，然后被catch抓住
				BigDecimal money = new BigDecimal(input);
				
				// 2. 转换成功后，我们再用compareTo检查它是不是大于0
				// money.compareTo(BigDecimal.ZERO) > 0 就表示 money > 0
				// 如果金额大于0，那它就是一个合格的充值金额！我们把它返回出去，循环结束！
				// 如果金额小于等于0，就提醒用户
				if (money.compareTo(BigDecimal.ZERO) > 0) {
					return money;
				} else {
					System.out.println("充值金额必须大于0哦，请重新输入！");
				}
			} catch (NumberFormatException e) {
				// 如果用户输入了像"abc"这样的文字，就会跳到这里
				System.out.println("输入无效，请输入一个正确的数字金额（比如 100 或 50.5），请重新输入！");
			}
		}
	}
	
	/**
	 * @param prompt 提示用户输入数据的字符串
	 * @return 返回用户输入的整数
	 * @MethodName: getValidIntegerInput
	 * @Description: 安全输入整数方法
	 */
	public static int getValidIntegerInput(String prompt) {
		System.out.print(prompt);
		while (true) {
			String input = SC.nextLine();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("输入无效，请输入一个整数！请重新输入：");
			}
		}
	}
	
	/**
	 * @param prompt 提示用户输入数据的字符串
	 * @param min    输入范围的最小值
	 * @param max    输入范围的最大值
	 * @return 返回用户输入的合法整数
	 * @MethodName: getValidIntegerInput
	 * @Description: 整数方法改进版，用于让用户输入一个范围内的整数
	 */
	public static int getValidIntegerInput(String prompt, int min, int max) {
		while (true) {
			int number = getValidIntegerInput(prompt);
			if (number >= min && number <= max) {
				return number;
			} else {
				System.out.print("选择的数字不在范围内！请输入 " + min + " 到 " + max + " 之间的数字：");
			}
		}
	}
}

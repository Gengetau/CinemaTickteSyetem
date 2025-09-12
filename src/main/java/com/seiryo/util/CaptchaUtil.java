package com.seiryo.util;

import java.util.Random;

/**
 * 验证码工具
 */
public class CaptchaUtil {
	/**
	 * @param length 验证码长度
	 * @return 返回一串验证码
	 * @MethodName: generateCode
	 * @Description: 验证码生成工具
	 */
	public static String generateCode(int length) {
		// 1. 准备字符串，里面装满了所有可能出现的字符
		String characterPool = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		// 2. 准备一个“字符数组”，用来存放我们抓出来的字符
		StringBuilder code = new StringBuilder();
		
		// 3. 拿出我们的“随机抓取机”
		Random random = new Random();
		
		// 4. 根据想要的长度，循环抓取
		for (int i = 0; i < length; i++) {
			// a. 在“字符数组”的长度范围内，生成一个随机的索引（位置）
			int randomIndex = random.nextInt(characterPool.length());
			
			// b. 根据这个随机位置，从“字符数组”里抓出一个字符
			char randomChar = characterPool.charAt(randomIndex);
			
			// c. 把抓出来的这个“字符”链接
			code.append(randomChar);
		}
		
		// 5. 返回串好的、完整的验证码
		return code.toString();
	}
	
	/**
	 * @return 成功返回true,失败返回false
	 * @MethodName: checkCaptcha
	 * @Description: 验证码验证工具
	 */
	public static boolean checkCaptcha() {
		// 生成8位数验证码
		String captcha = generateCode(8);
		System.out.println(captcha);// 展示验证码
		// 提示用户输入
		String code = ScannerUtil.nextLine("请输入上面验证码（不区分大小写）:");
		return code.equalsIgnoreCase(captcha);
	}
}

package com.seiryo.view;

import com.seiryo.ctrl.CinemaCtrl;
import com.seiryo.pojo.Cinema;
import com.seiryo.pojo.User;
import com.seiryo.service.CinemaService;
import com.seiryo.util.BGMPlayer;
import com.seiryo.util.ScannerUtil;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName CinemaView
 * @Description 影城主页面视图层
 * @dateTime 2025/9/9 21:48
 */
public class CinemaView {
	private CinemaCtrl cinemaCtrl;
	private CinemaService cinemaService;
	
	/**
	 * @param cinemaService 电影服务层
	 * @MethodName: setCinemaService
	 * @Description: 设置CinemaService
	 */
	public void setCinemaService(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}
	
	/**
	 * @param cinemaCtrl 电影控制器
	 * @MethodName: setCinemaCtrl
	 * @Description: 设置CinemaCtrl
	 */
	public void setCinemaCtrl(CinemaCtrl cinemaCtrl) {
		this.cinemaCtrl = cinemaCtrl;
	}
	
	/**
	 * @param user 登录的用户
	 * @MethodName: cinemaView
	 * @Description: 影城主页面视图
	 */
	public void cinemaView(User user) {
		// 播放背景音乐
		BGMPlayer.playBGM("/JJ.wav");
		boolean flag = true;
		while (flag) {
			System.out.println("========== 欢迎进入neko影城 ==========");
			System.out.println("广播：同时购买两张票以上有95折优惠哦！");
			System.out.println("1.	电影列表");
			System.out.println("2. 	我的订单");
			System.out.println("3. 	我的信息");
			System.out.println("4. 	退出电影城");
			int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3/4】进行操作：", 1, 4);
			// 进入影城操作层
			flag = cinemaCtrl.cinemaCtrl(user, choice);
		}
		// 停止音乐播放
		BGMPlayer.stopBGM();
	}
	
	/**
	 * @param user 登录用户
	 * @MethodName: cinemaTicketView
	 * @Description: 电影购票界面
	 */
	public void cinemaTicketView(User user) {
		boolean flag = true;// 设置循环变量
		// 1.购票循环
		while (flag) {
			System.out.println("========== 电影列表 ==========");
			
			// 2.打印标题行
			System.out.printf("%-10s \t %-18s \t %-19s \t %-16s \t %-10s\n", "电影编号", "电影名称", "上映时间", "电影价格", "观影时间");
			
			// 3.获取电影列表
			List<Cinema> cinemas = cinemaService.getAllCinemas();
			
			// 4.打印电影列表
			for (Cinema cinema : cinemas) {
				System.out.println(cinema);
			}
			
			// 5.提示用户进入选择购票
			Object object = ScannerUtil.getCinemaIdOrName("请输入您需要选购的电影编号或电影名称（输入NO退出）：");
			
			// 6.进入购票操作层
			flag = cinemaCtrl.buyTicketCtrl(object, user);
		}
	}
}

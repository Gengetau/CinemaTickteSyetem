package com.seiryo.ctrl;

import com.seiryo.pojo.Cinema;
import com.seiryo.pojo.Order;
import com.seiryo.pojo.OrderInfo;
import com.seiryo.pojo.User;
import com.seiryo.service.CinemaService;
import com.seiryo.service.OrderInfoService;
import com.seiryo.service.OrderService;
import com.seiryo.service.UserService;
import com.seiryo.util.ScannerUtil;
import com.seiryo.view.CinemaView;
import com.seiryo.view.InformationView;
import com.seiryo.view.OrderView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName CinemaCtrl
 * @Description 影城操作类
 * @dateTime 2025/9/9 22:07
 */
public class CinemaCtrl {
	private CinemaView cinemaView;
	private OrderView orderView;
	private InformationView informationView;
	private CinemaService cinemaService;
	private UserCtrl userCtrl;
	private UserService userService;
	private OrderService orderService;
	private OrderInfoService orderInfoService;
	
	public void setCinemaView(CinemaView cinemaView) {
		this.cinemaView = cinemaView;
	}
	
	public void setOrderView(OrderView orderView) {
		this.orderView = orderView;
	}
	
	public void setInformationView(InformationView informationView) {
		this.informationView = informationView;
	}
	
	public void setCinemaService(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}
	
	public void setUserCtrl(UserCtrl userCtrl) {
		this.userCtrl = userCtrl;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	
	/**
	 * @param user   登录用户
	 * @param choice 用户操作
	 * @return 操作结束返回false,操作继续返回true
	 * @MethodName: cinemaCtrl
	 * @Description: 影城操作方法
	 */
	public boolean cinemaCtrl(User user, int choice) {
		switch (choice) {
			case 1:// 电影列表
				cinemaView.cinemaTicketView(user);
				ScannerUtil.pressAnyKeyToReturn();
				break;
			case 2:// 我的订单
				orderView.myOrderView(user);
				ScannerUtil.pressAnyKeyToReturn();
				break;
			case 3:// 我的信息
				informationView.myInformationView(user);
				ScannerUtil.pressAnyKeyToReturn();
				break;
			case 4:// 返回登录页面
				System.out.println("已退出登录，欢迎下次光临！");
				return false;
		}
		return true;
	}
	
	/**
	 * @param object 用户输入值
	 * @return 操作结束返回false,操作继续返回true
	 * @MethodName: buyTicketCtrl
	 * @Description: 购票操作
	 */
	public boolean buyTicketCtrl(Object object, User user) {
		// 1.判断用户输入值并转换
		if (object instanceof Long) {
			// 1.1用户输入电影编号
			Long cinemaId = (Long) object;
			
			// 2.获取电影
			Cinema selectedCinema = cinemaService.getCinemaById(cinemaId);
			
			if (selectedCinema != null) {
				// 3.打印电影信息
				System.out.println(selectedCinema);
				System.out.println("您选择的《" + selectedCinema.getCinemaName() + "》，观影时间为" + selectedCinema.getMovieTime());
				
				// 4.选座和付款
				selectSeatsAndConfirm(selectedCinema, user);
			} else {
				System.out.println("你搜索的电影编号不存在，请重试!");
				return true;
			}
			
		} else if (object instanceof String) {
			// 1.2用户输入电影名称
			String cinemaName = (String) object;
			if (cinemaName.equalsIgnoreCase("no")) {
				System.out.println("购票取消，返回主页面");
				return false;
			}
			
			// 2.获取电影
			List<Cinema> selectedCinemas = cinemaService.getCinemasByName(cinemaName);
			
			if (selectedCinemas.isEmpty()) {
				System.out.println("您搜索的电影名称不存在，请重试！");
				return true;
			}
			
			// 3.判断获取到的列表有多少对象
			if (selectedCinemas.size() > 1) {
				// 4.打印列表
				for (Cinema selectedCinema : selectedCinemas) {
					System.out.println(selectedCinema);
				}
				
				// 5.选择电影编号
				int choice = ScannerUtil.getValidIntegerInput("请输入选择的电影编号:", 1, selectedCinemas.size());
				Long cinemaId = (long) choice - 1;
				
				// 6.获取电影
				Cinema selectedCinema = cinemaService.getCinemaById(cinemaId);
				
				// 7.打印电影信息
				System.out.println(selectedCinema);
				System.out.println("您选择的《" + selectedCinema.getCinemaName() + "》，观影时间为" + selectedCinema.getMovieTime());
				
				// 8.选座和付款
				selectSeatsAndConfirm(selectedCinema, user);
				
			} else {
				// 4.打印电影信息
				Cinema selectedCinema = selectedCinemas.get(0);
				System.out.println(selectedCinema);
				System.out.println("您选择的《" + selectedCinema.getCinemaName() + "》，观影时间为" + selectedCinema.getMovieTime());
				
				// 5.选座和付款
				selectSeatsAndConfirm(selectedCinema, user);
			}
		}
		return true;
	}
	
	/**
	 * @param selectedCinema 选择的电影
	 * @MethodName: selectSeatsAndConfirm
	 * @Description: 选座与付款流程
	 */
	private void selectSeatsAndConfirm(Cinema selectedCinema, User user) {
		// 1.获取已售座位信息,创建已选座位列表
		List<String> soldSeats = cinemaService.getSoldSeat(selectedCinema);
		List<String> selectedSeats = new ArrayList<>();
		
		// 2.购票循环
		while (true) {
			// 3.打印座位表
			System.out.println("======= " + selectedCinema.getCinemaName() + "的座位表如下（√表示可购买；×表示已售出） =======");
			for (int cow = 1; cow <= 4; cow++) {
				for (int rol = 1; rol <= 5; rol++) {
					String seatID = (cow + "" + rol);
					boolean isSold = soldSeats.contains(seatID);
					System.out.print(seatID + (isSold ? "×" : "√") + "\t");
				}
				System.out.println();
			}
			
			// 4.选择座位
			System.out.println("请选择座位 (可一次输入多个，用逗号或空格隔开，例如: 41,42)");
			String selectedSeat = ScannerUtil.nextLine("请选择座位号 (直接回车确认选择, 输入back返回): ");
			
			// 5.判断输入
			if (selectedSeat.equalsIgnoreCase("back")) {
				System.out.println("已退出购票");
				return;
			}
			
			// 6.预选座位
			if (!selectedSeat.isEmpty()) {
				String[] seatIds = selectedSeat.split("[,， ]+");
				boolean pd = true;
				for (int i = 1; i < seatIds.length; i++) {
					if (seatIds[i - 1].equals(seatIds[i])) {
						pd = false;
						break;
					}
				}
				
				if (!pd) {
					System.out.println("不能选择相同的座位!");
					continue;
				}
				
				for (String seatId : seatIds) {
					if (!soldSeats.contains(seatId)) {
						selectedSeats.add(seatId);
						System.out.println("已为您预选座位：" + seatId);
					} else {
						System.out.println("座位 " + seatId + " 不可选（可能不存在或已被预定）");
					}
				}
			} else {
				System.out.println("您还没有选择任何座位哦喵~");
				continue;
			}
			
			// 7.确认环节
			// 7.1获取购买数量
			int count = selectedSeats.size();// int类型数据转化为BigDecimal类型
			// 7.2调用折扣方法获得最终价格
			System.out.println("您的会员等级是:" + user.getUserVip());
			BigDecimal price = cinemaService.getCinemaPrice(user, count, selectedCinema.getCinemaPrice());
			System.out.println("\n您已选择 " + selectedSeats.size() + " 个座位：" + String.join(", ", selectedSeats) + "总共：" + price + "￥");
			String confirm = ScannerUtil.nextLine("确认并支付？ (Y/N): ");
			
			// 8，付款
			if ("y".equalsIgnoreCase(confirm)) {
				// 8.1调用方法进行付款
				boolean paySusses = userCtrl.payMoney(price, user);
				if (paySusses) {
					System.out.println("支付成功，剩余余额：" + user.getUserMoney() + "￥");
					// 8.2更新数据库的用户余额和积分
					userService.updateUserMoneyAndPoints(user);
					// 8.3更新用户会员等级
					userService.updateUserVipName(user);
				} else {
					System.out.println("支付失败，余额不足，请充值！");
					return;// 结束本次选座
				}
			} else {
				System.out.println("支付取消，正在返回");
				return; // 结束本次选座
			}
			
			// 9.数据库:订单表添加新订单
			Order newOrder = new Order();
			newOrder.setCinema(selectedCinema);
			newOrder.setUser(user);
			newOrder.setTotalPrice(price);
			orderService.addNewOrder(newOrder);
			
			// 10.获得新订单id
			Long newOrderId = orderService.selectNewOrderId(newOrder);
			
			// 11.数据库：订单详情表添加新订单
			Date today = new Date();
			for (String seatId : selectedSeats) {
				OrderInfo newOrderInfo = new OrderInfo();
				newOrderInfo.setOrderId(newOrderId);
				newOrderInfo.setPosition(seatId);
				newOrderInfo.setOrderState("未观看");
				newOrderInfo.setOrderDate(today);
				orderInfoService.insertNewOrder(newOrderInfo);
			}
			return;// 结束选座
		}
	}
}
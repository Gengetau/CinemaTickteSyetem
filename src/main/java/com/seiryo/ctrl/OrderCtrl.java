package com.seiryo.ctrl;

import com.seiryo.pojo.Cinema;
import com.seiryo.pojo.Order;
import com.seiryo.pojo.OrderInfo;
import com.seiryo.pojo.User;
import com.seiryo.service.OrderService;
import com.seiryo.service.UserService;
import com.seiryo.util.ScannerUtil;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderCtrl
 * @Description 订单操作类
 * @dateTime 2025/9/10 15:49
 */
public class OrderCtrl {
	private OrderService orderService;
	private UserService userService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * @param choice 用户进行的操作
	 * @param orders 订单列表
	 * @return 返回true或者false
	 * @MethodName: orderCtrl
	 * @Description: 订单界面操作方法
	 */
	public boolean orderCtrl(int choice, List<Order> orders) {
		switch (choice) {
			case 1:// 查看订单
				checkOrder(orders);
				break;
			case 2:// 取消订单
				cancelOrder(orders);
				break;
			case 3:// 返回主界面
				System.out.println("正在返回主界面");
				return false;
		}
		return true;
	}
	
	/**
	 * @param orders 订单列表
	 * @MethodName: checkOrder
	 * @Description: 查看订单详情
	 */
	private void checkOrder(List<Order> orders) {
		// 1.获取用户选择
		int choice = ScannerUtil.getValidIntegerInput("请选择要查看的订单：", 1, orders.size());
		
		// 2.获得订单详情列表
		List<OrderInfo> orderInfos = orders.get(choice - 1).getOrderInfos();
		
		// 3,展示订单详情
		System.out.printf("%-5s %-5s %-20s %-25s\n", "订单详情编号", "下单时间", "座位", "订单状态");
		for (int i = 0; i < orderInfos.size(); i++) {
			OrderInfo orderInfo = orderInfos.get(i);
			System.out.printf("%-5s %-10s", (i + 1), orderInfo);
		}
	}
	
	private void cancelOrder(List<Order> orders) {
		// 1.获取用户选择
		int input = ScannerUtil.getValidIntegerInput("请选择要查看的订单：", 1, orders.size());
		
		// 2.获取用户和电影对象
		User user = orders.get(input - 1).getUser();
		Cinema cinema = orders.get(input - 1).getCinema();
		
		// 3.获得订单详情列表
		List<OrderInfo> orderInfos = orders.get(input - 1).getOrderInfos();
		
		// 4,展示订单详情
		System.out.printf("%-5s %-5s %-20s %-25s\n", "订单详情编号", "下单时间", "座位", "订单状态");
		for (int i = 0; i < orderInfos.size(); i++) {
			OrderInfo orderInfo = orderInfos.get(i);
			System.out.printf("%-5s %-10s", (i + 1), orderInfo);
		}
		
		// 5.选择要取消的订单详情
		int choice = ScannerUtil.getValidIntegerInput("请选择要取消的订单：", 1, orderInfos.size());
		
		// 6.获取选择的订单详情对象
		OrderInfo selectedOI = orderInfos.get(choice - 1);
		
		// 7.判断是否观看
		if (selectedOI.getOrderState().equals("已观看")) {
			System.out.println("该订单已观看，不可取消！");
			return;
		}
		
		// 8.确认是否取消
		String confirm = ScannerUtil.nextLine("确认并取消？ (Y/N): ");
		
		// 9.判断
		if ("y".equalsIgnoreCase(confirm)) {
			// 10.改变订单状态
			selectedOI.setOrderState("订单已取消");
			
			// 11.更新数据库:改变订单状态
			orderService.deleteOrderInfo(selectedOI);
			
			// 12.更新余额和积分
			user.setUserMoney(user.getUserMoney().add(cinema.getCinemaPrice()));
			user.setUserPoint(user.getUserPoint() - cinema.getCinemaPrice().longValue());
			
			// 13.更新数据库
			userService.updateUserMoneyAndPoints(user);
			
			// 14.更新用户会员等级
			userService.updateUserVipName(user);
		} else {
			System.out.println("正在返回订单界面");
		}
	}
}

package com.seiryo.view;

import com.seiryo.ctrl.OrderCtrl;
import com.seiryo.pojo.Order;
import com.seiryo.pojo.User;
import com.seiryo.service.OrderService;
import com.seiryo.util.ScannerUtil;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderView
 * @Description 订单视图层
 * @dateTime 2025/9/10 15:17
 */
public class OrderView {
	private OrderService orderService;
	private OrderCtrl orderCtrl;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * @param orderCtrl 订单控制器
	 * @MethodName: setOrderCtrl
	 * @Description: 设置OrderCtrl
	 */
	public void setOrderCtrl(OrderCtrl orderCtrl) {
		this.orderCtrl = orderCtrl;
	}
	
	/**
	 * @param user 登录的用户
	 * @MethodName: myOrderView
	 * @Description: 我的订单界面
	 */
	public void myOrderView(User user) {
		boolean flag = true;
		while (flag) {
			System.out.println("========== 我的订单 ==========");
			System.out.println("========== " + user.getUsername() + "的订单如下 ===========");
			// 1.打印标题行
			System.out.printf("%-5s %-20s %-25s %-10s\n", "订单编号", "电影名字", "观影时间", "总价");
			
			// 2.获取订单列表
			List<Order> orders = orderService.selectAllOrders();
			
			// 3.判断订单是否为空
			if (!orders.isEmpty()) {
				// 3.1打印订单信息
				for (int i = 0; i < orders.size(); i++) {
					Order order = orders.get(i);
					System.out.printf("%-5d %-10s", (i + 1), order);
				}
				
				// 3.2选择操作
				System.out.println("1.	查看订单详情");
				System.out.println("2.	取消订单");
				System.out.println("3.	返回主界面");
				int choice = ScannerUtil.getValidIntegerInput("请输入【1/2/3】进行操作", 1, 3);
				
				// 3.3进入订单操作层
				flag = orderCtrl.orderCtrl(choice, orders);
			} else {
				// 4.订单为空
				System.out.println("您目前没有订单，请购票后查看！");
				flag = false;
			}
		}
	}
}

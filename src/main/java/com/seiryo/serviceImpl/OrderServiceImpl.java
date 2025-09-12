package com.seiryo.serviceImpl;

import com.seiryo.mapper.OrderMapper;
import com.seiryo.pojo.*;
import com.seiryo.service.LogService;
import com.seiryo.service.OrderService;
import com.seiryo.service.UserService;
import com.seiryo.util.MyBatisUtil;
import com.seiryo.util.ScannerUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderServiceImpl
 * @Description 订单服务层接口实现类
 * @dateTime 2025/9/10 14:43
 */
public class OrderServiceImpl implements OrderService {
	// 定义依赖接口
	private UserService userService;
	private LogService logService;
	
	// 注入依赖
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * @param order 新订单对象
	 * @MethodName: addNewOrder
	 * @Description: 添加新订单
	 */
	public void addNewOrder(Order order) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			int cows = orderMapper.insertNewOrder(order);
			if (cows > 0) {
				sqlSession.commit();
				System.out.println("新订单添加成功！");
			} else {
				sqlSession.rollback();
				System.out.println("订单添加失败！");
			}
		}
	}
	
	/**
	 * @return 返回订单列表
	 * @MethodName: selectAllOrders
	 * @Description: 查询所有订单
	 */
	public List<Order> selectAllOrders() {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			return orderMapper.selectAllOrders();
		}
	}
	
	
	/**
	 * @param orderInfo 要取消订单详情
	 * @MethodName: deleteOrder
	 * @Description: 取消订单
	 */
	public void deleteOrderInfo(OrderInfo orderInfo) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			int cows = orderMapper.deleteOrder(orderInfo);
			if (cows > 0) {
				sqlSession.commit();
				System.out.println("订单取消成功！");
			} else {
				sqlSession.rollback();
				System.out.println("订单取消失败！");
			}
		}
	}
	
	/**
	 * @MethodName: queryOrder
	 * @Description: 查询订单
	 */
	public void queryOrder(Admin admin) {
		System.out.println("========== 查询订单 ==========");
		String username = ScannerUtil.nextLine("请输入要查询的用户名：");
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			List<Order> orders = orderMapper.selectOrdersByUsername(username);
			logService.insertLog(admin, "查询用户名为" + username + "的订单信息");
			if (orders.isEmpty()) {
				System.out.println("该用户没有订单记录！");
			} else {
				System.out.println("用户 " + username + " 的订单记录如下：");
				orders.forEach(System.out::println);
			}
		}
	}
	
	/**
	 * @MethodName: updateOrder
	 * @Description: 修改订单
	 */
	public void updateOrder(Admin admin) {
		System.out.println("========== 修改订单 ==========");
		List<Order> orders = selectAllOrders();
		if (orders.isEmpty()) {
			System.out.println("当前没有订单！");
			return;
		}
		for (Order order : orders) {
			System.out.println(order.getOrderId() + "" + order);
		}
		int orderId = ScannerUtil.getValidIntegerInput("请输入要修改的订单ID：");
		String newState = ScannerUtil.nextLine("请输入新的订单状态（例如：已观看，已取消）：");
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			int rows = orderMapper.updateOrderStateByAdmin(orderId, newState);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("订单状态修改成功！");
				logService.insertLog(admin, "修改订单ID为" + orderId + "的订单状态成功");
			} else {
				sqlSession.rollback();
				System.out.println("订单状态修改失败！");
				logService.insertLog(admin, "修改订单ID为" + orderId + "的订单状态失败");
			}
		}
	}
	
	/**
	 * @MethodName: deleteOrder
	 * @Description: 删除订单（逻辑删除）删除订单详情
	 */
	public void deleteOrder(Admin admin) {
		System.out.println("========== 删除订单 ==========");
		List<Order> orders = selectAllOrders();
		if (orders.isEmpty()) {
			System.out.println("当前没有订单！");
			return;
		}
		for (Order order : orders) {
			System.out.println(order.getOrderId() + "" + order);
		}
		int orderId = ScannerUtil.getValidIntegerInput("请输入要删除的订单ID：");
		Order selectedOrder = new Order();
		for (Order order : orders) {
			if (order.getOrderId() == orderId) {
				selectedOrder = order;
				break;
			}
		}
		User user = selectedOrder.getUser();
		Cinema cinema = selectedOrder.getCinema();
		List<OrderInfo> orderInfos = selectedOrder.getOrderInfos();
		
		System.out.printf("%-15s \t %-15s \t %-15s \t %-25s\n", "订单详情编号", "下单时间", "座位", "订单状态");
		for (int i = 0; i < orderInfos.size(); i++) {
			OrderInfo orderInfo = orderInfos.get(i);
			System.out.printf("%-15s \t %-10s \n", (i + 1), orderInfo);
		}
		
		int choice = ScannerUtil.getValidIntegerInput("请选择要取消的订单：", 1, orderInfos.size());
		
		OrderInfo selectedOI = orderInfos.get(choice - 1);
		
		if (selectedOI.getOrderState().equals("已观看")) {
			System.out.println("该订单已观看，不可取消！");
			return;
		}
		
		String confirm = ScannerUtil.nextLine("确认并取消？ (Y/N): ");
		
		// 判断
		if ("y".equalsIgnoreCase(confirm)) {
			// 改变订单状态
			selectedOI.setOrderState("订单已取消");
			
			// 更新数据库:改变订单状态
			deleteOrderInfo(selectedOI);
			logService.insertLog(admin, "删除订单详情ID为" + selectedOI.getOrderInfoId() + "的订单详情成功");
			// 更新余额和积分
			user.setUserMoney(user.getUserMoney().add(cinema.getCinemaPrice()));
			user.setUserPoint(user.getUserPoint() - cinema.getCinemaPrice().longValue());
			
			// 更新数据库
			userService.updateUserMoneyAndPoints(user);
		} else {
			System.out.println("正在返回订单界面");
		}
	}
}

package com.seiryo.serviceImpl;

import com.seiryo.mapper.OrderMapper;
import com.seiryo.pojo.Admin;
import com.seiryo.pojo.Order;
import com.seiryo.pojo.OrderInfo;
import com.seiryo.service.LogService;
import com.seiryo.service.OrderService;
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
	// 获取SqlSession对象和Mapper代理对象
	private final SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private final OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
	// 服务对象
	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	/**
	 * @param order 新订单对象
	 * @MethodName: addNewOrder
	 * @Description: 添加新订单
	 */
	public void addNewOrder(Order order) {
		int cows = orderMapper.insertNewOrder(order);
		if (cows != 0) {
			System.out.println("新订单添加成功！");
		}
		System.out.println("订单添加失败！");
	}
	
	/**
	 * @param order 新订单对象
	 * @return 返回新订单id
	 * @MethodName: selectNewOrderId
	 * @Description: 查询新订单id
	 */
	public Long selectNewOrderId(Order order) {
		return orderMapper.selectNewOrderId(order);
	}
	
	/**
	 * @return 返回订单列表
	 * @MethodName: selectAllOrders
	 * @Description: 查询所有订单
	 */
	public List<Order> selectAllOrders() {
		return orderMapper.selectAllOrders();
	}
	
	
	/**
	 * @param orderInfo 要取消订单详情
	 * @MethodName: deleteOrder
	 * @Description: 取消订单
	 */
	public void deleteOrderInfo(OrderInfo orderInfo) {
		int cows = orderMapper.deleteOrder(orderInfo);
		if (cows != 0) {
			System.out.println("订单取消成功！");
		}
		System.out.println("订单取消失败！");
	}
	
	/**
	 * @MethodName: queryOrder
	 * @Description: 查询订单
	 */
	public void queryOrder(Admin admin) {
		System.out.println("========== 查询订单 ==========");
		String username = ScannerUtil.nextLine("请输入要查询的用户名：");
		List<Order> orders = orderMapper.selectOrdersByUsername(username);
		logService.insertLog(admin, "查询用户名为" + username + "的订单信息");
		if (orders.isEmpty()) {
			System.out.println("该用户没有订单记录！");
		} else {
			System.out.println("用户 " + username + " 的订单记录如下：");
			orders.forEach(System.out::println);
		}
	}
	
	/**
	 * @MethodName: updateOrder
	 * @Description: 修改订单
	 */
	public void updateOrder(Admin admin) {
		System.out.println("========== 修改订单 ==========");
		int orderId = ScannerUtil.getValidIntegerInput("请输入要修改的订单ID：");
		String newState = ScannerUtil.nextLine("请输入新的订单状态（例如：已观看，已取消）：");
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
	
	/**
	 * @MethodName: deleteOrder
	 * @Description: 删除订单
	 */
	public void deleteOrder(Admin admin) {
		System.out.println("========== 删除订单 ==========");
		int orderId = ScannerUtil.getValidIntegerInput("请输入要删除的订单ID：");
		int rows = orderMapper.deleteOrderById(orderId);
		if (rows > 0) {
			sqlSession.commit();
			System.out.println("订单删除成功！");
			logService.insertLog(admin, "删除订单ID为" + orderId + "的订单成功");
		} else {
			sqlSession.rollback();
			System.out.println("订单删除失败，ID不存在！");
			logService.insertLog(admin, "删除订单ID为" + orderId + "的订单失败");
		}
	}
}

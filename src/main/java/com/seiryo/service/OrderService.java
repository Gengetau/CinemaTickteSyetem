package com.seiryo.service;

import com.seiryo.pojo.Admin;
import com.seiryo.pojo.Order;
import com.seiryo.pojo.OrderInfo;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderService
 * @Description 订单服务层接口
 * @dateTime 2025/9/10 14:42
 */
public interface OrderService {
	/**
	 * @param order 新订单对象
	 * @MethodName: addNewOrder
	 * @Description: 添加新订单
	 */
	void addNewOrder(Order order);
	
	/**
	 * @param order 新订单对象
	 * @return 返回新订单id
	 * @MethodName: selectNewOrderId
	 * @Description: 查询新订单id
	 */
	Long selectNewOrderId(Order order);
	
	/**
	 * @return 返回订单列表
	 * @MethodName: selectAllOrders
	 * @Description: 查询所有订单
	 */
	List<Order> selectAllOrders();
	
	/**
	 * @param orderInfo 要取消订单详情
	 * @MethodName: deleteOrder
	 * @Description: 取消订单
	 */
	void deleteOrderInfo(OrderInfo orderInfo);
	
	/**
	 * @MethodName: queryOrder
	 * @Description: 查询订单
	 */
	void queryOrder(Admin admin);
	
	/**
	 * @MethodName: updateOrder
	 * @Description: 修改订单
	 */
	void updateOrder(Admin admin);
	
	/**
	 * @MethodName: deleteOrder
	 * @Description: 删除订单
	 */
	void deleteOrder(Admin admin);
}

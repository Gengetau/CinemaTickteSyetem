package com.seiryo.mapper;

import com.seiryo.pojo.Order;
import com.seiryo.pojo.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderMapper
 * @Description 订单数据层接口
 * @dateTime 2025/9/10 10:04
 */
public interface OrderMapper {
	/**
	 * @param cinemaId 需要查询的电影id
	 * @return 返回String类型列表
	 * @MethodName: selectSoldSeatsByCinemaId
	 * @Description: 通过电影编号查找已售出座位信息
	 */
	List<String> selectSoldSeatsByCinemaId(Long cinemaId);
	
	/**
	 * @param order 新订单对象
	 * @return 返回受影响的行数
	 * @MethodName: insertNewOrder
	 * @Description: 添加新订单
	 */
	int insertNewOrder(Order order);
	
	/**
	 * @return 返回订单列表
	 * @MethodName: selectAllOrders
	 * @Description: 查询所有订单
	 */
	List<Order> selectAllOrders();
	
	/**
	 * @param orderInfo 订单详情
	 * @return 返回受影响的行数
	 * @MethodName: deleteOrder
	 * @Description: 取消订单
	 */
	int deleteOrder(OrderInfo orderInfo);
	
	/**
	 * @param username 用户名
	 * @return 返回该用户的所有订单
	 * @MethodName: selectOrdersByUsername
	 * @Description: 根据用户名查询订单
	 */
	List<Order> selectOrdersByUsername(String username);
	
	/**
	 * @param orderId  订单ID
	 * @param newState 新状态
	 * @return 返回影响行数
	 * @MethodName: updateOrderStateByAdmin
	 * @Description: 修改订单状态
	 */
	// 添加注解
	int updateOrderStateByAdmin(@Param("orderId") int orderId, @Param("newState") String newState);
}

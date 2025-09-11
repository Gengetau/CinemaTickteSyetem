package com.seiryo.service;

import com.seiryo.pojo.OrderInfo;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderInfoService
 * @Description 订单详情服务层接口
 * @dateTime 2025/9/10 15:11
 */
public interface OrderInfoService {
	/**
	 * @param orderInfo 新的订单详情对象
	 * @MethodName: insertNewOrderInfo
	 * @Description: 添加新的订单详情
	 */
	void insertNewOrder(OrderInfo orderInfo);
}

package com.seiryo.mapper;

import com.seiryo.pojo.OrderInfo;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderInfoMapper
 * @Description 订单详情类数据层映射接口
 * @dateTime 2025/9/10 15:06
 */
public interface OrderInfoMapper {
	/**
	 * @param orderInfo 新的订单详情对象
	 * @return 返回受影响的行数
	 * @MethodName: insertNewOrderInfo
	 * @Description: 添加新的订单详情
	 */
	int insertNewOrderInfo(OrderInfo orderInfo);
}

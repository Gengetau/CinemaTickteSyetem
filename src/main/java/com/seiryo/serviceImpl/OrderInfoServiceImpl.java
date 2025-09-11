package com.seiryo.serviceImpl;

import com.seiryo.mapper.OrderInfoMapper;
import com.seiryo.pojo.OrderInfo;
import com.seiryo.service.OrderInfoService;
import com.seiryo.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author 11567
 * @version v1.0
 * @ClassName OrderInfoServiceImpl
 * @Description 订单详情服务层接口实现类
 * @dateTime 2025/9/10 15:12
 */
public class OrderInfoServiceImpl implements OrderInfoService {
	// 获取SqlSession对象和Mapper代理对象
	private final SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private final OrderInfoMapper orderInfoMapper = sqlSession.getMapper(OrderInfoMapper.class);
	
	/**
	 * @param orderInfo 新的订单详情对象
	 * @MethodName: insertNewOrderInfo
	 * @Description: 添加新的订单详情
	 */
	public void insertNewOrder(OrderInfo orderInfo) {
		int cows = orderInfoMapper.insertNewOrderInfo(orderInfo);
		if (cows != 0) {
			System.out.println("新订单详情添加成功！");
		}
		System.out.println("新订单详情添加失败");
	}
}

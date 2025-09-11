package com.seiryo.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 11567
 * @version v1.0
 * @ClassName Order
 * @Description 订单详情实体类
 * @dateTime 2025/9/9 17:46
 */
public class OrderInfo {
	// 来自 MY_ORDER_INFO表
	private Long orderInfoId;// 订单详情id
	private Long orderId;// 订单编号
	private String position;// 座位
	private String orderState;// 订单状态
	private Date orderDate;// 订单创建时间
	
	public OrderInfo() {
	}
	
	public OrderInfo(Long orderInfoId, Long orderId, String position, String orderState, Date orderDate) {
		this.orderInfoId = orderInfoId;
		this.orderId = orderId;
		this.position = position;
		this.orderState = orderState;
		this.orderDate = orderDate;
	}
	
	public Long getOrderInfoId() {
		return orderInfoId;
	}
	
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getOrderState() {
		return orderState;
	}
	
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String orderTime = sdf.format(orderDate);
		return String.format("%-25s \t %-15s \t %-25s", orderTime, position, orderState);
	}
}

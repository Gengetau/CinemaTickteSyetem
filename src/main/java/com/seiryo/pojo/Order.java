package com.seiryo.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName Order
 * @Description 订单实体类(包含用户实体 ， 电影实体 ， 订单详情实体列表)
 * @dateTime 2025/9/9 17:59
 */
public class Order {
	// 来自 MY_ORDER 表
	private Long orderId;// 订单id
	private BigDecimal totalPrice;// 订单总价
	
	// 订单包含的用户和电影对象
	private User user;// 用户对象
	private Cinema cinema;// 电影对象
	
	// 一个订单可以包含多个订单详情实体
	private List<OrderInfo> orderInfos;
	
	public Order() {
	}
	
	public Order(Long orderId, BigDecimal totalPrice, User user, Cinema cinema, List<OrderInfo> orderInfos) {
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.user = user;
		this.cinema = cinema;
		this.orderInfos = orderInfos;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public List<OrderInfo> getOrderInfos() {
		return orderInfos;
	}
	
	public void setOrderInfos(List<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}
	
	@Override
	public String toString() {
		return String.format(" %-20s %-25s %-10s", cinema.getCinemaName(), cinema.getMovieTime(), totalPrice);
	}
}

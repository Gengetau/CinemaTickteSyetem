package com.seiryo.pojo;

/**
 * @author 11567
 * @version v1.0
 * @ClassName Vip
 * @Description VIP信息表
 * @dateTime 2025/9/9 17:54
 */
public class Vip {
	private Integer vipId;// 会员等级编号
	private Integer vipPoint;// 会员积分等级
	private String vipName;// 会员等级名称
	private Double vipDiscount;// 会员折扣
	
	public Vip() {
	}
	
	public Vip(Integer vipId, Integer vipPoint, String vipName, Double vipDiscount) {
		this.vipId = vipId;
		this.vipPoint = vipPoint;
		this.vipName = vipName;
		this.vipDiscount = vipDiscount;
	}
	
	public Integer getVipId() {
		return vipId;
	}
	
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	
	public Integer getVipPoint() {
		return vipPoint;
	}
	
	public void setVipPoint(Integer vipPoint) {
		this.vipPoint = vipPoint;
	}
	
	public String getVipName() {
		return vipName;
	}
	
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	
	public Double getVipDiscount() {
		return vipDiscount;
	}
	
	public void setVipDiscount(Double vipDiscount) {
		this.vipDiscount = vipDiscount;
	}
	
	@Override
	public String toString() {
		return "Vip{" +
				"vipId=" + vipId +
				", vipPoint=" + vipPoint +
				", vipName='" + vipName + '\'' +
				", vipDiscount=" + vipDiscount +
				'}';
	}
}

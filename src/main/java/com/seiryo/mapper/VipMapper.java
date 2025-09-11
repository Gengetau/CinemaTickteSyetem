package com.seiryo.mapper;

import com.seiryo.pojo.Vip;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName VipMapper
 * @Description 会员数据层接口
 * @dateTime 2025/9/10 12:02
 */
public interface VipMapper {
	/**
	 * @param vipName 会员等级
	 * @return 返回折扣
	 * @MethodName: getVipDiscount
	 * @Description: 获取会员折扣
	 */
	Double selectVipDiscountByVipName(String vipName);
	
	/**
	 * @param points 积分
	 * @return 返回对应会员等级
	 * @MethodName: selectVipNameByPoints
	 * @Description: 根据积分查询对应会员等级
	 */
	String selectVipNameByPoints1(Long points);
	
	/**
	 * @param points 积分
	 * @return 返回下一级所需积分
	 * @MethodName: selectVipNameByPoints
	 * @Description: 根据当前积分查询下一会员等级所需积分
	 */
	Integer selectVipNameByPoints2(Long points);
	
	/**
	 * @return 返回所有VIP等级列表
	 * @MethodName: selectAllVips
	 * @Description: 获取所有VIP等级
	 */
	List<Vip> selectAllVips();
	
	/**
	 * @param vip 新VIP等级
	 * @return 返回影响行数
	 * @MethodName: insertNewVip
	 * @Description: 增加VIP等级
	 */
	int insertNewVip(Vip vip);
	
	/**
	 * @param vipId VIP等级ID
	 * @return 返回影响行数
	 * @MethodName: deleteVip
	 * @Description: 删除VIP等级
	 */
	int deleteVip(int vipId);
	
	/**
	 * @param vip VIP等级
	 * @return 返回影响行数
	 * @MethodName: updateVip
	 * @Description: 修改VIP等级
	 */
	int updateVip(Vip vip);
	
	/**
	 * @param vipId VIP等级ID
	 * @return 返回VIP对象
	 * @MethodName: selectVipById
	 * @Description: 根据ID查询VIP等级
	 */
	Vip selectVipById(int vipId);
}

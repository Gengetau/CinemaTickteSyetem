package com.seiryo.service;

import com.seiryo.pojo.Admin;

/**
 * @author 11567
 * @version v1.0
 * @ClassName VipService
 * @Description vip服务层接口
 * @dateTime 2025/9/10 21:36
 */
public interface VipService {
	// ================== 由管理员发起的对会员的操作 ==================
	
	/**
	 * @MethodName: addVip
	 * @Description: 增加VIP等级
	 */
	void addVip(Admin admin);
	
	/**
	 * @MethodName: deleteVip
	 * @Description: 删除VIP等级
	 */
	void deleteVip(Admin admin);
	
	/**
	 * @MethodName: updateVip
	 * @Description: 修改VIP等级
	 */
	void updateVip(Admin admin);
	
	/**
	 * @MethodName: queryVip
	 * @Description: 查询VIP等级
	 */
	void queryVip(Admin admin);
}

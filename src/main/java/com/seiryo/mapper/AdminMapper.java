package com.seiryo.mapper;

import com.seiryo.pojo.Admin;


/**
 * @author 11567
 * @version v1.0
 * @ClassName AdminMapper
 * @Description 管理员数据层映射接口
 * @dateTime 2025/9/9 21:09
 */
public interface AdminMapper {
	/**
	 * @return 返回管理员列表
	 * @MethodName: selectAllAdmins
	 * @Description: 查询所有管理员信息
	 */
	Admin selectAllAdmins(String inputName);
}

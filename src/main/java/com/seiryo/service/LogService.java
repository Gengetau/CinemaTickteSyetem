package com.seiryo.service;

import com.seiryo.pojo.Admin;

/**
 * @author 11567
 * @version v1.0
 * @ClassName LogService
 * @Description 日志服务层接口
 * @dateTime 2025/9/10 22:02
 */
public interface LogService {
	/**
	 * @MethodName: selectAllLog
	 * @Description: 查询所有日志
	 */
	void selectAllLog(Admin admin);
	
	/**
	 * @param admin   操作人
	 * @param logInfo 日志内容
	 * @MethodName: insertLog
	 * @Description: 插入日志
	 */
	void insertLog(Admin admin, String logInfo);
}

package com.seiryo.mapper;

import com.seiryo.pojo.Log;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName LogMapper
 * @Description 日志数据层接口
 * @dateTime 2025/9/10 22:08
 */
public interface LogMapper {
	/**
	 * @return 返回日志列表
	 * @MethodName: selectAllLog
	 * @Description: 查询所有日志
	 */
	List<Log> selectAllLog();
	
	/**
	 * @param log 日志对象
	 * @return 返回受影响的行数
	 * @MethodName: insertLog
	 * @Description: 插入日志
	 */
	int insertLog(Log log);
}

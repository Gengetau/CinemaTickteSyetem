package com.seiryo.serviceImpl;

import com.seiryo.mapper.LogMapper;
import com.seiryo.pojo.Admin;
import com.seiryo.pojo.Log;
import com.seiryo.service.LogService;
import com.seiryo.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName LogServiceImpl
 * @Description 日志服务层接口实现类
 * @dateTime 2025/9/10 22:03
 */
public class LogServiceImpl implements LogService {
	/**
	 * @MethodName: selectAllLog
	 * @Description: 查询所有日志
	 */
	public void selectAllLog(Admin admin) {
		System.out.println("========== 查询日志 ==========");
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
			List<Log> logs = logMapper.selectAllLog();
			for (Log log : logs) {
				System.out.println(log);
			}
			insertLog(admin, "日志查询");
		}
	}
	
	/**
	 * @param admin   操作人
	 * @param logInfo 日志内容
	 * @MethodName: insertLog
	 * @Description: 插入日志
	 */
	public void insertLog(Admin admin, String logInfo) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
			Log log = new Log();
			Date logDate = new Date();
			log.setLogInfo(logInfo);
			log.setLogDate(logDate);
			log.setLogName(admin.getAdminName());
			int cows = logMapper.insertLog(log);
			if (cows > 0) {
				sqlSession.commit();
				System.out.println("日志插入成功");
			} else {
				sqlSession.rollback();
				System.out.println("日志插入失败");
			}
		}
	}
}

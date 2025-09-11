package com.seiryo.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 11567
 * @version v1.0
 * @ClassName Log
 * @Description 日志实体类
 * @dateTime 2025/9/9 19:16
 */
public class Log {
	// 来自 LOG_INFO表
	private Long logId;// 日志编号
	private String logInfo;// 日志信息
	private Date logDate;// 操作时间
	private String logName;// 操作人
	
	public Log() {
	}
	
	public Log(Long logId, String logInfo, Date logDate, String logName) {
		this.logId = logId;
		this.logInfo = logInfo;
		this.logDate = logDate;
		this.logName = logName;
	}
	
	public Long getLogId() {
		return logId;
	}
	
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	
	public String getLogInfo() {
		return logInfo;
	}
	
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	
	public Date getLogDate() {
		return logDate;
	}
	
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	public String getLogName() {
		return logName;
	}
	
	public void setLogName(String logName) {
		this.logName = logName;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = sdf.format(logDate);
		return String.format("%-5s \t %-80s \t %-25s \t %-10s", logId, logInfo, date, logName);
	}
}
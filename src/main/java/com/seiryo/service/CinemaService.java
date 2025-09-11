package com.seiryo.service;

import com.seiryo.pojo.Admin;
import com.seiryo.pojo.Cinema;
import com.seiryo.pojo.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName CinemaService
 * @Description 电影服务层接口
 * @dateTime 2025/9/10 08:55
 */
public interface CinemaService {
	/**
	 * @MethodName: getAllCinemas
	 * @Description: 获取所有电影列表
	 */
	List<Cinema> getAllCinemas();
	
	/**
	 * @param cinemaId 电影编号
	 * @return 返回电影对象
	 * @MethodName: getCinemaById
	 * @Description: 根据编号获取电影对象
	 */
	Cinema getCinemaById(Long cinemaId);
	
	/**
	 * @param cinemaName 电影名称
	 * @return 返回电影列表
	 * @MethodName: getCinemasByName
	 * @Description: 根据名称获取电影列表
	 */
	List<Cinema> getCinemasByName(String cinemaName);
	
	/**
	 * @param cinema 选中的电影
	 * @return 返回座位列表
	 * @MethodName: getSoldSeatMap
	 * @Description: 获取座位信息
	 */
	List<String> getSoldSeat(Cinema cinema);
	
	/**
	 * @param user  购票用户
	 * @param count 购票数量
	 * @return 返回最终价格
	 * @MethodName: getCinemaPrice
	 * @Description: 获得最终价格
	 */
	BigDecimal getCinemaPrice(User user, int count, BigDecimal cinemaPrice);
	
	/**
	 * @MethodName: addCinema
	 * @Description: 增加电影
	 */
	void addCinema(Admin admin);
	
	/**
	 * @MethodName: deleteCinema
	 * @Description: 删除电影
	 */
	void deleteCinema(Admin admin);
	
	/**
	 * @MethodName: updateCinema
	 * @Description: 修改电影
	 */
	void updateCinema(Admin admin);
	
	/**
	 * @MethodName: queryCinema
	 * @Description: 查询电影
	 */
	void queryCinema(Admin admin);
}

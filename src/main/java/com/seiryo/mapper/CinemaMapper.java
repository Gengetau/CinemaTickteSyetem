package com.seiryo.mapper;

import com.seiryo.pojo.Cinema;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName CinemaMapper
 * @Description 电影数据层映射接口
 * @dateTime 2025/9/10 08:58
 */
public interface CinemaMapper {
	/**
	 * @MethodName: selectAllCinemas
	 * @Description: 获取所有电影列表
	 */
	List<Cinema> selectAllCinemas();
	
	/**
	 * @param cinemaId 电影编号
	 * @return 返回电影对象
	 * @MethodName: selectCinemaById
	 * @Description: 根据编号获取电影对象
	 */
	Cinema selectCinemaById(Long cinemaId);
	
	/**
	 * @param cinemaName 电影名称
	 * @return 返回电影列表
	 * @MethodName: getCinemasByName
	 * @Description: 根据名称获取电影列表
	 */
	List<Cinema> selectCinemasByName(String cinemaName);
	
	/**
	 * @param cinema 新电影
	 * @return 返回影响行数
	 * @MethodName: insertNewCinema
	 * @Description: 添加新电影
	 */
	int insertNewCinema(Cinema cinema);
	
	/**
	 * @param cinemaId 电影编号
	 * @return 返回影响行数
	 * @MethodName: deleteCinema
	 * @Description: 删除电影
	 */
	int deleteCinema(long cinemaId);
	
	/**
	 * @param cinema 电影
	 * @return 返回影响行数
	 * @MethodName: updateCinema
	 * @Description: 修改电影
	 */
	int updateCinema(Cinema cinema);
}

package com.seiryo.serviceImpl;

import com.seiryo.mapper.CinemaMapper;
import com.seiryo.mapper.OrderMapper;
import com.seiryo.mapper.VipMapper;
import com.seiryo.pojo.Admin;
import com.seiryo.pojo.Cinema;
import com.seiryo.pojo.User;
import com.seiryo.service.CinemaService;
import com.seiryo.service.LogService;
import com.seiryo.util.MyBatisUtil;
import com.seiryo.util.ScannerUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName CinemaServiceImpl
 * @Description 电影服务层接口实现类
 * @dateTime 2025/9/10 08:57
 */
public class CinemaServiceImpl implements CinemaService {
	// 获取SqlSession对象和Mapper代理对象
	private final SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private final CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
	private final OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
	private final VipMapper vipMapper = sqlSession.getMapper(VipMapper.class);
	// 服务对象
	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	/**
	 * @MethodName: getAllCinemas
	 * @Description: 获取所有电影列表
	 */
	public List<Cinema> getAllCinemas() {
		// 返回电影列表
		return cinemaMapper.selectAllCinemas();
	}
	
	/**
	 * @param cinemaId 电影编号
	 * @return 返回电影对象
	 * @MethodName: getCinemaById
	 * @Description: 根据编号获取电影对象
	 */
	public Cinema getCinemaById(Long cinemaId) {
		// 返回电影对象
		return cinemaMapper.selectCinemaById(cinemaId);
	}
	
	/**
	 * @param cinemaName 电影名称
	 * @return 返回电影列表
	 * @MethodName: getCinemasByName
	 * @Description: 根据名称获取电影列表
	 */
	public List<Cinema> getCinemasByName(String cinemaName) {
		// 返回电影列表
		return cinemaMapper.selectCinemasByName(cinemaName);
	}
	
	/**
	 * @param cinema 选中的电影
	 * @return 返回座位列表
	 * @MethodName: getSoldSeatMap
	 * @Description: 获取已售座位信息
	 */
	public List<String> getSoldSeat(Cinema cinema) {
		// 获取本场电影的订单信息
		return orderMapper.selectSoldSeatsByCinemaId(cinema.getCinemaId());
	}
	
	/**
	 * @param user  购票用户
	 * @param count 购票数量
	 * @return 返回最终价格
	 * @MethodName: getCinemaPrice
	 * @Description: 获得最终价格
	 */
	public BigDecimal getCinemaPrice(User user, int count, BigDecimal cinemaPrice) {
		// 1.计算总价
		cinemaPrice = cinemaPrice.multiply(new BigDecimal(count));
		
		// 2.判断数量
		if (count >= 2) {
			cinemaPrice = cinemaPrice.multiply(BigDecimal.valueOf(0.95));
		}
		
		// 3.计算会员折扣
		// 3.1获取会员折扣
		String vipName = user.getUserVip();
		Double discount = vipMapper.selectVipDiscountByVipName(vipName);
		
		// 4.计算最终价格
		cinemaPrice = cinemaPrice.multiply(new BigDecimal(discount));
		
		// 5.返回BigDecimal类型的价格
		return cinemaPrice;
	}
	
	/**
	 * @MethodName: addCinema
	 * @Description: 增加电影
	 */
	public void addCinema(Admin admin) {
		System.out.println("========== 增加电影 ==========");
		String cinemaName = ScannerUtil.nextLine("请输入电影名称：");
		String releaseTimeStr = ScannerUtil.nextLine("请输入上映时间 (yyyy-MM-dd)：");
		String cinemaPriceStr = ScannerUtil.nextLine("请输入电影票价：");
		String movieTimeStr = ScannerUtil.nextLine("请输入观影时间 (yyyy-MM-dd-HH:mm)：");
		String cinemaState = ScannerUtil.nextLine("请输入电影状态（上架/下架）：");
		int cinemaSeats = ScannerUtil.getValidIntegerInput("请输入电影座位数：");
		
		// 转换日期和时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date releaseTime = dateFormat.parse(releaseTimeStr);
			Date movieTime = timeFormat.parse(movieTimeStr);
			BigDecimal cinemaPrice = new BigDecimal(cinemaPriceStr);
			
			Cinema newCinema = new Cinema();
			newCinema.setCinemaName(cinemaName);
			newCinema.setReleaseTime(releaseTime);
			newCinema.setCinemaPrice(cinemaPrice);
			newCinema.setMovieTime(movieTime);
			newCinema.setCinemaState(cinemaState);
			newCinema.setCinemaSeats(cinemaSeats);
			
			int rows = cinemaMapper.insertNewCinema(newCinema);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("新电影添加成功！");
				logService.insertLog(admin, "新电影" + newCinema.getCinemaName() + "添加成功");
			} else {
				sqlSession.rollback();
				System.out.println("新电影添加失败！");
				logService.insertLog(admin, "新电影" + newCinema.getCinemaName() + "添加失败");
			}
		} catch (ParseException | NumberFormatException e) {
			System.out.println("输入格式错误，请重新输入！");
			sqlSession.rollback();
		}
	}
	
	/**
	 * @MethodName: deleteCinema
	 * @Description: 删除电影
	 */
	public void deleteCinema(Admin admin) {
		System.out.println("========== 删除电影 ==========");
		System.out.println("所有电影列表：");
		List<Cinema> allCinemas = cinemaMapper.selectAllCinemas();
		System.out.printf("%-10s \t %-18s \t %-19s \t %-16s \t %-10s\n", "电影编号", "电影名称", "上映时间", "电影价格", "观影时间");
		for (Cinema cinema : allCinemas) {
			System.out.println(cinema);
		}
		long cinemaId = ScannerUtil.getValidIntegerInput("请输入要删除的电影ID：");
		Cinema cinema = cinemaMapper.selectCinemaById(cinemaId);
		int rows = cinemaMapper.deleteCinema(cinemaId);
		if (rows > 0) {
			sqlSession.commit();
			System.out.println("电影删除成功！");
			logService.insertLog(admin, "电影" + cinema.getCinemaName() + "删除成功");
		} else {
			sqlSession.rollback();
			System.out.println("电影删除失败，电影ID不存在！");
			logService.insertLog(admin, "电影" + cinema.getCinemaName() + "删除失败");
		}
	}
	
	/**
	 * @MethodName: updateCinema
	 * @Description: 修改电影
	 */
	public void updateCinema(Admin admin) {
		System.out.println("========== 修改电影 ==========");
		System.out.println("所有电影列表：");
		List<Cinema> allCinemas = cinemaMapper.selectAllCinemas();
		System.out.printf("%-10s \t %-18s \t %-19s \t %-16s \t %-10s\n", "电影编号", "电影名称", "上映时间", "电影价格", "观影时间");
		for (Cinema cinema : allCinemas) {
			System.out.println(cinema);
		}
		long cinemaId = ScannerUtil.getValidIntegerInput("请输入要修改的电影ID：");
		
		Cinema existingCinema = cinemaMapper.selectCinemaById(cinemaId);
		if (existingCinema == null) {
			System.out.println("电影ID不存在！");
			return;
		}
		
		System.out.println("当前电影信息：");
		System.out.println("电影名称：" + existingCinema.getCinemaName());
		System.out.println("电影票价：" + existingCinema.getCinemaPrice());
		System.out.println("电影状态：" + existingCinema.getCinemaState());
		
		String newCinemaName = ScannerUtil.nextLine("请输入新电影名称（直接回车跳过）：");
		String newCinemaPriceStr = ScannerUtil.nextLine("请输入新电影票价（直接回车跳过）：");
		String newCinemaState = ScannerUtil.nextLine("请输入新电影状态（上架/下架，直接回车跳过）：");
		
		if (!newCinemaName.isEmpty()) {
			existingCinema.setCinemaName(newCinemaName);
			logService.insertLog(admin, "编号" + existingCinema.getCinemaId() + "的电影名称被修改为" + existingCinema.getCinemaName());
		}
		if (!newCinemaPriceStr.isEmpty()) {
			existingCinema.setCinemaPrice(new BigDecimal(newCinemaPriceStr));
			logService.insertLog(admin, "编号" + existingCinema.getCinemaId() + "的电影票价被修改为" + existingCinema.getCinemaPrice());
		}
		if (!newCinemaState.isEmpty()) {
			existingCinema.setCinemaState(newCinemaState);
			logService.insertLog(admin, "编号" + existingCinema.getCinemaId() + "的电影状态被修改为" + existingCinema.getCinemaState());
		}
		
		int rows = cinemaMapper.updateCinema(existingCinema);
		if (rows > 0) {
			sqlSession.commit();
			System.out.println("电影信息修改成功！");
		} else {
			sqlSession.rollback();
			System.out.println("电影信息修改失败！");
		}
	}
	
	/**
	 * @MethodName: queryCinema
	 * @Description: 查询电影
	 */
	public void queryCinema(Admin admin) {
		System.out.println("========== 查询电影 ==========");
		System.out.println("1. 按ID查询");
		System.out.println("2. 按名称查询");
		int choice = ScannerUtil.getValidIntegerInput("请选择查询方式：");
		if (choice == 1) {
			long cinemaId = ScannerUtil.getValidIntegerInput("请输入电影ID：");
			Cinema cinema = cinemaMapper.selectCinemaById(cinemaId);
			if (cinema != null) {
				System.out.println("查询结果：");
				System.out.println(cinema);
				logService.insertLog(admin, "查询编号为" + cinemaId + "的电影");
			} else {
				System.out.println("未找到该电影！");
			}
		} else if (choice == 2) {
			String cinemaName = ScannerUtil.nextLine("请输入电影名称：");
			List<Cinema> cinemas = cinemaMapper.selectCinemasByName(cinemaName);
			if (!cinemas.isEmpty()) {
				System.out.println("查询结果：");
				for (Cinema cinema : cinemas) {
					System.out.println(cinema);
				}
				logService.insertLog(admin, "查询名称为" + cinemaName + "的电影");
			} else {
				System.out.println("未找到相关电影！");
			}
		}
	}
}

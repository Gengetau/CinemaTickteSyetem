package com.seiryo.serviceImpl;

import com.seiryo.mapper.AdminMapper;
import com.seiryo.pojo.Admin;
import com.seiryo.service.*;
import com.seiryo.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author 11567
 * @version v1.0
 * @ClassName AdminServiceImpl
 * @Description 管理员服务层接口实现类
 * @dateTime 2025/9/9 21:56
 */
public class AdminServiceImpl implements AdminService {
	// 定义依赖的Service层接口
	private UserService userService;
	private CinemaService cinemaService;
	private VipService vipService;
	private OrderService orderService;
	
	
	// Setter方法用于依赖注入
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setCinemaService(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}
	
	public void setVipService(VipService vipService) {
		this.vipService = vipService;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * @param inputName 输入的账号
	 * @param inputPass 输入的密码
	 * @return 返回管理员对象
	 * @MethodName: adminLogin
	 * @Description: 管理员登录逻辑
	 */
	public Admin adminLogin(String inputName, String inputPass) {
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			Admin admin = adminMapper.selectAllAdmins(inputName);
			if (admin != null && admin.getAdminPass().equals(inputPass)) {
				return admin;
			}
		}
		System.out.println("账号或密码错误，请重新输入");
		return null;
	}
	
	/**
	 * @MethodName: insertUser
	 * @Description: 增加用户
	 */
	public void insertUser(Admin admin) {
		userService.insertUserByAdmin(admin);
	}
	
	/**
	 * @MethodName: deleteUser
	 * @Description: 删除用户(逻辑删除)
	 */
	public void deleteUser(Admin admin) {
		userService.deleteUserByAdmin(admin);
	}
	
	/**
	 * @MethodName: updateUser
	 * @Description: 修改用户
	 */
	public void updateUser(Admin admin) {
		userService.updateUserByAdmin(admin);
	}
	
	/**
	 * @MethodName: queryUser
	 * @Description: 查询用户
	 */
	public void queryUser(Admin admin) {
		userService.queryUserByAdmin(admin);
	}
	
	/**
	 * @MethodName: insertMovie
	 * @Description: 增加电影
	 */
	public void insertMovie(Admin admin) {
		cinemaService.addCinema(admin);
	}
	
	/**
	 * @MethodName: deleteMovie
	 * @Description: 删除电影
	 */
	public void deleteMovie(Admin admin) {
		cinemaService.deleteCinema(admin);
	}
	
	/**
	 * @MethodName: updateMovie
	 * @Description: 修改电影
	 */
	public void updateMovie(Admin admin) {
		cinemaService.updateCinema(admin);
	}
	
	/**
	 * @MethodName: queryMovie
	 * @Description: 查询电影
	 */
	public void queryMovie(Admin admin) {
		cinemaService.queryCinema(admin);
	}
	
	/**
	 * @MethodName: insertVip
	 * @Description: 增加VIP等级
	 */
	public void insertVip(Admin admin) {
		vipService.addVip(admin);
	}
	
	/**
	 * @MethodName: deleteVip
	 * @Description: 删除VIP等级
	 */
	public void deleteVip(Admin admin) {
		vipService.deleteVip(admin);
	}
	
	/**
	 * @MethodName: updateVip
	 * @Description: 修改VIP等级
	 */
	public void updateVip(Admin admin) {
		vipService.updateVip(admin);
	}
	
	/**
	 * @MethodName: queryVip
	 * @Description: 查询VIP等级
	 */
	public void queryVip(Admin admin) {
		vipService.queryVip(admin);
	}
	
	/**
	 * @MethodName: updateOrder
	 * @Description: 修改订单
	 */
	public void updateOrder(Admin admin) {
		orderService.updateOrder(admin);
	}
	
	/**
	 * @MethodName: deleteOrder
	 * @Description: 删除订单
	 */
	public void deleteOrder(Admin admin) {
		orderService.deleteOrder(admin);
	}
	
	/**
	 * @MethodName: queryOrder
	 * @Description: 查询订单
	 */
	public void queryOrder(Admin admin) {
		orderService.queryOrder(admin);
	}
}

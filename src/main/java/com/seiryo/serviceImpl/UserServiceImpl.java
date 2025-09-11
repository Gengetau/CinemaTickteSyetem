package com.seiryo.serviceImpl;

import com.seiryo.mapper.UserMapper;
import com.seiryo.mapper.VipMapper;
import com.seiryo.pojo.User;
import com.seiryo.service.UserService;
import com.seiryo.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName UserServiceImpl
 * @Description 用户服务层接口实现类
 * @dateTime 2025/9/9 20:05
 */
public class UserServiceImpl implements UserService {
	// 获取SqlSession对象和Mapper代理对象
	private final SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	private final VipMapper vipMapper = sqlSession.getMapper(VipMapper.class);
	
	/**
	 * @param inputName 用户输入的账号
	 * @param inputPass 用户输入的密码
	 * @return 返回登录成功的用户
	 * @MethodName: userLogin
	 * @Description: 用户登录逻辑方法
	 */
	public User userLogin(String inputName, String inputPass) {
		// 1.通过Mapper代理对象执行sql语句，获得用户列表
		List<User> users = userMapper.selectAllUsers();
		
		// 2.对比账号密码
		for (User user : users) {
			if (user.getUsername().equals(inputName)) {
				if (user.getUserpass().equals(inputPass)) {
					return user;
				}
			}
		}
		System.out.println("账号或密码错误，请重新输入");
		return null;
	}
	
	/**
	 * @param inputName 用户输入的账号
	 * @return 成功返回true,失败返回false
	 * @MethodName: userRegister
	 * @Description: 用户注册逻辑方法
	 */
	public boolean userRegister(String inputName) {
		// 1.通过Mapper代理对象执行sql语句，获得用户列表
		List<User> users = userMapper.selectAllUsers();
		
		// 2.判断是否重名
		for (User user : users) {
			if (!user.getUsername().equals(inputName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param user 注册的新用户
	 * @MethodName: addUser
	 * @Description: 添加新用户
	 */
	public void addUser(User user) {
		// 1.通过Mapper代理对象执行sql语句，添加新用户
		int cows = userMapper.insertNewUser(user);
		if (cows != 0) {
			System.out.println("新用户添加成功！");
		}
		System.out.println("新用户添加失败");
	}
	
	/**
	 * @param user 注册的新用户
	 * @MethodName: addUserInfo
	 * @Description: 添加新用户数据
	 */
	public void addUserInfo(User user) {
		int cows = userMapper.insertNewUserInfo(user);
		if (cows != 0) {
			System.out.println("新用户数据添加成功！");
		}
		System.out.println("新用户数据添加失败");
	}
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserMoneyAndPoints
	 * @Description: 更新用户余额和积分
	 */
	public void updateUserMoneyAndPoints(User user) {
		int cows = userMapper.updateUserMoneyAndPoints(user.getUserid());
		if (cows != 0) {
			System.out.println("用户积分增加！当前积分为：" + user.getUserPoint());
		}
		System.out.println("用户数据修改失败");
	}
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserMoney
	 * @Description: 更新用户余额
	 */
	public void updateUserMoney(User user) {
		int cows = userMapper.updateUserMoneyAndPoints(user.getUserid());
		if (cows != 0) {
			System.out.println("用户充值成功！当前余额为：" + user.getUserMoney());
		}
		System.out.println("用户充值失败");
	}
	
	/**
	 * @param user 需要更新的用户
	 * @MethodName: updateUserVipName
	 * @Description: 更新用户会员等级
	 */
	public void updateUserVipName(User user) {
		// 获取会员等级和对应积分
		String newVipName = vipMapper.selectVipNameByPoints1(user.getUserPoint());
		Integer newVipPoints = vipMapper.selectVipNameByPoints2(user.getUserPoint());
		if (newVipName.equals(user.getUserVip())) {
			if (newVipPoints != null) {
				System.out.println("距离下一会员等级还差" + (newVipPoints - user.getUserPoint()) + "积分");
			} else {
				System.out.println("您已是我们影城最高级的黑钻会员！");
			}
		} else {
			user.setUserVip(newVipName);
			int cows = userMapper.updateUserVipName(user.getUserid());
			if (cows != 0) {
				System.out.println("用户会员等级提升！当前等级为：" + user.getUserVip());
			}
			System.out.println("用户数据修改失败");
		}
	}
	
	/**
	 * @param user 当前用户
	 * @MethodName: updateUserPass
	 * @Description: 更新用户密码
	 */
	public void updateUserPass(User user) {
		int cows = userMapper.updateUserPass(user);
		if (cows != 0) {
			System.out.println("用户密码修改成功！");
		}
		System.out.println("用户密码修改失败");
	}
	
	/**
	 * @param user 当前用户
	 * @MethodName: updateUserPhone
	 * @Description: 更新用户手机号
	 */
	public void updateUserPhone(User user) {
		int cows = userMapper.updateUserPhone(user);
		if (cows != 0) {
			System.out.println("用户手机号修改成功！");
		}
		System.out.println("用户手机号修改失败");
	}
	
	/**
	 * @param user 要修改的用户
	 * @MethodName: updateUserByAdmin
	 * @Description: 管理员修改用户信息
	 */
	public void updateUserByAdmin(User user) {
		int cows = userMapper.updateUserByAdmin(user);
		if (cows > 0) {
			sqlSession.commit();
			System.out.println("管理员修改用户信息成功！");
		} else {
			sqlSession.rollback();
			System.out.println("管理员修改用户信息失败！");
		}
	}
}

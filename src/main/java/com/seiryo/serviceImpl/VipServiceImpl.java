package com.seiryo.serviceImpl;

import com.seiryo.mapper.VipMapper;
import com.seiryo.pojo.Admin;
import com.seiryo.pojo.Vip;
import com.seiryo.service.LogService;
import com.seiryo.service.VipService;
import com.seiryo.util.MyBatisUtil;
import com.seiryo.util.ScannerUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author 11567
 * @version v1.0
 * @ClassName VipServiceImpl
 * @Description vip服务层接口实现类
 * @dateTime 2025/9/10 21:38
 */
public class VipServiceImpl implements VipService {
	// 定义依赖接口
	private LogService logService;
	
	// 注入依赖
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	/**
	 * @MethodName: addVip
	 * @Description: 增加VIP等级
	 */
	public void addVip(Admin admin) {
		System.out.println("========== 增加VIP等级 ==========");
		String vipName = ScannerUtil.nextLine("请输入VIP等级名称：");
		int vipPoint = ScannerUtil.getValidIntegerInput("请输入所需积分：");
		double vipDiscount = Double.parseDouble(ScannerUtil.nextLine("请输入折扣（例如0.95）："));
		
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			VipMapper vipMapper = sqlSession.getMapper(VipMapper.class);
			Vip newVip = new Vip();
			newVip.setVipName(vipName);
			newVip.setVipPoint(vipPoint);
			newVip.setVipDiscount(vipDiscount);
			
			int rows = vipMapper.insertNewVip(newVip);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("VIP等级添加成功！");
				logService.insertLog(admin, "添加VIP等级" + vipName + "成功");
			} else {
				sqlSession.rollback();
				System.out.println("VIP等级添加失败！");
				logService.insertLog(admin, "添加VIP等级" + vipName + "失败");
			}
		}
	}
	
	/**
	 * @MethodName: deleteVip
	 * @Description: 删除VIP等级
	 */
	public void deleteVip(Admin admin) {
		System.out.println("========== 删除VIP等级 ==========");
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			VipMapper vipMapper = sqlSession.getMapper(VipMapper.class);
			List<Vip> allVips = vipMapper.selectAllVips();
			for (Vip vip : allVips) {
				System.out.println(vip);
			}
			int vipId = ScannerUtil.getValidIntegerInput("请输入要删除的VIP等级ID：");
			
			int rows = vipMapper.deleteVip(vipId);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("VIP等级删除成功！");
				logService.insertLog(admin, "删除id为" + vipId + "的VIP等级成功");
			} else {
				sqlSession.rollback();
				System.out.println("VIP等级删除失败，ID不存在！");
				logService.insertLog(admin, "删除id为" + vipId + "的VIP等级失败");
			}
		}
	}
	
	/**
	 * @MethodName: updateVip
	 * @Description: 修改VIP等级
	 */
	public void updateVip(Admin admin) {
		System.out.println("========== 修改VIP等级 ==========");
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			VipMapper vipMapper = sqlSession.getMapper(VipMapper.class);
			List<Vip> allVips = vipMapper.selectAllVips();
			for (Vip vip : allVips) {
				System.out.println(vip);
			}
			int vipId = ScannerUtil.getValidIntegerInput("请输入要修改的VIP等级ID：");
			
			Vip existingVip = vipMapper.selectVipById(vipId);
			if (existingVip == null) {
				System.out.println("VIP等级ID不存在！");
				return;
			}
			
			String newVipName = ScannerUtil.nextLine("请输入新VIP等级名称（直接回车跳过）：");
			String newVipPointStr = ScannerUtil.nextLine("请输入新所需积分（直接回车跳过）：");
			String newVipDiscountStr = ScannerUtil.nextLine("请输入新折扣（直接回车跳过）：");
			
			if (!newVipName.isEmpty()) {
				existingVip.setVipName(newVipName);
			}
			if (!newVipPointStr.isEmpty()) {
				existingVip.setVipPoint(Integer.parseInt(newVipPointStr));
			}
			if (!newVipDiscountStr.isEmpty()) {
				existingVip.setVipDiscount(Double.parseDouble(newVipDiscountStr));
			}
			
			int rows = vipMapper.updateVip(existingVip);
			if (rows > 0) {
				sqlSession.commit();
				System.out.println("VIP等级修改成功！");
				logService.insertLog(admin, "修改id为" + vipId + "的VIP等级信息成功");
			} else {
				sqlSession.rollback();
				System.out.println("VIP等级修改失败！");
				logService.insertLog(admin, "修改id为" + vipId + "的VIP等级信息失败");
			}
		}
	}
	
	/**
	 * @MethodName: queryVip
	 * @Description: 查询VIP等级
	 */
	public void queryVip(Admin admin) {
		System.out.println("========== 查询VIP等级 ==========");
		System.out.println("1. 查询所有VIP等级");
		System.out.println("2. 按ID查询特定VIP等级");
		int choice = ScannerUtil.getValidIntegerInput("请选择查询方式【1/2】：", 1, 2);
		
		try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
			VipMapper vipMapper = sqlSession.getMapper(VipMapper.class);
			
			if (choice == 1) {
				List<Vip> allVips = vipMapper.selectAllVips();
				System.out.println("所有VIP等级列表：");
				if (allVips.isEmpty()) {
					System.out.println("系统中没有任何VIP等级信息。");
				} else {
					System.out.printf("%-10s \t %-15s \t %-15s \t %-10s\n", "VIP ID", "等级名称", "所需积分", "折扣");
					for (Vip vip : allVips) {
						System.out.printf("%-10d \t %-15s \t %-15d \t %-10.2f\n", vip.getVipId(), vip.getVipName(), vip.getVipPoint(), vip.getVipDiscount());
					}
				}
				logService.insertLog(admin, "查询所有VIP等级信息");
			} else { // choice == 2
				int vipId = ScannerUtil.getValidIntegerInput("请输入要查询的VIP等级ID：");
				Vip vip = vipMapper.selectVipById(vipId);
				System.out.println("查询结果：");
				if (vip != null) {
					System.out.printf("%-10s \t %-15s \t %-15s \t %-10s\n", "VIP ID", "等级名称", "所需积分", "折扣");
					System.out.printf("%-10d \t %-15s \t %-15d \t %-10.2f\n", vip.getVipId(), vip.getVipName(), vip.getVipPoint(), vip.getVipDiscount());
					logService.insertLog(admin, "查询ID为 " + vipId + " 的VIP等级信息");
				} else {
					System.out.println("未找到ID为 " + vipId + " 的VIP等级！");
				}
			}
		}
	}
}

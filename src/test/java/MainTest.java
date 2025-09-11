import com.seiryo.ctrl.*;
import com.seiryo.serviceImpl.*;
import com.seiryo.view.*;

/**
 * @author 11567
 * @version v1.0
 * @ClassName MainTest
 * @Description 主测试类
 * @dateTime 2025/9/10 23:15
 */
public class MainTest {
	/**
	 * @MethodName: main
	 * @Description: 启动方法
	 */
	public static void main(String[] args) {
		// ============= 1. 创建所有对象实例 =============
		// Service层实例
		UserServiceImpl userService = new UserServiceImpl();
		AdminServiceImpl adminService = new AdminServiceImpl();
		CinemaServiceImpl cinemaService = new CinemaServiceImpl();
		OrderServiceImpl orderService = new OrderServiceImpl();
		OrderInfoServiceImpl orderInfoService = new OrderInfoServiceImpl();
		VipServiceImpl vipService = new VipServiceImpl();
		LogServiceImpl logService = new LogServiceImpl();
		
		// Controller层实例
		UserCtrl userCtrl = new UserCtrl();
		AdminCtrl adminCtrl = new AdminCtrl();
		CinemaCtrl cinemaCtrl = new CinemaCtrl();
		OrderCtrl orderCtrl = new OrderCtrl();
		InformationCtrl informationCtrl = new InformationCtrl();
		
		// View层实例
		MainView mainView = new MainView();
		AdminView adminView = new AdminView();
		CinemaView cinemaView = new CinemaView();
		OrderView orderView = new OrderView();
		InformationView informationView = new InformationView();
		
		// ============= 2. 开始依赖注入 =============
		
		// 2.1 Service层内部的依赖注入
		adminService.setUserService(userService);
		adminService.setCinemaService(cinemaService);
		adminService.setVipService(vipService);
		adminService.setOrderService(orderService);
		adminService.setLogService(logService);
		cinemaService.setLogService(logService);
		orderService.setLogService(logService);
		vipService.setLogService(logService);
		
		// 2.2 向Controller层注入它所需要的Service
		userCtrl.setUserService(userService);
		adminCtrl.setAdminService(adminService);
		adminCtrl.setLogService(logService);
		cinemaCtrl.setCinemaService(cinemaService);
		cinemaCtrl.setUserService(userService);
		cinemaCtrl.setOrderService(orderService);
		cinemaCtrl.setOrderInfoService(orderInfoService);
		orderCtrl.setOrderService(orderService);
		orderCtrl.setUserService(userService);
		informationCtrl.setUserService(userService);
		
		// 2.3 向Controller层注入它所需要的View
		userCtrl.setCinemaView(cinemaView);
		userCtrl.setAdminView(adminView);
		adminCtrl.setAdminView(adminView);
		adminCtrl.setCinemaView(cinemaView);
		adminCtrl.setOrderView(orderView);
		adminCtrl.setInformationView(informationView);
		cinemaCtrl.setOrderView(orderView);
		cinemaCtrl.setInformationView(informationView);
		cinemaCtrl.setCinemaView(cinemaView);
		
		// 2.4 Controller之间的依赖注入
		userCtrl.setAdminCtrl(adminCtrl);
		cinemaCtrl.setUserCtrl(userCtrl);
		
		// 2.5 向View层注入它所需要的Controller或Service
		mainView.setUserCtrl(userCtrl);
		adminView.setAdminCtrl(adminCtrl);
		cinemaView.setCinemaCtrl(cinemaCtrl);
		cinemaView.setCinemaService(cinemaService);
		orderView.setOrderCtrl(orderCtrl);
		orderView.setOrderService(orderService);
		informationView.setInformationCtrl(informationCtrl);
		
		// ============= 3. 一切准备就绪，启动程序！ =============
		mainView.mainView();
	}
}

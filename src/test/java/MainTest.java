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
		// 创建Service层实例
		UserServiceImpl userService = new UserServiceImpl();
		AdminServiceImpl adminService = new AdminServiceImpl();
		CinemaServiceImpl cinemaService = new CinemaServiceImpl();
		OrderServiceImpl orderService = new OrderServiceImpl();
		OrderInfoServiceImpl orderInfoService = new OrderInfoServiceImpl();
		VipServiceImpl vipService = new VipServiceImpl();
		
		// 创建Ctrl层实例
		UserCtrl userCtrl = new UserCtrl();
		AdminCtrl adminCtrl = new AdminCtrl();
		CinemaCtrl cinemaCtrl = new CinemaCtrl();
		OrderCtrl orderCtrl = new OrderCtrl();
		InformationCtrl informationCtrl = new InformationCtrl();
		
		// 创建View层实例
		MainView mainView = new MainView();
		AdminView adminView = new AdminView();
		CinemaView cinemaView = new CinemaView();
		OrderView orderView = new OrderView();
		InformationView informationView = new InformationView();
		
		// 1. 注入Service到Controller
		userCtrl.setUserService(userService);
		adminCtrl.setAdminService(adminService);
		cinemaCtrl.setCinemaService(cinemaService);
		cinemaCtrl.setUserService(userService);
		cinemaCtrl.setOrderService(orderService);
		cinemaCtrl.setOrderInfoService(orderInfoService);
		orderCtrl.setOrderService(orderService);
		orderCtrl.setUserService(userService);
		informationCtrl.setUserService(userService);
		
		// 2. 注入View到Controller
		userCtrl.setCinemaView(cinemaView);
		userCtrl.setAdminView(adminView);
		adminCtrl.setAdminView(adminView);
		adminCtrl.setCinemaView(cinemaView);
		adminCtrl.setOrderView(orderView);
		adminCtrl.setInformationView(informationView);
		cinemaCtrl.setOrderView(orderView);
		cinemaCtrl.setInformationView(informationView);
		
		// 3. 注入Controller到View
		mainView.setUserCtrl(userCtrl);
		adminView.setAdminCtrl(adminCtrl);
		cinemaView.setCinemaCtrl(cinemaCtrl);
		orderView.setOrderCtrl(orderCtrl);
		informationView.setInformationCtrl(informationCtrl);
		
		// 4. 启动主程序
		mainView.mainView();
	}
}

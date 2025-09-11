# CinemaTS - neko影城售票系统

这是一个基于Java控制台的电影院售票系统，使用Maven构建，数据库为MySQL。

## 如何运行项目

1.  **克隆项目**
    ```bash
    git clone [https://github.com/YourUsername/CinemaTicketSystem.git](https://github.com/YourUsername/CinemaTicketSystem.git)
    ```

2.  **数据库设置**
    - 确保您已安装并运行 MySQL 数据库。
    - 使用数据库管理工具（如 Navicat）创建一个新的数据库，名称为 `CinemaTS`。
    - 在这个新创建的 `CinemaTS` 数据库中，执行项目根目录下 `/sql/Cinema_DB.sql` 脚本，这将自动创建所有需要的表和初始数据。

3.  **配置项目**
    - 在 IntelliJ IDEA 中打开项目。
    - 打开 `src/main/resources/mybatis-config.xml` 文件。
    - 根据您本地的 MySQL 设置，修改以下数据库连接信息：
      ```xml
      <property name="url" value="jdbc:mysql://localhost:3306/CinemaTS?useSSl=false;serverTimezone=UTC"/>
      <property name="username" value="你的数据库用户名"/>
      <property name="password" value="你的数据库密码"/>
      ```

4.  **运行**
    - 在 IDEA 中找到 `src/test/java/MainTest.java` 文件。
    - 右键点击并选择 `Run 'MainTest.main()'` 即可启动程序。

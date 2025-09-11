package com.seiryo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 11567
 * @version v1.0
 * @ClassName MyBatisUtil
 * @Description MyBatis核心加载工具
 * @dateTime 2025/9/9 19:36
 */
public class MyBatisUtil {
	/**
	 * @return 返回SqlSession对象
	 * @MethodName: getSqlSession
	 * @Description: 获取SqlSession对象方法
	 */
	public static SqlSession getSqlSession() {
		// 1.加载mybatis的核心配置文件，获取SqlSessionFactory
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 2.返回SqlSession对象
		return sqlSessionFactory.openSession();
	}
}

package cqupt.wss.mybaits.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 获取SqlSessionFactory工具类
 * */
public class SqlSessionFactoryUtils {
	private static SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 获取sqlSessionFactory
	 * */
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	static {
		try {
			//1.读取config文件
			//创建SqlSessionFactoryBuilder对象
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			InputStream inputstream;
			inputstream = Resources.getResourceAsStream("SqlMapConfig.xml");
			//通过输入流创建SqlSessionFactory
			sqlSessionFactory = ssfb.build(inputstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

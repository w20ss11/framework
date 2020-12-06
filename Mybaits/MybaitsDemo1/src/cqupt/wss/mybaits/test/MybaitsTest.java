package cqupt.wss.mybaits.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cqupt.wss.mybaits.pojo.User;
import cqupt.wss.mybaits.utils.SqlSessionFactoryUtils;

public class MybaitsTest {
	
	@Test
	public void testGetUserById() throws IOException{
		//1.读取config文件
		//创建SqlSessionFactoryBuilder对象
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		InputStream inputstream = Resources.getResourceAsStream("SqlMapConfig.xml");
		//通过输入流创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = ssfb.build(inputstream);
		//创建SqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//执行查询  参数1：sql id 参数2：入参（和sql映射文件一一对应）
		User user = sqlSession.selectOne("user.getUserById", 1);
		//输出结果
		System.out.println(user);
		//释放资源
		sqlSession.close();
	}
	
	@Test
	public void testGetUserByUserName(){
		//通过工具类获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
//		List<User> list = sqlSession.selectList("user.getUserByUserName", "%张%");
		List<User> list = sqlSession.selectList("user.getUserByUserName", "张");
		for(User user:list) {
			System.out.println(user);
		}
		//释放资源
		sqlSession.close();
	}
	
	@Test
	public void testInsertUser(){
		//通过工具类获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = new User();
		user.setUsername("张飞2");
		user.setSex("1");
		user.setBirthday(new Date());
		user.setAddress("重邮");
		sqlSession.insert("user.insertUser",user);
		System.out.println(user);
		//提交事务
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	
	@Test
	public void testInsertUserUUID(){
		//通过工具类获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = new User();
		user.setUsername("张飞3");
		user.setSex("1");
		user.setBirthday(new Date());
		user.setAddress("重邮");
		sqlSession.insert("user.insertUserUUID",user);
		System.out.println(user);
		//提交事务
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	
	@Test
	public void testUpdateUser(){
		//通过工具类获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = new User();
		user.setId(28);
		user.setUsername("张飞update");
		
		sqlSession.update("user.updateUser",user);
		//提交事务
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	
	@Test
	public void testDeleteUser(){
		//通过工具类获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("user.deleteUser",29);
		//提交事务
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
}

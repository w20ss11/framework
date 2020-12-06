package cqupt.wss.mybaits.test;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cqupt.wss.mybaits.Mapper.UserMapper;
import cqupt.wss.mybaits.pojo.User;
import cqupt.wss.mybaits.utils.SqlSessionFactoryUtils;

public class UserMapperTest {

	@Test
	public void testGetUserById() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		//获取接口的代理实现类(此时只有接口，没有实现类)
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserById(10);
		System.out.println(user);
		sqlSession.close();
	}

	@Test
	public void testGetUserByUserName() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.getUserByUserName("张");
		for(User user:list)
			System.out.println(user);
		sqlSession.close();
	}

	@Test
	public void testInsertUser() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setUsername("张飞mapper");
		user.setSex("2");
		user.setBirthday(new Date());
		user.setAddress("重邮mapper");
		
		userMapper.insertUser(user);
		sqlSession.commit();
		sqlSession.close();
	}

}

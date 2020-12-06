package cqupt.wss.mybaits.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cqupt.wss.mybaits.dao.UserDao;
import cqupt.wss.mybaits.dao.impl.UserDaoImpl;
import cqupt.wss.mybaits.pojo.User;

public class UserDaoTest {

	@Test
	public void testGetUserById() {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserById(10);
		System.out.println(user);
	}

	@Test
	public void testGetUserByUserName() {
		UserDao userDao = new UserDaoImpl();
		List<User> list = userDao.getUserByUserName("张");
		for(User user:list)
			System.out.println(user);
	}

	@Test
	public void testInsertUser() {
		UserDao userDao = new UserDaoImpl();
		User user = new User();
		user.setUsername("张飞dao");
		user.setSex("2");
		user.setBirthday(new Date());
		user.setAddress("重邮dao");
		userDao.insertUser(user);
	}

}

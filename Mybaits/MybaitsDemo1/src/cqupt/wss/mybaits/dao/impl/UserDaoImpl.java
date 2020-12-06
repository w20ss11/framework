package cqupt.wss.mybaits.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cqupt.wss.mybaits.dao.UserDao;
import cqupt.wss.mybaits.pojo.User;
import cqupt.wss.mybaits.utils.SqlSessionFactoryUtils;

/**
 * 用户信息持久化实现
 * @author wss
 *
 */
public class UserDaoImpl implements UserDao {

	@Override
	public User getUserById(Integer id) {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		User user = sqlSession.selectOne("user.getUserById", id); 
		sqlSession.close();
		return user;
	}

	@Override
	public List<User> getUserByUserName(String userName) {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		List<User> list = sqlSession.selectList("user.getUserByUserName", userName);
		sqlSession.close();
		return list;
	}

	@Override
	public void insertUser(User user) {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession(true);
		sqlSession.insert("user.insertUser", user);
		sqlSession.close();
	}

}

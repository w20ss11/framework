package cqupt.wss.mybaits.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cqupt.wss.mybaits.Mapper.UserMapper;
import cqupt.wss.mybaits.pojo.Order;
import cqupt.wss.mybaits.pojo.QueryVo;
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
	
	//day02
	
	@Test
	public void testGetUserByQueryVo() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo vo = new QueryVo();
		User user = new User();
		user.setUsername("张");
		vo.setUser(user);
		List<User> list = userMapper.getUserByQueryVo(vo);
		for(User u:list)
			System.out.println(u);
		sqlSession.close();
	}
	
	@Test
	public void testGetUserCount() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Integer userCount = userMapper.getUserCount();
		System.out.println("用户总记录数为："+userCount);
		sqlSession.close();
	}

	@Test
	/*动态sql标签：if where*/
	public void testGetUserByPojo() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("张");
		user.setSex("1");
		List<User> list = userMapper.getUserByPojo(user);
		for(User u:list)
			System.out.println(u);
		sqlSession.close();
	}
	
	@Test
	/*动态sql标签：foreach*/
	public void testGetUserByIds() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo vo = new QueryVo();
		vo.setIds(Arrays.asList(1,10,27,30));
		List<User> list = userMapper.getUserByIds(vo);
		for(User u:list)
			System.out.println(u);
		sqlSession.close();
	}
	
	@Test
	public void testGetUserOrderMap() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.getUserOrderMap();
		for(User user:list) {
			System.out.println(user);
			for(Order order: user.getOrders()) {
				if(order.getId()!=null)
					System.out.println("   此用户对应订单有："+order);
			}
		}
		sqlSession.close();
	}
}

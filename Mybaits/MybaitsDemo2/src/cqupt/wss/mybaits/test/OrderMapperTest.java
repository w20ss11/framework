package cqupt.wss.mybaits.test;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cqupt.wss.mybaits.Mapper.OrderMapper;
import cqupt.wss.mybaits.pojo.Order;
import cqupt.wss.mybaits.pojo.OrderUser;
import cqupt.wss.mybaits.utils.SqlSessionFactoryUtils;

public class OrderMapperTest {

	@Test
	public void testGetOrderList() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		//获取orderMapper代理实现
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> list = orderMapper.getOrderList();
		for(Order order:list) {
			System.out.println(order);
		}
		sqlSession.close();
	}
	
	@Test
	public void testGetOrderListMap() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		//获取orderMapper代理实现
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> list = orderMapper.getOrderListMap();
		for(Order order:list) {
			System.out.println(order);
		}
		sqlSession.close();
	}

	@Test
	public void testGetOrderUser() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		//获取orderMapper代理实现
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		List<OrderUser> list = orderMapper.getOrderUser();
		for(OrderUser orderUser:list) {
			System.out.println(orderUser);
		}
		sqlSession.close();
	}
	
	@Test
	public void testGetOrderUserMap() {
		SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		//获取orderMapper代理实现
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> list = orderMapper.getOrderUserMap();
		for(Order order:list) {
			System.out.println(order);
			System.out.println("   此订单用户为："+order.getUser());
		}
		sqlSession.close();
	}
}

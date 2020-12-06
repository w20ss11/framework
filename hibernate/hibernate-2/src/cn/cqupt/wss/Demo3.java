package cn.cqupt.wss;

import java.util.Arrays;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.domain.LinkMan;
import cn.cqupt.wss.utils.HibernateUtils;

/**
 * day04
 * */
public class Demo3 {
	
	@Test
	// Query
	public void test0() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer where cust_name like ?1";//cust_name 类中属性名
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery(hql);
		query.setParameter(1, "王%");
		List<Customer> list = query.list();
		System.out.println(list.size());
		for(Customer customer : list) {
			System.out.println(customer.toString());
		}
		
		tx.commit();
		session.close();
	}
	
	@Test
	/**
	 * 初始化数据
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// 创建一个客户
		Customer customer = new Customer();
		customer.setCust_name("Cu张");

		for (int i = 1; i <= 5; i++) {
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("Link张" + i);
			linkMan.setCustomer(customer);

			customer.getLinkMans().add(linkMan);

			session.save(linkMan);
		}
		session.save(customer);

		tx.commit();
	}

	@Test
	/**
	 * HQL的简单查询
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// 简单的查询
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery("from Customer");
		List<Customer> list = query.list();

		// sql中支持*号的写法：select * from cst_customer; 但是在HQL中不支持*号的写法。
		/*
		 * Query query = session.createQuery("select * from Customer");// 报错
		 * List<Customer> list = query.list();
		 */

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * 别名查询
	 */
	public void demo3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// 别名的查询
		/*
		 * Query query = session.createQuery("from Customer c"); List<Customer>
		 * list = query.list();
		 */

		@SuppressWarnings("unchecked")//别名 from Customer c
		Query<Customer> query = session.createQuery("select c from Customer c");
		List<Customer> list = query.list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * 排序查询
	 */
	public void demo4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// 排序的查询
		// 默认情况
		// List<Customer> list = session.createQuery("from Customer order by cust_id").list();
		// 设置降序排序 升序使用asc 降序使用desc
		@SuppressWarnings("unchecked")
		List<Customer> list = session.createQuery("from Customer order by cust_id desc").list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * 条件查询
	 */
	public void demo5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// 条件的查询
		// 一、按位置绑定：根据参数的位置进行绑定。
		// 一个条件
		
//		@SuppressWarnings("unchecked")
//		Query<Customer> query = session.createQuery("from Customer where cust_name = ?1"); 
//		query.setParameter(1, "李四"); 
//		List<Customer> list = query.list();


		// 多个条件
		
//		@SuppressWarnings("unchecked")
//		Query<Customer> query = session.createQuery("from Customer where cust_industry = ?1 and cust_name like ?2");
//		query.setParameter(1, "百度"); query.setParameter(2, "李%");
//		List<Customer> list = query.list();
		 

		// 二、按名称绑定
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery("from Customer where cust_industry = :aaa and cust_name like :bbb");
		// 设置参数:
		query.setParameter("aaa", "百度");
		query.setParameter("bbb", "李%");
		List<Customer> list = query.list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * 投影查询
	 */
	public void demo6() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// 投影查询
		// 单个属性

//		@SuppressWarnings("unchecked")
//		List<Object> list = session.createQuery("select c.cust_name from Customer c").list(); 
//		for (Object object :list) {
//			System.out.println(object); 
//		}


		// 多个属性:
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = session.createQuery("select c.cust_name,c.cust_industry from Customer c").list(); 
//		for(Object[] objects : list) {
//			System.out.println(Arrays.toString(objects));
//		}
		
		// 查询多个属性，但是我想封装到对象中。
		@SuppressWarnings("unchecked")
		List<Customer> list = session.createQuery("select new Customer(cust_name,cust_industry) from Customer").list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * 分页查询
	 */
	public void demo7() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// 分页查询
		@SuppressWarnings("unchecked")
		Query<LinkMan> query = session.createQuery("from LinkMan");
		query.setFirstResult(0);//从第1条开始
		query.setMaxResults(5);//每页显示5条
		List<LinkMan> list = query.list();

		for (LinkMan linkMan : list) {
			System.out.println(linkMan);
		}
		tx.commit();
	}

	@Test
	/**
	 * 分组统计查询
	 */
	public void demo8() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// 聚合函数的使用：count(),max(),min(),avg(),sum()
		Object object = session.createQuery("select count(*) from Customer").uniqueResult();
		System.out.println(object);
		// 分组统计：
		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createQuery("select cust_source,count(*) from Customer group by cust_source having count(*) >=2").list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		tx.commit();
	}

	@Test
	/**
	 * HQL的多表查询
	 */
	public void demo9() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		// SQL:SELECT * FROM cst_customer c INNER JOIN cst_linkman l ON
		// c.cust_id = l.lkm_cust_id;
		// HQL:内连接 from Customer c inner join c.linkMans
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = session.createQuery("from Customer c inner join c.linkMans").list(); 
//		for (Object[] objects : list) { 
//			System.out.println(Arrays.toString(objects)); 
//		}

		
		// HQL:迫切内连接 其实就在普通的内连接inner join后添加一个关键字fetch. from Customer c inner
		// join fetch c.linkMans
		@SuppressWarnings("unchecked")
		List<Customer> list = session.createQuery("select distinct c from Customer c inner join fetch c.linkMans")
				.list();// 通知hibernate，将另一个对象的数据封装到该对象中
		for (Customer customer : list) {
			System.out.println(customer);
		}
		
		tx.commit();
	}
}

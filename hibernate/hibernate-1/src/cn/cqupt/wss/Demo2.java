package cn.cqupt.wss;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.utils.HibernateUtils;

public class Demo2 {

	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer  = new Customer();
		customer.setCust_name("王沙沙3");
		customer.setCust_id((long) 3);
		Serializable id = session.save(customer);
		System.out.println(id);
		tx.commit();
		session.close();
	}
	
	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		/**
		 * get方法
		 * 	* 采用的是立即加载，执行到这行代码的时候，就会马上发送SQL语句去查询。
		 *  * 查询后返回是真实对象本身。
		 * 	* 查询一个找不到的对象的时候，返回null
		 * 
		 * load方法
		 * 	* 采用的是延迟加载（lazy懒加载），执行到这行代码的时候，不会发送SQL语句，当真正使用这个对象的时候才会发送SQL语句。
		 *  * 查询后返回的是代理对象。javassist-3.18.1-GA.jar 利用javassist技术产生的代理。
		 *  * 查询一个找不到的对象的时候，返回ObjectNotFoundException
		 */
		// 使用get方法查询
		/*Customer customer = session.get(Customer.class, 100l); // 发送SQL语句
		System.out.println(customer);*/
		
		// 使用load方法查询
		Customer customer = session.load(Customer.class, (long)1);
		System.out.println(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// 修改操作
	public void test3(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		// 直接创建对象，进行修改
		/*Customer customer = new Customer();
		customer.setCust_id(1l);
		customer.setCust_name("王聪");
		session.update(customer);*/
		
		// 先查询，再修改(推荐)
		Customer customer = session.get(Customer.class, (long)1);
		customer.setCust_name("李四");
		session.update(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// 删除操作
	public void test4(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		// 直接创建对象，删除
	/*	Customer customer = new Customer();
		customer.setCust_id(1l);
		session.delete(customer);*/
		
		// 先查询再删除(推荐)--级联删除
		Customer customer = session.get(Customer.class,1l);
		session.delete(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// 保存或更新
	public void demo5(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		/*Customer customer  = new Customer();
		customer.setCust_name("王凤");
		session.saveOrUpdate(customer);*/
		
		Customer customer = new Customer();
		customer.setCust_id(2l);
		customer.setCust_name("李四");
		session.saveOrUpdate(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// 查询所有
	public void demo6(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		// 接收HQL：Hibernate Query Language 面向对象的查询语言
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery("from Customer");
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		
		// 接收SQL：
//		@SuppressWarnings("unchecked")
//		Query<Customer[]> query = session.createSQLQuery("select * from customer");
//		List<Customer[]> list = query.list();
//		for (Object[] objects : list) {
//			System.out.println(Arrays.toString(objects));
//		}
		tx.commit();
		session.close();
	}

	
	@Test
	// 快照区
	public void test7() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		// 使用get方法查询
		Customer customer = session.get(Customer.class, 2l); // 发送SQL语句
		customer.setCust_industry("百度");
		System.out.println(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// Query
	public void test8() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
//		String hql = "from Customer";
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
	// 分页
	public void test9() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer";
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery(hql);
		List<Customer> list = query.list();
		//设置分页 不同数据库 hibernate自动换成对应数据库的分页语句
		query.setFirstResult(0);//从哪开始
		query.setMaxResults(3);//每页显示几条
		for(Customer customer : list) {
			System.out.println(customer.toString());
		}
		
		tx.commit();
		session.close();
	}
	
	@Test
	// criteria
	public void test10() {
		//1.创建session对象
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
        // 获得CriteriaBuilder 用来创建CriteriaQuery
		CriteriaBuilder builder = session.getCriteriaBuilder();
        // 创建CriteriaQuery 参数为返回结果类型
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        // 返会查询表 参数类型为要查询的持久类
		Root<Customer> root = criteria.from(Customer.class);
        // 设置where条件
		String id = "1";
		criteria.where(builder.equal(root.get("cust_id"), id));
        // 创建query 查询
		Query<Customer> query = session.createQuery(criteria);
        // 返回结果
//		Customer customer = query.getSingleResult();
//		System.out.println(customer.toString());
		List<Customer> customers = query.getResultList();
		for(Customer customer:customers) {
			System.out.println(customer.toString());
		}
		tx.commit();
		session.close();
	}
}

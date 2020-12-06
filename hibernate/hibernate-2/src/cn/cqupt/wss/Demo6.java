package cn.cqupt.wss;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.domain.LinkMan;
import cn.cqupt.wss.utils.HibernateUtils;

/**
 * day04 抓取策略 可以在 Customer的set 和  LinkMan的many-to-one 内设置 lazy 和 fetch
 *  这里全是set中的lazy 和 fetch 的属性设置
 *  fetch控制sql的格式， lazy控制发送sql的时机
 * */

public class Demo6 {
	@Test
	/**
	 * 默认情况：
	 */
	public void demo1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 查询1号客户
		Customer customer = session.get(Customer.class, 1l);// 发送一条查询客户的SQL
		System.out.println(customer.getCust_name());
		// 查看1号客户的每个联系人的信息
		for (LinkMan linkMan : customer.getLinkMans()) {// 发送一条根据客户ID查询联系人的SQL
			System.out.println(linkMan.getLkm_name());
		}
		tx.commit();
	}
	
	@Test
	/**
	 * Customer 的set 设置fetch="select" lazy="true"
	 */
	public void demo2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 查询1号客户
		Customer customer = session.get(Customer.class, 1l);// 发送一条查询客户的SQL
		System.out.println(customer.getCust_name());
		// 查看1号客户的每个联系人的信息
		for (LinkMan linkMan : customer.getLinkMans()) {// 发送一条根据客户ID查询联系人的SQL
			System.out.println(linkMan.getLkm_name());
		}
		tx.commit();
	}
	
	@Test
	/**
	 * Customer 的set 设置 fetch="select" lazy="false"
	 */
	public void demo3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 查询1号客户
		Customer customer = session.get(Customer.class, 1l);// 发送两条SQL语句：查询客户的名称，查询客户关联联系人
		System.out.println(customer.getCust_name());
		// 查看1号客户的每个联系人的信息
//		for (LinkMan linkMan : customer.getLinkMans()) {// 
//			System.out.println(linkMan.getLkm_name());
//		}
		
		System.out.println(customer.getLinkMans().size());
		tx.commit();
	}
	
	@Test
	/**
	 * Customer 的set 设置fetch="select" lazy="extra"
	 */
	public void demo4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 查询1号客户
		Customer customer = session.get(Customer.class, 1l);// 发送一条查询1号客户的SQL语句
		System.out.println(customer.getCust_name());
		
		System.out.println(customer.getLinkMans().size());// 发送一条select count() from ...;
		tx.commit();
	}
	
	@Test
	/**
	 *  Customer 的set 设置fetch="join" lazy=失效
	 */
	public void demo5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 查询1号客户
		Customer customer = session.get(Customer.class, 1l);// 发送一条迫切左外连接查询记录
		System.out.println(customer.getCust_name());
		
		System.out.println(customer.getLinkMans().size());// 不发送
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	/**
	 *  Customer 的set 设置fetch="subselect" lazy="true"
	 */
	public void demo6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		//必须查询多个才有效果，不然hibernate会智能的设置sql语句为=1l 而不是IN(1l,2l,3l...)
		List<Customer> list = session.createQuery("from Customer").list();// 发送查询所有客户的SQL
		for (Customer customer : list) {
			System.out.println(customer.getCust_name());
			System.out.println(customer.getLinkMans().size());// 发送一条子查询
		}
		
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	/**
	 *  Customer 的set 设置fetch="subselect" lazy="false"
	 */
	public void demo7(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		List<Customer> list = session.createQuery("from Customer").list();// 发送查询所有客户的SQL，发送一条子查询
		for (Customer customer : list) {
			System.out.println(customer.getCust_name());
			System.out.println(customer.getLinkMans().size());// 
		}
		
		tx.commit();
	}
}

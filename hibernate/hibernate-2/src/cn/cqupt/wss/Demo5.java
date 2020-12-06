package cn.cqupt.wss;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.Test;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.utils.HibernateUtils;

/**
 * day04 
 * */

public class Demo5 {
	
	@Test
	/**
	 * HQL�Ķ���ѯ
	 */
	public void demo0() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// SQL:SELECT * FROM cst_customer c INNER JOIN cst_linkman l ON
		// c.cust_id = l.lkm_cust_id;
		// HQL:������ from Customer c inner join c.linkMans

		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createQuery("from Customer c inner join c.linkMans").list(); 
		for (Object[] objects : list) { 
			System.out.println(Arrays.toString(objects)); 
		}

		// HQL:���������� ��ʵ������ͨ��������inner join�����һ���ؼ���fetch. from Customer c inner
		// join fetch c.linkMans
//		@SuppressWarnings("unchecked")
//		List<Customer> list = session.createQuery("select distinct c from Customer c inner join fetch c.linkMans").list();
//		list.forEach(System.out::println);
		
		tx.commit();
	}
	
	/**
	 * SQL
	 * */
	@Test
	public void demo1(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
//		@SuppressWarnings("unchecked")
//		Query<Customer[]> query = session.createSQLQuery("select * from customer");
//		List<Customer[]> list = query.list();
//		for (Object[] objects : list) {
//			System.out.println(Arrays.toString(objects));
//		}
		
		@SuppressWarnings("unchecked")
		NativeQuery<Customer> query = session.createNativeQuery("select * from customer");
		query.addEntity(Customer.class);
		List<Customer> list = query.list();
		list.forEach(System.out::println);
		
		
		tx.commit();
		session.close();
	}
	
	@Test
	/**
	 * �༶����ӳټ���
	 * * ��<class>�ı�ǩ�����õ�lazy
	 */
	public void demo2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
//		Customer customer = session.load(Customer.class, 1l); 
//		System.out.println(customer);//����ŷ���� (lazy=true)
		
//		Customer customer = session.load(Customer.class, 1l);//����ͷ���ѯ���(lazy=false)
//		System.out.println(customer);
		
		Customer customer = session.load(Customer.class, 1l);
		Hibernate.initialize(customer);//lazy=false ��Ȼ�����﷢���
		System.out.println(customer);
		
		tx.commit();
	}
}

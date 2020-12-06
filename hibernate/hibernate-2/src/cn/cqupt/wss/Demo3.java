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
		
		String hql = "from Customer where cust_name like ?1";//cust_name ����������
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery(hql);
		query.setParameter(1, "��%");
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
	 * ��ʼ������
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// ����һ���ͻ�
		Customer customer = new Customer();
		customer.setCust_name("Cu��");

		for (int i = 1; i <= 5; i++) {
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("Link��" + i);
			linkMan.setCustomer(customer);

			customer.getLinkMans().add(linkMan);

			session.save(linkMan);
		}
		session.save(customer);

		tx.commit();
	}

	@Test
	/**
	 * HQL�ļ򵥲�ѯ
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// �򵥵Ĳ�ѯ
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery("from Customer");
		List<Customer> list = query.list();

		// sql��֧��*�ŵ�д����select * from cst_customer; ������HQL�в�֧��*�ŵ�д����
		/*
		 * Query query = session.createQuery("select * from Customer");// ����
		 * List<Customer> list = query.list();
		 */

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * ������ѯ
	 */
	public void demo3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// �����Ĳ�ѯ
		/*
		 * Query query = session.createQuery("from Customer c"); List<Customer>
		 * list = query.list();
		 */

		@SuppressWarnings("unchecked")//���� from Customer c
		Query<Customer> query = session.createQuery("select c from Customer c");
		List<Customer> list = query.list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * �����ѯ
	 */
	public void demo4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// ����Ĳ�ѯ
		// Ĭ�����
		// List<Customer> list = session.createQuery("from Customer order by cust_id").list();
		// ���ý������� ����ʹ��asc ����ʹ��desc
		@SuppressWarnings("unchecked")
		List<Customer> list = session.createQuery("from Customer order by cust_id desc").list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * ������ѯ
	 */
	public void demo5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// �����Ĳ�ѯ
		// һ����λ�ð󶨣����ݲ�����λ�ý��а󶨡�
		// һ������
		
//		@SuppressWarnings("unchecked")
//		Query<Customer> query = session.createQuery("from Customer where cust_name = ?1"); 
//		query.setParameter(1, "����"); 
//		List<Customer> list = query.list();


		// �������
		
//		@SuppressWarnings("unchecked")
//		Query<Customer> query = session.createQuery("from Customer where cust_industry = ?1 and cust_name like ?2");
//		query.setParameter(1, "�ٶ�"); query.setParameter(2, "��%");
//		List<Customer> list = query.list();
		 

		// ���������ư�
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery("from Customer where cust_industry = :aaa and cust_name like :bbb");
		// ���ò���:
		query.setParameter("aaa", "�ٶ�");
		query.setParameter("bbb", "��%");
		List<Customer> list = query.list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * ͶӰ��ѯ
	 */
	public void demo6() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// ͶӰ��ѯ
		// ��������

//		@SuppressWarnings("unchecked")
//		List<Object> list = session.createQuery("select c.cust_name from Customer c").list(); 
//		for (Object object :list) {
//			System.out.println(object); 
//		}


		// �������:
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = session.createQuery("select c.cust_name,c.cust_industry from Customer c").list(); 
//		for(Object[] objects : list) {
//			System.out.println(Arrays.toString(objects));
//		}
		
		// ��ѯ������ԣ����������װ�������С�
		@SuppressWarnings("unchecked")
		List<Customer> list = session.createQuery("select new Customer(cust_name,cust_industry) from Customer").list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}

	@Test
	/**
	 * ��ҳ��ѯ
	 */
	public void demo7() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// ��ҳ��ѯ
		@SuppressWarnings("unchecked")
		Query<LinkMan> query = session.createQuery("from LinkMan");
		query.setFirstResult(0);//�ӵ�1����ʼ
		query.setMaxResults(5);//ÿҳ��ʾ5��
		List<LinkMan> list = query.list();

		for (LinkMan linkMan : list) {
			System.out.println(linkMan);
		}
		tx.commit();
	}

	@Test
	/**
	 * ����ͳ�Ʋ�ѯ
	 */
	public void demo8() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// �ۺϺ�����ʹ�ã�count(),max(),min(),avg(),sum()
		Object object = session.createQuery("select count(*) from Customer").uniqueResult();
		System.out.println(object);
		// ����ͳ�ƣ�
		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createQuery("select cust_source,count(*) from Customer group by cust_source having count(*) >=2").list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		tx.commit();
	}

	@Test
	/**
	 * HQL�Ķ���ѯ
	 */
	public void demo9() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		// SQL:SELECT * FROM cst_customer c INNER JOIN cst_linkman l ON
		// c.cust_id = l.lkm_cust_id;
		// HQL:������ from Customer c inner join c.linkMans
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = session.createQuery("from Customer c inner join c.linkMans").list(); 
//		for (Object[] objects : list) { 
//			System.out.println(Arrays.toString(objects)); 
//		}

		
		// HQL:���������� ��ʵ������ͨ��������inner join�����һ���ؼ���fetch. from Customer c inner
		// join fetch c.linkMans
		@SuppressWarnings("unchecked")
		List<Customer> list = session.createQuery("select distinct c from Customer c inner join fetch c.linkMans")
				.list();// ֪ͨhibernate������һ����������ݷ�װ���ö�����
		for (Customer customer : list) {
			System.out.println(customer);
		}
		
		tx.commit();
	}
}

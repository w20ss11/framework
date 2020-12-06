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
		customer.setCust_name("��ɳɳ3");
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
		 * get����
		 * 	* ���õ����������أ�ִ�е����д����ʱ�򣬾ͻ����Ϸ���SQL���ȥ��ѯ��
		 *  * ��ѯ�󷵻�����ʵ������
		 * 	* ��ѯһ���Ҳ����Ķ����ʱ�򣬷���null
		 * 
		 * load����
		 * 	* ���õ����ӳټ��أ�lazy�����أ���ִ�е����д����ʱ�򣬲��ᷢ��SQL��䣬������ʹ����������ʱ��Żᷢ��SQL��䡣
		 *  * ��ѯ�󷵻ص��Ǵ������javassist-3.18.1-GA.jar ����javassist���������Ĵ���
		 *  * ��ѯһ���Ҳ����Ķ����ʱ�򣬷���ObjectNotFoundException
		 */
		// ʹ��get������ѯ
		/*Customer customer = session.get(Customer.class, 100l); // ����SQL���
		System.out.println(customer);*/
		
		// ʹ��load������ѯ
		Customer customer = session.load(Customer.class, (long)1);
		System.out.println(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// �޸Ĳ���
	public void test3(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		// ֱ�Ӵ������󣬽����޸�
		/*Customer customer = new Customer();
		customer.setCust_id(1l);
		customer.setCust_name("����");
		session.update(customer);*/
		
		// �Ȳ�ѯ�����޸�(�Ƽ�)
		Customer customer = session.get(Customer.class, (long)1);
		customer.setCust_name("����");
		session.update(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// ɾ������
	public void test4(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		// ֱ�Ӵ�������ɾ��
	/*	Customer customer = new Customer();
		customer.setCust_id(1l);
		session.delete(customer);*/
		
		// �Ȳ�ѯ��ɾ��(�Ƽ�)--����ɾ��
		Customer customer = session.get(Customer.class,1l);
		session.delete(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// ��������
	public void demo5(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		/*Customer customer  = new Customer();
		customer.setCust_name("����");
		session.saveOrUpdate(customer);*/
		
		Customer customer = new Customer();
		customer.setCust_id(2l);
		customer.setCust_name("����");
		session.saveOrUpdate(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	// ��ѯ����
	public void demo6(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		// ����HQL��Hibernate Query Language �������Ĳ�ѯ����
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery("from Customer");
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		
		// ����SQL��
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
	// ������
	public void test7() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		// ʹ��get������ѯ
		Customer customer = session.get(Customer.class, 2l); // ����SQL���
		customer.setCust_industry("�ٶ�");
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
	// ��ҳ
	public void test9() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer";
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery(hql);
		List<Customer> list = query.list();
		//���÷�ҳ ��ͬ���ݿ� hibernate�Զ����ɶ�Ӧ���ݿ�ķ�ҳ���
		query.setFirstResult(0);//���Ŀ�ʼ
		query.setMaxResults(3);//ÿҳ��ʾ����
		for(Customer customer : list) {
			System.out.println(customer.toString());
		}
		
		tx.commit();
		session.close();
	}
	
	@Test
	// criteria
	public void test10() {
		//1.����session����
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
        // ���CriteriaBuilder ��������CriteriaQuery
		CriteriaBuilder builder = session.getCriteriaBuilder();
        // ����CriteriaQuery ����Ϊ���ؽ������
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        // �����ѯ�� ��������ΪҪ��ѯ�ĳ־���
		Root<Customer> root = criteria.from(Customer.class);
        // ����where����
		String id = "1";
		criteria.where(builder.equal(root.get("cust_id"), id));
        // ����query ��ѯ
		Query<Customer> query = session.createQuery(criteria);
        // ���ؽ��
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

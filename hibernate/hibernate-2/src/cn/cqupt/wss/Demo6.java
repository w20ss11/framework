package cn.cqupt.wss;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.domain.LinkMan;
import cn.cqupt.wss.utils.HibernateUtils;

/**
 * day04 ץȡ���� ������ Customer��set ��  LinkMan��many-to-one ������ lazy �� fetch
 *  ����ȫ��set�е�lazy �� fetch ����������
 *  fetch����sql�ĸ�ʽ�� lazy���Ʒ���sql��ʱ��
 * */

public class Demo6 {
	@Test
	/**
	 * Ĭ�������
	 */
	public void demo1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);// ����һ����ѯ�ͻ���SQL
		System.out.println(customer.getCust_name());
		// �鿴1�ſͻ���ÿ����ϵ�˵���Ϣ
		for (LinkMan linkMan : customer.getLinkMans()) {// ����һ�����ݿͻ�ID��ѯ��ϵ�˵�SQL
			System.out.println(linkMan.getLkm_name());
		}
		tx.commit();
	}
	
	@Test
	/**
	 * Customer ��set ����fetch="select" lazy="true"
	 */
	public void demo2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);// ����һ����ѯ�ͻ���SQL
		System.out.println(customer.getCust_name());
		// �鿴1�ſͻ���ÿ����ϵ�˵���Ϣ
		for (LinkMan linkMan : customer.getLinkMans()) {// ����һ�����ݿͻ�ID��ѯ��ϵ�˵�SQL
			System.out.println(linkMan.getLkm_name());
		}
		tx.commit();
	}
	
	@Test
	/**
	 * Customer ��set ���� fetch="select" lazy="false"
	 */
	public void demo3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);// ��������SQL��䣺��ѯ�ͻ������ƣ���ѯ�ͻ�������ϵ��
		System.out.println(customer.getCust_name());
		// �鿴1�ſͻ���ÿ����ϵ�˵���Ϣ
//		for (LinkMan linkMan : customer.getLinkMans()) {// 
//			System.out.println(linkMan.getLkm_name());
//		}
		
		System.out.println(customer.getLinkMans().size());
		tx.commit();
	}
	
	@Test
	/**
	 * Customer ��set ����fetch="select" lazy="extra"
	 */
	public void demo4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);// ����һ����ѯ1�ſͻ���SQL���
		System.out.println(customer.getCust_name());
		
		System.out.println(customer.getLinkMans().size());// ����һ��select count() from ...;
		tx.commit();
	}
	
	@Test
	/**
	 *  Customer ��set ����fetch="join" lazy=ʧЧ
	 */
	public void demo5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);// ����һ�������������Ӳ�ѯ��¼
		System.out.println(customer.getCust_name());
		
		System.out.println(customer.getLinkMans().size());// ������
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	/**
	 *  Customer ��set ����fetch="subselect" lazy="true"
	 */
	public void demo6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		//�����ѯ�������Ч������Ȼhibernate�����ܵ�����sql���Ϊ=1l ������IN(1l,2l,3l...)
		List<Customer> list = session.createQuery("from Customer").list();// ���Ͳ�ѯ���пͻ���SQL
		for (Customer customer : list) {
			System.out.println(customer.getCust_name());
			System.out.println(customer.getLinkMans().size());// ����һ���Ӳ�ѯ
		}
		
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	/**
	 *  Customer ��set ����fetch="subselect" lazy="false"
	 */
	public void demo7(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		List<Customer> list = session.createQuery("from Customer").list();// ���Ͳ�ѯ���пͻ���SQL������һ���Ӳ�ѯ
		for (Customer customer : list) {
			System.out.println(customer.getCust_name());
			System.out.println(customer.getLinkMans().size());// 
		}
		
		tx.commit();
	}
}

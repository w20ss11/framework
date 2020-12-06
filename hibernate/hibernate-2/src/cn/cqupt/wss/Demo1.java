package cn.cqupt.wss;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.domain.LinkMan;
import cn.cqupt.wss.utils.HibernateUtils;


/**
 * day03
 * */
public class Demo1 {

	@Test
	public void test1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ���������ͻ�
		Customer customer1 = new Customer();
		customer1.setCust_name("��1");
		Customer customer2 = new Customer();
		customer2.setCust_name("��2");
		
		// ����������ϵ��
		LinkMan linkMan1 = new LinkMan();
		linkMan1.setLkm_name("���");
		LinkMan linkMan2 = new LinkMan();
		linkMan2.setLkm_name("�绨");
		LinkMan linkMan3 = new LinkMan();
		linkMan3.setLkm_name("����");
		
		// ���ù�ϵ:
		linkMan1.setCustomer(customer1);
		linkMan2.setCustomer(customer1);
		linkMan3.setCustomer(customer2);
		customer1.getLinkMans().add(linkMan1);
		customer1.getLinkMans().add(linkMan2);
		customer2.getLinkMans().add(linkMan3);
		
		// ��������:
		session.save(linkMan1);
		session.save(linkMan2);
		session.save(linkMan3);
		session.save(customer1);
		session.save(customer2);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void test2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("��3");

		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("��3link");
		customer.getLinkMans().add(linkMan);
		linkMan.setCustomer(customer);
		
		// ֻ����һ���Ƿ���ԣ������ԣ���һ��˲ʱ�����쳣���־�̬���������һ��˲ʱ̬����
		// session.save(customer);
		session.save(linkMan);
		
		tx.commit();
	}
	
	@Test
	/**
	 *  �����������²�����
	 *  * ����ͻ�������ϵ�ˣ������������ǿͻ�������Ҫ��Customer.hbm.xml�н�������
	 *  * <set name="linkMans" cascade="save-update">
	 */
	public void test3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("��4");

		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("��4Link");
		
		customer.getLinkMans().add(linkMan);
		linkMan.setCustomer(customer);
		
		session.save(customer);
		
		tx.commit();
	}
	
	@Test
	/**
	 *  �����������²�����
	 *  * ������ϵ�˼����ͻ�����������������ϵ�˶�����Ҫ��LinkMan.hbm.xml�н�������
	 *  * <many-to-one name="customer" cascade="save-update" class="com.itheima.hibernate.domain.Customer" column="lkm_cust_id"/>
	 */
	public void test4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("��6");

		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("��6link");
		
		customer.getLinkMans().add(linkMan);
		linkMan.setCustomer(customer);
		
		session.save(linkMan);
		
		tx.commit();
	}
	
	@Test
	/**
	 * ���Զ���ĵ���
	 * * ǰ�᣺һ�Զ��˫��������cascade="save-update"
	 */
	public void demo5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("��5");

		LinkMan linkMan1 = new LinkMan();
		linkMan1.setLkm_name("��5_link1");
		LinkMan linkMan2 = new LinkMan();
		linkMan2.setLkm_name("��5_link2");
		LinkMan linkMan3 = new LinkMan();
		linkMan3.setLkm_name("��5_link3");
		
		linkMan1.setCustomer(customer);
		customer.getLinkMans().add(linkMan2);
		customer.getLinkMans().add(linkMan3);
		
		// ˫����������cascade
//		session.save(linkMan1); // ���ͼ���insert���  4��
//		session.save(customer); // ���ͼ���insert���  3��
		session.save(linkMan2); // ���ͼ���insert���  1��
		
		tx.commit();
	}
	
	@Test
	/**
	 * ����ɾ����
	 * * ɾ���ͻ�����ɾ����ϵ�ˣ�ɾ���������ǿͻ�����Ҫ��Customer.hbm.xml������
	 * * <set name="linkMans" cascade="delete">
	 */
	public void demo6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// û�����ü���ɾ����Ĭ�����:�޸�����ϵ�˵������ɾ���ͻ�
		/*Customer customer = session.get(Customer.class, 1l);
		session.delete(customer);*/
		
		// ɾ���ͻ���ͬʱɾ����ϵ��
		Customer customer = session.get(Customer.class, 131l);
		session.delete(customer);
		
		tx.commit();
	}
	
	@Test
	/**
	 * ����ɾ����
	 * * ɾ����ϵ�˼���ɾ���ͻ���ɾ������������ϵ�ˣ���Ҫ��LinkMan.hbm.xml������
	 * * <many-to-one name="customer" cascade="delete">
	 */
	public void demo7(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ɾ���ͻ���ͬʱɾ����ϵ��
		LinkMan linkMan = session.get(LinkMan.class, 1l);
		session.delete(linkMan);
		
		tx.commit();
	}
	
	@Test
	/**
	 * ��6����ϵ��ԭ����127�ſͻ������ڸ�Ϊ1�ſͻ�
	 */
	public void demo8(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ6����ϵ��
		LinkMan linkMan = session.get(LinkMan.class, 6l);
		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);
		// ˫��Ĺ���
		linkMan.setCustomer(customer);
		customer.getLinkMans().add(linkMan);
		
		tx.commit();
	}
	
	@Test
	/**
	 * ����cascade��inverse������
	 */
	public void demo9(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("��7");
		
		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("��7link");
		
		customer.getLinkMans().add(linkMan);
		
		// ������Customer.hbm.xml�ϵ�set��������cascade="save-update" inverse="true"
		session.save(customer); // �ͻ�����뵽���ݿ⣬��ϵ��Ҳ����뵽���ݿ⣬�������Ϊnull
		//��Ϊcustomer��inverseΪtrue ���Բ�����������������linkman �����������Ϊinverse����ֻ������customer���������
		tx.commit();
	}
}

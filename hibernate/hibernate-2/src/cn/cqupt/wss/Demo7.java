package cn.cqupt.wss;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.cqupt.wss.domain.LinkMan;
import cn.cqupt.wss.utils.HibernateUtils;

/**
 * day04 ץȡ���� ������ Customer��set ��  LinkMan��many-to-one ������ lazy �� fetch
 *  ����ȫ��many-to-one�е�lazy �� fetch ����������
 * */

public class Demo7 {
	@Test
	/**
	 * Ĭ��ֵ        //�� fetch=select lazy=proxy
	 */
	public void demo1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// ����һ����ѯ��ϵ�����
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// ����һ��select����ѯ��ϵ���������Ŀͻ�
		
		tx.commit();
	}
	
	@Test
	/**
	 * LinkMan �� many-to-one ���� fetch="select" lazy="proxy"
	 */
	public void demo2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// ����һ����ѯ��ϵ�����
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// ����һ��select����ѯ��ϵ���������Ŀͻ�
		
		tx.commit();
	}
	
	@Test
	/**
	 * LinkMan �� many-to-one ����  fetch="select" lazy="false"
	 */
	public void demo3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// ����һ����ѯ��ϵ�����,����һ��select����ѯ��ϵ���������Ŀͻ�
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// 
		
		tx.commit();
	}
	
	@Test
	/**
	 * LinkMan �� many-to-one ����  fetch="join" lazy=ʧЧ
	 */
	public void demo4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// ����һ�������������Ӳ�ѯ��ϵ���������Ŀͻ���
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// 
		
		tx.commit();
	}
}

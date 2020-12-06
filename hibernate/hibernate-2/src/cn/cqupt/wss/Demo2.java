package cn.cqupt.wss;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.cqupt.wss.domain.Role;
import cn.cqupt.wss.domain.User;
import cn.cqupt.wss.utils.HibernateUtils;


/**
 * day03
 * */
public class Demo2 {
	
	@Test
	/**
	 * ���������¼���������û��ͽ�ɫ
	 */
	public void test1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ����2���û�
		User user1 = new User();
		user1.setUser_name("U��1");
		User user2 = new User();
		user2.setUser_name("U��2");
		
		// ����3����ɫ
		Role role1 = new Role();
		role1.setRole_name("R1�з���");
		Role role2 = new Role();
		role2.setRole_name("R2�г���");
		Role role3 = new Role();
		role3.setRole_name("R3���ز�");
		
		// ����˫��Ĺ�����ϵ:
		user1.getRoles().add(role1);
		user1.getRoles().add(role2);
		user2.getRoles().add(role2);
		user2.getRoles().add(role3);
		role1.getUsers().add(user1);
		role2.getUsers().add(user1);
		role2.getUsers().add(user2);
		role3.getUsers().add(user2);
		
		// �������:��Զཨ����˫��Ĺ�ϵ������һ���������ά����
		// һ���Ǳ������������ά��Ȩ��
		session.save(user1);
		session.save(user2);
		session.save(role1);
		session.save(role2);
		session.save(role3);
		
		tx.commit();
	}
	
	
	@Test
	/**
	 * ��Զ�Ĳ�����
	 * * ֻ����һ���Ƿ���ԣ������ԣ�˲ʱ�����쳣
	 */
	public void test2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ����1���û�
		User user1 = new User();
		user1.setUser_name("U��1");
		
		// ����1����ɫ
		Role role1 = new Role();
		role1.setRole_name("R1�з���");
		
		// ����˫��Ĺ�����ϵ:
		user1.getRoles().add(role1);
		role1.getUsers().add(user1);
		// ֻ�����û���
		// session.save(user1);
		session.save(role1);
		
		tx.commit();
	}
	
	@Test
	/**
	 * ��Զ�ļ������棺
	 * * �����û����������ɫ�����û���ӳ���ļ������á�
	 * * ��User.hbm.xml�е�set������ cascade="save-update"
	 */
	public void test3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ����2���û�
		User user1 = new User();
		user1.setUser_name("Li2");
		
		// ����3����ɫ
		Role role1 = new Role();
		role1.setRole_name("R2�з���");
		
		// ����˫��Ĺ�����ϵ:
		user1.getRoles().add(role1);
		role1.getUsers().add(user1);
		// ֻ�����û���
		session.save(user1);
		
		tx.commit();
	}
	
	/**
	 * ��Զ�ļ������棺
	 * * �����ɫ���������û����ڽ�ɫ��ӳ���ļ������á�
	 * * ��Role.hbm.xml�е�set������ cascade="save-update"
	 */
	@Test
	public void demo4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ����2���û�
		User user1 = new User();
		user1.setUser_name("U1��");
		
		// ����3����ɫ
		Role role1 = new Role();
		role1.setRole_name("R1���ز�");
		
		// ����˫��Ĺ�����ϵ:
		user1.getRoles().add(role1);
		role1.getUsers().add(user1);
		// ֻ�����û���
		session.save(role1);
		
		tx.commit();
	}
	
	/**
	 * ��Զ�ļ���ɾ����
	 * * ɾ���û�����ɾ����ɫ
	 * * ��User.hbm.xml�е�set������ cascade="delete"
	 */
	@Test
	public void demo5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ1���û�:
		User user  = session.get(User.class, 7l);
		session.delete(user);
		
		tx.commit();
	}
	
	/**
	 * ��Զ�ļ���ɾ����
	 * * ɾ����ɫ����ɾ���û�
	 * * ��Role.hbm.xml�е�set������ cascade="delete"
	 */
	@Test
	public void demo6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��ѯ4�Ž�ɫ:
		Role role  = session.get(Role.class, 4l);
		session.delete(role);
		
		tx.commit();
	}
	
	@Test
	/**
	 * ���û�ѡ���ɫ
	 */
	public void demo7(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��4���û���ѡ5�Ž�ɫ
		// ��ѯ4���û�
		User user  = session.get(User.class, 4l);
		// ��ѯ5�Ž�ɫ
		Role role = session.get(Role.class, 5l);
		user.getRoles().add(role);
		
		tx.commit();
	}
	
	@Test
	/**
	 * ���û���ѡ��ɫ
	 */
	public void demo8(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��9���û���ԭ�е�10�Ž�ɫ��Ϊ9�Ž�ɫ
		// ��ѯ9���û�
		User user  = session.get(User.class, 9l);
		// ��ѯ9 10�Ž�ɫ
		Role role10 = session.get(Role.class, 10l);
		Role role9 = session.get(Role.class, 9l);
//		System.out.println(user.toString());
		user.getRoles().remove(role10);
		user.getRoles().add(role9);
//		System.out.println(user.toString());
		tx.commit();
	}
	
	@Test
	/**
	 * ���û���ѡ��ɫ
	 */
	public void demo9(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// ��2���û�ɾ��1�Ž�ɫ
		// ��ѯ2���û�
		User user  = session.get(User.class, 2l);
		// ��ѯ2�Ž�ɫ
		Role role = session.get(Role.class, 1l);
		user.getRoles().remove(role);
		
		tx.commit();
	}
}

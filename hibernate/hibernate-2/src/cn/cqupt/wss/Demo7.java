package cn.cqupt.wss;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.cqupt.wss.domain.LinkMan;
import cn.cqupt.wss.utils.HibernateUtils;

/**
 * day04 抓取策略 可以在 Customer的set 和  LinkMan的many-to-one 内设置 lazy 和 fetch
 *  这里全是many-to-one中的lazy 和 fetch 的属性设置
 * */

public class Demo7 {
	@Test
	/**
	 * 默认值        //即 fetch=select lazy=proxy
	 */
	public void demo1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// 发送一条查询联系人语句
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// 发送一条select语句查询联系人所关联的客户
		
		tx.commit();
	}
	
	@Test
	/**
	 * LinkMan 的 many-to-one 设置 fetch="select" lazy="proxy"
	 */
	public void demo2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// 发送一条查询联系人语句
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// 发送一条select语句查询联系人所关联的客户
		
		tx.commit();
	}
	
	@Test
	/**
	 * LinkMan 的 many-to-one 设置  fetch="select" lazy="false"
	 */
	public void demo3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// 发送一条查询联系人语句,发送一条select语句查询联系人所关联的客户
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// 
		
		tx.commit();
	}
	
	@Test
	/**
	 * LinkMan 的 many-to-one 设置  fetch="join" lazy=失效
	 */
	public void demo4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		LinkMan linkMan = session.get(LinkMan.class, 6l);// 发送一条迫切左外连接查询联系人所关联的客户。
		System.out.println(linkMan.getLkm_name());
		System.out.println(linkMan.getCustomer().getCust_name());// 
		
		tx.commit();
	}
}

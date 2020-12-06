package cn.cqupt.wss;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.cqupt.wss.domain.Customer;

public class Demo1 {
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = new Customer();//
		
		customer.setCust_name("Tom");
//		Long cust_id = (long) 123;
//		customer.setCust_id(cust_id);

		session.save(customer);//������

		transaction.commit();
		session.close();
		sessionFactory.close();
	}



}

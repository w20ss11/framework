package cn.cqupt.wss.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.cqupt.wss.dao.CustomerDao;
import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.utils.HibernateUtils;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> find() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Customer> list = session.createQuery("from Customer").list();
		
		tx.commit();
		return list;
	}
	
	@Override
	// 保存客户的方法
	public void save(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		session.save(customer);
		
		tx.commit();
	}

}

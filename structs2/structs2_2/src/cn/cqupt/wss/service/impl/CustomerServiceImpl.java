package cn.cqupt.wss.service.impl;

import java.util.List;

import cn.cqupt.wss.dao.CustomerDao;
import cn.cqupt.wss.dao.impl.CustomerDaoImpl;
import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.service.CustomerService;


public class CustomerServiceImpl implements CustomerService {

	@Override
	public List<Customer> find() {
		// 调用DAO:
		CustomerDao customerDao = new CustomerDaoImpl();
		return customerDao.find();
	}

	@Override
	// 业务层保存客户的方法
	public void save(Customer customer) {
		// 调用DAO:
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.save(customer);
		
	}
}

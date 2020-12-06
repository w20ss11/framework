package cn.cqupt.wss.dao;

import java.util.List;

import cn.cqupt.wss.domain.Customer;


public interface CustomerDao {

	List<Customer> find();
	
	void save(Customer customer);

}

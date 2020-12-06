package cn.cqupt.wss.service;

import java.util.List;

import cn.cqupt.wss.domain.Customer;


public interface CustomerService {
	public List<Customer> find();	
	public void save(Customer customer);	
}

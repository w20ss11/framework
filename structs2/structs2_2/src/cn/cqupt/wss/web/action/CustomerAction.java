package cn.cqupt.wss.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.service.CustomerService;
import cn.cqupt.wss.service.impl.CustomerServiceImpl;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 模型驱动使用的对象
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	/**
	 *  查询客户列表的方法
	 * @return
	 */
	public String find(){
		// 调用业务层
		CustomerService customerService = new CustomerServiceImpl();
		List<Customer> list = customerService.find();
		// 页面跳转
		ServletActionContext.getRequest().setAttribute("list", list);
		return "findSuccess";
	}
	
	/**
	 * 跳转到添加页面的方法
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * 保存客户的方法
	 */
	public String save(){
		// 接收数据
		// 封装数据
		// 调用业务层
		CustomerService customerService = new CustomerServiceImpl();
		customerService.save(customer);
		// 页面跳转
		return "saveSuccess";
	}


}


package cn.cqupt.wss.demo1;

import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 访问Servlet的API方式一：完全解耦合的方式
 * 不能获取session对象，能获取数据
 * day02
 */
public class RequestDemo1 extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// 一、接收参数：
		// 利用Struts2中的对象ActionContext对象.
		ActionContext context = ActionContext.getContext();
		// 调用ActionContext中的方法。
		// 类似于Map<String,String[]> request.getParameterMap();
		Map<String, Parameter> map = context.getParameters();
		for (String key : map.keySet()) {
			Parameter values = map.get(key);
			System.out.println(key+"    "+values);
		}
		
		// 二、向域对象中存入数据 context.getSession():并不是获取session对象，而是将session对象的数据封装到一个map中
		context.put("reqName", "reqValue1");// 相当于request.setAttribute();
		context.getSession().put("sessName", "sessValue1"); // 相当于session.setAttribute();
		context.getApplication().put("appName", "appValue1"); // 相当于application.setAttribute();
		
		return SUCCESS;
	}
}

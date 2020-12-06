package cn.cqupt.wss.demo1;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 访问Servlet的API的方式二：原生的方式
 *
 */
public class RequestDemo2 extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// 一、接收数据
		// 直接获得request对象，通过ServletActionContext
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			String[] values = map.get(key);
			System.out.println(key+"    "+Arrays.toString(values));
		}
		
		// 二、向域对象中保存数据
		// 向request中保存数据:
		request.setAttribute("reqName", "reqValue2");
		// 向session中保存数据
		request.getSession().setAttribute("sessName", "sessValue2");
		// 向application中保存数据
		ServletActionContext.getServletContext().setAttribute("appName", "appValue2");
		return SUCCESS;
	}
}

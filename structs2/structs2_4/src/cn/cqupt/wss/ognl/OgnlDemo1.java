package cn.cqupt.wss.ognl;

import org.junit.jupiter.api.Test;

import cn.cqupt.wss.domain.User;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * day03
 * ognl在java环境下的使用
 * */

public class OgnlDemo1 {
	
	/**
	 * ognl调用对象的方法
	 * @throws OgnlException 
	 * */
	@Test
	public void demo1() throws OgnlException{
		OgnlContext context = new OgnlContext();
		Object root = context.getRoot();
		Object obj = Ognl.getValue("'hello'.length()", context, root);
		System.out.println(obj);
	}
	
	/**
	 * ognl调用对象的静态方法
	 * @throws OgnlException 
	 * */
	@Test
	public void demo2() throws OgnlException{
		OgnlContext context = new OgnlContext();
		Object root = context.getRoot();
		Object obj = Ognl.getValue("@java.lang.Math@random()", context, root);
		System.out.println(obj);
	}
	
	/**
	 * 访问root中数据
	 * @throws OgnlException 
	 * */
	@Test
	public void demo3() throws OgnlException{
		OgnlContext context = new OgnlContext();
		User user = new User("wss", "123456");
		context.setRoot(user);
		
		Object root = context.getRoot();
		Object username = Ognl.getValue("username", context, root);
		Object password = Ognl.getValue("password", context, root);
		System.out.println(username+"  "+password);
	}
	
	/**
	 * 访问context中数据
	 * @throws OgnlException 
	 * */
	@Test
	public void demo4() throws OgnlException{
		OgnlContext context = new OgnlContext();
		Object root = context.getRoot();
		context.put("name", "namevalue");
		Object obj = Ognl.getValue("#name", context, root);
		System.out.println(obj);
	}
	
	
}

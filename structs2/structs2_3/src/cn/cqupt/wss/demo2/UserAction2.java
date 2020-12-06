package cn.cqupt.wss.demo2;

import com.opensymphony.xwork2.ActionSupport;

import cn.cqupt.wss.domain.User;
/**
 * 数据封装的方式二：属性驱动-在页面中提供表达式的方式
 *
 */
public class UserAction2 extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 提供一个User对象:
	private User user;
	// 提供user的set和get方法：一定要提供get方法。
	// 因为拦截器完成数据封装，需要创建User对象。通过get方法可以获得同一个对象，将数据封装到同一个对象中。
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String execute() throws Exception {
		System.out.println(user);
		return NONE;
	}

}

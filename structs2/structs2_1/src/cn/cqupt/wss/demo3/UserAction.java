package cn.cqupt.wss.demo3;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1147411293869539529L;
	public String find(){
		System.out.println("查询用户...");
		return NONE;
	}
	public String update(){
		System.out.println("修改用户...");
		return NONE;
	}
	public String delete(){
		System.out.println("删除用户...");
		return NONE;
	}
	public String save(){
		System.out.println("保存用户...");
		return NONE;
	}
}

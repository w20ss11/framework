package cn.cqupt.wss.beans;

public class HelloWorld {
	private String name;
	
	public void setName(String name) {
		this.name = name;
		System.out.println("HelloWorld's setName method,name id "+name);
	}
	
	public void hello() {
		System.out.println("hello "+name);
	}
	
	public HelloWorld(){
		System.out.println("HelloWorld's constructor");
	}
}

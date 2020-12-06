package cn.cqupt.wss.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		//创建对象和为name属性赋值可用spring完成
//		HelloWorld helloWorld = new HelloWorld();
//		helloWorld.setName("wss");
		
		//1.创建Spring的IOC容易对象，首先对IOC容器初始化
		//BeanFactory 和 ApplicationContext
		//ApplicationContext有两个主要实现类：ClassPathXmlApplicationContext 和 FileSystemXmlApplicationContext
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.从IOC容器中获取Bean实例
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");//通过id来读取IOC容器中bean
//		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);//只有IOC中只有一个HelloWorld的bean时可用
		
		//3.调用hello方法
		helloWorld.hello();
		
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		
		Car car2 = (Car) ctx.getBean("car2");
		System.out.println(car2);
	}

}

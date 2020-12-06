package cn.cqupt.wss.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		//���������Ϊname���Ը�ֵ����spring���
//		HelloWorld helloWorld = new HelloWorld();
//		helloWorld.setName("wss");
		
		//1.����Spring��IOC���׶������ȶ�IOC������ʼ��
		//BeanFactory �� ApplicationContext
		//ApplicationContext��������Ҫʵ���ࣺClassPathXmlApplicationContext �� FileSystemXmlApplicationContext
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.��IOC�����л�ȡBeanʵ��
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");//ͨ��id����ȡIOC������bean
//		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);//ֻ��IOC��ֻ��һ��HelloWorld��beanʱ����
		
		//3.����hello����
		helloWorld.hello();
		
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		
		Car car2 = (Car) ctx.getBean("car2");
		System.out.println(car2);
	}

}

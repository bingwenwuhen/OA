package cn.com.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SessionFactoryTest {
	@Test
	public void sessionFactoryTest(){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		SessionFactory sessionFactory=(SessionFactory) context.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
}

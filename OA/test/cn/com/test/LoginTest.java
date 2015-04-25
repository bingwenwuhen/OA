package cn.com.test;

import org.junit.Test;

import cn.com.dao.LoginDao;

public class LoginTest extends BaseSpring {

	@Test
	public void  testLogin(){
		LoginDao loginDao=(LoginDao) context.getBean("loginDao");
		loginDao.getUserByUAndP("xixuan", "xiaxuan");
	}
}

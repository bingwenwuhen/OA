package cn.com.test;

import org.junit.Test;

import cn.com.dao.UserDao;

public class UserTest extends BaseSpring{
	@Test
	public void test(){
		UserDao userDao=(UserDao) context.getBean("userDao");
		userDao.getUserByUsername("xiaxuan");
	}
}

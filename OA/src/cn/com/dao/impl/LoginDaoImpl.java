package cn.com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.com.dao.LoginDao;
import cn.com.domain.User;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Override
	public User getUserByUAndP(String username, String password) {
		List<User> userList=this.hibernateTemplate.find("from User where username=? and password=?",new Object[]{username,password});
		if(userList.size()!=0){
			return userList.get(0);
		}else{
			return null;
		}
	}

}

package cn.com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dao.LoginDao;
import cn.com.domain.User;
import cn.com.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name="loginDao")
	private  LoginDao loginDao;
	@Override
	public User getUserByUAndP(String username, String password) {
		return this.loginDao.getUserByUAndP(username, password);
	}

}

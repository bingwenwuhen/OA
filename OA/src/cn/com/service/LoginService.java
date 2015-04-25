package cn.com.service;

import cn.com.domain.User;

public interface LoginService {
	public User getUserByUAndP(String username, String password);
}

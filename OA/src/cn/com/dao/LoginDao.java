package cn.com.dao;

import cn.com.domain.User;

public interface LoginDao {
	public User  getUserByUAndP(String username,String password);
}

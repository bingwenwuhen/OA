package cn.com.service;

import java.io.Serializable;
import java.util.Collection;

import cn.com.domain.User;

public interface UserService {
	public Collection<User> getAllUser();
	
	public void saveUser(User user);
	
	public void deleteUserById(Serializable id);
	
	public User getUserById(Serializable id);
	
	public void updateUser(User user);
}

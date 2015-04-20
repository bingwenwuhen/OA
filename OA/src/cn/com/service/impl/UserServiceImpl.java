package cn.com.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dao.UserDao;
import cn.com.domain.User;
import cn.com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userDao;

	public Collection<User> getAllUser() {
		//return this.userDao.getAllEntry();
		return this.userDao.getUsers();
	}

	@Transactional(readOnly=false)
	public void saveUser(User user) {
		this.userDao.saveEntry(user);
	}

	@Transactional(readOnly=false)
	public void deleteUserById(Serializable id) {
		this.userDao.deleteEntry(id);
	}

	public User getUserById(Serializable id) {
		return (User) this.userDao.getEntryById(id);
	}

	@Transactional(readOnly=false)
	public void updateUser(User user) {
		this.userDao.updateEntry(user);
	}

}

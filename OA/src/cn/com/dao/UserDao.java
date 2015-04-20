package cn.com.dao;

import java.util.Collection;

import cn.com.dao.base.BaseDao;
import cn.com.domain.User;

public interface UserDao<T> extends BaseDao<T> {
	
	public Collection<User> getUsers();
	
	
}

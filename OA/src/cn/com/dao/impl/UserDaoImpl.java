package cn.com.dao.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dao.UserDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User> {

	@Override
	public Collection<User> getUsers() {
		List<User> userList=this.hibernateTemplate.find("from User u left join fetch u.department d left join fetch u.posts p");
		return new HashSet<User>(userList);
	}

}

package cn.com.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.dao.DepartmentDao;
import cn.com.domain.Department;
import cn.com.domain.User;
import cn.com.utils.DeleteMode;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	@Override
	public void saveDepartment(Department department) {
		this.getHibernateTemplate().save(department);
	}

	@Override
	public void updateDepartment(Department department) {
		this.getHibernateTemplate().update(department);
	}

	@Override
	public void deleteDepartmentById(Serializable id,String deleteMode) {
		Department department=getDepartmentById(id);
		if("del".equals(deleteMode)){			//单表的删除
			this.getHibernateTemplate().delete(department);
		}else if("del_pre_release".equals(deleteMode)){
			//删除之前先解除关系
			Set<User> users=department.getUsers();
			for(User user:users){
				user.setDepartment(null);
			}
			this.getHibernateTemplate().delete(department);
		}else{
			//级联删除
			department.setUsers(null);
			this.getHibernateTemplate().delete(department);
		}
	}

	@Override
	public Collection<Department> getAllDepartment() {
		return this.getHibernateTemplate().find("from Department");
	}

	@Override
	public Department getDepartmentById(Serializable id) {
		return (Department) this.getHibernateTemplate().get(Department.class, id);
	}

}

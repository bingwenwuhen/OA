package cn.com.dao;

import java.io.Serializable;
import java.util.Collection;

import cn.com.dao.base.BaseDao;
import cn.com.domain.Department;
import cn.com.utils.DeleteMode;

public interface DepartmentDao<T> extends BaseDao<T> {
	public void saveDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public void deleteDepartmentById(Serializable id,String deleteMode);
	
	public Collection<Department> getAllDepartment();
	
	public Department getDepartmentById(Serializable id);
}

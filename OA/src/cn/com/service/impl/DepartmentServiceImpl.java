package cn.com.service.impl;

import java.io.Serializable;
import java.util.Collection;

import cn.com.dao.DepartmentDao;
import cn.com.domain.Department;
import cn.com.service.DepartmentService;
import cn.com.utils.DeleteMode;

public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDao departmentDao;
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public void saveDepartment(Department department) {
		this.departmentDao.saveEntry(department);
	}

	@Override
	public void updateDepartment(Department department) {
		this.departmentDao.updateDepartment(department);
	}

	@Override
	public void deleteDepartmentById(Serializable id,String deleteMode) {
		this.departmentDao.deleteDepartmentById(id,deleteMode);
	}

	@Override
	public Collection<Department> getAllDepartment() {
		return this.departmentDao.getAllDepartment();
	}

	@Override
	public Department getDepartmentById(Serializable id) {
		return this.departmentDao.getDepartmentById(id);
	}

}

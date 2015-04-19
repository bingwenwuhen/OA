package cn.com.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dao.DepartmentDao;
import cn.com.domain.Department;
import cn.com.service.DepartmentService;
import cn.com.utils.DeleteMode;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	

	@Override
	public void saveDepartment(Department department) {
		this.departmentDao.saveEntry(department);
	}

	@Override
	public void updateDepartment(Department department) {
		this.departmentDao.updateEntry(department);
	}

	@Override
	public void deleteDepartmentById(Serializable id,String deleteMode) {
		//this.departmentDao.deleteDepartmentById(id,deleteMode);
		this.departmentDao.deleteEntry(id);
	}

	public Collection<Department> getAllDepartment() {
		return this.departmentDao.getAllEntry();
	}

	public Department getDepartmentById(Serializable id) {
		return (Department) this.departmentDao.getEntryById(id);
	}

}

package cn.com.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.com.dao.DepartmentDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.Department;
import cn.com.domain.User;
import cn.com.utils.DeleteMode;
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao<Department> {

}

package cn.com.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.dao.PrivilegeDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.Menuitem;

@Repository("privilegeDao")
public class PrivilegeDaoImpl  extends BaseDaoImpl<Menuitem> implements PrivilegeDao<Menuitem>{
	
}

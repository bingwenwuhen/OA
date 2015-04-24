package cn.com.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dao.PrivilegeDao;
import cn.com.domain.Menuitem;
import cn.com.service.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {
	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;
	
	@Override
	public Collection<Menuitem> getPrivileges() {
		return this.privilegeDao.getAllEntry();
	}

}

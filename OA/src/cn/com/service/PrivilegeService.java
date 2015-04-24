package cn.com.service;

import java.util.Collection;

import cn.com.domain.Menuitem;

public interface PrivilegeService {
	public Collection<Menuitem> getPrivileges(Long uid);
}

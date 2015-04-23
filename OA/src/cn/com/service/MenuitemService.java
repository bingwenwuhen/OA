package cn.com.service;

import java.util.Collection;

import cn.com.domain.Menuitem;

public interface MenuitemService {
	public Collection<Menuitem> getAllMenuitem();
	
	public Collection<Menuitem> getMenuitemsByPid(Long pid);
}

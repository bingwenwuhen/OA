package cn.com.service.impl;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dao.MenuitemDao;
import cn.com.domain.Menuitem;
import cn.com.service.MenuitemService;
@Service("menuitemService")
public class MenuitemServiceImpl implements MenuitemService {
	
	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;
	public Collection<Menuitem> getAllMenuitem() {
		return this.menuitemDao.getAllEntry();
	}
	@Override
	public Collection<Menuitem> getMenuitemsByPid(Long pid) {
		return this.menuitemDao.getMenuitemsByPid(pid);
	}
	@Override
	public Set<Menuitem> getMenuitemsByIDS(Long[] ids) {
		return this.menuitemDao.getMenuitemsByIDS(ids);
	}
	@Override
	public Collection<Menuitem> getMenuitemsByUser() {
		return this.menuitemDao.getMenuitemsByUser();
	}

}

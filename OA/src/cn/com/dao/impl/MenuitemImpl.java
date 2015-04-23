package cn.com.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import cn.com.dao.MenuitemDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.Menuitem;

@Repository("menuitemDao")
public class MenuitemImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao<Menuitem> {

	@Override
	public Collection<Menuitem> getMenuitemsByPid(Long pid) {
		return this.hibernateTemplate.find("from Menuitem where pid=?",pid);
	}

}

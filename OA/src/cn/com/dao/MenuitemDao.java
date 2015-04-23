package cn.com.dao;

import java.util.Collection;

import cn.com.dao.base.BaseDao;
import cn.com.domain.Menuitem;

public interface MenuitemDao<T> extends BaseDao<T> {
	public Collection<Menuitem> getMenuitemsByPid(Long pid);
}

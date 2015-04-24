package cn.com.dao;

import java.util.Collection;

import cn.com.dao.base.BaseDao;
import cn.com.domain.Menuitem;

public interface PrivilegeDao<T> extends BaseDao<T> {
	public Collection<Menuitem> getMenuitemByUID(Long uid);
}

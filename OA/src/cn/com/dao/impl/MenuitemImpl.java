package cn.com.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.dao.MenuitemDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.Menuitem;

@Repository("menuitemDao")
public class MenuitemImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao<Menuitem> {

}

package cn.com.dao.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.com.dao.PrivilegeDao;
import cn.com.dao.UserDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.Menuitem;
import cn.com.domain.User;

@Repository("privilegeDao")
public class PrivilegeDaoImpl  extends BaseDaoImpl<Menuitem> implements PrivilegeDao<Menuitem>{
	
	@Resource(name="userDao")
	private UserDao userDao;

	@Override
	public Collection<Menuitem> getMenuitemByUID(Long uid) {
		/*
		 * 如果是admin则把所有的菜单项的checked设置为true
		 * 如果不是admin，则先遍历所有的菜单项，再遍历用户能访问到的菜单项，然后把所有的菜单项中，用户能够访问到的checked设置为true
		 */
		User user=(User) this.userDao.getEntryById(uid);
		Collection<Menuitem> menuitemList=this.getAllEntry();
		Collection<Menuitem> menuitems=this.hibernateTemplate.find("from Menuitem m inner join fetch m.users u where u.uid=?",uid);
		if("admin".equals(user.getUsername())){
			for(Menuitem menuitem:menuitemList){
				menuitem.setChecked(true);
			}
		}else{
			for(Menuitem menuitem:menuitemList){
				for(Menuitem menuitem2:menuitems){
					if(menuitem.getMid().longValue()==menuitem2.getMid().longValue()){
						menuitem.setChecked(true);
					}
				}
			}
		}
		return menuitemList;
	}
	
}

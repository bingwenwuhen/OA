package cn.com.dao.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Override
	public Set<Menuitem> getMenuitemsByIDS(Long[] ids) {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("from Menuitem ");
		stringBuffer.append(" where mid in (");
		for(int i=0;i<ids.length;i++){
			if(i<ids.length-1){
				stringBuffer.append(ids[i]+",");
			}
			else{
				stringBuffer.append(ids[i]+")");
			}
		}
		
		List<Menuitem> menuitemList=this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<Menuitem>(menuitemList);
	}

}

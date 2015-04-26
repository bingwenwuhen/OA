package cn.com.dao;

import java.util.Collection;

import cn.com.dao.base.BaseDao;
import cn.com.domain.Kynamic;
import cn.com.domain.Version;

public interface KynamicDao<T> extends BaseDao<T> {
	public Kynamic getKynamicByName(String name);
	
	public Collection<Kynamic> getSiblingNodes(Long kid);
	
	public Kynamic getParentNode(Long kid);
	
	public void updateNode(Kynamic kynamic);
	
	public Collection<Version> getVersionByKid(Long kid);
	
}

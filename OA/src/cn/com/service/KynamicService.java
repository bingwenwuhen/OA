package cn.com.service;

import java.util.Collection;

import cn.com.domain.Kynamic;

public interface KynamicService {
	public Collection<Kynamic> getAllKynamic();
	
	public void saveKynamic(Kynamic kynamic);
	
	public boolean exsitName(String name);
	
	public void deleteKynamicByID(Long kid);
	
	public Collection<Kynamic> getSiblingNodes(Long kid);
	
	public Kynamic getParentNode(Long kid);
}

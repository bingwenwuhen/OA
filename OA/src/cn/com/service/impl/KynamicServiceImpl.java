package cn.com.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dao.KynamicDao;
import cn.com.domain.Kynamic;
import cn.com.service.KynamicService;

@Service("kynamicService")
public class KynamicServiceImpl implements KynamicService {
	
	@Resource(name="kynamicDao")
	private KynamicDao kynamicDao;
	
	public Collection<Kynamic> getAllKynamic() {
		return this.kynamicDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void saveKynamic(Kynamic kynamic) {
		this.kynamicDao.saveEntry(kynamic);
	}

	@Override
	public boolean exsitName(String name) {
		Kynamic kynamic=this.kynamicDao.getKynamicByName(name);
		return kynamic!=null?true:false;
	}

	@Transactional(readOnly=false)
	public void deleteKynamicByID(Long kid) {
		this.kynamicDao.deleteEntry(kid);
	}

	@Override
	public Collection<Kynamic> getSiblingNodes(Long kid) {
		return this.kynamicDao.getSiblingNodes(kid);
	}

	@Override
	public Kynamic getParentNode(Long kid) {
		return this.kynamicDao.getParentNode(kid);
	}



}

package cn.com.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dao.KynamicDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.Kynamic;
@Repository("kynamicDao")
public class KynamicDaoImpl extends BaseDaoImpl<Kynamic> implements KynamicDao<Kynamic> {

	@Override
	public Kynamic getKynamicByName(String name) {
		List<Kynamic> kynamicList=this.hibernateTemplate.find("from Kynamic where name=?",name);
		if(kynamicList.size()==0){
			return null;
		}else{
			return kynamicList.get(0);
		}
	}

	@Override
	public Collection<Kynamic> getSiblingNodes(Long kid) {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("from Kynamic ");
		stringBuffer.append(" where pid in (");
		stringBuffer.append("select pid from Kynamic where kid=?)");
		return this.hibernateTemplate.find(stringBuffer.toString(),kid);
	}

	@Override
	public Kynamic getParentNode(Long kid) {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("from Kynamic ");
		stringBuffer.append(" where kid=(");
		stringBuffer.append("select pid from Kynamic where kid=?");
		List<Kynamic> kynamicList= this.hibernateTemplate.find(stringBuffer.toString(),kid);
		return kynamicList.get(0);
	}

}

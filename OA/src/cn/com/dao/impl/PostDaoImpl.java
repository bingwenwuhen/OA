package cn.com.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.com.dao.PostDao;
import cn.com.dao.base.impl.BaseDaoImpl;
import cn.com.domain.Post;

@Repository("postDao")
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao<Post> {

	public Set<Post> getPostsByIDS(Long[] pids) {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("from Post ");
		stringBuffer.append(" where pid in (");
		for(int i=0;i<pids.length;i++){
			if(i<pids.length-1){
				stringBuffer.append(pids[i]+",");
			}else{
				stringBuffer.append(pids[i]+")");
			}
		}
		List<Post> postList=this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<Post>(postList);
	}

}

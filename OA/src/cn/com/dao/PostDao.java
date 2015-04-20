package cn.com.dao;

import java.util.Set;

import cn.com.dao.base.BaseDao;
import cn.com.domain.Post;

public interface PostDao<T> extends BaseDao<T> {
	public Set<Post> getPostsByIDS(Long[] pids);
}

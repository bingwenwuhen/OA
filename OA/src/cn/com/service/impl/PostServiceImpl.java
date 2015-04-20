package cn.com.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dao.PostDao;
import cn.com.domain.Post;
import cn.com.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {
	@Resource(name="postDao")
	private PostDao postDao;

	@Override
	public Collection<Post> getAllPost() {
		return this.postDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void updatePost(Post post) {
		this.postDao.updateEntry(post);
	}

	@Transactional(readOnly=false)
	public void savePost(Post post) {
		this.postDao.saveEntry(post);
	}

	@Transactional(readOnly=false)
	public void deletePost(Serializable id) {
		this.postDao.deleteEntry(id);
	}

	@Override
	public Post getPostById(Serializable id) {
		return (Post) this.postDao.getEntryById(id);
	}

	@Override
	public Set<Post> getPostsByIDS(Long[] pids) {
		return this.postDao.getPostsByIDS(pids);
	}

}

package cn.com.test;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import cn.com.domain.Department;
import cn.com.domain.Post;
import cn.com.domain.User;
import cn.com.service.DepartmentService;
import cn.com.service.PostService;
import cn.com.service.UserService;
import cn.com.service.impl.DepartmentServiceImpl;
import cn.com.service.impl.PostServiceImpl;
import cn.com.service.impl.UserServiceImpl;

public class testSaveUser {
	@Test
	public void saveUser(){
		User user=new User();
		//一般属性的赋值
		user.setUsername("xiaxuan");
		user.setSex("male");
		//建立department与user之间的关系
		DepartmentService departmentService=new DepartmentServiceImpl();
		Department department=departmentService.getDepartmentById(2);
		//建立user与post之间的关系
		PostService postService=new PostServiceImpl();
		Long[] pids={1L,2L};
		Set<Post> posts=postService.getPostsByIDS(pids);
		user.setPosts(posts);
		user.setDepartment(department);
		UserService userService=new UserServiceImpl();
		userService.saveUser(user);
	}
}

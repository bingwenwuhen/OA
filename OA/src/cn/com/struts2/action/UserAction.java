package cn.com.struts2.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.com.domain.Department;
import cn.com.domain.Post;
import cn.com.domain.User;
import cn.com.service.DepartmentService;
import cn.com.service.PostService;
import cn.com.service.UserService;
import cn.com.struts2.action.base.BaseAction;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="postService")
	private PostService postService;
	
	private Long did;
	
	private Long[] pids;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long[] getPids() {
		return pids;
	}

	public void setPids(Long[] pids) {
		this.pids = pids;
	}

	public String getAllUser(){
		Collection<User> userList=this.userService.getAllUser();
		ActionContext.getContext().getValueStack().push(userList);
		return listAction;
	}
	
	public String addUI(){
		/*
		 * 1、把部门表的所有数据查询出来
		 * 2、把岗位表的数据查询出来
		 * 3、跳转到增加页面
		 */
		Collection<Department> departmentList=this.departmentService.getAllDepartment();
		ActionContext.getContext().put("departmentList", departmentList);
		Collection<Post> postList=this.postService.getAllPost();
		ActionContext.getContext().put("postList", postList);
		return addUI;
	}
	
	public String add(){
		/*
		 * 如何获取页面中的数据
		 * 		*如果数据来源于一张表，直接用模型驱动来获取
		 * 		*如果页面中的数据来源于多张表，则可以直接采用模型驱动的方式
		 */
		/*
		 * 1、创建一个user对象
		 * 2、把模型驱动的值赋值给user对象
		 * 3、根据did提取出该部门
		 * 4、根据pid提取出岗位
		 * 5、建立user对象和部门、岗位之间的关系
		 * 6、执行save操作
		 */
		User user=new User();
		//一般属性的赋值
		BeanUtils.copyProperties(this.getModel(), user);
		//建立department与user之间的关系
		Department department=this.departmentService.getDepartmentById(did);
		user.setDepartment(department);
		//建立user与post之间的关系
		Set<Post> posts=this.postService.getPostsByIDS(pids);
		user.setPosts(posts);
		this.userService.saveUser(user);
		return action2action;
	}
	
	public String delete(){
		this.userService.deleteUserById(this.getModel().getUid());
		return action2action;
	}
	
	public String updateUI(){
		/*
		 * 1、值的回显
		 * 		*用户的一般属性的回显
		 * 		*部门的回显
		 * 		*岗位的回显
		 * 2、把部门的数据全部提取出来
		 * 3、把岗位的数据全部提取出来
		 */
		//把用户放入到对象栈中
		User user=this.userService.getUserById(this.getModel().getUid());
		ActionContext.getContext().getValueStack().push(user);
		this.did=user.getDepartment().getDid();
		Set<Post> posts=user.getPosts();
		int index=0;
		this.pids=new Long[posts.size()];
		for(Post post:posts){
			this.pids[index]=post.getPid();
			index++;
		}
		//把岗位表和部门表的数据提取出来
		Collection<Department> departmentList=this.departmentService.getAllDepartment();
		ActionContext.getContext().put("departmentList", departmentList);
		Collection<Post> postList=this.postService.getAllPost();
		ActionContext.getContext().put("postList", postList);
		return updateUI;
	}
	
	public String update(){
		/*
		 * 1、利用模型驱动获取用户的一般数据
		 * 2、利用属性驱动获取最新的did和pids
		 * 3、根据用户的id提取出user对象
		 * 4、把模型驱动的值复制到user对象中
		 * 5、重新建立user对象和岗位、部门之间的关系
		 */
		User user=new User();
		BeanUtils.copyProperties(this.getModel(), user);
		//重新建立user和部门之间的关系
		Department department=this.departmentService.getDepartmentById(did);
		user.setDepartment(department);
		//重新建立user与岗位之间的关系
		Set<Post> posts=this.postService.getPostsByIDS(pids);
		user.setPosts(posts);
		this.userService.updateUser(user);
		return action2action;
	}
	
	public String checkUsername(){
		User user=this.userService.getUserByUsername(this.getModel().getUsername());
		if(user==null){
			ActionContext.getContext().getValueStack().push("该用户名可以使用");
			//this.message="该用户名可以使用";
		}else{
			ActionContext.getContext().getValueStack().push("该用户名已存在");
			//this.message="该用户名已存在";
		}
		return SUCCESS;
	}
}

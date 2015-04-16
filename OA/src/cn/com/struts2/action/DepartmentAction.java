package cn.com.struts2.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import cn.com.domain.Department;
import cn.com.service.DepartmentService;
import cn.com.struts2.action.base.BaseAction;
import cn.com.utils.DeleteMode;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DepartmentAction extends BaseAction implements ModelDriven<Department> {
	private DepartmentService	departmentService;

	private Department model=new Department();
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public String getAllDepartment(){
		Collection<Department> departmentList=this.departmentService.getAllDepartment();
		//把departmentList放入到值栈中
		/**
		 * 值栈
		 * 	*值栈的生命周期
		 * 		值栈的生命周期就是一次请求
		 * 	*值栈的数据结构
		 * 		对象栈
		 * 		map栈
		 *  *对象栈和map栈有什么区别
		 *  	对象栈是一个list
		 *  	map栈是一个map
		 *  *怎么将一个数据放入到map栈中
		 *  *怎么样将一个数据放入到对象栈中
		 *  *对象栈中的数据有什么特别之处
		 */
		/*//把departmentList放入到了对象栈的栈顶
		ActionContext.getContext().getValueStack().push(departmentList);
		//把departmentList放入到对象栈中的栈顶
		ActionContext.getContext().getValueStack().getRoot().add(0,departmentList);
		//把departmentList放入到对象栈栈底
		ActionContext.getContext().getValueStack().getRoot().add(departmentList);*/
		
		
		/**
		 * 对象栈的说明
		 * 		*处于对象栈的对象中的属性是可以直接访问的
		 * 		*如果在对象栈中有一样名称的属性，从栈顶开始查找，直到找到为止
		 * 		*一般情况下，回显的数据应该放到对象栈中，这样效果比较高
		 * 		*用ognl表达式访问对象栈，直接属性名称就可以了，不用加#
		 */
		
		//map栈
		/**
		 * 	说明：
		 * 		*request，session，application都在map栈中存储的
		 * 		*可以把一个对象放入到map中
		 * 		*ognl表达式访问map中的内容
		 * 			如果一个对象在request中		
		 * 				#request.对象的key值.属性
		 * 			如果一个对象直接放到map中
		 * 				#对象的key值.属性
		 * 			把一个对象放入到map中，是不能直接访问该对象的属性
		 */
		/*//把一个对象放到map栈中
		ActionContext.getContext().put("departmentList", departmentList);
		//把一个对象放入到request中
		ServletActionContext.getRequest().setAttribute("departmentList",departmentList);*/
		ActionContext.getContext().put("departmentList", departmentList);
		
		//ActionContext.getContext().getValueStack().getRoot().add(0,departmentList);
		/*List<List<Department>> lists=new ArrayList<List<Department>>();
		Department department1=new Department();
		department1.setDname("department_name1");
		Department department2=new Department();
		department2.setDname("department_name2");
		List<Department> departmentList1=new ArrayList<Department>();
		departmentList1.add(department1);
		List<Department> departmentList2=new ArrayList<Department>();
		departmentList2.add(department2);
		lists.add(departmentList1);
		lists.add(departmentList2);*/
		//ActionContext.getContext().getValueStack().getRoot().add(0, lists);
		/*List<Map<String,Department>> list2=new ArrayList<Map<String,Department>>();
		Map<String,Department> map1=new HashMap<String,Department>();
		map1.put("d1", department1);
		Map<String,Department> map2=new HashMap<String,Department>();
		map2.put("d2", department2);
		list2.add(map1);
		list2.add(map2);
		Map<String,List<Department>> maps=new HashMap<String,List<Department>>();
		maps.put("list1", departmentList1);
		maps.put("list2", departmentList2);
		ActionContext.getContext().put("map", maps);*/
 		return listAction;
	}
	
	public String deleteDepartment(){
		this.departmentService.deleteDepartmentById(this.model.getDid(), DeleteMode.DEL_PRE_RELEASE);
		return action2action;
	}
	
	public String addUI(){
		return addUI;
	}
	
	public String add(){
		/**
		 * 1.先建一个department
		 * 2、将模型驱动里面的值赋值到department中
		 * 执行save方法
		 */
		Department department=new Department();
		//对象属性的赋值过程
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.saveDepartment(department);
		return action2action;
	}
	
	/**
	 * 一般情况下，如果数据进行回显，则把数据放入对象栈中，页面可以根据name属性的值进行回显
	 * 如果把数据放入map栈中，则页面根据value的值进行回显，而且value='%{ongl表达式}'
	 * @return
	 */
	public String updateUI(){
		Department department=this.departmentService.getDepartmentById(this.getModel().getDid());
		ActionContext.getContext().getValueStack().getRoot().add(0,department);
		return updateUI;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String update(){
		Department department=this.departmentService.getDepartmentById(this.getModel().getDid());
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.updateDepartment(department);
		return action2action;
	}
	
	@Override
	public Department getModel() {
		
		return this.model;
	}
	
	
}

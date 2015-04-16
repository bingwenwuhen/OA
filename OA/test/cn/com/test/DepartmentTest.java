package cn.com.test;

import org.junit.Test;

import cn.com.service.DepartmentService;

public class DepartmentTest extends BaseSpring{
	@Test
	public void testQuery(){
		DepartmentService departmentService=(DepartmentService) context.getBean("departmentService");
		System.out.println(departmentService);
	}
}

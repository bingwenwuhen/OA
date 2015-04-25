package cn.com.test;

import org.junit.Test;

import cn.com.domain.Kynamic;
import cn.com.service.KynamicService;

public class KynamicTest extends BaseSpring {

	@Test
	public void testSaveKynamic(){
		Kynamic kynamic=new Kynamic();
		kynamic.setIsParent(true);
		kynamic.setName("知识管理");
		kynamic.setPid(0L);
		KynamicService kynamicService=(KynamicService) context.getBean("kynamicService");
		kynamicService.saveKynamic(kynamic);
	}
}

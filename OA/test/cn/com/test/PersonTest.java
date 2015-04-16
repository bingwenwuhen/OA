package cn.com.test;

import org.junit.Test;

import cn.com.domain.Person;
import cn.com.service.PersonService;

public class PersonTest extends BaseSpring  {
	
	@Test
	public void testSavePerson(){
		PersonService personService=(PersonService) context.getBean("personService");
		Person person=new Person();
		person.setPname("bingwen");
		personService.savePerson(person);
		
	}
}

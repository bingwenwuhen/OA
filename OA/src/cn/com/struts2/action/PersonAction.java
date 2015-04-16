package cn.com.struts2.action;

import org.apache.struts2.ServletActionContext;

import cn.com.domain.Person;
import cn.com.service.PersonService;

import com.opensymphony.xwork2.ActionSupport;

public class PersonAction extends ActionSupport {
	private PersonService personService;

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public String savePerson(){
		Person person=new Person();
		person.setPname("bingwen");
		personService.savePerson(person);
		return null;
		
	}
	
	public String getPerson(){
		Person person=this.personService.getPersonById(null);
		ServletActionContext.getRequest().setAttribute("person", person);
		return "index";
	}
}

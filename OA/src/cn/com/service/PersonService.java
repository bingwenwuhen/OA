package cn.com.service;

import cn.com.domain.Person;

public interface PersonService {
	public void savePerson(Person person);
	public Person getPersonById(Long id);
}

package cn.com.dao;

import cn.com.domain.Person;

public interface PersonDao {
	public void savePerson(Person person);
	
	public Person getPersonById(Long id);
}

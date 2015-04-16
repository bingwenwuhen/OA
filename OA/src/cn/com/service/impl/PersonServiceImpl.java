package cn.com.service.impl;

import cn.com.dao.PersonDao;
import cn.com.domain.Person;
import cn.com.service.PersonService;

public class PersonServiceImpl implements PersonService {
	PersonDao personDao;
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	@Override
	public void savePerson(Person person) {
		this.personDao.savePerson(person);
	}

	public Person getPersonById(Long id) {
		return this.personDao.getPersonById(id);
	}
}

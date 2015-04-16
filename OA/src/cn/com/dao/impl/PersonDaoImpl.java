package cn.com.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.dao.PersonDao;
import cn.com.domain.Person;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

	@Override
	public void savePerson(Person person) {
		this.getHibernateTemplate().save(person);
	}

	@Override
	public Person getPersonById(Long id) {
		Person person=(Person) this.getHibernateTemplate().load(Person.class, 1L);
		System.out.println(person.getPname());
		return person;
	}
}

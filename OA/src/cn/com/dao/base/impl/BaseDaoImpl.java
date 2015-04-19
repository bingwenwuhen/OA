package cn.com.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.com.dao.base.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class clazz;
	/**
	 * 1、在父类中要执行一段代码，这个代码的执行时间为子类创建对象的时候，这段代码已经执行完了，根据这种需求，有两种方案
	 * 		   *利用static语句块
	 * 		   *利用父类的构造函数
	 * 2、分析
	 * 		因为得到ParameterizedType需要用到this关键字，而this关键字不能出现在static语句块中
	 * 		所以只能选择父类的构造器
	 */
	public BaseDaoImpl(){
		ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class) type.getActualTypeArguments()[0];
	}
	
	
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
	
	@Override
	public Collection<T> getAllEntry() {
		return this.hibernateTemplate.find("from "+this.clazz.getName());
	}

	@Override
	public T getEntryById(Serializable id) {
		return (T) this.hibernateTemplate.get(clazz, id);
	}

	@Override
	public void saveEntry(T t) {
		this.hibernateTemplate.save(t);
	}

	@Override
	public void updateEntry(T t) {
		this.hibernateTemplate.update(t);
	}

	@Override
	public void deleteEntry(Serializable id) {
	  T t=this.getEntryById(id);
	  this.hibernateTemplate.delete(t);
	}

}

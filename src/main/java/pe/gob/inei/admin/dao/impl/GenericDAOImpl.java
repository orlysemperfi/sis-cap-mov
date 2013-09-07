package pe.gob.inei.admin.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.GenericDAO;
import pe.gob.inei.admin.dao.HibernateUtil;

public class GenericDAOImpl<TIPO, ID extends Serializable> implements GenericDAO<TIPO, ID> {

	public Class<TIPO> domainClass;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		if (domainClass == null) {
			ParameterizedType tipo = (ParameterizedType) getClass().getGenericSuperclass();
			domainClass = (Class<TIPO>)tipo.getActualTypeArguments()[0];
	 	}
	}
 
	@SuppressWarnings("unchecked")
	public TIPO findById (ID id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		TIPO valor = (TIPO)session.get(domainClass, id);
		tx.commit();
		return valor;
	}
 
	public void update(TIPO t) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(t);
	 	tx.commit();
	}
 
	public void save(TIPO t) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(t);
	 	tx.commit();
	}
	 
	public void saveOrUpdate(TIPO t) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
	 	tx.commit();
	}
 
	public void delete(TIPO t) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
	 	tx.commit();
	}
 
	@SuppressWarnings("unchecked")
	public List<TIPO> findAll() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<TIPO> lista = session.createQuery("select o from "+domainClass.getName()+" o").list();
		tx.commit();
		return lista;
	}
 
	public void deleteById(ID id) {
	 	TIPO obj = findById(id);
	 	delete(obj);
	}
 
	public int count() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
	 	Integer count = (Integer) session.createQuery(
 			"select count(*) from " + domainClass.getName() + " o").uniqueResult();
		tx.commit();
		return count.intValue();
	}
}

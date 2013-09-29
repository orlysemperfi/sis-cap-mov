package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.sistencuesta.Personal;

public class PersonalDAOImpl extends GenericDAOImpl<Personal, Integer> implements PersonalDAO {

	public Personal buscarPorDni(String dni) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Personal o where o.numeroDocumento=:p_numeroDocumento");
		query.setString("p_numeroDocumento", dni);
		Personal personal = (Personal) query.uniqueResult();
		tx.commit();
		return personal;
	}
	

	public Personal buscarPorCodigo(Integer codigoPersonal)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Personal o where o.codigoPersonal=:p_codigoPersonal");
		query.setInteger("p_codigoPersonal", codigoPersonal);
		Personal personal = (Personal) query.uniqueResult();
		tx.commit();
		return personal;
	}

	@SuppressWarnings("unchecked")
	public List<Personal> buscarPorUbigeo(String codigoUbigeo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Personal o where o.codigoUbigeo=:p_codigoUbigeo");
		List<Personal> lista = query.list();
		tx.commit();
		return lista;
	}
}
 
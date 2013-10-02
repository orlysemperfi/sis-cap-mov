package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.EstablecimientoDAO;
import pe.gob.inei.sistencuesta.Establecimiento;

public class EstablecimientoDAOImpl extends GenericDAOImpl<Establecimiento, Integer> implements EstablecimientoDAO{	

	@SuppressWarnings("unchecked")
	public List<Establecimiento> buscar(String codigoUbigeo){
		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Establecimiento o where o.ubigeo.codigoUbigeo=:p_codigoUbigeo");
		query.setString("p_codigoUbigeo", codigoUbigeo);
		List<Establecimiento> lista = query.list();
		tx.commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Establecimiento> buscar(String codigoUbigeo,String codigosEstablecimiento)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Establecimiento o where o.ubigeo.codigoUbigeo=:p_codigoUbigeo and  o.codigoEstablecimiento NOT IN ("+codigosEstablecimiento+") ");
		query.setString("p_codigoUbigeo", codigoUbigeo);
		List<Establecimiento> lista = query.list();
		tx.commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Establecimiento> buscarxCodigos(String codigosEstablecimiento){
		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("select o from Establecimiento o where o.codigoEstablecimiento IN ("+codigosEstablecimiento+")  ");
		List<Establecimiento> lista = query.list();
		tx.commit();
		return lista;
	}
	
}
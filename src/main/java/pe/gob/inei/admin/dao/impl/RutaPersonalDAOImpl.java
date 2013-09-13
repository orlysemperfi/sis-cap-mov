package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.RutaPersonalId;

public class RutaPersonalDAOImpl extends GenericDAOImpl<RutaPersonal, RutaPersonalId> implements RutaPersonalDAO{	
	
	@SuppressWarnings("unchecked")
	public List<RutaPersonal> buscarUbigeo(String ubigeo){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from RutaPersonal o where o.ruta.ubigeo.codigoUbigeo=:p_codigoUbigeo");
		query.setString("p_codigoUbigeo", ubigeo);
		List<RutaPersonal> lista = query.list();
		tx.commit();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<RutaPersonal> buscarPersona(Integer codigoPersonal) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from RutaPersonal o where o.personal.codigoPersonal=:p_codigoPersonal ");
		query.setInteger("p_codigoUbigeo", codigoPersonal);
		List<RutaPersonal> lista = query.list();
		tx.commit();
		return lista;
	}

	
}

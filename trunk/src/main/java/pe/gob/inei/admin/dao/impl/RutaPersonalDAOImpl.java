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
		query.setInteger("p_codigoPersonal", codigoPersonal);
		List<RutaPersonal> lista = query.list();
		tx.commit();
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<RutaPersonal> buscarPersonaRuta(Integer codigoRuta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from RutaPersonal o where o.codigoRuta=:p_codigoRuta ");
		query.setInteger("p_codigoRuta", codigoRuta);
		List<RutaPersonal> lista = query.list();
		tx.commit();
		return lista;		
	}
	public RutaPersonal buscarPersonaRuta(Integer codigoRuta, Integer codigoPersonal)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from RutaPersonal o where o.codigoRuta=:p_codigoRuta and o.personal.codigoPersonal=:p_codigoPersonal");
		query.setInteger("p_codigoRuta", codigoRuta);
		query.setInteger("p_codigoPersonal", codigoPersonal);
		RutaPersonal rutaPersonal= (RutaPersonal) query.uniqueResult();
		tx.commit();
		return rutaPersonal;
	}
	public void registrar(Integer codigoRuta, Integer codigoPersonal, Integer numeroEncuestas, String fechaInicio, String fechaFin, Integer nroEncuestasPorDia, Integer correlativoInicial)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into RutaPersonal o values(o.codigoRuta, o.codigoPersonal, o.numeroEncuestas, " + 
											"o.fechaInicio, o.fechaFin, o.nroEncuestasPorDia, o.correlativoInicial, estado) VALUES ( " +
											":p_codigoRuta, :p_codigoPersonal, :p_numeroEncuestas, " +
											":p_fechaInicio, :p_fechaFin, :p_nroEncuestasPorDia, :p_correlativoInicial, :p_estado)");

		query.setInteger("p_codigoRuta", codigoRuta);
		query.setInteger("p_codigoPersonal", codigoPersonal);
		query.setInteger("p_numeroEncuestas",  numeroEncuestas);
		query.setString("p_fechaInicio", fechaInicio);
		query.setString("p_fechaFin", fechaFin);
		query.setInteger("p_nroEncuestasPorDia", nroEncuestasPorDia);
		query.setInteger("p_correlativoInicial", correlativoInicial);
		query.setString("p_estado", "A");
		tx.commit();
	}
	public void actualizar(Integer codigoRuta, Integer codigoPersonal, Integer numeroEncuestas, String fechaInicio, String fechaFin, Integer nroEncuestasPorDia, Integer correlativoInicial)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("update from RutaPersonal o set  o.numeroEncuestas:p_numeroEncuestas, o.fechaInicio=:p_fechaInicio, " + 
													"o.fechaFin=:p_fechaFin, o.nroEncuestasPorDia=:p_nroEncuestasPorDia, o.correlativoInicial=:p_correlativoInicial " +
											"where o.codigoRuta=:p_codigoRuta and  o.codigoPersonal=:p_codigoPersonal");

		query.setInteger("p_codigoRuta", codigoRuta);
		query.setInteger("p_codigoPersonal", codigoPersonal);
		query.setInteger("p_numeroEncuestas",  numeroEncuestas);
		query.setString("p_fechaInicio", fechaInicio);
		query.setString("p_fechaFin", fechaFin);
		query.setInteger("p_nroEncuestasPorDia", nroEncuestasPorDia);
		query.setInteger("p_correlativoInicial", correlativoInicial);
		tx.commit();
	}
	public void eliminar(Integer codigoRuta, Integer codigoPersonal)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("Delete from RutaPersonal o where o.codigoRuta=:p_codigoRuta and o.codigoPersonal=:p_codigoPersonal ");		
		query.setInteger("p_codigoRuta", codigoRuta);
		query.setInteger("p_codigoPersonal", codigoPersonal);
		tx.commit();
	}

	
}

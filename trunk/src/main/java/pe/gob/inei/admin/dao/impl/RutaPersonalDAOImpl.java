package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.Ruta;
import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.RutaPersonalId;
import pe.gob.inei.sistencuesta.Ubigeo;

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
		Query query = session.createQuery("select o from RutaPersonal o where o.ruta.codigoRuta=:p_codigoRuta ");
		query.setInteger("p_codigoRuta", codigoRuta);
		List<RutaPersonal> lista = query.list();
		tx.commit();
		return lista;		
	}
	public RutaPersonal buscarPersonaRuta(Integer codigoRuta, Integer codigoPersonal)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from RutaPersonal o where o.ruta.codigoRuta=:p_codigoRuta ");//and o.personal.codigoPersonal=:p_codigoPersonal");
		query.setInteger("p_codigoRuta", codigoRuta);
		//query.setInteger("p_codigoPersonal", codigoPersonal);
		RutaPersonal rutaPersonal= (RutaPersonal) query.uniqueResult();
		tx.commit();
		return rutaPersonal;
	}
	public void registrar(Integer codigoRuta, Integer codigoPersonal, Integer numeroEncuestas, String fechaInicio, String fechaFin, Integer nroEncuestasPorDia, Integer correlativoInicial)
	{
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		PersonalDAO personalDAO=DAOFactory.getInstance().getPersonalDAO();
		
		Ruta ruta=rutaDAO.buscarxCodigo(codigoRuta);
		Personal personal=personalDAO.buscarPorCodigo(codigoPersonal);
		
		RutaPersonalId rutaPersonalId=new RutaPersonalId(codigoPersonal, codigoRuta);
		RutaPersonal rutaPersonal = new RutaPersonal(rutaPersonalId, personal, ruta, numeroEncuestas, nroEncuestasPorDia, correlativoInicial, "A");
		
		rutaPersonal.setFechainicio(fechaInicio);
		rutaPersonal.setFechafin(fechaFin);
		
		save(rutaPersonal);
	}
	public void actualizar(Integer codigoRuta, Integer codigoPersonalOriginal, Integer codigoPersonal, Integer numeroEncuestas, String fechaInicio, String fechaFin, Integer nroEncuestasPorDia, Integer correlativoInicial)
	{
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		PersonalDAO personalDAO=DAOFactory.getInstance().getPersonalDAO();
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		/*
		Ruta ruta=rutaDAO.findById(codigoRuta);
		Personal personal=personalDAO.buscarPorCodigo(codigoPersonal);
		*/
		RutaPersonal rutaPersonal = rutaPersonalDAO.buscarPersonaRuta(codigoRuta, codigoPersonalOriginal);
		//RutaPersonalId rutaPersonalId=rutaPersonal.getId();
		/*RutaPersonalId rutaPersonalId=new RutaPersonalId(codigoPersonal, codigoRuta);
		
		rutaPersonalId.setCodigoPersonal(codigoPersonal);
		*/
		//rutaPersonalId.setCodigoRuta(codigoRuta);
		
/*
		rutaPersonal.setRuta(ruta);
		rutaPersonal.setPersonal(personal);
		rutaPersonal.setId(rutaPersonalId);
		*/
		rutaPersonal.setNumeroEncuestas(numeroEncuestas);
		rutaPersonal.setNroEncuestasPorDia(nroEncuestasPorDia);
		rutaPersonal.setCorrelativoInicial(correlativoInicial);
		rutaPersonal.setFechainicio(fechaInicio);
		rutaPersonal.setFechafin(fechaFin);
		
		update(rutaPersonal);
	}
	public void eliminar(Integer codigoRuta, Integer codigoPersonal)
	{
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		delete(rutaPersonalDAO.buscarPersonaRuta(codigoRuta, codigoPersonal));
	}
	
	@SuppressWarnings("unchecked")
	public List<RutaPersonal> buscarPorDni(String dni)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from RutaPersonal o where o.personal.numeroDocumento=:p_numeroDocumento and estado IN ('A','P') ");
		query.setString("p_numeroDocumento", dni);
		List<RutaPersonal> lista = query.list();
		tx.commit();
		return lista;		
	}
}

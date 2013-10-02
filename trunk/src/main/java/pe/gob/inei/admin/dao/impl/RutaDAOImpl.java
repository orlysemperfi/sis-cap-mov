package pe.gob.inei.admin.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.Ruta;
import pe.gob.inei.sistencuesta.Ubigeo;
import pe.gob.inei.sistencuesta.Establecimiento;


public class RutaDAOImpl  extends GenericDAOImpl<Ruta, Integer> implements RutaDAO
{
	@SuppressWarnings("unchecked")
	public List<Ruta> buscar(String codigoEncuesta)
	{		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ruta o where o.encuesta.codigoEncuesta=:p_codigoEncuesta ");
		query.setString("p_codigoEncuesta", codigoEncuesta);
		List<Ruta> lista = query.list();
		tx.commit();
		return lista;		
	}
		
	public Ruta buscarxCodigo(Integer codigoRuta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ruta o where o.codigoRuta=:p_codigoRuta ");
		query.setInteger("p_codigoRuta", codigoRuta);
		Ruta ruta = (Ruta) query.uniqueResult();
		tx.commit();
		return ruta;
	}
	public void registrar(String codigoEncuesta, String codigoUbigeo, String numeroRuta, String descripcion, Integer numeroEncuestas, Integer correlativoInicial, Integer correlativoFinal,  List<Establecimiento> establecimientos)
	{
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		
		Ubigeo ubigeo=ubigeoDAO.findById(codigoUbigeo);
		Encuesta encuesta=encuestaDAO.buscarxCodigo(codigoEncuesta);
		
		
		Ruta ruta = new Ruta(ubigeo, encuesta, numeroRuta, descripcion, numeroEncuestas, correlativoInicial, correlativoFinal, "A");
		Set<Establecimiento> establecimientosHash = new HashSet<Establecimiento>();
		establecimientosHash.addAll(establecimientos);
		ruta.setEstablecimientos(establecimientosHash);
		
		
		
		save(ruta);
	}
	public void actualizar(Integer codigoRuta, String codigoEncuesta, String codigoUbigeo, String numeroRuta, String descripcion, Integer numeroEncuestas, Integer correlativoInicial, Integer correlativoFinal,  List<Establecimiento> establecimientos)
	{
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		
		Ubigeo ubigeo=ubigeoDAO.findById(codigoUbigeo);
		Ruta ruta = rutaDAO.buscarxCodigo(codigoRuta);
				
		ruta.setUbigeo(ubigeo);
		ruta.setNumeroRuta(numeroRuta);
		ruta.setDescripcion(descripcion);
		ruta.setNumeroEncuestas(numeroEncuestas);
		ruta.setCorrelativoInicial(correlativoInicial);
		ruta.setCorrelativoFinal(correlativoFinal);
		
		Set<Establecimiento> establecimientosHash = new HashSet<Establecimiento>();
		establecimientosHash.addAll(establecimientos);
		ruta.setEstablecimientos(establecimientosHash);
		
		
		update(ruta);
	}
	public void eliminar(Integer codigoRuta)
	{
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		delete(rutaDAO.buscarxCodigo(codigoRuta));
	}
	public void registrarDetalle(Integer codigoRuta, Integer codigoEstablecimiento)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into DetalleRuta o values(o.codigoRuta, o.codigoEstablecimiento) VALUES ( " +
											":p_codigoRuta, :p_codigoEstablecimiento)");

		query.setInteger("p_codigoRuta", codigoRuta);
		query.setInteger("p_codigoEstablecimiento", codigoEstablecimiento);
		tx.commit();
	}
	public void eliminarDetalle(Integer codigoRuta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from DetalleRuta where o.codigoRuta=:p_codigoRuta ");

		query.setInteger("p_codigoRuta", codigoRuta);
		tx.commit();	
	}
	@SuppressWarnings("unchecked")
	public List<Ruta> buscarRutaPorUbigeo(String codigoUbigeo) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ruta o where o.codigoUbigeo=:p_codigoUbigeo");
		query.setString("p_codigoUbigeo", codigoUbigeo);
		List<Ruta> ruta = query.list();
		tx.commit();
		return ruta;
	}

	@SuppressWarnings("unchecked")
	public List<Ruta> buscar(String codigoEncuesta, String codigoUbigeo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ruta o where o.encuesta.codigoEncuesta=:p_codigoEncuesta and o.ubigeo.codigoUbigeo=:p_codigoUbigeo ");
		query.setString("p_codigoEncuesta", codigoEncuesta);
		query.setString("p_codigoUbigeo", codigoUbigeo);
		List<Ruta> lista = query.list();
		tx.commit();
		return lista;		
	}
}

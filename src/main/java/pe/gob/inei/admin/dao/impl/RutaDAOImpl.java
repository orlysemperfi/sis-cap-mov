package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.admin.dao.EstablecimientoDAO;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.MarcoMuestral;
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
		Query query = session.createQuery("select o from Ruta o where o.codigoEncuesta=:p_codigoEncuesta ");
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
	public void registrar(String codigoEncuesta, String codigoUbigeo, String numeroRuta, String descripcion, Integer numeroEncuestas, Integer correlativoInicial, Integer correlativoFinal)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into Ruta o values(o.codigoEncuesta, o.codigoUbigeo, o.numeroRuta, " + 
											"o.descripcion, o.numeroEncuestas, o.correlativoInicial, o.correlativoFinal, estado) VALUES ( " +
											":p_codigoEncuesta, :p_codigoUbigeo, :p_numeroRuta, :p_descripcion, " +
											":p_numeroEncuestas, :p_correlativoInicial, :p_correlativoFinal, :p_estado)");

		query.setString("p_codigoEncuesta", codigoEncuesta);
		query.setString("p_codigoUbigeo", codigoUbigeo);
		query.setString("p_numeroRuta",  numeroRuta);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_numeroEncuestas", numeroEncuestas);
		query.setInteger("p_correlativoInicial", correlativoInicial);
		query.setInteger("p_correlativoFinal", correlativoFinal);
		query.setString("p_estado", "A");
		tx.commit();
	}
	public void actualizar(Integer codigoRuta, String codigoEncuesta, String codigoUbigeo, String numeroRuta, String descripcion, Integer numeroEncuestas, Integer correlativoInicial, Integer correlativoFinal)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("Update from Ruta o set o.codigoEncuesta=:p_codigoEncuesta, o.codigoUbigeo=:p_codigoUbigeo, " + 
													"o.numeroRuta=:p_numeroRuta, o.descripcion=:p_descripcion, o.numeroEncuestas=:p_numeroEncuestas, " +
													"o.correlativoInicial=:p_correlativoInicial, o.correlativoFinal=:p_correlativoFinal " +
											"where o.codigoRuta=:p_codigoRuta");
		
		query.setInteger("p_codigoRuta", codigoRuta);
		query.setString("p_codigoEncuesta", codigoEncuesta);
		query.setString("p_codigoUbigeo", codigoUbigeo);
		query.setString("p_numeroRuta",  numeroRuta);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_numeroEncuestas", numeroEncuestas);
		query.setInteger("p_correlativoInicial", correlativoInicial);
		query.setInteger("p_correlativoFinal", correlativoFinal);
		tx.commit();
	}
	public void eliminar(Integer codigoRuta)
	{

		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("Delete from Ruta o where o.codigoRuta=:p_codigoRuta ");		
		query.setInteger("p_codigoRuta", codigoRuta);
		tx.commit();
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
}

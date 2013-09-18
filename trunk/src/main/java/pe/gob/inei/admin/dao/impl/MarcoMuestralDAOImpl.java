package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.sistencuesta.MarcoMuestral;

public class MarcoMuestralDAOImpl extends GenericDAOImpl<MarcoMuestral, String> implements MarcoMuestralDAO{	

	@SuppressWarnings("unchecked")
	public List<MarcoMuestral> buscar()
	{		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from MarcoMuestral o ");
		List<MarcoMuestral> lista = query.list();
		tx.commit();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<MarcoMuestral> buscar(String nombre, String encuesta, Integer año)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from MarcoMuestral o ");
		List<MarcoMuestral> lista = query.list();
		tx.commit();
		return lista;
	}


	public MarcoMuestral buscarxCodigo(String codigoMarcoMuestral)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from MarcoMuestral o where o.codigoMarcoMuestral=:p_codigoMarcoMuestral ");
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		MarcoMuestral marcoMuestral = (MarcoMuestral) query.uniqueResult();
		tx.commit();
		return marcoMuestral;
	}
	
	public void registrar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into MarcoMuestral o values(o.nombre, o.año , o.descripcion, " + 
											"o.objetivo, o.fechaInicio, o.fechaFin, o.tipoArea, " +
											"o.codigoRubro, o.codigoMarcoMuestral) VALUES ( " +
											":p_nombre, :p_año, :p_descripcion, :p_objetivo, :p_fechaInicio, :p_fechaFin, :p_tipoArea, " +
											":p_codigoRubro, :p_codigoMarcoMuestral)");
		
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		query.setInteger("p_año", año);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_numeroEncuestas", numeroEncuestas);
		query.setString("p_tipoUbigeo", tipoUbigeo);
		query.setString("p_tipoArea", tipoArea);
		query.setString("p_estado", "A");
		tx.commit();		
	}
	
	public void actualizar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into MarcoMuestral o values(o.nombre, o.año , o.descripcion, " + 
											"o.objetivo, o.fechaInicio, o.fechaFin, o.tipoArea, " +
											"o.codigoRubro, o.codigoMarcoMuestral) VALUES ( " +
											":p_nombre, :p_año, :p_descripcion, :p_objetivo, :p_fechaInicio, :p_fechaFin, :p_tipoArea, " +
											":p_codigoRubro, :p_codigoMarcoMuestral)");
		
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		query.setInteger("p_año", año);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_numeroEncuestas", numeroEncuestas);
		query.setString("p_tipoUbigeo", tipoUbigeo);
		query.setString("p_tipoArea", tipoArea);
		query.setString("p_estado", "A");
		tx.commit();
		
	}
	
	public void eliminar(String codigoMarcoMuestral)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from MarcoMuestral o where codigoMarcoMuestral=:p_codigoMarcoMuestral");
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		tx.commit();		
	}
	

	public void registrarDetalle(String codigoMarcoMuestral, String codigoUbigeo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into DetalleMarcoMuestral o values(o.codigoMarcoMuestral, o.codigoUbigeo) VALUES ( " +
											":p_codigoMarcoMuestral, :p_codigoUbigeo)");
		
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		query.setString("p_codigoUbigeo", codigoUbigeo);
		tx.commit();
		
	}
	public void eliminarDetalle(String codigoMarcoMuestral)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from DetalleMarcoMuestral o where o.codigoMarcoMuestral=:p_codigoMarcoMuestral");
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		tx.commit();		
	}
	
}
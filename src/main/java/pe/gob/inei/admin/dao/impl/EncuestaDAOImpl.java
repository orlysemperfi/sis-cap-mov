package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.admin.dao.RubroDAO;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.sistencuesta.MarcoMuestral;
import pe.gob.inei.sistencuesta.Rubro;
import pe.gob.inei.sistencuesta.Encuesta;

public class EncuestaDAOImpl extends GenericDAOImpl<Encuesta, String> implements EncuestaDAO{	
	
	@SuppressWarnings("unchecked")
	public List<Encuesta> buscar(String nombre, Integer ano ){
		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Encuesta o ");//where o.nombre =:p_nombre
		//query.setString("p_nombre", nombre);
		//query.setInteger("p_año", ano);
		List<Encuesta> lista = query.list();
		tx.commit();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Encuesta buscarxCodigo( String codigoEncuesta) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Encuesta o where o.codigoEncuesta=:p_codigoEncuesta ");
		query.setString("p_codigoEncuesta", codigoEncuesta);
		Encuesta encuesta = (Encuesta) query.uniqueResult();
		tx.commit();
		return encuesta;
	}

	public void registrar(String codigoEncuesta, String nombre, Integer año, String descripcion, String objetivo, String fechaInicio, String fechaFin, String tipoArea, Integer codigoRubro, String codigoMarcoMuestral)
	{		
		//String codEncuesta;
		
		RubroDAO rubroDAO=DAOFactory.getInstance().getRubroDAO();
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();

		Rubro rubro =rubroDAO.buscarPorCodigo(codigoRubro);
		MarcoMuestral marcoMuestral =marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral);
		
		Encuesta encuesta = new Encuesta(codigoEncuesta, rubro, marcoMuestral, año, nombre, descripcion, objetivo,fechaInicio, fechaFin, tipoArea, "A");
		
		
		save(encuesta);
		
		//save(encuesta);
		/*
		Session session = HibernateUtil.getCurrentSession();		
		Transaction tx = session.beginTransaction();
		session.save(encuesta);
		tx.commit();
		*/
		
		/*
		Query query = session.createQuery("insert into Encuesta o values(o.codigoEncuesta, o.nombre, o.año , o.descripcion, " + 
											"o.objetivo, o.fechaInicio, o.fechaFin, o.tipoArea, " +
											"o.codigoRubro, o.codigoMarcoMuestral) VALUES ( " +
											":p_codigoEncuesta, :p_nombre, :p_año, :p_descripcion, :p_objetivo, :p_fechaInicio, :p_fechaFin, :p_tipoArea, " +
											":p_codigoRubro, :p_codigoMarcoMuestral)");
		
		query.setString("p_codigoEncuesta", codigoEncuesta);
		query.setString("p_nombre", nombre);
		query.setInteger("p_año", año);
		query.setString("p_descripcion", descripcion);
		query.setString("p_objetivo", objetivo);
		query.setString("p_fechaInicio", fechaInicio);
		query.setString("p_fechaFin", fechaFin);
		query.setString("p_tipoArea", "A");//tipoArea
		query.setInteger("p_codigoRubro", codigoRubro);
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		tx.commit();
		*/
		/*
		 Query query = session.createSQLQuery(" { CALL sp_mantenimientoEncuesta(:TipoConsulta,:CodigoEncuesta,:Anio,:Nombre,:Descripcion,:Objetivo,:FechaInicio,:FechaFin,:TipoArea,:Estado,:CodigoRubro,:CodigoMarcoMuestral) }");

	        query.setParameter("TipoConsulta", "INSERT");
	        query.setParameter("CodigoEncuesta", codigoEncuesta);
	        query.setParameter("Anio", año);
	        query.setParameter("Nombre", nombre);
	        query.setParameter("Descripcion", descripcion);
	        query.setParameter("Objetivo", objetivo);
	        query.setParameter("FechaInicio", fechaInicio);
	        query.setParameter("FechaFin", fechaFin);
	        query.setParameter("TipoArea", "A");//tipoArea
	        query.setParameter("Estado", "A");
	        query.setParameter("CodigoRubro", codigoRubro);
	        query.setParameter("CodigoMarcoMuestral", codigoMarcoMuestral);
	        query.executeUpdate();
*/
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void actualizar(String codigoEncuesta, String nombre, Integer año, String descripcion, String objetivo, String fechaInicio, String fechaFin, String tipoArea, Integer codigoRubro, String codigoMarcoMuestral)
	{
		/*
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("Update from Encuesta o set o.nombre=:p_nombre, o.año=:p_año , o.descripcion=:p_descripcion, " + 
											"o.objetivo=:p_objetivo, o.fechaInicio=:p_fechaInicio, o.fechaFin=:p_fechaFin, o.tipoArea=:p_tipoArea, " +
											"o.codigoRubro=:p_codigoRubro, o.codigoMarcoMuestral=:p_codigoMarcoMuestral, o.estado=:p_estado " + 
											" where o.codigoEncuesta=:p_codigoEncuesta ");
		query.setString("p_codigoEncuesta", codigoEncuesta);
		query.setString("p_nombre", nombre);
		query.setInteger("p_año", año);
		query.setString("p_descripcion", descripcion);
		query.setString("p_objetivo", objetivo);
		query.setString("p_fechaInicio", fechaInicio);
		query.setString("p_fechaFin", fechaFin);
		query.setString("p_tipoArea", tipoArea);
		query.setInteger("p_codigoRubro", codigoRubro);
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		query.setString("p_estado", "A");		
		tx.commit();
		*/
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		Encuesta encuesta=encuestaDAO.buscarxCodigo(codigoEncuesta);
		
		RubroDAO rubroDAO=DAOFactory.getInstance().getRubroDAO();
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();

		encuesta.setRubro(rubroDAO.buscarPorCodigo(codigoRubro));
		encuesta.setMarcoMuestral(marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral));
		
		encuesta.setAño(año);
		encuesta.setNombre(nombre);
		encuesta.setDescripcion(descripcion);
		encuesta.setObjetivo(objetivo);
		encuesta.setFechainicio(fechaInicio);
		encuesta.setFechafin(fechaFin);
		encuesta.setTipoArea(tipoArea);
		
		update(encuesta);
		
	}	
	
	@SuppressWarnings("unchecked")
	public void eliminar(String codigoEncuesta)
	{		/*
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("Delete from Encuesta o where o.codigoEncuesta=:p_codigoEncuesta ");
		query.setString("p_codigoEncuesta", codigoEncuesta);
		tx.commit();
		*/
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		delete(encuestaDAO.buscarxCodigo(codigoEncuesta));
		
	}	
}
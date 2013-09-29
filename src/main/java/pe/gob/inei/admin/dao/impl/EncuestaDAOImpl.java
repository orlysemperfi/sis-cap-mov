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
		String where="";
		
		if(nombre.length()>0) where=" where o.nombre like '"+nombre+"%' ";
		if(ano>0){
			if(where.length()==0) where = "where "; 			
			else where+=" and ";
			where+=" o.año = "+ano.toString();
		}
			
				
		Query query = session.createQuery("select o from Encuesta o " + where);//where o.nombre =:p_nombre
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
		RubroDAO rubroDAO=DAOFactory.getInstance().getRubroDAO();
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();

		Rubro rubro =rubroDAO.buscarPorCodigo(codigoRubro);
		MarcoMuestral marcoMuestral =marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral);
		
		Encuesta encuesta = new Encuesta(codigoEncuesta, rubro, marcoMuestral, año, nombre, descripcion, objetivo,fechaInicio, fechaFin, tipoArea, "A");
		
		save(encuesta);
	}
	
	
	@SuppressWarnings("unchecked")
	public void actualizar(String codigoEncuesta, String nombre, Integer año, String descripcion, String objetivo, String fechaInicio, String fechaFin, String tipoArea, Integer codigoRubro, String codigoMarcoMuestral)
	{
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
	{	
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		delete(encuestaDAO.buscarxCodigo(codigoEncuesta));
	}
	
	@SuppressWarnings("unchecked")
	public List<Encuesta> buscarEncuestas() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Encuesta o ");
		
		List<Encuesta> lista = query.list();
		tx.commit();
		return lista;
	}	
}
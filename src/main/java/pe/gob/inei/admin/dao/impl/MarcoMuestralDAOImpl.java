package pe.gob.inei.admin.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.sistencuesta.MarcoMuestral;
import pe.gob.inei.sistencuesta.Ubigeo;

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
		String where="";
		
		if(nombre.length()>0) where=" where o.descripcion like '"+nombre+"%' ";
		if(año>0){
			if(where.length()==0) where = "where "; 			
			else where+=" and ";
			where+=" o.año = "+año.toString();
		}
		
		Query query = session.createQuery("select o from MarcoMuestral o " + where);
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
	
	
	public void registrar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea, String estado, List<Ubigeo> ubigeos)
	{		
		MarcoMuestral marcoMuestral = new MarcoMuestral(codigoMarcoMuestral, año, descripcion, numeroEncuestas, tipoUbigeo, tipoArea, estado);
		Set<Ubigeo> ubigeosHash = new HashSet<Ubigeo>();
		ubigeosHash.addAll(ubigeos);
		
		marcoMuestral.setUbigeos(ubigeosHash);
		
		save(marcoMuestral);
	}
	
	public void actualizar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea, String estado, List<Ubigeo> ubigeos)
	{
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		MarcoMuestral marcoMuestral = marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral);

		marcoMuestral.setAño(año);
		marcoMuestral.setDescripcion(descripcion);
		marcoMuestral.setNumeroEncuestas(numeroEncuestas);
		marcoMuestral.setTipoUbigeo(tipoUbigeo);
		marcoMuestral.setTipoArea(tipoArea);
		marcoMuestral.setEstado(estado);

		Set<Ubigeo> ubigeosHash = new HashSet<Ubigeo>();
		ubigeosHash.addAll(ubigeos);
		
		marcoMuestral.setUbigeos(ubigeosHash);
		
		update(marcoMuestral);
	}
	
	public void eliminar(String codigoMarcoMuestral)
	{
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		delete(marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral));
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
	

	@SuppressWarnings("unchecked")
	public List<MarcoMuestral> buscarPorTipoArea(String tipoArea)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from MarcoMuestral o where o.tipoArea=:p_tipoArea ");
		query.setString("p_tipoArea", tipoArea);
		List<MarcoMuestral> lista = query.list();
		tx.commit();
		return lista;
	}

}
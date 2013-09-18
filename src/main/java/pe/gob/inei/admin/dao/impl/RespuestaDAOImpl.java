package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.RespuestaDAO;
import pe.gob.inei.sistencuesta.Respuesta;

public class RespuestaDAOImpl extends GenericDAOImpl<Respuesta, Integer> implements RespuestaDAO{
	
	@SuppressWarnings("unchecked")
	public List<Respuesta> buscar(Integer codigoPregunta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Respuesta o where o.codigoPregunta=:p_codigoPregunta");
		query.setInteger("p_codigoPregunta", codigoPregunta);
		List<Respuesta> lista = query.list();
		tx.commit();
		return lista;		
	}
	public Respuesta buscarxCodigo(Integer codigoRespuesta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Respuesta o where o.codigoRespuesta=:p_codigoRespuesta");
		query.setInteger("p_codigoRespuesta", codigoRespuesta);
		Respuesta respuesta = (Respuesta) query.uniqueResult();
		tx.commit();
		return respuesta;
	}
	public void registrar(String valor, String descripcion, Integer posicion, Integer codigoPregunta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into Respuesta o values(o.valor, o.descripcion, o.posicion, o.codigoPregunta) VALUES (" + 
											":p_valor, :p_descripcion, :p_posicion, :p_codigoPregunta)");
		
		query.setString("p_valor", valor);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_posicion", posicion);
		query.setInteger("p_codigoPregunta", codigoPregunta);
		tx.commit();
	}
	public void actualizar(Integer codigoRespuesta, String valor, String descripcion, Integer posicion,Integer codigoPregunta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("update from Respuesta o set o.valor=:p_valor, o.descripcion=:p_descripcion, "+
												"o.posicion=:p_posicion, o.codigoPregunta=:p_codigoPregunta " + 
											" where o.codigoRespuesta=:p_codigoRespuesta ");
		
		query.setInteger("p_codigoRespuesta", codigoRespuesta);
		query.setString("p_valor", valor);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_posicion", posicion);
		query.setInteger("p_codigoPregunta", codigoPregunta);
		tx.commit();
		
	}
	public void eliminar(Integer codigoRespuesta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from Respuesta o where o.codigoRespuesta=:p_codigoRespuesta ");
		
		query.setInteger("p_codigoRespuesta", codigoRespuesta);
		tx.commit();
	}
}

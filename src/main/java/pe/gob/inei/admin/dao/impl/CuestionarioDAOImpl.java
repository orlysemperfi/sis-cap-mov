package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.CuestionarioDAO;
import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.sistencuesta.Cuestionario;

public class CuestionarioDAOImpl extends GenericDAOImpl<Cuestionario, Integer> implements CuestionarioDAO{	

	
	@SuppressWarnings("unchecked")
	public List<Cuestionario> buscar(String codigoEncuesta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Cuestionario o where o.codigoEncuesta=:p_codigoEncuesta");
		query.setString("p_codigoEncuesta", codigoEncuesta);
		List<Cuestionario> lista = query.list();
		tx.commit();
		return lista;
	}
	public Cuestionario buscarxCodigo(Integer codigoCuestionario)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Cuestionario o where o.codigoCuestionario=:p_codigoCuestionario");
		query.setInteger("p_codigoCuestionario", codigoCuestionario);
		Cuestionario cuestionario = (Cuestionario) query.uniqueResult();
		tx.commit();
		return cuestionario;		
	}
	public void registrar(Integer numero, String descripcion, Integer codigoCategoria, String codigoEncuesta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into Cuestionario o values(o.numero, o.descripcion, o.codigoCategoria, o.codigoEncuesta, o.flagPlantilla, o.estado) VALUES (" + 
											":p_numero, :p_descripcion, :p_codigoCategoria, :p_codigoEncuesta, :p_flagPlantilla, :p_estado)");
		
		query.setInteger("p_numero", numero);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_codigoCategoria", codigoCategoria);
		query.setString("p_codigoEncuesta", codigoEncuesta);
		query.setString("p_flagPlantilla", "N");
		query.setString("p_estado", "A");
		tx.commit();
	}
	public void actualizar(Integer codigoCuestionario, Integer numero, String descripcion, Integer codigoCategoria, String codigoEncuesta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("update from Cuestionario o set o.numero=:p_numero, o.descripcion=:p_descripcion, " + 
											"o.codigoCategoria=:p_codigoCategoria, o.codigoEncuesta=:p_codigoEncuesta " + 
											"where o.codigoCuestionario=:p_codigoCuestionario");
		
		query.setInteger("p_codigoCuestionario", codigoCuestionario);
		query.setInteger("p_numero", numero);
		query.setString("p_descripcion", descripcion);
		query.setInteger("p_codigoCategoria", codigoCategoria);
		query.setString("p_codigoEncuesta", codigoEncuesta);
		tx.commit();
	}
	public void eliminar(Integer codigoCuestionario)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from Cuestionario o where o.codigoCuestionario=:p_codigoCuestionario");
		
		query.setInteger("p_codigoCuestionario", codigoCuestionario);
		tx.commit();
	}
}

package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.CapituloDAO;
import pe.gob.inei.sistencuesta.Capitulo;

public class CapituloDAOImpl extends GenericDAOImpl<Capitulo, Integer> implements CapituloDAO{
	
	@SuppressWarnings("unchecked")
	public List<Capitulo> buscar(Integer codigoCuestionario)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Capitulo o where o.codigoCuestionario=:p_codigoCuestionario");
		query.setInteger("p_codigoCuestionario", codigoCuestionario);
		List<Capitulo> lista = query.list();
		tx.commit();
		return lista;
	}
	public Capitulo buscarxCodigo(Integer codigoCapitulo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Capitulo o where o.codigoCapitulo=:p_codigoCapitulo");
		query.setInteger("p_codigoCapitulo", codigoCapitulo);
		Capitulo capitulo = (Capitulo) query.uniqueResult();
		tx.commit();
		return capitulo;
	}
	public void registrar(Integer numero, String titulo, Integer codigoCuestionario)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into Capitulo o values(o.numero, o.titulo, o.codigoCuestionario) VALUES (" + 
											":p_numero, :p_titulo, :p_codigoCuestionario)");
		
		query.setInteger("p_numero", numero);
		query.setString("p_titulo", titulo);
		query.setInteger("p_codigoCuestionario", codigoCuestionario);
		tx.commit();
	}
	public void actualizar(Integer codigoCapitulo, Integer numero, String titulo, Integer codigoCuestionario)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("update from Capitulo o set o.numero=:p_numero, o.titulo=:p_titulo, " + 
												"o.codigoCuestionario=:p_codigoCuestionario " + 
											"where o.codigoCapitulo=:p_codigoCapitulo");
		
		query.setInteger("p_codigoCapitulo", codigoCapitulo);
		query.setInteger("p_numero", numero);
		query.setString("p_titulo", titulo);
		query.setInteger("p_codigoCuestionario", codigoCuestionario);
		tx.commit();
	}
	public void eliminar(Integer codigoCapitulo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from Capitulo o where o.codigoCapitulo=:p_codigoCapitulo");
		
		query.setInteger("p_codigoCapitulo", codigoCapitulo);
		tx.commit();
		
	}
}

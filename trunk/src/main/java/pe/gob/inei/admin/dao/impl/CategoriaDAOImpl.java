package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.CategoriaDAO;
import pe.gob.inei.sistencuesta.Categoria;
import pe.gob.inei.sistencuesta.Encuesta;

public class CategoriaDAOImpl extends GenericDAOImpl<Categoria, Integer> implements CategoriaDAO{	

	@SuppressWarnings("unchecked")
	public List<Categoria> buscar(){
		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Categoria o ");
		List<Categoria> lista = query.list();
		tx.commit();
		return lista;
	}
	@SuppressWarnings("unchecked")
	public Categoria buscarxCodigo(Integer codigoCategoria){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Categoria o where o.codigoCategoria=:p_codigoCategoria ");
		query.setInteger("p_codigoCategoria", codigoCategoria);
		Categoria categoria= (Categoria) query.uniqueResult();
		tx.commit();
		return categoria;
	}
	
}
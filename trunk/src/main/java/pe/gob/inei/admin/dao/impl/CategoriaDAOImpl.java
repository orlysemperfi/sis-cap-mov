package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.CategoriaDAO;
import pe.gob.inei.sistencuesta.Categoria;

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
}

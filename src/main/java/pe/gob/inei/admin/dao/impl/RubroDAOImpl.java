package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.RubroDAO;
import pe.gob.inei.sistencuesta.Rubro;

public class RubroDAOImpl extends GenericDAOImpl<Rubro, Integer> implements RubroDAO{	

	@SuppressWarnings("unchecked")
	public List<Rubro> buscar(){
		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Rubro o ");
		List<Rubro> lista = query.list();
		tx.commit();
		return lista;
		/*
		
		Query query = session.createSQLQuery(" { CALL sp_mantenimientoRubro(:TipoConsulta,:CodigoRubro,:Nombre) }");
        //query.setResultTransformer(Transformers.aliasToBean(Rubro.class));
        query.setParameter("TipoConsulta", "SELECT");
        query.setParameter("CodigoRubro", "");
        query.setParameter("Nombre", "");
        List<Rubro> lista = query.list();
		tx.commit();
		return lista;
        
        */
	}
	
	public Rubro buscarPorCodigo(Integer codigoRubro)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Rubro o where o.codigoRubro=:p_codigoRubro ");
		query.setInteger("p_codigoRubro", codigoRubro);
		Rubro rubro = (Rubro)query.uniqueResult();
		tx.commit();
		return rubro;		
	}
	
}

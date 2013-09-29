package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.Ubigeo;

public class UbigeoDAOImpl extends GenericDAOImpl<Ubigeo, String> implements UbigeoDAO{
	
	@SuppressWarnings("unchecked")
	public List<Ubigeo> BuscarPorDepartamento(){
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoProvincia='00' and o.codigoDistrito='00'");
		List<Ubigeo> ubigeo = query.list();
		tx.commit();
		return ubigeo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ubigeo> BuscarPorProvincia(String codDepartamento) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoDepartamento=:p_codigoDepartamento and o.codigoProvincia<>'00' and o.codigoDistrito='00'");
		query.setString("p_codigoDepartamento", codDepartamento);	
		List<Ubigeo> ubigeo = query.list();
		tx.commit();
		return ubigeo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ubigeo> BuscarPorDistrito(String codDepartamento, String codProvincia) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoDepartamento=:p_codigoDepartamento and o.codigoProvincia=:p_codigoProvincia and o.codigoDistrito<>'00'");
		query.setString("p_codigoDepartamento", codDepartamento);
		query.setString("p_codigoProvincia", codProvincia);
		List<Ubigeo> ubigeo = query.list();
		tx.commit();
		return ubigeo;
	}
	@SuppressWarnings("unchecked")
	public List<Ubigeo> buscarDepartamento()
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoProvincia='00' and o.codigoDistrito='00' ");
		List<Ubigeo> lista = query.list();
		tx.commit();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ubigeo> buscarProvincia(String codigoDepartamento)
	{		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoDepartamento=:p_codigoDepartamento and o.codigoProvincia<>'00'  and o.codigoDistrito='00' ");
		query.setString("p_codigoDepartamento", codigoDepartamento);
		List<Ubigeo> lista = query.list();
		tx.commit();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ubigeo> buscarDistrito(String codigoDepartamento, String codigoProvincia)
	{		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoDepartamento=:p_codigoDepartamento and o.codigoProvincia=:p_codigoProvincia and o.codigoDistrito<>'00' ");
		query.setString("p_codigoDepartamento", codigoDepartamento);
		query.setString("p_codigoProvincia", codigoProvincia);
		List<Ubigeo> lista = query.list();
		tx.commit();
		return lista;		
	}

	@SuppressWarnings("unchecked")
	public List<Ubigeo> buscarUbigeoxCodigos(String codigos)
	{		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoUbigeo IN ("+codigos+") ");
		List<Ubigeo> lista = query.list();
		tx.commit();
		return lista;		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Ubigeo> buscarUbigeoPorMarcoMuestral(String codigoMarcoMuestral)
	{		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoUbigeo=:p_codigoMarcoMuestral");
		query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		List<Ubigeo> lista = query.list();
		tx.commit();
		return lista;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Ubigeo> buscarDepartamentoNoMarcoMuestral(String codigoMarcoMuestral)
	{		
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoProvincia='00' and o.codigoDistrito='00' ");
		//query.setString("p_codigoMarcoMuestral", codigoMarcoMuestral);
		List<Ubigeo> lista = query.list();
		tx.commit();
		return lista;		
	}

	public Ubigeo BuscarPorCodigo(String codDepartamento,
			String codProvincia, String codDistrito) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Ubigeo o where o.codigoDepartamento=:p_codigoDepartamento and o.codigoProvincia=:p_codigoProvincia and o.codigoDistrito=:p_codigoDistrito");
		query.setString("p_codigoDepartamento", codDepartamento);
		query.setString("p_codigoProvincia", codProvincia);
		query.setString("p_codigoDistrito", codDistrito);
		Ubigeo ubigeo = (Ubigeo) query.uniqueResult();
		tx.commit();
		return ubigeo;
	}

}

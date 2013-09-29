package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.CuestionarioDAO;
import pe.gob.inei.admin.dao.CategoriaDAO;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.sistencuesta.Cuestionario;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.Categoria;
import pe.gob.inei.sistencuesta.Capitulo;
import pe.gob.inei.sistencuesta.Pregunta;
import pe.gob.inei.sistencuesta.Respuesta;

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

	public void registrar(String codigoEncuesta, Integer codigoCategoria, Cuestionario cuestionario)
	{	
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		CategoriaDAO categoriaDAO=DAOFactory.getInstance().getCategoriaDAO();

		cuestionario.setEncuesta(encuestaDAO.buscarxCodigo(codigoEncuesta));
		cuestionario.setCategoria(categoriaDAO.buscarxCodigo(codigoCategoria));
		
		save(cuestionario);
	}
	
	public void actualizar(String codigoEncuesta, Integer codigoCategoria, Cuestionario cuestionario)
	{
		CategoriaDAO categoriaDAO=DAOFactory.getInstance().getCategoriaDAO();
		cuestionario.setCategoria(categoriaDAO.buscarxCodigo(codigoCategoria));		
		update(cuestionario);		
	}	
	
	public void eliminar(Integer codigoCuestionario)
	{	
		deleteById(codigoCuestionario);		
	}
}

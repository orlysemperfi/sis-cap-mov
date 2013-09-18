package pe.gob.inei.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.PreguntaDAO;
import pe.gob.inei.sistencuesta.Pregunta;

public class PreguntaDAOImpl extends GenericDAOImpl<Pregunta, Integer> implements PreguntaDAO{
	
	@SuppressWarnings("unchecked")
	public List<Pregunta> buscar(Integer codigoCapitulo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Pregunta o where o.codigoCapitulo=:p_codigoCapitulo");
		query.setInteger("p_codigoCapitulo", codigoCapitulo);
		List<Pregunta> lista = query.list();
		tx.commit();
		return lista;		
	}
	public Pregunta buscarxCodigo(Integer codigoPregunta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Pregunta o where o.codigoPregunta=:p_codigoPregunta");
		query.setInteger("p_codigoPregunta", codigoPregunta);
		Pregunta pregunta = (Pregunta) query.uniqueResult();
		tx.commit();
		return pregunta;
		
	}
	public void registrar(Integer numero, Integer nivelPregunta, String descripcion, String tipoPregunta, Integer longitudDato, String tipoValidacion, String opcional, String respuestaOtros, Integer codigoCategoria, Integer codigoCapitulo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into Pregunta o values(o.numero, o.nivelPregunta, o.descripcion, o.tipoPregunta, o.longitudDato, " + 
																"o.tipoValidacion, o.opcional, o.respuestaOtros, o.codigoCategoria, o.codigoCapitulo ) VALUES (" +
											":p_numero, :p_nivelPregunta, :p_descripcion, :p_tipoPregunta, :p_longitudDato, "+ 
											":p_tipoValidacion, :p_opcional, :p_respuestaOtros, :p_codigoCategoria, :p_codigoCapitulo)");
		
		query.setInteger("p_numero", numero);
		query.setInteger("p_nivelPregunta", nivelPregunta);
		query.setString("p_descripcion", descripcion);
		query.setString("p_tipoPregunta", tipoPregunta);
		query.setInteger("p_longitudDato", longitudDato);
		query.setString("p_tipoValidacion", tipoValidacion);		
		query.setString("p_opcional", opcional);
		query.setString("p_respuestaOtros", respuestaOtros);
		query.setInteger("p_codigoCategoria", codigoCategoria);
		query.setInteger("p_codigoCapitulo", codigoCapitulo);
		tx.commit();
	}
	public void actualizar(Integer codigoPregunta, Integer numero, Integer nivelPregunta, String descripcion, String tipoPregunta, Integer longitudDato, String tipoValidacion, String opcional, String respuestaOtros, Integer codigoCategoria, Integer codigoCapitulo)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("update from Pregunta o  set o.numero=:p_numero, o.nivelPregunta=:p_nivelPregunta, o.descripcion=:p_descripcion, " + 
													"o.tipoPregunta=:p_tipoPregunta, o.longitudDato=:p_longitudDato, o.tipoValidacion=:p_tipoValidacion, " + 
														"o.opcional=:p_opcional, o.respuestaOtros=:p_respuestaOtros, o.codigoCategoria=:p_codigoCategoria," +
													" o.codigoCapitulo=:p_codigoCapitulo where o.codigoPregunta=:p_codigoPregunta ");
		
		query.setInteger("p_codigoPregunta", codigoPregunta);
		query.setInteger("p_numero", numero);
		query.setInteger("p_nivelPregunta", nivelPregunta);
		query.setString("p_descripcion", descripcion);
		query.setString("p_tipoPregunta", tipoPregunta);
		query.setInteger("p_longitudDato", longitudDato);
		query.setString("p_tipoValidacion", tipoValidacion);		
		query.setString("p_opcional", opcional);
		query.setString("p_respuestaOtros", respuestaOtros);
		query.setInteger("p_codigoCategoria", codigoCategoria);
		query.setInteger("p_codigoCapitulo", codigoCapitulo);
		tx.commit();		
	}
	public void eliminar(Integer codigoPregunta)
	{
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete from Pregunta o where o.codigoPregunta=:p_codigoPregunta ");
		
		query.setInteger("p_codigoPregunta", codigoPregunta);
		tx.commit();
	}

}

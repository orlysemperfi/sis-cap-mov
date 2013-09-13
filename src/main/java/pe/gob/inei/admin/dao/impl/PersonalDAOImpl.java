package pe.gob.inei.admin.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.sistencuesta.Personal;

public class PersonalDAOImpl extends GenericDAOImpl<Personal, Integer> implements PersonalDAO {

	public Personal buscarPorDni(String dni) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select o from Personal o where o.numeroDocumento=:p_numeroDocumento");
		query.setString("p_numeroDocumento", dni);
		Personal personal = (Personal) query.uniqueResult();
		tx.commit();
		return personal;
	}

}
 
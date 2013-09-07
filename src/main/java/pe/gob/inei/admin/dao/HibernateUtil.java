package pe.gob.inei.admin.dao;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pe.gob.inei.admin.util.Constantes;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration ();
			configuration.configure();
			sessionFactory = configuration.buildSessionFactory();
		}
	}
	
	public static Session crearSession () {
		return sessionFactory.openSession();
	}
	
	public static Session getCurrentSession () {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		return (Session) context.getSessionMap().get(Constantes.SESION_HIBERNATE);
	}
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}

package pe.gob.inei.admin.aplicacion;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.inei.admin.dao.HibernateUtil;
import pe.gob.inei.admin.util.Constantes;

public class HibernateListener implements HttpSessionListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(HibernateListener.class);
	
    public void sessionCreated(HttpSessionEvent event) {
    	LOG.debug("sessionCreated - event: {}", event);
    	HttpSession httpSession = event.getSession();
    	httpSession.setAttribute(Constantes.SESION_HIBERNATE, HibernateUtil.crearSession());
    	LOG.debug("Sesion creada");
    }

    public void sessionDestroyed(HttpSessionEvent event) {
    	LOG.debug("sessionDestroyed - event: {}", event);
    	HttpSession httpSession = event.getSession();
    	Session session = (Session) httpSession.getAttribute(Constantes.SESION_HIBERNATE);
    	session.close();
    	LOG.debug("Sesion cerrada");
    }
	
}

package pe.gob.inei.admin.dao;

import pe.gob.inei.admin.dao.impl.DAOFactoryImpl;

public abstract class DAOFactory {

	private static DAOFactory daoFactory;
 
	public abstract PersonalDAO getPersonalDAO();
	public abstract RutaPersonalDAO getRutaPersonalDAO();
	public abstract UbigeoDAO getUbigeoDao();
	
	public synchronized static DAOFactory getInstance () {
		if (daoFactory==null) {
			daoFactory = new DAOFactoryImpl();
		}
		return daoFactory;
	}
	
}

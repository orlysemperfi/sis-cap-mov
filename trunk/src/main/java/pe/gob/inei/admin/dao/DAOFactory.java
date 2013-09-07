package pe.gob.inei.admin.dao;

import pe.gob.inei.admin.dao.impl.DAOFactoryImpl;

public abstract class DAOFactory {

	private static DAOFactory daoFactory;

	public synchronized static DAOFactory getInstance () {
		if (daoFactory==null) {
			daoFactory = new DAOFactoryImpl();
		}
		return daoFactory;
	}
	
}

package pe.gob.inei.admin.dao.impl;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;

public class DAOFactoryImpl extends DAOFactory {
	private PersonalDAOImpl personalDAOImpl = new PersonalDAOImpl();
	private RutaPersonalDAOImpl rutaPersonalDAOImpl=new RutaPersonalDAOImpl();
	public DAOFactoryImpl() {
	}

	@Override
	public PersonalDAO getPersonalDAO() {
		

		return personalDAOImpl;
	}

	@Override
	public RutaPersonalDAO getRutaPersonalDAO() {
		// TODO Auto-generated method stub
		return rutaPersonalDAOImpl;
	}
	
}

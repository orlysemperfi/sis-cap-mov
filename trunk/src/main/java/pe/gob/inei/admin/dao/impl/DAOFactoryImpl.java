package pe.gob.inei.admin.dao.impl;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;

public class DAOFactoryImpl extends DAOFactory {
	private PersonalDAOImpl personalDAOImpl = new PersonalDAOImpl();
	private RutaPersonalDAOImpl rutaPersonalDAOImpl=new RutaPersonalDAOImpl();
	private UbigeoDAOImpl ubigeoDAOImpl=new UbigeoDAOImpl();
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

	@Override
	public UbigeoDAO getUbigeoDao() {
		// TODO Auto-generated method stub
		return ubigeoDAOImpl;
	}
	
}

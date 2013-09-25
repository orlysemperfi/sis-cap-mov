package pe.gob.inei.admin.dao;

import pe.gob.inei.admin.dao.impl.DAOFactoryImpl;

public abstract class DAOFactory {

	private static DAOFactory daoFactory;
 
	public abstract PersonalDAO getPersonalDAO();
	public abstract RutaPersonalDAO getRutaPersonalDAO();
	public abstract UbigeoDAO getUbigeoDAO();
	public abstract RutaDAO getRutaDAO();
	public abstract EstablecimientoDAO getEstablecimientoDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	public abstract EncuestaDAO getEncuestaDAO(); 
	public abstract RubroDAO getRubroDAO();
	public abstract MarcoMuestralDAO getMarcoMuestralDAO();
	public abstract CuestionarioDAO getCuestionarioDAO();
	
	public synchronized static DAOFactory getInstance () {
		if (daoFactory==null) {
			daoFactory = new DAOFactoryImpl();
		}
		return daoFactory;
	}
	
}

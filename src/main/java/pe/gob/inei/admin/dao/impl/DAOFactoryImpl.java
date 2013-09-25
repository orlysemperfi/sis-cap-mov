package pe.gob.inei.admin.dao.impl;

import pe.gob.inei.admin.dao.CategoriaDAO;
import pe.gob.inei.admin.dao.CuestionarioDAO;
import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.admin.dao.EstablecimientoDAO;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RubroDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;

public class DAOFactoryImpl extends DAOFactory {
	private PersonalDAOImpl personalDAOImpl = new PersonalDAOImpl();
	private RutaPersonalDAOImpl rutaPersonalDAOImpl=new RutaPersonalDAOImpl();
	private UbigeoDAOImpl ubigeoDAOImpl=new UbigeoDAOImpl();
	private EncuestaDAOImpl encuestaDAOImpl=new EncuestaDAOImpl();
	private RubroDAOImpl rubroDAOImpl=new RubroDAOImpl();
	private MarcoMuestralDAOImpl marcoMuestralDAOImpl=new MarcoMuestralDAOImpl();
	private RutaDAOImpl rutaDAOImpl=new RutaDAOImpl();
	private EstablecimientoDAOImpl establecimientoDAOImpl=new EstablecimientoDAOImpl();
	private CategoriaDAOImpl categoriaDAOImpl=new CategoriaDAOImpl();
	private CuestionarioDAOImpl cuestionarioDAOImpl=new CuestionarioDAOImpl();

	@Override
	public PersonalDAO getPersonalDAO() {
		return personalDAOImpl;
	}

	@Override
	public RutaPersonalDAO getRutaPersonalDAO() {
		return rutaPersonalDAOImpl;
	}

	@Override
	public UbigeoDAO getUbigeoDAO() {
		return ubigeoDAOImpl;
	}

	@Override
	public RutaDAO getRutaDAO() {
		return rutaDAOImpl;
	}

	@Override
	public EstablecimientoDAO getEstablecimientoDAO() {
		return establecimientoDAOImpl;
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return categoriaDAOImpl;
	}

	@Override
	public EncuestaDAO getEncuestaDAO() {
		return encuestaDAOImpl;
	}

	@Override
	public RubroDAO getRubroDAO() {
		return rubroDAOImpl;
	}

	@Override
	public MarcoMuestralDAO getMarcoMuestralDAO() {
		return marcoMuestralDAOImpl;
	}

	@Override
	public CuestionarioDAO getCuestionarioDAO() {
		return cuestionarioDAOImpl;
	}
	
}

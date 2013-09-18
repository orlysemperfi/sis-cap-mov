package pe.gob.inei.admin.dao.impl;

import pe.gob.inei.admin.dao.CategoriaDAO;
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
	public UbigeoDAO getUbigeoDAO() {
		// TODO Auto-generated method stub
		return ubigeoDAOImpl;
	}

	@Override
	public RutaDAO getRutaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstablecimientoDAO getEstablecimientoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	public EncuestaDAOImpl getEncuestaDAOImpl() {
		return encuestaDAOImpl;
	}

	public void setEncuestaDAOImpl(EncuestaDAOImpl encuestaDAOImpl) {
		this.encuestaDAOImpl = encuestaDAOImpl;
	}

	public RubroDAOImpl getRubroDAOImpl() {
		return rubroDAOImpl;
	}

	public void setRubroDAOImpl(RubroDAOImpl rubroDAOImpl) {
		this.rubroDAOImpl = rubroDAOImpl;
	}

	public MarcoMuestralDAOImpl getMarcoMuestralDAOImpl() {
		return marcoMuestralDAOImpl;
	}

	public void setMarcoMuestralDAOImpl(MarcoMuestralDAOImpl marcoMuestralDAOImpl) {
		this.marcoMuestralDAOImpl = marcoMuestralDAOImpl;
	}

	public RutaDAOImpl getRutaDAOImpl() {
		return rutaDAOImpl;
	}

	public void setRutaDAOImpl(RutaDAOImpl rutaDAOImpl) {
		this.rutaDAOImpl = rutaDAOImpl;
	}

	public EstablecimientoDAOImpl getEstablecimientoDAOImpl() {
		return establecimientoDAOImpl;
	}

	public void setEstablecimientoDAOImpl(EstablecimientoDAOImpl establecimientoDAOImpl) {
		this.establecimientoDAOImpl = establecimientoDAOImpl;
	}

	public CategoriaDAOImpl getCategoriaDAOImpl() {
		return categoriaDAOImpl;
	}

	public void setCategoriaDAOImpl(CategoriaDAOImpl categoriaDAOImpl) {
		this.categoriaDAOImpl = categoriaDAOImpl;
	}

	@Override
	public EncuestaDAO getEncuestaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RubroDAO getRubroDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarcoMuestralDAO getMarcoMuestralDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

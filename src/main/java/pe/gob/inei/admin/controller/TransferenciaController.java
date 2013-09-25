package pe.gob.inei.admin.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.Ruta;
import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.Ubigeo;

@ManagedBean(name = "transferencia")
@ViewScoped
public class TransferenciaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6256978288485403182L;
	private String dni;
	private List<RutaPersonal> rutaPersonal;
	private String ruta;
	private String codDep;
	private String codProv;
	private String codDist;
	private String numRuta;
	private List<Ubigeo> departamentos;
	private List<Ubigeo> provincias;
	private List<Ubigeo> distritos;
	private List<Ubigeo> distritosGen;
	private List<Ruta> rutas;

	public TransferenciaController() {
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		departamentos = ubigeoDAO.BuscarPorDepartamento();
		
	}

	public void buscar(ActionEvent event) {
		PersonalDAO personalDAO = DAOFactory.getInstance().getPersonalDAO();
		Personal personal = personalDAO.buscarPorDni(dni);
		if (personal != null) {
			RutaPersonalDAO rutaPersonalDAO = DAOFactory.getInstance()
					.getRutaPersonalDAO();
			rutaPersonal = rutaPersonalDAO.buscarPersona(personal
					.getCodigoPersonal());
		}
	}

	public void buscarProvincia() {
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		provincias = ubigeoDAO.BuscarPorProvincia(codDep);
	}

	public void buscarDistrito() {
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		distritos = ubigeoDAO.BuscarPorDistrito(codDep, codProv);

	}
	public void buscarRutas(){
		System.out.println("ubigeo:"+codDep+"-"+codProv+"-"+codDist);
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		Ubigeo distritosGen = ubigeoDAO.BuscarPorCodigo(codDep, codProv,codDist);
		System.out.println(distritosGen.getCodigoUbigeo());
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		rutas=rutaDAO.buscarRutaPorUbigeo(distritosGen.getCodigoUbigeo());
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<RutaPersonal> getRutaPersonal() {
		return rutaPersonal;
	}

	public void setRutaPersonal(List<RutaPersonal> rutaPersonal) {
		this.rutaPersonal = rutaPersonal;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public List<Ubigeo> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Ubigeo> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Ubigeo> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Ubigeo> provincias) {
		this.provincias = provincias;
	}

	public String getCodDep() {
		return codDep;
	}

	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}

	public String getCodProv() {
		return codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getCodDist() {
		return codDist;
	}

	public void setCodDist(String codDist) {
		this.codDist = codDist;
	}

	public List<Ubigeo> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Ubigeo> distritos) {
		this.distritos = distritos;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public String getNumRuta() {
		return numRuta;
	}

	public void setNumRuta(String numRuta) {
		this.numRuta = numRuta;
	}

	public List<Ubigeo> getDistritosGen() {
		return distritosGen;
	}

	public void setDistritosGen(List<Ubigeo> distritosGen) {
		this.distritosGen = distritosGen;
	}

}

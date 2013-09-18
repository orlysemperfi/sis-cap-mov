package pe.gob.inei.admin.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.Ubigeo;

@ManagedBean(name="desempeno")
@ViewScoped
public class DesempenoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dni;
	private List<RutaPersonal> rutaPersonal;
	private String ruta;
	private String codDepartamento;
	private String codProvincia;
	private List<Ubigeo> ubigeo;
	private List<Ubigeo> ubigeoProvincia;
	public DesempenoController() {
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDao();
		ubigeo= ubigeoDAO.BuscarPorDepartamento();
		}

	public void buscar(ActionEvent event){
		PersonalDAO personalDAO=DAOFactory.getInstance().getPersonalDAO();
		Personal personal=personalDAO.buscarPorDni(dni);
		if (personal!=null){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		rutaPersonal =rutaPersonalDAO.buscarPersona(personal.getCodigoPersonal());
		}
	}
	public void buscarProvincia(ActionEvent event){
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDao();
		ubigeoProvincia=ubigeoDAO.BuscarPorProvincia(codDepartamento);
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

	public List<Ubigeo> getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(List<Ubigeo> ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public List<Ubigeo> getUbigeoProvincia() {
		return ubigeoProvincia;
	}

	public void setUbigeoProvincia(List<Ubigeo> ubigeoProvincia) {
		this.ubigeoProvincia = ubigeoProvincia;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	
}

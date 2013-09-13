package pe.gob.inei.admin.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.RutaPersonal;

@ManagedBean(name="desempeno")
@ViewScoped
public class DesempenoController implements Serializable {
	
	private String dni;
	private List<RutaPersonal> rutaPersonal;
	public DesempenoController() {
		
	}

	public void buscar(ActionEvent event){
		PersonalDAO personalDAO=DAOFactory.getInstance().getPersonalDAO();
		Personal personal=personalDAO.buscarPorDni(dni);
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		rutaPersonal =rutaPersonalDAO.buscarPersona(personal.getCodigoPersonal());
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

	
}

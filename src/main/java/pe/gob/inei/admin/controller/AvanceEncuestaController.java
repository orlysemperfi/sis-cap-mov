package pe.gob.inei.admin.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.gob.inei.admin.dao.CuestionarioDAO;
import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.sistencuesta.Cuestionario;
import pe.gob.inei.sistencuesta.Encuesta;

@ManagedBean(name="avanceEncuesta")
@ViewScoped
public class AvanceEncuestaController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6757176650150622824L;
	private List<Encuesta> encuestas;
	private List<Cuestionario> cuestionarios;
	private String codEnc;
	private String codCue;

	public AvanceEncuestaController() {
		buscarEncuesta();
	}
	public void buscarEncuesta(){
		EncuestaDAO encuestaDAO= DAOFactory.getInstance().getEncuestaDAO();
		//encuestas=encuestaDAO.buscarEncuestas();
	}
	public void buscarCuestionario(){
		CuestionarioDAO cuestionarioDAO= DAOFactory.getInstance().getCuestionarioDAO();
		cuestionarios=cuestionarioDAO.buscar(codEnc);
	}

	
	public String getCodEnc() {
		return codEnc;
	}

	public void setCodEnc(String codEnc) {
		this.codEnc = codEnc;
	}

	public String getCodCue() {
		return codCue;
	}

	public void setCodCue(String codCue) {
		this.codCue = codCue;
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	public List<Cuestionario> getCuestionarios() {
		return cuestionarios;
	}
	public void setCuestionarios(List<Cuestionario> cuestionarios) {
		this.cuestionarios = cuestionarios;
	}
}

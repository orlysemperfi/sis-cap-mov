package pe.gob.inei.admin.controller;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.MarcoMuestral;
import pe.gob.inei.sistencuesta.Ubigeo;

@ManagedBean(name="marcoMuestral")
@ViewScoped
public class MarcoMuestralController implements Serializable {

	private String codigoMarcoMuestral;
	private Integer año;
	private String descripcion;
	private Integer numeroEncuestas;
	private String tipoUbigeo;
	private String tipoArea;
	private String estado;
	
	private String codigoEncuestas;
	
		
	private List<MarcoMuestral> marcoMuestral;
	private MarcoMuestral selectedMarcoMuestral;
	private List<Ubigeo> ubigeo;
	private List<Ubigeo> ubigeoSeleccionado;
	
	
	
	public MarcoMuestralController() {
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();		
		ubigeo =ubigeoDAO.buscarDepartamento();
		//ubigeoSeleccionado =ubigeoDAO.buscarDepartamento();//ubigeoDAO.buscarUbigeoPorMarcoMuestral("N");
		
	}
	
	public void buscar(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		marcoMuestral=marcoMuestralDAO.buscar("", "", año);
		/*
		if (encuesta!=null){
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		rutaPersonal =rutaPersonalDAO.buscarPersona(encuesta.getCodigoPersonal());
		}
		*/
	}	

	public void buscarPorCodigo(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		selectedMarcoMuestral=marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral);
		
		if (selectedMarcoMuestral!=null){
			UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
			ubigeo =ubigeoDAO.buscarDepartamentoNoMarcoMuestral(codigoMarcoMuestral);
			ubigeoSeleccionado =ubigeoDAO.buscarUbigeoPorMarcoMuestral(codigoMarcoMuestral);
		}		
	}

	public void buscarDepartamento(ActionEvent event){
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		ubigeo =ubigeoDAO.buscarDepartamento();
	}
	public void grabar(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		
		if(codigoMarcoMuestral== "")
		{
			marcoMuestralDAO.registrar(codigoMarcoMuestral, año, descripcion, numeroEncuestas, tipoUbigeo, tipoArea);	
		}
		else
		{
			marcoMuestralDAO.actualizar(codigoMarcoMuestral, año, descripcion, numeroEncuestas, tipoUbigeo, tipoArea);	
		}
	}
	
	public void eliminar(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		marcoMuestralDAO.eliminar(codigoMarcoMuestral);
	}

	public List<MarcoMuestral> getMarcoMuestral() {
		return marcoMuestral;
	}

	public void setMarcoMuestral(List<MarcoMuestral> marcoMuestral) {
		this.marcoMuestral = marcoMuestral;
	}

	public MarcoMuestral getSelectedMarcoMuestral() {
		return selectedMarcoMuestral;
	}

	public void setSelectedMarcoMuestral(MarcoMuestral selectedMarcoMuestral) {
		this.selectedMarcoMuestral = selectedMarcoMuestral;
	}

	public List<Ubigeo> getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(List<Ubigeo> ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getCodigoMarcoMuestral() {
		return codigoMarcoMuestral;
	}
	public void setCodigoMarcoMuestral(String codigoMarcoMuestral) {
		this.codigoMarcoMuestral = codigoMarcoMuestral;
	}
	public Integer getAño() {
		return año;
	}
	public void setAño(Integer año) {
		this.año = año;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getNumeroEncuestas() {
		return numeroEncuestas;
	}
	public void setNumeroEncuestas(Integer numeroEncuestas) {
		this.numeroEncuestas = numeroEncuestas;
	}
	public String getTipoUbigeo() {
		return tipoUbigeo;
	}
	public void setTipoUbigeo(String tipoUbigeo) {
		this.tipoUbigeo = tipoUbigeo;
	}
	public String getTipoArea() {
		return tipoArea;
	}
	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}

	public List<Ubigeo> getUbigeoSeleccionado() {
		return ubigeoSeleccionado;
	}

	public void setUbigeoSeleccionado(List<Ubigeo> ubigeoSeleccionado) {
		this.ubigeoSeleccionado = ubigeoSeleccionado;
	}

	public String getCodigoEncuestas() {
		return codigoEncuestas;
	}

	public void setCodigoEncuestas(String codigoEncuestas) {
		this.codigoEncuestas = codigoEncuestas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

package pe.gob.inei.admin.controller;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.sistencuesta.Establecimiento;
import pe.gob.inei.sistencuesta.Ruta;
import pe.gob.inei.sistencuesta.Ubigeo;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.RutaPersonal;

@ManagedBean(name="asignarCarga")
@ViewScoped
public class AsignarCargaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3035412819443793899L;
	private Integer codigoRuta;
	private Integer codigoPersonal;
	private Integer numeroEncuestas;
	private String fechaInicio;
	private String fechaFin;
	private Integer nroEncuestasPorDia;
	private Integer correlativoInicial;

	private List<RutaPersonal> rutaPersonal;
	private RutaPersonal selectedRutaPersonal;
	
	public AsignarCargaController()
	{
		
	}
	
	public void buscar(ActionEvent event){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		rutaPersonal=rutaPersonalDAO.buscarPersonaRuta(codigoRuta);
	}

	public void buscarPorCodigo(ActionEvent event){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		selectedRutaPersonal=rutaPersonalDAO.buscarPersonaRuta(codigoRuta, codigoPersonal);
	}
	
	public void grabar(ActionEvent event){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();		
		if(codigoRuta== 0)
		{
			rutaPersonalDAO.registrar(codigoRuta, codigoPersonal, numeroEncuestas, fechaInicio, fechaFin, nroEncuestasPorDia, correlativoInicial);	
		}
		else
		{
			rutaPersonalDAO.actualizar(codigoRuta, codigoPersonal, numeroEncuestas, fechaInicio, fechaFin, nroEncuestasPorDia, correlativoInicial);	
		}
	}
	
	public void eliminar(ActionEvent event){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		rutaPersonalDAO.eliminar(codigoRuta, codigoPersonal);
	}
	
	public Integer getCodigoRuta() {
		return codigoRuta;
	}
	public void setCodigoRuta(Integer codigoRuta) {
		this.codigoRuta = codigoRuta;
	}
	public Integer getCodigoPersonal() {
		return codigoPersonal;
	}
	public void setCodigoPersonal(Integer codigoPersonal) {
		this.codigoPersonal = codigoPersonal;
	}
	public Integer getNumeroEncuestas() {
		return numeroEncuestas;
	}
	public void setNumeroEncuestas(Integer numeroEncuestas) {
		this.numeroEncuestas = numeroEncuestas;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getNroEncuestasPorDia() {
		return nroEncuestasPorDia;
	}
	public void setNroEncuestasPorDia(Integer nroEncuestasPorDia) {
		this.nroEncuestasPorDia = nroEncuestasPorDia;
	}
	public Integer getCorrelativoInicial() {
		return correlativoInicial;
	}
	public void setCorrelativoInicial(Integer correlativoInicial) {
		this.correlativoInicial = correlativoInicial;
	}

	public List<RutaPersonal> getRutaPersonal() {
		return rutaPersonal;
	}

	public void setRutaPersonal(List<RutaPersonal> rutaPersonal) {
		this.rutaPersonal = rutaPersonal;
	}

	public RutaPersonal getSelectedRutaPersonal() {
		return selectedRutaPersonal;
	}

	public void setSelectedRutaPersonal(RutaPersonal selectedRutaPersonal) {
		this.selectedRutaPersonal = selectedRutaPersonal;
	}
	
	
}

package pe.gob.inei.admin.controller;
import java.util.Date; 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.admin.dao.RubroDAO;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.Rubro;
import pe.gob.inei.sistencuesta.MarcoMuestral;
//import pe.gob.inei.sistencuesta.TipoArea;

@ManagedBean(name="encuesta")
@ViewScoped
public class EncuestaController implements Serializable {

	
	private String codigoEncuesta;
	private String nombre;
	private Integer año;
	private String descripcion;
	private String objetivo;
	private String fechaInicio;
	private String fechaFin;
	private String tipoArea;
	private Integer codigoRubro;
	private String codigoMarcoMuestral;	
	private Date dFechaInicio;
	private Date dFechaFin;
	
	private List<Encuesta> encuesta;
	private Encuesta selectedEncuesta;
	private List<Rubro> rubro;
	private List<MarcoMuestral> marcoMuestral;
	
	private Boolean pintaPanel;
	private Boolean pintaListado;
	private Boolean agregar;
	private Boolean desactivaCodigo;
	private Boolean verEliminar;
	
	public EncuestaController() {
		RubroDAO rubroDAO=DAOFactory.getInstance().getRubroDAO();
		rubro =rubroDAO.buscar();
		pintaPanel=false;
		pintaListado=true;
		verEliminar=true;
		desactivaCodigo=false;
	}

	public void elegirTipoArea()
	{
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		marcoMuestral =marcoMuestralDAO.buscarPorTipoArea(tipoArea);
	}
	public void buscar(ActionEvent event){
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		encuesta=encuestaDAO.buscar(nombre, año);
	}	

	public void nuevo(ActionEvent event){
		pintaListado=false;
		pintaPanel=true;
		agregar=true;
		verEliminar=true;
		desactivaCodigo=false;
	}

	public void editar(ActionEvent event){
		pintaListado=false;
		pintaPanel=true;
		agregar=false;
		verEliminar=false;
		desactivaCodigo=true;
		
		codigoEncuesta=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmEncuesta");
		
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		selectedEncuesta=encuestaDAO.buscarxCodigo(codigoEncuesta);
		
		nombre=selectedEncuesta.getNombre();
		año=selectedEncuesta.getAño();
		descripcion=selectedEncuesta.getDescripcion();
		objetivo=selectedEncuesta.getObjetivo();
		fechaInicio=selectedEncuesta.getFechainicio();
		fechaFin=selectedEncuesta.getFechafin();
		
		
		dFechaInicio= new GregorianCalendar(Integer.parseInt(fechaInicio.substring(6, 10)),Integer.parseInt(fechaInicio.substring(3, 5))-1,Integer.parseInt(fechaInicio.substring(0, 2))).getTime();
		dFechaFin= new GregorianCalendar(Integer.parseInt(fechaFin.substring(6, 10)),Integer.parseInt(fechaFin.substring(3, 5))-1,Integer.parseInt(fechaFin.substring(0, 2))).getTime();
		//dFechaFin= new GregorianCalendar(2013,10,31).getTime();
		//dFechaFin = new Date();
		
		tipoArea=selectedEncuesta.getTipoArea();
		elegirTipoArea();
		codigoRubro=selectedEncuesta.getRubro().getCodigoRubro();
		codigoMarcoMuestral=selectedEncuesta.getMarcoMuestral().getCodigoMarcoMuestral();	
		
	}
	
	public void limpiar()
	{
		codigoEncuesta="";
		nombre="";
		año=0;
		descripcion="";
		objetivo="";
		fechaInicio="";
		fechaFin="";
		tipoArea="";
		codigoRubro=0;
		codigoMarcoMuestral="";	
		dFechaInicio=null;
		dFechaFin=null;
		
	}
	public void grabar(ActionEvent event){		
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
			
		if(agregar)
			encuestaDAO.registrar(codigoEncuesta, nombre, año, descripcion, objetivo, fechaInicio, fechaFin, tipoArea, codigoRubro, codigoMarcoMuestral);
		else
			encuestaDAO.actualizar(codigoEncuesta, nombre, año, descripcion, objetivo, fechaInicio, fechaFin, tipoArea, codigoRubro, codigoMarcoMuestral);
	public void grabar(ActionEvent event){		
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();		
		String mensajeError="";
		Long fInicio=(long) 0, fFin=(long) 0;
		
		fInicio= Long.parseLong(fechaInicio.substring(6, 10))*10000+Long.parseLong(fechaInicio.substring(3, 5))*100+Long.parseLong(fechaInicio.substring(0, 2));
		fFin= Long.parseLong(fechaFin.substring(6, 10))*10000+Long.parseLong(fechaFin.substring(3, 5))*100+Long.parseLong(fechaFin.substring(0, 2));
		
		if(año<2000) 
			mensajeError="Año no valido";
		else if(fInicio>fFin) 
			mensajeError="La Fecha Final debe ser mayor al Inicial";
		
		if(mensajeError.length()>0)
		{
			FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Encuesta", mensajeError);
	        FacesContext.getCurrentInstance().addMessage(null, message2); 	
		}
		else
		{
			if(agregar)
				encuestaDAO.registrar(codigoEncuesta, nombre, año, descripcion, objetivo, fechaInicio, fechaFin, tipoArea, codigoRubro, codigoMarcoMuestral);
			else
				encuestaDAO.actualizar(codigoEncuesta, nombre, año, descripcion, objetivo, fechaInicio, fechaFin, tipoArea, codigoRubro, codigoMarcoMuestral);
			
			limpiar();
			pintaListado=true;
			pintaPanel=false;
			
			encuesta=encuestaDAO.buscar(nombre, año);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Encuesta", "Registro guardado correctamente" );
	        FacesContext.getCurrentInstance().addMessage(null, message);  
		}
	}
	
	public void cancelar(ActionEvent event){
		limpiar();
		pintaListado=true;
		pintaPanel=false;
	}
	public void preEliminar(ActionEvent event){
		codigoEncuesta=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmEncuesta");
	}
	
	public void eliminar(ActionEvent event){
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		//codigoEncuesta=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmEncuesta");
		encuestaDAO.eliminar(codigoEncuesta);
		limpiar();
		pintaListado=true;
		pintaPanel=false;
		encuesta=encuestaDAO.buscar(nombre, año);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Encuesta", "Registro e correctamente" );
        FacesContext.getCurrentInstance().addMessage(null, message);  
	}
	
	public List<Encuesta> getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(List<Encuesta> encuesta) {
		this.encuesta = encuesta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Encuesta getSelectedEncuesta() {
		return selectedEncuesta;
	}

	public void setSelectedEncuesta(Encuesta selectedEncuesta) {
		this.selectedEncuesta = selectedEncuesta;
	}

	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
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

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getCodigoRubro() {
		return codigoRubro;
	}

	public void setCodigoRubro(Integer codigoRubro) {
		this.codigoRubro = codigoRubro;
	}

	public String getCodigoMarcoMuestral() {
		return codigoMarcoMuestral;
	}

	public void setCodigoMarcoMuestral(String codigoMarcoMuestral) {
		this.codigoMarcoMuestral = codigoMarcoMuestral;
	}

	public List<Rubro> getRubro() {
		return rubro;
	}

	public void setRubro(List<Rubro> rubro) {
		this.rubro = rubro;
	}

	public List<MarcoMuestral> getMarcoMuestral() {
		return marcoMuestral;
	}

	public void setMarcoMuestral(List<MarcoMuestral> marcoMuestral) {
		this.marcoMuestral = marcoMuestral;
	}
/*
	public List<TipoArea> getTipoAreaArr() {
		return tipoAreaArr;
	}

	public void setTipoAreaArr(List<TipoArea> tipoAreaArr) {
		this.tipoAreaArr = tipoAreaArr;
	}
*/
	public Boolean getPintaPanel() {
		return pintaPanel;
	}

	public void setPintaPanel(Boolean pintaPanel) {
		this.pintaPanel = pintaPanel;
	}

	public Boolean getPintaListado() {
		return pintaListado;
	}

	public void setPintaListado(Boolean pintaListado) {
		this.pintaListado = pintaListado;
	}

	public Boolean getAgregar() {
		return agregar;
	}

	public void setAgregar(Boolean agregar) {
		this.agregar = agregar;
	}

	public Boolean getVerEliminar() {
		return verEliminar;
	}

	public void setVerEliminar(Boolean verEliminar) {
		this.verEliminar = verEliminar;
	}

	public Boolean getDesactivaCodigo() {
		return desactivaCodigo;
	}

	public void setDesactivaCodigo(Boolean desactivaCodigo) {
		this.desactivaCodigo = desactivaCodigo;
	}

	public Date getdFechaInicio() {
		return dFechaInicio;
	}

	public void setdFechaInicio(Date dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}

	public Date getdFechaFin() {
		return dFechaFin;
	}

	public void setdFechaFin(Date dFechaFin) {
		this.dFechaFin = dFechaFin;
	}
	
	
	
}

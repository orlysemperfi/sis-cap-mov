package pe.gob.inei.admin.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.MarcoMuestral;
import pe.gob.inei.sistencuesta.Ubigeo;

import javax.faces.application.FacesMessage;  

import org.primefaces.event.TransferEvent;  
  
import org.primefaces.model.DualListModel; 

@ManagedBean(name="marco_Muestral")
@ViewScoped
public class MarcoMuestral_Controller implements Serializable {

	private String codigoMarcoMuestral;
	private Integer a�o;
	private String descripcion;
	private Integer numeroEncuestas;
	private String tipoUbigeo;
	private String tipoArea;
	private String estado;
	
	private String codigoEncuestas;
	
		
	private List<MarcoMuestral> marcoMuestral;
	private MarcoMuestral selectedMarcoMuestral;
	
	private DualListModel<Ubigeo> ubigeoDual;
	private List<Ubigeo> ubigeo;
	//private List<Ubigeo> ubigeoSeleccionado;
	
	private List<String> ubigeoDep;
	
	private Boolean pintaPanel;
	private Boolean pintaListado;
	private Boolean agregar;
	private Boolean desactivaCodigo;
	private Boolean verEliminar;


	 public void onTransfer(TransferEvent event) {		 
	        StringBuilder builder = new StringBuilder();  
	        for(Object item : event.getItems()) {  
	            builder.append(((Ubigeo) item).getNombre()).append("<br />");  
	        }  
	        /*
	        FacesMessage msg = new FacesMessage();  
	        msg.setSeverity(FacesMessage.SEVERITY_INFO);  
	        msg.setSummary("Items Transferred");  
	        msg.setDetail(builder.toString());  
	          
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        */
	        descripcion=builder.toString();
	    }  
	 
	public MarcoMuestral_Controller() {
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();

		//List<Ubigeo> ubigeo;
		List<Ubigeo> ubigeoSeleccionado;
		
		ubigeo =ubigeoDAO.buscarDepartamento();
		ubigeoSeleccionado = ubigeoDAO.buscarUbigeoPorMarcoMuestral("NS");
		
		ubigeoDual= new DualListModel<Ubigeo>(ubigeo, ubigeoSeleccionado);
		ubigeoDep=new ArrayList<String>();
		
		pintaPanel=false;
		pintaListado=true;
		verEliminar=true;
		desactivaCodigo=false;
	}
	public void nuevo(ActionEvent event){
		pintaListado=false;
		pintaPanel=true;
		agregar=true;
		verEliminar=true;
		desactivaCodigo=false;	
		estado="S";
		
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		//List<Ubigeo> ubigeo;
		List<Ubigeo> ubigeoSeleccionado;
		
		ubigeo =ubigeoDAO.buscarDepartamento();
		ubigeoSeleccionado = ubigeoDAO.buscarUbigeoPorMarcoMuestral("NS");
		ubigeoDual= new DualListModel<Ubigeo>(ubigeo, ubigeoSeleccionado); 
		
		
	}

	public void editar(ActionEvent event){
		//List<Ubigeo> ubigeo;
		List<Ubigeo> ubigeoSeleccionado;
		
		
		pintaListado=false;
		pintaPanel=true;
		agregar=false;
		verEliminar=false;
		desactivaCodigo=true;
		
		codigoMarcoMuestral=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmMarcoMuestral");
		
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		selectedMarcoMuestral=marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral);

		a�o=selectedMarcoMuestral.getA�o();
		descripcion=selectedMarcoMuestral.getDescripcion();
		numeroEncuestas=selectedMarcoMuestral.getNumeroEncuestas();
		tipoUbigeo=selectedMarcoMuestral.getTipoUbigeo();
		tipoArea=selectedMarcoMuestral.getTipoArea();
		estado=selectedMarcoMuestral.getEstado();
		

		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		ubigeo =ubigeoDAO.buscarDepartamento();// ubigeoDAO.buscarDepartamentoNoMarcoMuestral(codigoMarcoMuestral);
		
		ubigeoSeleccionado = new ArrayList<Ubigeo> (selectedMarcoMuestral.getUbigeos());// ubigeoDAO.buscarUbigeoPorMarcoMuestral(codigoMarcoMuestral);
		//ubigeoDual= new DualListModel<Ubigeo>(ubigeo, ubigeoSeleccionado);
		
		ubigeoDep=new ArrayList<String>();
		Integer index;
		for(index=0;index<ubigeoSeleccionado.size();index++)
		{
			ubigeoDep.add(ubigeoSeleccionado.get(index).getCodigoUbigeo());
		}
		
	}
	
	public void limpiar()
	{
		codigoMarcoMuestral="";
		a�o=0;
		descripcion="";
		numeroEncuestas=0;
		tipoUbigeo="";
		tipoArea="";
		estado="S";		
	}
	
	public void buscar(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		marcoMuestral=marcoMuestralDAO.buscar(descripcion, "", a�o);
		/*
		if (encuesta!=null){
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		rutaPersonal =rutaPersonalDAO.buscarPersona(encuesta.getCodigoPersonal());
		}
		*/
	}	
/*
	public void buscarPorCodigo(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		selectedMarcoMuestral=marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral);
		
		if (selectedMarcoMuestral!=null){
			UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
			ubigeo =ubigeoDAO.buscarDepartamentoNoMarcoMuestral(codigoMarcoMuestral);
			ubigeoSeleccionado =ubigeoDAO.buscarUbigeoPorMarcoMuestral(codigoMarcoMuestral);
		}		
	}
*/
	/*
	public void buscarDepartamento(ActionEvent event){
		List<Ubigeo> ubigeo;
		List<Ubigeo> ubigeoSeleccionado;
		
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		ubigeo =ubigeoDAO.buscarDepartamento();
	}
	*/
	public void grabar(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		Integer index;
		String codUbigeo="'0'"; 
		
		for(index=0;index<ubigeoDep.size();index++)
		{
			codUbigeo+=", '"+ubigeoDep.get(index)+"' ";
		}
		
		List<Ubigeo> ubigeos= ubigeoDAO.buscarUbigeoxCodigos(codUbigeo);		
		
		if(agregar)
			marcoMuestralDAO.registrar(codigoMarcoMuestral, a�o, descripcion, numeroEncuestas, tipoUbigeo, tipoArea, estado, ubigeos);
		else
			marcoMuestralDAO.actualizar(codigoMarcoMuestral, a�o, descripcion, numeroEncuestas, tipoUbigeo, tipoArea, estado, ubigeos);
		
		limpiar();
		
		
		pintaListado=true;
		pintaPanel=false;
		
		marcoMuestral=marcoMuestralDAO.buscar("", "", 0);
		
	}
	
	public void eliminar(ActionEvent event){
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		marcoMuestralDAO.eliminar(codigoMarcoMuestral);
		
		limpiar();
		pintaListado=true;
		pintaPanel=false;
		marcoMuestral=marcoMuestralDAO.buscar("", "", a�o);
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
	public Integer getA�o() {
		return a�o;
	}
	public void setA�o(Integer a�o) {
		this.a�o = a�o;
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
/*
	public List<Ubigeo> getUbigeoSeleccionado() {
		return ubigeoSeleccionado;
	}

	public void setUbigeoSeleccionado(List<Ubigeo> ubigeoSeleccionado) {
		this.ubigeoSeleccionado = ubigeoSeleccionado;
	}
*/
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

	public Boolean getDesactivaCodigo() {
		return desactivaCodigo;
	}

	public void setDesactivaCodigo(Boolean desactivaCodigo) {
		this.desactivaCodigo = desactivaCodigo;
	}

	public Boolean getVerEliminar() {
		return verEliminar;
	}

	public void setVerEliminar(Boolean verEliminar) {
		this.verEliminar = verEliminar;
	}
	public DualListModel<Ubigeo> getUbigeoDual() {
		return ubigeoDual;
	}
	public void setUbigeoDual(DualListModel<Ubigeo> ubigeoDual) {
		this.ubigeoDual = ubigeoDual;
	}

	public List<String> getUbigeoDep() {
		return ubigeoDep;
	}

	public void setUbigeoDep(List<String> ubigeoDep) {
		this.ubigeoDep = ubigeoDep;
	}
	
}

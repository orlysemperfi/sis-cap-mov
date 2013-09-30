package pe.gob.inei.admin.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.EncuestaDAO;
import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.admin.dao.EstablecimientoDAO;
import pe.gob.inei.sistencuesta.Ruta;
import pe.gob.inei.sistencuesta.Ubigeo;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.Establecimiento;

@ManagedBean(name="ruta")
@ViewScoped
public class RutaController implements Serializable {

	private List<Ruta> ruta;
	private List<Establecimiento> establecimiento;
	private List<Establecimiento> selectedEstablecimiento;
	
	private List<Ubigeo> departamento;
	private List<Ubigeo> provincia;
	private List<Ubigeo> distrito;
	
	private String codigoEncuesta;
	private String encuestaNombre;
	private String encuestaAño;
	private Integer encuestaTotal;
	private Integer encuestaAsignada;
	private Integer encuestaFinal;
	
	private Encuesta encuesta;
	private Ruta selectedRuta;
	private Integer codigoRuta;
	
	private String codigoMarcoMuestral;
	private String codigoDepartamento;
	private String codigoProvincia;
	private String codigoDistrito;
	
	private String numeroRuta;
	private String descripcion;
	private Integer numeroEncuestas;
	private Integer correlativoInicial;
	private Integer correlativoFinal;
	private String codigoUbigeo;
	private Integer correlativoActual;
	
	private Boolean pintaPanel;
	private Boolean pintaListado;
	private Boolean agregar;
	private Boolean desactivaCodigo;
	private Boolean verEliminar;
	
	private List<String> strEstablecimiento;
	private Boolean desactivaNuevo;
	
	public RutaController()
	{		
		desactivaNuevo=true;
		strEstablecimiento= new ArrayList<String>();
		
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
		strEstablecimiento=new ArrayList<String>();
		
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		encuesta = encuestaDAO.buscarxCodigo(codigoEncuesta);
		getDatosEncuesta();
	}
	public void calculaCorrelativoFinal()
	{
		correlativoFinal=(correlativoInicial+numeroEncuestas-1);
		if(correlativoFinal<0)correlativoFinal=0;
	}

	public void getDatosEncuesta()
	{
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		encuesta = encuestaDAO.buscarxCodigo(codigoEncuesta);
		List<Ruta> rutaAsignada; 
		Integer index;
		
		encuestaNombre=encuesta.getNombre();
		encuestaTotal=encuesta.getMarcoMuestral().getNumeroEncuestas();
		departamento=new ArrayList<Ubigeo>(encuesta.getMarcoMuestral().getUbigeos());  //encuesta.getMarcoMuestral().getUbigeos());
		
		encuestaFinal=0;
		encuestaAsignada=0;
		if(encuesta.getRutas()!=null)
		{
			if(encuesta.getRutas().size()>0)
			{
				rutaAsignada = new ArrayList<Ruta> (encuesta.getRutas());
				for(index=0;index<rutaAsignada.size();index++)
				{
					encuestaAsignada+=rutaAsignada.get(index).getNumeroEncuestas();
					if(rutaAsignada.get(index).getCorrelativoFinal()>encuestaFinal)
						encuestaFinal=rutaAsignada.get(index).getCorrelativoFinal();
				}
				encuestaAsignada=encuestaAsignada-numeroEncuestas;
			}
		}
	}
	
	
	public void editar(ActionEvent event){
		pintaListado=false;
		pintaPanel=true;
		agregar=false;
		verEliminar=false;
		desactivaCodigo=true;
		strEstablecimiento=new ArrayList<String>();
				
		codigoRuta= Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmRuta"));
		
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		selectedRuta=rutaDAO.buscarxCodigo(codigoRuta);
		
		numeroRuta=selectedRuta.getNumeroRuta();
		descripcion=selectedRuta.getDescripcion();
		numeroEncuestas=selectedRuta.getNumeroEncuestas();
		correlativoInicial=selectedRuta.getCorrelativoInicial();
		correlativoFinal=selectedRuta.getCorrelativoFinal();
		correlativoActual=correlativoInicial;
		

		codigoEncuesta=selectedRuta.getEncuesta().getCodigoEncuesta();
		codigoMarcoMuestral=selectedRuta.getEncuesta().getMarcoMuestral().getCodigoMarcoMuestral();
		getDatosEncuesta();
		
		codigoUbigeo=selectedRuta.getUbigeo().getCodigoUbigeo();
		
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		codigoDepartamento=selectedRuta.getUbigeo().getCodigoDepartamento();		
		provincia=ubigeoDAO.buscarProvincia(codigoDepartamento);
		
		codigoProvincia=selectedRuta.getUbigeo().getCodigoProvincia();
		distrito=ubigeoDAO.buscarDistrito(codigoDepartamento, codigoProvincia);		
		codigoDistrito=selectedRuta.getUbigeo().getCodigoDistrito();

		distrito=ubigeoDAO.buscarDistrito(codigoDepartamento, codigoProvincia);
		
		EstablecimientoDAO establecimientoDAO=DAOFactory.getInstance().getEstablecimientoDAO();
		establecimiento=establecimientoDAO.buscar(codigoDepartamento+codigoProvincia+codigoDistrito);

		selectedEstablecimiento = new ArrayList<Establecimiento> (selectedRuta.getEstablecimientos());
		
		strEstablecimiento=new ArrayList<String>();
		Integer index;
		for(index=0;index<selectedEstablecimiento.size();index++)
		{
			strEstablecimiento.add(selectedEstablecimiento.get(index).getCodigoEstablecimiento().toString());
		}
		
	}
	
	public void limpiar()
	{
		codigoRuta=0;
		codigoMarcoMuestral="";
		codigoDepartamento="";
		codigoProvincia="";
		codigoDistrito="";
		
		numeroRuta="";
		descripcion="";
		numeroEncuestas=0;
		correlativoInicial=0;
		correlativoFinal=0;
		codigoUbigeo="";
		correlativoActual=0;
		strEstablecimiento=new ArrayList<String>();
	}
	
	public void buscar(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		ruta=rutaDAO.buscar(codigoEncuesta);
		desactivaNuevo=true;
		if(ruta!=null)
		{
			if(ruta.size()>0)
			{
				desactivaNuevo=false;
				encuestaAño= String.valueOf(ruta.get(0).getEncuesta().getAño());
				encuestaNombre=ruta.get(0).getEncuesta().getNombre();
			}
		}
		
		EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
		encuesta=encuestaDAO.buscarxCodigo(codigoEncuesta);
		if(encuesta!=null)
		{
			desactivaNuevo=false;
			encuestaAño= String.valueOf(encuesta.getAño());
			encuestaNombre=encuesta.getNombre();
		}
		
	}
	public void buscarPorCodigo(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		selectedRuta=rutaDAO.buscarxCodigo(codigoRuta);
		if(selectedRuta!=null)
		{
			EstablecimientoDAO establecimientoDAO=DAOFactory.getInstance().getEstablecimientoDAO();
			establecimiento=establecimientoDAO.buscar(selectedRuta.getUbigeo().getCodigoUbigeo());
		}
	}
	public void buscarDepartamento(){
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		departamento=ubigeoDAO.buscarUbigeoPorMarcoMuestral(codigoMarcoMuestral);
	}
	public void buscarProvincia(){
		codigoProvincia="";
		codigoDistrito="";
		strEstablecimiento=new ArrayList<String>();
		
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		provincia=ubigeoDAO.buscarProvincia(codigoDepartamento);
		distrito=ubigeoDAO.buscarDistrito(codigoDepartamento, codigoProvincia);
		EstablecimientoDAO establecimientoDAO=DAOFactory.getInstance().getEstablecimientoDAO();
		establecimiento=establecimientoDAO.buscar(codigoDepartamento+codigoProvincia+codigoDistrito);
	}
	public void buscarDistrito(){
		codigoDistrito="";
		strEstablecimiento=new ArrayList<String>();
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		distrito=ubigeoDAO.buscarDistrito(codigoDepartamento, codigoProvincia);
		EstablecimientoDAO establecimientoDAO=DAOFactory.getInstance().getEstablecimientoDAO();
		establecimiento=establecimientoDAO.buscar(codigoDepartamento+codigoProvincia+codigoDistrito);
	}

	public void buscarEstablecimiento(){
		strEstablecimiento=new ArrayList<String>();
		EstablecimientoDAO establecimientoDAO=DAOFactory.getInstance().getEstablecimientoDAO();
		establecimiento=establecimientoDAO.buscar(codigoDepartamento+codigoProvincia+codigoDistrito);
	}
	
	
	public void grabar(ActionEvent event){
		EstablecimientoDAO establecimientoDAO=DAOFactory.getInstance().getEstablecimientoDAO();
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		codigoUbigeo=codigoDepartamento+codigoProvincia+codigoDistrito;
		Integer index;
		String codEstab="0", mensajeError=""; 
		Integer encLibre=encuestaTotal-encuestaAsignada;
		
		if(encLibre<numeroEncuestas) mensajeError+="El numero de encuestas es mayor al disponible: " + encLibre.toString();
		
		if(agregar)
		{
			if(encuestaFinal>correlativoInicial) mensajeError+="El correlativo inicial debe ser mayor al ultimo usado: " + encuestaFinal.toString();
		}
		else
		{
			//if(encuestaFinal>correlativoInicial && correlativoInicial!=correlativoActual) mensajeError+="mOEl correlativo inicial debe ser mayor al ultimo usado: " + correlativoActual.toString();
		}
		
		if(mensajeError.length()>0)
		{
			FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ruta", mensajeError);
	        FacesContext.getCurrentInstance().addMessage(null, message2); 	
		}
		else
		{
			for(index=0;index<strEstablecimiento.size();index++)
			{
				codEstab+=","+strEstablecimiento.get(index);
			}
			
			correlativoFinal=correlativoInicial+numeroEncuestas-1;
			selectedEstablecimiento = establecimientoDAO.buscarxCodigos(codEstab);
			
			if(agregar)
				rutaDAO.registrar(codigoEncuesta, codigoUbigeo, numeroRuta, descripcion, numeroEncuestas, correlativoInicial, correlativoFinal, selectedEstablecimiento);
			else
				rutaDAO.actualizar(codigoRuta, codigoEncuesta, codigoUbigeo, numeroRuta, descripcion, numeroEncuestas, correlativoInicial, correlativoFinal, selectedEstablecimiento);
			
			limpiar();
			pintaListado=true;
			pintaPanel=false;
			
			ruta=rutaDAO.buscar(codigoEncuesta);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ruta", "Registro guardado correctamente" );
	        FacesContext.getCurrentInstance().addMessage(null, message); 
		}
	}
	
	public void eliminar(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		codigoRuta= Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmRuta"));
		rutaDAO.eliminar(codigoRuta);
		limpiar();
		pintaListado=true;
		pintaPanel=false;
		ruta=rutaDAO.buscar(codigoEncuesta);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ruta", "Registro eliminado correctamente" );
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public List<Ruta> getRuta() {
		return ruta;
	}

	public void setRuta(List<Ruta> ruta) {
		this.ruta = ruta;
	}

	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}

	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	public Ruta getSelectedRuta() {
		return selectedRuta;
	}

	public void setSelectedRuta(Ruta selectedRuta) {
		this.selectedRuta = selectedRuta;
	}

	public Integer getCodigoRuta() {
		return codigoRuta;
	}

	public void setCodigoRuta(Integer codigoRuta) {
		this.codigoRuta = codigoRuta;
	}

	public List<Establecimiento> getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(List<Establecimiento> establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getCodigoMarcoMuestral() {
		return codigoMarcoMuestral;
	}

	public void setCodigoMarcoMuestral(String codigoMarcoMuestral) {
		this.codigoMarcoMuestral = codigoMarcoMuestral;
	}

	public List<Ubigeo> getDepartamento() {
		return departamento;
	}

	public void setDepartamento(List<Ubigeo> departamento) {
		this.departamento = departamento;
	}

	public List<Ubigeo> getProvincia() {
		return provincia;
	}

	public void setProvincia(List<Ubigeo> provincia) {
		this.provincia = provincia;
	}

	public List<Ubigeo> getDistrito() {
		return distrito;
	}

	public void setDistrito(List<Ubigeo> distrito) {
		this.distrito = distrito;
	}

	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	
	
	public String getCodigoDistrito() {
		return codigoDistrito;
	}

	public void setCodigoDistrito(String codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}	

	public String getNumeroRuta() {
		return numeroRuta;
	}

	public void setNumeroRuta(String numeroRuta) {
		this.numeroRuta = numeroRuta;
	}

	public Integer getCorrelativoInicial() {
		return correlativoInicial;
	}

	public void setCorrelativoInicial(Integer correlativoInicial) {
		this.correlativoInicial = correlativoInicial;
	}

	public Integer getCorrelativoFinal() {
		return correlativoFinal;
	}

	public void setCorrelativoFinal(Integer correlativoFinal) {
		this.correlativoFinal = correlativoFinal;
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

	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}

	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
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



	public String getEncuestaNombre() {
		return encuestaNombre;
	}



	public void setEncuestaNombre(String encuestaNombre) {
		this.encuestaNombre = encuestaNombre;
	}



	public String getEncuestaAño() {
		return encuestaAño;
	}



	public void setEncuestaAño(String encuestaAño) {
		this.encuestaAño = encuestaAño;
	}


	public List<Establecimiento> getSelectedEstablecimiento() {
		return selectedEstablecimiento;
	}


	public void setSelectedEstablecimiento(List<Establecimiento> selectedEstablecimiento) {
		this.selectedEstablecimiento = selectedEstablecimiento;
	}


	public List<String> getStrEstablecimiento() {
		return strEstablecimiento;
	}


	public void setStrEstablecimiento(List<String> strEstablecimiento) {
		this.strEstablecimiento = strEstablecimiento;
	}


	public Boolean getDesactivaNuevo() {
		return desactivaNuevo;
	}


	public void setDesactivaNuevo(Boolean desactivaNuevo) {
		this.desactivaNuevo = desactivaNuevo;
	}


	public Integer getEncuestaTotal() {
		return encuestaTotal;
	}


	public void setEncuestaTotal(Integer encuestaTotal) {
		this.encuestaTotal = encuestaTotal;
	}


	public Integer getEncuestaAsignada() {
		return encuestaAsignada;
	}


	public void setEncuestaAsignada(Integer encuestaAsignada) {
		this.encuestaAsignada = encuestaAsignada;
	}


	public Integer getEncuestaFinal() {
		return encuestaFinal;
	}


	public void setEncuestaFinal(Integer encuestaFinal) {
		this.encuestaFinal = encuestaFinal;
	}


	public Integer getCorrelativoActual() {
		return correlativoActual;
	}


	public void setCorrelativoActual(Integer correlativoActual) {
		this.correlativoActual = correlativoActual;
	}
	
}

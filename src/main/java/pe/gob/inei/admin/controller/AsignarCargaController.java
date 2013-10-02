package pe.gob.inei.admin.controller;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.Ruta;
import pe.gob.inei.sistencuesta.Ubigeo;
import pe.gob.inei.sistencuesta.RutaPersonal;

@ManagedBean(name="asignarCarga")
@ViewScoped
public class AsignarCargaController implements Serializable {

	private String codigoEncuesta;
	private String descripcionEncuesta;
	private Integer codigoRuta;
		
	private Integer codigoPersonal;
	private Integer codigoPersonalOriginal;
	private Integer numeroEncuestas;
	private String fechaInicio;
	private String fechaFin;
	private Integer nroEncuestasPorDia;
	private Integer correlativoInicial;

	private Long fechaInicioEncuesta;
	private Long fechaFinEncuesta;
	private String fInicioEncuesta;
	private String fFinEncuesta;
	
	
	private List<RutaPersonal> rutaPersonal;
	private List<Ruta> ruta;
	private RutaPersonal selectedRutaPersonal;
	private Ruta selectedRuta;
	
	private String numeroRuta;
	
	private List<Ubigeo> departamento;
	private List<Ubigeo> provincia;
	private List<Ubigeo> distrito;
	
	private String departamentoNombre;
	private String provinciaNombre;
	private String distritoNombre;
	
	private String codigoDepartamento;
	private String codigoProvincia;
	private String codigoDistrito;
	
	private String personalDni;
	private String personalNombre;
	private String personalApPaterno;
	private String personalApMaterno;

	private Boolean pintaPanel;
	private Boolean pintaListado;
	private Boolean agregar;
	private Boolean desactivaCodigo;
	private Boolean verEliminar;
	private Boolean verDNI;
	
	public AsignarCargaController()
	{
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		departamento=ubigeoDAO.buscarDepartamento();
		provincia=ubigeoDAO.buscarDepartamento();
		distrito=ubigeoDAO.buscarDepartamento();
	
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
	}

	public void editar(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
				
		pintaListado=false;
		pintaPanel=true;
		agregar=false;
		verEliminar=false;
		desactivaCodigo=true;
				
		codigoRuta= Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmRuta"));
		
		
		selectedRuta=rutaDAO.buscarxCodigo(codigoRuta);
		
		fInicioEncuesta=selectedRuta.getEncuesta().getFechainicio();
		fFinEncuesta=selectedRuta.getEncuesta().getFechafin();
		
		fechaInicioEncuesta= Long.parseLong(fInicioEncuesta.substring(6, 10))*10000+Long.parseLong(fInicioEncuesta.substring(3, 5))*100+Long.parseLong(fInicioEncuesta.substring(0, 2));
		fechaFinEncuesta= Long.parseLong(fFinEncuesta.substring(6, 10))*10000+Long.parseLong(fFinEncuesta.substring(3, 5))*100+Long.parseLong(fFinEncuesta.substring(0, 2));
		
		codigoPersonal=0;
		codigoPersonalOriginal=0;
		selectedRutaPersonal=rutaPersonalDAO.buscarPersonaRuta(codigoRuta, codigoPersonal);
		
		if(selectedRutaPersonal!=null)
		{
			codigoPersonal=selectedRutaPersonal.getPersonal().getCodigoPersonal();
			codigoPersonalOriginal=codigoPersonal;
			personalDni=selectedRutaPersonal.getPersonal().getNumeroDocumento();
			personalNombre=selectedRutaPersonal.getPersonal().getNombres();
			personalApPaterno=selectedRutaPersonal.getPersonal().getApellidos();

			numeroEncuestas=selectedRutaPersonal.getNumeroEncuestas();
			fechaInicio=selectedRutaPersonal.getFechainicio();
			fechaFin=selectedRutaPersonal.getFechafin();
			nroEncuestasPorDia=selectedRutaPersonal.getNroEncuestasPorDia();
			correlativoInicial=selectedRutaPersonal.getCorrelativoInicial();			
		}
		else
		{
			numeroEncuestas=selectedRuta.getNumeroEncuestas();
			correlativoInicial=selectedRuta.getCorrelativoInicial();
		}
		
		numeroRuta=selectedRuta.getNumeroRuta();
		selectedRuta.getUbigeo().getNombre();
		distritoNombre=selectedRuta.getUbigeo().getNombre();		
		departamentoNombre=ubigeoDAO.findById(selectedRuta.getUbigeo().getCodigoDepartamento()+"0000").getNombre();
		provinciaNombre=ubigeoDAO.findById(selectedRuta.getUbigeo().getCodigoDepartamento()+selectedRuta.getUbigeo().getCodigoProvincia()+"00").getNombre();
		
		if(codigoPersonal==0)
		{
			agregar=true;
			verEliminar=true;
			desactivaCodigo=false;
		}
		else
		{
			agregar=false;
			verEliminar=false;
			desactivaCodigo=true;
		}
	}
	
	public void limpiar()
	{
		codigoPersonal=0;
		codigoPersonalOriginal=0;
		personalDni="";
		personalNombre="";
		personalApPaterno="";
		personalApMaterno="";

		numeroRuta="";
		departamentoNombre="";
		provinciaNombre="";
		distritoNombre="";
		numeroEncuestas=0;
		fechaInicio="";
		fechaFin="";
		nroEncuestasPorDia=0;
		correlativoInicial=0;
		selectedRutaPersonal=null;
		selectedRuta=null;		
	}
	public void buscarPersonal(ActionEvent event){
		PersonalDAO personalDAO=DAOFactory.getInstance().getPersonalDAO();
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		String mensajeError="";
		
		List<RutaPersonal> rutaPers= rutaPersonalDAO.buscarPorDni(personalDni);
		
		if(rutaPers!=null)
		{
			if(rutaPers.size()>0)
			{
				mensajeError="El Personal ingresado ya ha sido asignado a otra Ruta";
				codigoPersonal=0;
				personalNombre="";
				personalApPaterno="";		
			}
		}
		if(mensajeError.length()==0)
		{
			Personal personal=personalDAO.buscarPorDni(personalDni);
			if (personal!=null){
				codigoPersonal=personal.getCodigoPersonal();
				personalDni=personal.getNumeroDocumento();
				personalNombre=personal.getNombres();
				personalApPaterno=personal.getApellidos();
			}
			else
			{
				mensajeError="No Existe un personal con el dni ingresado";
				codigoPersonal=0;
				personalNombre="";
				personalApPaterno="";				
			}
		}
		if(mensajeError.length()>0)
		{
			
			FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Asignar Carga", mensajeError);
	        FacesContext.getCurrentInstance().addMessage(null, message3); 	
		}
		
	}
	public void buscar(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		ruta=rutaDAO.buscar(codigoEncuesta);
		if(ruta!=null)
		{
			if(ruta.size()>0)
			{
				descripcionEncuesta= ruta.get(0).getEncuesta().getDescripcion();
			}
		}
	}

	public void buscarPorCodigo(ActionEvent event){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		selectedRutaPersonal=rutaPersonalDAO.buscarPersonaRuta(codigoRuta, codigoPersonal);
	}
	
	public void grabar(ActionEvent event){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();	
		String mensajeError="";
		Long fInicio=(long) 0, fFin=(long) 0;
		
		fInicio= Long.parseLong(fechaInicio.substring(6, 10))*10000+Long.parseLong(fechaInicio.substring(3, 5))*100+Long.parseLong(fechaInicio.substring(0, 2));
		fFin= Long.parseLong(fechaFin.substring(6, 10))*10000+Long.parseLong(fechaFin.substring(3, 5))*100+Long.parseLong(fechaFin.substring(0, 2));
		
		if(nroEncuestasPorDia>numeroEncuestas) 
			mensajeError="El Numero de encuestas por dia no puede ser mayor al total";
		else if(nroEncuestasPorDia==0) 
			mensajeError="El Numero de encuestas por dia debe ser mayor a cero";
		else if(fInicio>fFin) 
			mensajeError="La Fecha Final debe ser mayor al Inicial";
		else if(fInicio<fechaInicioEncuesta) 
			mensajeError="La Fecha Inicial no puede ser menor al de la encuesta: "  + fInicioEncuesta;
		else if(fFin>fechaFinEncuesta) 
			mensajeError="La Fecha Final no puede ser mayor al de la encuesta: " + fFinEncuesta;
		else if(codigoPersonal==0) 
			mensajeError="No se ha eligido un encuestador";
		
		if(mensajeError.length()>0)
		{
			FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Asignacion de Carga", mensajeError);
	        FacesContext.getCurrentInstance().addMessage(null, message2); 	
		}
		else
		{
			if(agregar)
				rutaPersonalDAO.registrar(codigoRuta, codigoPersonal, numeroEncuestas, fechaInicio, fechaFin, nroEncuestasPorDia, correlativoInicial);
			else
				rutaPersonalDAO.actualizar(codigoRuta, codigoPersonalOriginal, codigoPersonal, numeroEncuestas, fechaInicio, fechaFin, nroEncuestasPorDia, correlativoInicial);
			
			limpiar();
			pintaListado=true;
			pintaPanel=false;
			
			RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
			ruta=rutaDAO.buscar(codigoEncuesta);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación de Carga", "Registro guardado correctamente" );
	        FacesContext.getCurrentInstance().addMessage(null, message); 
		}
	}
	
	public void eliminar(ActionEvent event){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		codigoRuta= Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmRuta"));
		codigoPersonal=0;
		selectedRutaPersonal=rutaPersonalDAO.buscarPersonaRuta(codigoRuta, codigoPersonal);
		
		if(selectedRutaPersonal!=null)
		{
			codigoPersonal=selectedRutaPersonal.getPersonal().getCodigoPersonal();			
			rutaPersonalDAO.eliminar(codigoRuta, codigoPersonal);
		}
		limpiar();
		pintaListado=true;
		pintaPanel=false;
		//ruta=rutaDAO.buscar(codigoEncuesta);
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		ruta=rutaDAO.buscar(codigoEncuesta);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación de Carga", "Registro eliminado correctamente" );
        FacesContext.getCurrentInstance().addMessage(null, message); 
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

	public Ruta getSelectedRuta() {
		return selectedRuta;
	}

	public void setSelectedRuta(Ruta selectedRuta) {
		this.selectedRuta = selectedRuta;
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

	public String getDepartamentoNombre() {
		return departamentoNombre;
	}

	public void setDepartamentoNombre(String departamentoNombre) {
		this.departamentoNombre = departamentoNombre;
	}

	public String getProvinciaNombre() {
		return provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public String getDistritoNombre() {
		return distritoNombre;
	}

	public void setDistritoNombre(String distritoNombre) {
		this.distritoNombre = distritoNombre;
	}

	public String getPersonalDni() {
		return personalDni;
	}

	public void setPersonalDni(String personalDni) {
		this.personalDni = personalDni;
	}

	public String getPersonalNombre() {
		return personalNombre;
	}

	public void setPersonalNombre(String personalNombre) {
		this.personalNombre = personalNombre;
	}

	public String getPersonalApPaterno() {
		return personalApPaterno;
	}

	public void setPersonalApPaterno(String personalApPaterno) {
		this.personalApPaterno = personalApPaterno;
	}

	public String getPersonalApMaterno() {
		return personalApMaterno;
	}

	public void setPersonalApMaterno(String personalApMaterno) {
		this.personalApMaterno = personalApMaterno;
	}

	public String getNumeroRuta() {
		return numeroRuta;
	}

	public void setNumeroRuta(String numeroRuta) {
		this.numeroRuta = numeroRuta;
	}

	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}

	public String getDescripcionEncuesta() {
		return descripcionEncuesta;
	}

	public void setDescripcionEncuesta(String descripcionEncuesta) {
		this.descripcionEncuesta = descripcionEncuesta;
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

	public List<Ruta> getRuta() {
		return ruta;
	}

	public void setRuta(List<Ruta> ruta) {
		this.ruta = ruta;
	}
	
	public Integer getCodigoPersonalOriginal() {
		return codigoPersonalOriginal;
	}
	public void setCodigoPersonalOriginal(Integer codigoPersonalOriginal) {
		this.codigoPersonalOriginal = codigoPersonalOriginal;
	}

	public Boolean getVerDNI() {
		return verDNI;
	}

	public void setVerDNI(Boolean verDNI) {
		this.verDNI = verDNI;
	}

	public Long getFechaInicioEncuesta() {
		return fechaInicioEncuesta;
	}

	public void setFechaInicioEncuesta(Long fechaInicioEncuesta) {
		this.fechaInicioEncuesta = fechaInicioEncuesta;
	}

	public Long getFechaFinEncuesta() {
		return fechaFinEncuesta;
	}

	public void setFechaFinEncuesta(Long fechaFinEncuesta) {
		this.fechaFinEncuesta = fechaFinEncuesta;
	}

	public String getfInicioEncuesta() {
		return fInicioEncuesta;
	}

	public void setfInicioEncuesta(String fInicioEncuesta) {
		this.fInicioEncuesta = fInicioEncuesta;
	}

	public String getfFinEncuesta() {
		return fFinEncuesta;
	}

	public void setfFinEncuesta(String fFinEncuesta) {
		this.fFinEncuesta = fFinEncuesta;
	}
}

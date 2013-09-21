package pe.gob.inei.admin.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DualListModel;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.EncuestaDAO;/*
import pe.gob.inei.admin.dao.CategoriaDAO;
import pe.gob.inei.admin.dao.CuestionarioDAO;
import pe.gob.inei.admin.dao.CapituloDAO;
import pe.gob.inei.admin.dao.PreguntaDAO;
import pe.gob.inei.admin.dao.RespuestaDAO;
*/


import pe.gob.inei.admin.dao.MarcoMuestralDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.Encuesta;
import pe.gob.inei.sistencuesta.Categoria;
import pe.gob.inei.sistencuesta.Cuestionario;
import pe.gob.inei.sistencuesta.Capitulo;
import pe.gob.inei.sistencuesta.Pregunta;
import pe.gob.inei.sistencuesta.Respuesta;
import pe.gob.inei.sistencuesta.Ubigeo;

@ManagedBean(name="cuestionario")
@ViewScoped
public class CuestionarioController  implements Serializable {

	private String codigoEncuesta;
	private String nombreEncuesta;
	private String codigoCuestionario;
	private Integer numeroCuestionario;
	private String descripcionCuestionario;
	private Integer codigoCategoria;
	
	private Integer codigoCapitulo;
	private Integer numeroCapitulo;
	private String tituloCapitulo;
	
	private Integer codigoPregunta;
	private Integer numeroPregunta;
	private Integer nivelPregunta;
	private String descripcionPregunta;
	private String tipoPregunta;
	private Integer longitudDato;
	private String tipoValidacion;
	private String opcional;
	private String respuestaOtros;
	
	private Integer codigoRespuesta;
	private String valorRespuesta;
	private String descripcionRespuesta;
	private Integer posicion;
	
	private List<Categoria> categoria;
	private List<Cuestionario> cuestionario;
	private List<Capitulo> capitulo;
	private List<Pregunta> pregunta;
	private List<Respuesta> respuesta;
		

	private Boolean pintaPanel;
	private Boolean pintaListado;
	private Boolean pintaPregunta;
	private Boolean agregar;
	private Boolean desactivaCodigo;
	private Boolean verEliminar;
	
	public CuestionarioController() {
		/*UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();		
		ubigeo =ubigeoDAO.buscarDepartamento();
		ubigeoSeleccionado = ubigeoDAO.buscarUbigeoPorMarcoMuestral("NS");
		
		setUbigeoDual(new DualListModel<Ubigeo>(ubigeo, ubigeoSeleccionado)); 
        */
		setPintaPanel(false);
		setPintaListado(true);
		setVerEliminar(true);
		setDesactivaCodigo(false);
	}
	
	public void nuevo(ActionEvent event){
		setPintaListado(false);
		setPintaPanel(true);
		setAgregar(true);
		setVerEliminar(true);
		setDesactivaCodigo(false);	
		/*
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		ubigeo =ubigeoDAO.buscarDepartamento();
		ubigeoSeleccionado = ubigeoDAO.buscarUbigeoPorMarcoMuestral("NS");
		setUbigeoDual(new DualListModel<Ubigeo>(ubigeo, ubigeoSeleccionado)); 
		*/
	}
	
	public void editar(ActionEvent event){
		setPintaListado(false);
		setPintaPanel(true);
		setAgregar(false);
		setVerEliminar(false);
		setDesactivaCodigo(true);
		/*
		codigoMarcoMuestral=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prmMarcoMuestral");
		
		MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		selectedMarcoMuestral=marcoMuestralDAO.buscarxCodigo(codigoMarcoMuestral);
	
		año=selectedMarcoMuestral.getAño();
		descripcion=selectedMarcoMuestral.getDescripcion();
		numeroEncuestas=selectedMarcoMuestral.getNumeroEncuestas();
		tipoUbigeo=selectedMarcoMuestral.getTipoUbigeo();
		tipoArea=selectedMarcoMuestral.getTipoArea();
		estado=selectedMarcoMuestral.getEstado();
		
	
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		ubigeo =ubigeoDAO.buscarDepartamentoNoMarcoMuestral(codigoMarcoMuestral);
		ubigeoSeleccionado = ubigeoDAO.buscarUbigeoPorMarcoMuestral(codigoMarcoMuestral);
		setUbigeoDual(new DualListModel<Ubigeo>(ubigeo, ubigeoSeleccionado)); 
		*/
	}
	
	public void limpiar()
	{
		/*codigoMarcoMuestral="";
		año=0;
		descripcion="";
		numeroEncuestas=0;
		tipoUbigeo="";
		tipoArea="";
		estado="";	*/	
	}
	
	public void agregarCapitulo(ActionEvent event){
	
	}

	public void agregarPregunta(ActionEvent event){
		pintaPregunta=true;
		pintaPanel=false;
	}
	
	public void buscar(ActionEvent event){
		/*MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		marcoMuestral=marcoMuestralDAO.buscar("", "", 0);
		*/
	}	
	
	public void grabar(ActionEvent event){
		/*MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();

		if(agregar)
			marcoMuestralDAO.registrar(codigoMarcoMuestral, año, descripcion, numeroEncuestas, tipoUbigeo, tipoArea, estado, ubigeoDual.getTarget());
		else
			marcoMuestralDAO.actualizar(codigoMarcoMuestral, año, descripcion, numeroEncuestas, tipoUbigeo, tipoArea, estado, ubigeoDual.getTarget());
		*/
		
		limpiar();
		pintaListado=true;
		pintaPanel=false;
		
		//marcoMuestral=marcoMuestralDAO.buscar("", "", 0);		
	}
	
	public void eliminar(ActionEvent event){
		/*MarcoMuestralDAO marcoMuestralDAO=DAOFactory.getInstance().getMarcoMuestralDAO();
		marcoMuestralDAO.eliminar(codigoMarcoMuestral);
		*/
		limpiar();
		pintaListado=true;
		pintaPanel=false;
		//marcoMuestral=marcoMuestralDAO.buscar("", "", año);
	}
	
	
	public void editarRespuesta(ActionEvent event){
		
	}
	
	
	
	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}
	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}
	public String getCodigoCuestionario() {
		return codigoCuestionario;
	}
	public void setCodigoCuestionario(String codigoCuestionario) {
		this.codigoCuestionario = codigoCuestionario;
	}
	public Integer getNumeroCuestionario() {
		return numeroCuestionario;
	}
	public void setNumeroCuestionario(Integer numeroCuestionario) {
		this.numeroCuestionario = numeroCuestionario;
	}
	public String getDescripcionCuestionario() {
		return descripcionCuestionario;
	}
	public void setDescripcionCuestionario(String descripcionCuestionario) {
		this.descripcionCuestionario = descripcionCuestionario;
	}
	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public Integer getCodigoCapitulo() {
		return codigoCapitulo;
	}
	public void setCodigoCapitulo(Integer codigoCapitulo) {
		this.codigoCapitulo = codigoCapitulo;
	}
	public Integer getNumeroCapitulo() {
		return numeroCapitulo;
	}
	public void setNumeroCapitulo(Integer numeroCapitulo) {
		this.numeroCapitulo = numeroCapitulo;
	}
	public String getTituloCapitulo() {
		return tituloCapitulo;
	}
	public void setTituloCapitulo(String tituloCapitulo) {
		this.tituloCapitulo = tituloCapitulo;
	}
	public Integer getCodigoPregunta() {
		return codigoPregunta;
	}
	public void setCodigoPregunta(Integer codigoPregunta) {
		this.codigoPregunta = codigoPregunta;
	}
	public Integer getNumeroPregunta() {
		return numeroPregunta;
	}
	public void setNumeroPregunta(Integer numeroPregunta) {
		this.numeroPregunta = numeroPregunta;
	}
	public Integer getNivelPregunta() {
		return nivelPregunta;
	}
	public void setNivelPregunta(Integer nivelPregunta) {
		this.nivelPregunta = nivelPregunta;
	}
	public String getDescripcionPregunta() {
		return descripcionPregunta;
	}
	public void setDescripcionPregunta(String descripcionPregunta) {
		this.descripcionPregunta = descripcionPregunta;
	}
	public String getTipoPregunta() {
		return tipoPregunta;
	}
	public void setTipoPregunta(String tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
	public Integer getLongitudDato() {
		return longitudDato;
	}
	public void setLongitudDato(Integer longitudDato) {
		this.longitudDato = longitudDato;
	}
	public String getTipoValidacion() {
		return tipoValidacion;
	}
	public void setTipoValidacion(String tipoValidacion) {
		this.tipoValidacion = tipoValidacion;
	}
	public String getOpcional() {
		return opcional;
	}
	public void setOpcional(String opcional) {
		this.opcional = opcional;
	}
	public String getRespuestaOtros() {
		return respuestaOtros;
	}
	public void setRespuestaOtros(String respuestaOtros) {
		this.respuestaOtros = respuestaOtros;
	}
	public Integer getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getValorRespuesta() {
		return valorRespuesta;
	}
	public void setValorRespuesta(String valorRespuesta) {
		this.valorRespuesta = valorRespuesta;
	}
	public String getDescripcionRespuesta() {
		return descripcionRespuesta;
	}
	public void setDescripcionRespuesta(String descripcionRespuesta) {
		this.descripcionRespuesta = descripcionRespuesta;
	}
	public Integer getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	public List<Capitulo> getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(List<Capitulo> capitulo) {
		this.capitulo = capitulo;
	}
	public List<Pregunta> getPregunta() {
		return pregunta;
	}
	public void setPregunta(List<Pregunta> pregunta) {
		this.pregunta = pregunta;
	}
	public List<Respuesta> getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(List<Respuesta> respuesta) {
		this.respuesta = respuesta;
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

	public String getNombreEncuesta() {
		return nombreEncuesta;
	}

	public void setNombreEncuesta(String nombreEncuesta) {
		this.nombreEncuesta = nombreEncuesta;
	}

	public List<Cuestionario> getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(List<Cuestionario> cuestionario) {
		this.cuestionario = cuestionario;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	public Boolean getPintaPregunta() {
		return pintaPregunta;
	}

	public void setPintaPregunta(Boolean pintaPregunta) {
		this.pintaPregunta = pintaPregunta;
	}
	
}

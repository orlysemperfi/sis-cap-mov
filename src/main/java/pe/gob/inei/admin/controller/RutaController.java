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
	private List<Ubigeo> departamento;
	private List<Ubigeo> provincia;
	private List<Ubigeo> distrito;
	private String codigoEncuesta;
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
	
	public RutaController()
	{		
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		departamento=ubigeoDAO.buscarDepartamento();
		provincia=ubigeoDAO.buscarDepartamento();
		distrito=ubigeoDAO.buscarDepartamento();
		
		EstablecimientoDAO establecimientoDAO=DAOFactory.getInstance().getEstablecimientoDAO();
		establecimiento=establecimientoDAO.buscar("150101");
	}
	
	public void buscar(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		ruta=rutaDAO.buscar(codigoEncuesta);
		if(ruta!=null)
		{
			EncuestaDAO encuestaDAO=DAOFactory.getInstance().getEncuestaDAO();
			encuesta=encuestaDAO.buscarxCodigo(codigoEncuesta);			
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
	public void buscarDepartamento(ActionEvent event){
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		departamento=ubigeoDAO.buscarUbigeoPorMarcoMuestral(codigoMarcoMuestral);
	}
	public void buscarProvincia(ActionEvent event){
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		provincia=ubigeoDAO.buscarProvincia(codigoDepartamento);
	}
	public void buscarDistrito(ActionEvent event){
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		distrito=ubigeoDAO.buscarDistrito(codigoDepartamento, codigoProvincia);
	}

	public void grabar(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		codigoUbigeo=codigoDepartamento+codigoProvincia+codigoDistrito;
		if(codigoRuta== 0)
		{
			rutaDAO.registrar(codigoEncuesta, codigoUbigeo, numeroRuta, descripcion, numeroEncuestas, correlativoInicial, correlativoFinal);	
		}
		else
		{
			rutaDAO.actualizar(codigoRuta, codigoEncuesta, codigoUbigeo, numeroRuta, descripcion, numeroEncuestas, correlativoInicial, correlativoFinal);	
		}
	}
	
	public void eliminar(ActionEvent event){
		RutaDAO rutaDAO=DAOFactory.getInstance().getRutaDAO();
		rutaDAO.eliminar(codigoRuta);
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
	
}

package pe.gob.inei.sistencuesta;

// Generated 12/09/2013 09:31:22 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Encuesta generated by hbm2java
 */
@Entity
@Table(name = "encuesta", catalog = "bd_sistencuesta")
public class Encuesta implements java.io.Serializable {

	private String codigoEncuesta;
	private Rubro rubro;
	private MarcoMuestral marcoMuestral;
	private int a�o;
	private String nombre;
	private String descripcion;
	private String objetivo;
	private Date fechainicio;
	private Date fechafin;
	private String tipoArea;
	private String estado;
	private Set<Ruta> rutas = new HashSet<Ruta>(0);
	private Set<Cuestionario> cuestionarios = new HashSet<Cuestionario>(0);

	public Encuesta() {
	}

	public Encuesta(String codigoEncuesta, Rubro rubro,
			MarcoMuestral marcoMuestral, int a�o, String nombre,
			String descripcion, String objetivo, String tipoArea, String estado) {
		this.codigoEncuesta = codigoEncuesta;
		this.rubro = rubro;
		this.marcoMuestral = marcoMuestral;
		this.a�o = a�o;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetivo = objetivo;
		this.tipoArea = tipoArea;
		this.estado = estado;
	}

	public Encuesta(String codigoEncuesta, Rubro rubro,
			MarcoMuestral marcoMuestral, int a�o, String nombre,
			String descripcion, String objetivo, Date fechainicio,
			Date fechafin, String tipoArea, String estado, Set<Ruta> rutas,
			Set<Cuestionario> cuestionarios) {
		this.codigoEncuesta = codigoEncuesta;
		this.rubro = rubro;
		this.marcoMuestral = marcoMuestral;
		this.a�o = a�o;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetivo = objetivo;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.tipoArea = tipoArea;
		this.estado = estado;
		this.rutas = rutas;
		this.cuestionarios = cuestionarios;
	}

	@Id
	@Column(name = "CodigoEncuesta", unique = true, nullable = false, length = 5)
	public String getCodigoEncuesta() {
		return this.codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoRubro", nullable = false)
	public Rubro getRubro() {
		return this.rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoMarcoMuestral", nullable = false)
	public MarcoMuestral getMarcoMuestral() {
		return this.marcoMuestral;
	}

	public void setMarcoMuestral(MarcoMuestral marcoMuestral) {
		this.marcoMuestral = marcoMuestral;
	}

	@Column(name = "A�o", nullable = false)
	public int getA�o() {
		return this.a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	@Column(name = "Nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Objetivo", nullable = false, length = 100)
	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechainicio", length = 0)
	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechafin", length = 0)
	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	@Column(name = "TipoArea", nullable = false, length = 1)
	public String getTipoArea() {
		return this.tipoArea;
	}

	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}

	@Column(name = "Estado", nullable = false, length = 1)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encuesta")
	public Set<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(Set<Ruta> rutas) {
		this.rutas = rutas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encuesta")
	public Set<Cuestionario> getCuestionarios() {
		return this.cuestionarios;
	}

	public void setCuestionarios(Set<Cuestionario> cuestionarios) {
		this.cuestionarios = cuestionarios;
	}

}

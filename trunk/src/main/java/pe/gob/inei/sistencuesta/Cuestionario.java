package pe.gob.inei.sistencuesta;

// Generated 13/09/2013 09:04:32 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cuestionario generated by hbm2java
 */
@Entity
@Table(name = "cuestionario", catalog = "bd_sistencuesta")
public class Cuestionario implements java.io.Serializable {

	private Integer codigoCuestionario;
	private Categoria categoria;
	private Encuesta encuesta;
	private Integer numero;
	private String descripcion;
	private String flagPlantilla;
	private String estado;
	private Set<Capitulo> capitulos = new HashSet<Capitulo>(0);
	private Set<Cedula> cedulas = new HashSet<Cedula>(0);

	public Cuestionario() {
	}

	public Cuestionario(Categoria categoria, String descripcion,
			String flagPlantilla, String estado) {
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.flagPlantilla = flagPlantilla;
		this.estado = estado;
	}

	public Cuestionario(Categoria categoria, Encuesta encuesta, Integer numero,
			String descripcion, String flagPlantilla, String estado,
			Set<Capitulo> capitulos, Set<Cedula> cedulas) {
		this.categoria = categoria;
		this.encuesta = encuesta;
		this.numero = numero;
		this.descripcion = descripcion;
		this.flagPlantilla = flagPlantilla;
		this.estado = estado;
		this.capitulos = capitulos;
		this.cedulas = cedulas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CodigoCuestionario", unique = true, nullable = false)
	public Integer getCodigoCuestionario() {
		return this.codigoCuestionario;
	}

	public void setCodigoCuestionario(Integer codigoCuestionario) {
		this.codigoCuestionario = codigoCuestionario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoCategoria", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoEncuesta")
	public Encuesta getEncuesta() {
		return this.encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	@Column(name = "Numero")
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "FlagPlantilla", nullable = false, length = 1)
	public String getFlagPlantilla() {
		return this.flagPlantilla;
	}

	public void setFlagPlantilla(String flagPlantilla) {
		this.flagPlantilla = flagPlantilla;
	}

	@Column(name = "Estado", nullable = false, length = 1)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuestionario")
	public Set<Capitulo> getCapitulos() {
		return this.capitulos;
	}

	public void setCapitulos(Set<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuestionario")
	public Set<Cedula> getCedulas() {
		return this.cedulas;
	}

	public void setCedulas(Set<Cedula> cedulas) {
		this.cedulas = cedulas;
	}

}

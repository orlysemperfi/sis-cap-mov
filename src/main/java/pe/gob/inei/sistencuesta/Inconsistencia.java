package pe.gob.inei.sistencuesta;

// Generated 17/09/2013 09:30:35 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Inconsistencia generated by hbm2java
 */
@Entity
@Table(name = "inconsistencia", catalog = "bd_sistencuesta")
public class Inconsistencia implements java.io.Serializable {

	private Integer codigoInconsistencia;
	private CedulaRespuesta cedulaRespuesta;
	private String descripcion;
	private String tipoValidacion;
	private String estado;

	public Inconsistencia() {
	}

	public Inconsistencia(CedulaRespuesta cedulaRespuesta, String descripcion,
			String estado) {
		this.cedulaRespuesta = cedulaRespuesta;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public Inconsistencia(CedulaRespuesta cedulaRespuesta, String descripcion,
			String tipoValidacion, String estado) {
		this.cedulaRespuesta = cedulaRespuesta;
		this.descripcion = descripcion;
		this.tipoValidacion = tipoValidacion;
		this.estado = estado;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CodigoInconsistencia", unique = true, nullable = false)
	public Integer getCodigoInconsistencia() {
		return this.codigoInconsistencia;
	}

	public void setCodigoInconsistencia(Integer codigoInconsistencia) {
		this.codigoInconsistencia = codigoInconsistencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoCedulaRespuesta", nullable = false)
	public CedulaRespuesta getCedulaRespuesta() {
		return this.cedulaRespuesta;
	}

	public void setCedulaRespuesta(CedulaRespuesta cedulaRespuesta) {
		this.cedulaRespuesta = cedulaRespuesta;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "TipoValidacion", length = 2)
	public String getTipoValidacion() {
		return this.tipoValidacion;
	}

	public void setTipoValidacion(String tipoValidacion) {
		this.tipoValidacion = tipoValidacion;
	}

	@Column(name = "Estado", nullable = false, length = 1)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}

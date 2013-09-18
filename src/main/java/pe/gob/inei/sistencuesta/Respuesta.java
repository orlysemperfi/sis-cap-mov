package pe.gob.inei.sistencuesta;

// Generated 17/09/2013 09:30:35 PM by Hibernate Tools 3.4.0.CR1

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
 * Respuesta generated by hbm2java
 */
@Entity
@Table(name = "respuesta", catalog = "bd_sistencuesta")
public class Respuesta implements java.io.Serializable {

	private Integer codigoRespuesta;
	private Pregunta pregunta;
	private String valor;
	private String descripcion;
	private int posicion;
	private String codigoPregunta;
	private Set<CedulaRespuesta> cedulaRespuestas = new HashSet<CedulaRespuesta>(
			0);
	private Set<Regla> reglas = new HashSet<Regla>(0);

	public Respuesta() {
	}

	public Respuesta(Pregunta pregunta, String valor, String descripcion,
			int posicion, String codigoPregunta) {
		this.pregunta = pregunta;
		this.valor = valor;
		this.descripcion = descripcion;
		this.posicion = posicion;
		this.codigoPregunta = codigoPregunta;
	}

	public Respuesta(Pregunta pregunta, String valor, String descripcion,
			int posicion, String codigoPregunta,
			Set<CedulaRespuesta> cedulaRespuestas, Set<Regla> reglas) {
		this.pregunta = pregunta;
		this.valor = valor;
		this.descripcion = descripcion;
		this.posicion = posicion;
		this.codigoPregunta = codigoPregunta;
		this.cedulaRespuestas = cedulaRespuestas;
		this.reglas = reglas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CodigoRespuesta", unique = true, nullable = false)
	public Integer getCodigoRespuesta() {
		return this.codigoRespuesta;
	}

	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Pregunta_CodigoPregunta", nullable = false)
	public Pregunta getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	@Column(name = "Valor", nullable = false, length = 50)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Posicion", nullable = false)
	public int getPosicion() {
		return this.posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	@Column(name = "CodigoPregunta", nullable = false, length = 12)
	public String getCodigoPregunta() {
		return this.codigoPregunta;
	}

	public void setCodigoPregunta(String codigoPregunta) {
		this.codigoPregunta = codigoPregunta;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "respuesta")
	public Set<CedulaRespuesta> getCedulaRespuestas() {
		return this.cedulaRespuestas;
	}

	public void setCedulaRespuestas(Set<CedulaRespuesta> cedulaRespuestas) {
		this.cedulaRespuestas = cedulaRespuestas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "respuesta")
	public Set<Regla> getReglas() {
		return this.reglas;
	}

	public void setReglas(Set<Regla> reglas) {
		this.reglas = reglas;
	}

}

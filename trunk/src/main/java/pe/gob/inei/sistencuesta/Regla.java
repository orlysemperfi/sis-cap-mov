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
 * Regla generated by hbm2java
 */
@Entity
@Table(name = "regla", catalog = "bd_sistencuesta")
public class Regla implements java.io.Serializable {

	private Integer codigoRegla;
	private Pregunta preguntaByCodigoPreguntaOrigen;
	private Respuesta respuesta;
	private Pregunta preguntaByCodigoPreguntaDestino;
	private String descripcion;
	private String respuestaVacia;

	public Regla() {
	}

	public Regla(Pregunta preguntaByCodigoPreguntaOrigen,
			Pregunta preguntaByCodigoPreguntaDestino, String descripcion,
			String respuestaVacia) {
		this.preguntaByCodigoPreguntaOrigen = preguntaByCodigoPreguntaOrigen;
		this.preguntaByCodigoPreguntaDestino = preguntaByCodigoPreguntaDestino;
		this.descripcion = descripcion;
		this.respuestaVacia = respuestaVacia;
	}

	public Regla(Pregunta preguntaByCodigoPreguntaOrigen, Respuesta respuesta,
			Pregunta preguntaByCodigoPreguntaDestino, String descripcion,
			String respuestaVacia) {
		this.preguntaByCodigoPreguntaOrigen = preguntaByCodigoPreguntaOrigen;
		this.respuesta = respuesta;
		this.preguntaByCodigoPreguntaDestino = preguntaByCodigoPreguntaDestino;
		this.descripcion = descripcion;
		this.respuestaVacia = respuestaVacia;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CodigoRegla", unique = true, nullable = false)
	public Integer getCodigoRegla() {
		return this.codigoRegla;
	}

	public void setCodigoRegla(Integer codigoRegla) {
		this.codigoRegla = codigoRegla;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoPreguntaOrigen", nullable = false)
	public Pregunta getPreguntaByCodigoPreguntaOrigen() {
		return this.preguntaByCodigoPreguntaOrigen;
	}

	public void setPreguntaByCodigoPreguntaOrigen(
			Pregunta preguntaByCodigoPreguntaOrigen) {
		this.preguntaByCodigoPreguntaOrigen = preguntaByCodigoPreguntaOrigen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoRespuesta")
	public Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoPreguntaDestino", nullable = false)
	public Pregunta getPreguntaByCodigoPreguntaDestino() {
		return this.preguntaByCodigoPreguntaDestino;
	}

	public void setPreguntaByCodigoPreguntaDestino(
			Pregunta preguntaByCodigoPreguntaDestino) {
		this.preguntaByCodigoPreguntaDestino = preguntaByCodigoPreguntaDestino;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "RespuestaVacia", nullable = false, length = 1)
	public String getRespuestaVacia() {
		return this.respuestaVacia;
	}

	public void setRespuestaVacia(String respuestaVacia) {
		this.respuestaVacia = respuestaVacia;
	}

}
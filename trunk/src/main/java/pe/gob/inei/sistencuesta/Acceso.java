package pe.gob.inei.sistencuesta;

// Generated 15/09/2013 10:46:14 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Acceso generated by hbm2java
 */
@Entity
@Table(name = "acceso", catalog = "bd_sistencuesta")
public class Acceso implements java.io.Serializable {

	private AccesoId id;
	private Opcion opcion;
	private Perfil perfil;
	private String escritura;
	private String eliminacion;

	public Acceso() {
	}

	public Acceso(AccesoId id, Opcion opcion, Perfil perfil, String escritura,
			String eliminacion) {
		this.id = id;
		this.opcion = opcion;
		this.perfil = perfil;
		this.escritura = escritura;
		this.eliminacion = eliminacion;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoOpcion", column = @Column(name = "CodigoOpcion", nullable = false)),
			@AttributeOverride(name = "codigoPerfil", column = @Column(name = "CodigoPerfil", nullable = false)) })
	public AccesoId getId() {
		return this.id;
	}

	public void setId(AccesoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoOpcion", nullable = false, insertable = false, updatable = false)
	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodigoPerfil", nullable = false, insertable = false, updatable = false)
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Column(name = "Escritura", nullable = false, length = 1)
	public String getEscritura() {
		return this.escritura;
	}

	public void setEscritura(String escritura) {
		this.escritura = escritura;
	}

	@Column(name = "Eliminacion", nullable = false, length = 1)
	public String getEliminacion() {
		return this.eliminacion;
	}

	public void setEliminacion(String eliminacion) {
		this.eliminacion = eliminacion;
	}

}

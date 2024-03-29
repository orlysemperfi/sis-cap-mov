package pe.gob.inei.sistencuesta;

// Generated 17/09/2013 09:30:35 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccesoId generated by hbm2java
 */
@Embeddable
public class AccesoId implements java.io.Serializable {

	private int codigoOpcion;
	private int codigoPerfil;

	public AccesoId() {
	}

	public AccesoId(int codigoOpcion, int codigoPerfil) {
		this.codigoOpcion = codigoOpcion;
		this.codigoPerfil = codigoPerfil;
	}

	@Column(name = "CodigoOpcion", nullable = false)
	public int getCodigoOpcion() {
		return this.codigoOpcion;
	}

	public void setCodigoOpcion(int codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}

	@Column(name = "CodigoPerfil", nullable = false)
	public int getCodigoPerfil() {
		return this.codigoPerfil;
	}

	public void setCodigoPerfil(int codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AccesoId))
			return false;
		AccesoId castOther = (AccesoId) other;

		return (this.getCodigoOpcion() == castOther.getCodigoOpcion())
				&& (this.getCodigoPerfil() == castOther.getCodigoPerfil());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoOpcion();
		result = 37 * result + this.getCodigoPerfil();
		return result;
	}

}

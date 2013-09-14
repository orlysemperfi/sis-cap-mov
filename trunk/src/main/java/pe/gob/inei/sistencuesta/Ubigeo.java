package pe.gob.inei.sistencuesta;

// Generated 13/09/2013 09:04:32 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ubigeo generated by hbm2java
 */
@Entity
@Table(name = "ubigeo", catalog = "bd_sistencuesta")
public class Ubigeo implements java.io.Serializable {

	private String codigoUbigeo;
	private String nombre;
	private String codigoDepartamento;
	private String codigoProvincia;
	private String codigoDistrito;
	private Set<Personal> personals = new HashSet<Personal>(0);
	private Set<MarcoMuestral> marcoMuestrals = new HashSet<MarcoMuestral>(0);
	private Set<Ruta> rutas = new HashSet<Ruta>(0);

	public Ubigeo() {
	}

	public Ubigeo(String codigoUbigeo, String nombre) {
		this.codigoUbigeo = codigoUbigeo;
		this.nombre = nombre;
	}

	public Ubigeo(String codigoUbigeo, String nombre,
			String codigoDepartamento, String codigoProvincia,
			String codigoDistrito, Set<Personal> personals,
			Set<MarcoMuestral> marcoMuestrals, Set<Ruta> rutas) {
		this.codigoUbigeo = codigoUbigeo;
		this.nombre = nombre;
		this.codigoDepartamento = codigoDepartamento;
		this.codigoProvincia = codigoProvincia;
		this.codigoDistrito = codigoDistrito;
		this.personals = personals;
		this.marcoMuestrals = marcoMuestrals;
		this.rutas = rutas;
	}

	@Id
	@Column(name = "CodigoUbigeo", unique = true, nullable = false, length = 8)
	public String getCodigoUbigeo() {
		return this.codigoUbigeo;
	}

	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
	}

	@Column(name = "Nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "CodigoDepartamento", length = 2)
	public String getCodigoDepartamento() {
		return this.codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	@Column(name = "CodigoProvincia", length = 2)
	public String getCodigoProvincia() {
		return this.codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	@Column(name = "CodigoDistrito", length = 2)
	public String getCodigoDistrito() {
		return this.codigoDistrito;
	}

	public void setCodigoDistrito(String codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ubigeo")
	public Set<Personal> getPersonals() {
		return this.personals;
	}

	public void setPersonals(Set<Personal> personals) {
		this.personals = personals;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ubigeos")
	public Set<MarcoMuestral> getMarcoMuestrals() {
		return this.marcoMuestrals;
	}

	public void setMarcoMuestrals(Set<MarcoMuestral> marcoMuestrals) {
		this.marcoMuestrals = marcoMuestrals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ubigeo")
	public Set<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(Set<Ruta> rutas) {
		this.rutas = rutas;
	}

}

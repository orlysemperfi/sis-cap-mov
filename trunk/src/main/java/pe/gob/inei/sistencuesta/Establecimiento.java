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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Establecimiento generated by hbm2java
 */
@Entity
@Table(name = "establecimiento", catalog = "bd_sistencuesta")
public class Establecimiento implements java.io.Serializable {

	private Integer codigoEstablecimiento;
	private String nombre;
	private String zona;
	private String manzana;
	private String numero;
	private String referenciaGeografica;
	private Set<Ruta> rutas = new HashSet<Ruta>(0);

	public Establecimiento() {
	}

	public Establecimiento(String nombre) {
		this.nombre = nombre;
	}

	public Establecimiento(String nombre, String zona, String manzana,
			String numero, String referenciaGeografica, Set<Ruta> rutas) {
		this.nombre = nombre;
		this.zona = zona;
		this.manzana = manzana;
		this.numero = numero;
		this.referenciaGeografica = referenciaGeografica;
		this.rutas = rutas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CodigoEstablecimiento", unique = true, nullable = false)
	public Integer getCodigoEstablecimiento() {
		return this.codigoEstablecimiento;
	}

	public void setCodigoEstablecimiento(Integer codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}

	@Column(name = "Nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Zona", length = 20)
	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Column(name = "Manzana", length = 10)
	public String getManzana() {
		return this.manzana;
	}

	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	@Column(name = "Numero", length = 5)
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "ReferenciaGeografica", length = 100)
	public String getReferenciaGeografica() {
		return this.referenciaGeografica;
	}

	public void setReferenciaGeografica(String referenciaGeografica) {
		this.referenciaGeografica = referenciaGeografica;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "detalle_ruta", catalog = "bd_sistencuesta", joinColumns = { @JoinColumn(name = "CodigoEstablecimiento", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CodigoRuta", nullable = false, updatable = false) })
	public Set<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(Set<Ruta> rutas) {
		this.rutas = rutas;
	}

}

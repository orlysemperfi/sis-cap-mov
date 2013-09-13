package pe.gob.inei.sistencuesta;

// Generated 12/09/2013 09:31:22 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Perfil generated by hbm2java
 */
@Entity
@Table(name = "perfil", catalog = "bd_sistencuesta")
public class Perfil implements java.io.Serializable {

	private Integer codigoPerfil;
	private String nombre;
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	private Set<Acceso> accesos = new HashSet<Acceso>(0);

	public Perfil() {
	}

	public Perfil(String nombre) {
		this.nombre = nombre;
	}

	public Perfil(String nombre, Set<Usuario> usuarios, Set<Acceso> accesos) {
		this.nombre = nombre;
		this.usuarios = usuarios;
		this.accesos = accesos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CodigoPerfil", unique = true, nullable = false)
	public Integer getCodigoPerfil() {
		return this.codigoPerfil;
	}

	public void setCodigoPerfil(Integer codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	@Column(name = "Nombre", nullable = false, length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	public Set<Acceso> getAccesos() {
		return this.accesos;
	}

	public void setAccesos(Set<Acceso> accesos) {
		this.accesos = accesos;
	}

}

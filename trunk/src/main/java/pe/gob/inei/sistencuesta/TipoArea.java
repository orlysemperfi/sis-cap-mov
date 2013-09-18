package pe.gob.inei.sistencuesta;


public class TipoArea implements java.io.Serializable {

	private String codigoTipoArea;
	private String nombre;

	public TipoArea() {
	}

	public TipoArea(String codigoTipoArea, String nombre) {
		this.nombre = nombre;
		this.codigoTipoArea = codigoTipoArea;
	}
	
	public String getCodigoTipoArea() {
		return codigoTipoArea;
	}

	public void setCodigoTipoArea(String codigoTipoArea) {
		this.codigoTipoArea = codigoTipoArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

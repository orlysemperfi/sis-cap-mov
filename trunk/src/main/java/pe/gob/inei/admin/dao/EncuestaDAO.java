package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Encuesta;

public interface EncuestaDAO extends GenericDAO<Encuesta, String>{

	List<Encuesta> buscar(String nombre, Integer ano );
	Encuesta buscarxCodigo( String codigoEncuesta);
	void registrar(String codigoEncuesta, String nombre, Integer año, String descripcion, String objetivo, String fechaInicio, String fechaFin, String tipoArea, Integer codigoRubro, String codigoMarcoMuestral);
	void actualizar(String codigoEncuesta, String nombre, Integer año, String descripcion, String objetivo, String fechaInicio, String fechaFin, String tipoArea, Integer codigoRubro, String codigoMarcoMuestral);
	void eliminar(String codigoEncuesta);
	List<Encuesta> buscarEncuestas();
}

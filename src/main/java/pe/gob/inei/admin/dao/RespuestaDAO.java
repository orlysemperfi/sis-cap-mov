package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Respuesta;

public interface RespuestaDAO extends GenericDAO<Respuesta, Integer>{

	List<Respuesta> buscar(Integer codigoPregunta);
	Respuesta buscarxCodigo(Integer codigoRespuesta);
	void registrar(String valor, String descripcion, Integer posicion, Integer codigoPregunta);
	void actualizar(Integer codigoRespuesta, String valor, String descripcion, Integer posicion, Integer codigoPregunta);
	void eliminar(Integer codigoRespuesta);
}

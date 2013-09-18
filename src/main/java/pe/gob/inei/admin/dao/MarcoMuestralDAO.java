package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.MarcoMuestral;;

public interface MarcoMuestralDAO extends GenericDAO<MarcoMuestral, String> {
	List<MarcoMuestral> buscar();
	List<MarcoMuestral> buscar(String nombre, String encuesta, Integer año);
	MarcoMuestral buscarxCodigo( String codigoMarcoMuestral);
	void registrar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea);
	void actualizar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea);
	void eliminar(String codigoMarcoMuestral);
	void registrarDetalle(String codigoMarcoMuestral, String codigoUbigeo);
	void eliminarDetalle(String codigoMarcoMuestral);
}

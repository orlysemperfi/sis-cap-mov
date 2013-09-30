package pe.gob.inei.admin.dao;

import java.util.List;

import pe.gob.inei.sistencuesta.MarcoMuestral;
import pe.gob.inei.sistencuesta.Ubigeo;

public interface MarcoMuestralDAO extends GenericDAO<MarcoMuestral, String> {
	List<MarcoMuestral> buscar();
	List<MarcoMuestral> buscar(String nombre, String encuesta, Integer año);
	MarcoMuestral buscarxCodigo( String codigoMarcoMuestral);
	void registrar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea, String estado, List<Ubigeo> ubigeos);
	void actualizar(String codigoMarcoMuestral, Integer año, String descripcion, Integer numeroEncuestas, String tipoUbigeo, String tipoArea, String estado, List<Ubigeo> ubigeos);
	void eliminar(String codigoMarcoMuestral);
	void registrarDetalle(String codigoMarcoMuestral, String codigoUbigeo);
	void eliminarDetalle(String codigoMarcoMuestral);
	List<MarcoMuestral> buscarPorTipoArea(String tipoArea);
}

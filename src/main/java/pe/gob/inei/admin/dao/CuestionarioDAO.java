package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Cuestionario;

public interface CuestionarioDAO extends GenericDAO<Cuestionario, Integer>{

	List<Cuestionario> buscar(String codigoEncuesta);
	Cuestionario buscarxCodigo(Integer codigoCuestionario);
	void registrar(Integer numero, String descripcion, Integer codigoCategoria, String codigoEncuesta);
	void actualizar(Integer codigoCuestionario, Integer numero, String descripcion, Integer codigoCategoria, String codigoEncuesta);
	void eliminar(Integer codigoCuestionario);
}

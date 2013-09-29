package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Cuestionario;

public interface CuestionarioDAO extends GenericDAO<Cuestionario, Integer>{

	List<Cuestionario> buscar(String codigoEncuesta);
	Cuestionario buscarxCodigo(Integer codigoCuestionario);
	void registrar(String codigoEncuesta, Integer codigoCategoria, Cuestionario cuestionario);
	void actualizar(String codigoEncuesta, Integer codigoCategoria, Cuestionario cuestionario);
	void eliminar(Integer codigoCuestionario);
}

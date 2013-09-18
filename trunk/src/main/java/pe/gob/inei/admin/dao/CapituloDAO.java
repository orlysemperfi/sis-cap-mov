package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Capitulo;

public interface CapituloDAO extends GenericDAO<Capitulo, Integer>{

	List<Capitulo> buscar(Integer codigoCuestionario);
	Capitulo buscarxCodigo(Integer codigoCapitulo);
	void registrar(Integer numero, String titulo, Integer codigoCuestionario);
	void actualizar(Integer codigoCapitulo, Integer numero, String titulo, Integer codigoCuestionario);
	void eliminar(Integer codigoCapitulo);
}

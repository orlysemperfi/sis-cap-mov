package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Categoria;

public interface CategoriaDAO extends GenericDAO<Categoria, Integer> {
	List<Categoria> buscar();
	Categoria buscarxCodigo(Integer codigoCategoria);
}

package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Rubro;

public interface RubroDAO extends GenericDAO<Rubro, Integer> {
	List<Rubro> buscar();
	Rubro buscarPorCodigo(Integer codigoRubro);
}

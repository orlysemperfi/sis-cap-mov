package pe.gob.inei.admin.dao;

import java.util.List;

import pe.gob.inei.sistencuesta.Ubigeo;

public interface UbigeoDAO extends GenericDAO<Ubigeo, String> {
	List<Ubigeo> BuscarPorDepartamento();
	List<Ubigeo> BuscarPorProvincia(String codDepartamento);
	List<Ubigeo> BuscarPorDistrito(String codDepartamento,String codProvincia);

}

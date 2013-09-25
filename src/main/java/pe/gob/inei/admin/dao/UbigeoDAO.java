package pe.gob.inei.admin.dao;

import java.util.List;

import pe.gob.inei.sistencuesta.Ubigeo;

public interface UbigeoDAO extends GenericDAO<Ubigeo, String> {
	List<Ubigeo> BuscarPorDepartamento();
	List<Ubigeo> BuscarPorProvincia(String codDepartamento);
	List<Ubigeo> BuscarPorDistrito(String codDepartamento,String codProvincia);
	Ubigeo BuscarPorCodigo(String codDepartamento,String codProvincia,String codDistrito);
	List<Ubigeo> buscarDepartamento();
	List<Ubigeo> buscarProvincia(String codigoDepartamento);
	List<Ubigeo> buscarDistrito(String codigoDepartamento, String codigoProvincia);
	List<Ubigeo> buscarUbigeoPorMarcoMuestral(String codigoMarcoMuestral);
	List<Ubigeo> buscarDepartamentoNoMarcoMuestral(String codigoMarcoMuestral);

}

package pe.gob.inei.admin.dao;

import java.util.List;

import pe.gob.inei.sistencuesta.Personal;

public interface PersonalDAO extends GenericDAO<Personal, Integer>{
	Personal buscarPorDni(String dni);
	Personal buscarPorCodigo(Integer codigoPersonal);
	List<Personal> buscarPorUbigeo(String codigoUbigeo);

}
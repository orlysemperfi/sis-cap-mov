package pe.gob.inei.admin.dao;

import pe.gob.inei.sistencuesta.Personal;

public interface PersonalDAO extends GenericDAO<Personal, Integer>{
	Personal buscarPorDni(String dni);

}

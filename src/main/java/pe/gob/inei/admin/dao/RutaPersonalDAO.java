package pe.gob.inei.admin.dao;

import java.util.List;

import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.RutaPersonalId;

public interface RutaPersonalDAO extends GenericDAO<RutaPersonal, RutaPersonalId>{
	
	List<RutaPersonal> buscarUbigeo(String ubigeo );
	List<RutaPersonal> buscarPersona( Integer codigoPersonal );

}

package pe.gob.inei.admin.dao;

import java.util.List;

import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.RutaPersonalId;

public interface RutaPersonalDAO extends GenericDAO<RutaPersonal, RutaPersonalId>{
	
	List<RutaPersonal> buscarUbigeo(String ubigeo );
	List<RutaPersonal> buscarPersona(Integer codigoPersonal);
	List<RutaPersonal> buscarPersonaRuta(Integer codigoRuta);
	RutaPersonal buscarPersonaRuta(Integer codigoRuta, Integer codigoPersonal);
	void registrar(Integer codigoRuta, Integer codigoPersonal, Integer numeroEncuestas, String fechaInicio, String fechaFin, Integer nroEncuestasPorDia, Integer correlativoInicial);
	void actualizar(Integer codigoRuta, Integer codigoPersonalOriginal, Integer codigoPersonal, Integer numeroEncuestas, String fechaInicio, String fechaFin, Integer nroEncuestasPorDia, Integer correlativoInicial);
	void eliminar(Integer codigoRuta, Integer codigoPersonal);
	
}
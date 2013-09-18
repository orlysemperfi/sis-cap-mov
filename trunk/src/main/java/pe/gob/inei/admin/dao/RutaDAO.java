package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Ruta;

public interface RutaDAO extends GenericDAO<Ruta, Integer> {
	List<Ruta> buscar(String codigoEncuesta);
	Ruta buscarxCodigo(Integer codigoRuta);
	void registrar(String codigoEncuesta, String codigoUbigeo, String numeroRuta, String descripcion, Integer numeroEncuestas, Integer correlativoInicial, Integer correlativoFinal);
	void actualizar(Integer codigoRuta, String codigoEncuesta, String codigoUbigeo, String numeroRuta, String descripcion, Integer numeroEncuestas, Integer correlativoInicial, Integer correlativoFinal);
	void eliminar(Integer codigoRuta);
	void registrarDetalle(Integer codigoRuta, Integer codigoEstablecimiento);
	void eliminarDetalle(Integer codigoRuta);
}
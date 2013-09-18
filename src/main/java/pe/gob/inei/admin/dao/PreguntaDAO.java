package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Pregunta;

public interface PreguntaDAO extends GenericDAO<Pregunta, Integer>{

	List<Pregunta> buscar(Integer codigoCapitulo);
	Pregunta buscarxCodigo(Integer codigoPregunta);
	void registrar(Integer numero, Integer nivelPregunta, String descripcion, String tipoPregunta, Integer longitudDato, String tipoValidacion, String opcional, String respuestaOtros, Integer codigoCategoria, Integer codigoCapitulo);
	void actualizar(Integer codigoPregunta, Integer numero, Integer nivelPregunta, String descripcion, String tipoPregunta, Integer longitudDato, String tipoValidacion, String opcional, String respuestaOtros, Integer codigoCategoria, Integer codigoCapitulo);
	void eliminar(Integer codigoPregunta);
}

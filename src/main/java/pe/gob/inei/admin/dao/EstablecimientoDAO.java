package pe.gob.inei.admin.dao;

import java.util.List;
import pe.gob.inei.sistencuesta.Establecimiento;

public interface EstablecimientoDAO extends GenericDAO<Establecimiento, Integer> {
	List<Establecimiento> buscar(String codigoUbigeo);
	List<Establecimiento> buscarxCodigos(String codigosEstablecimiento);
}

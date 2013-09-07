package pe.gob.inei.admin.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<TIPO, ID extends Serializable> {
	
	TIPO findById (ID id);
	
	void update(TIPO t);
	
	void save(TIPO t);
	
	void saveOrUpdate(TIPO t);
	
	void delete(TIPO t);
	
	void deleteById(ID id);
	
	List<TIPO> findAll();
	
	int count();

}

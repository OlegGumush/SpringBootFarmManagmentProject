package farm.repository;

import java.util.List;

import farm.entity.BaseEntity;

public interface IRepository<T extends BaseEntity> {

	T insert(T entity);
	
	T update(T entity);
	
	T findById(long id);
	
	boolean removeById(long id);
		
	List<T> findAll(int page, int size, String orderBy);
}

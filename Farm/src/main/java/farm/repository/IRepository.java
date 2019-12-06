package farm.repository;

import java.util.List;

import farm.entity.BaseEntity;

public interface IRepository<T extends BaseEntity> {

	T insert(T entity);
	
	T update(T entity);
	
	T findById(long id);
	
	boolean removeById(long id);
			
	List<T> findAll();	
	
	List<T> findAllWithOrder(String orderBy);		
	
	List<T> findAllWithPage(int page, int size);
	
	List<T> findAllWithPageAndOrder(int page, int size, String orderBy);
}

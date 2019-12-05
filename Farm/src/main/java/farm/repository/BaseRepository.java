package farm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import farm.entity.BaseEntity;
import farm.exception.FarmException;
import farm.repository.query.Filter;
import farm.repository.query.SearchParams;

@Repository
public abstract class BaseRepository<T extends BaseEntity> implements IRepository<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Class<T> type;

	public BaseRepository(Class<T> type) {
		
		this.type = type;
	}
	
	@Override
	@Transactional
	public T insert(T entity) {
		
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public T findById(long id) {
		
		return entityManager.find(type, id);
	}

	@Override
	@Transactional
	public boolean removeById(long id) {
		
		T entity = entityManager.find(type, id);

		if(entity == null) {
			return false;
		}
		
		entityManager.remove(entity);
		return true;
	}

	@Override
	@Transactional
	public T update(T entity) {
		
		entityManager.merge(entity);
		return entity;
	}
	
	@Override
	/**
	 * Default: page 0, size 100
	 */
	public List<T> findAll() {		
		return findAll(0, 100);
	}

	@Override
	public List<T> findAll(int page, int size) {		
		return findAll(page, size, new SearchParams());
	}
	
	public List<T> findAll(int page, int size, SearchParams sp) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> CriteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> root = CriteriaQuery.from(type);

		
		Predicate[] predicates = getPredicates(sp,root,criteriaBuilder);
		CriteriaQuery.select(root).where(criteriaBuilder.and(predicates));    

		List<T> entities = entityManager.createQuery(CriteriaQuery)
		.setFirstResult(page * size)
		.setMaxResults(size)
		.getResultList();
		
		return entities;
	}
	
	public List<T> find(SearchParams sp) {
		
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> CriteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> root = CriteriaQuery.from(type);
		
		
		Predicate[] predicates = getPredicates(sp,root,criteriaBuilder);
		
		CriteriaQuery.select(root).where(criteriaBuilder.and(predicates));    
		
		List<T> animals = entityManager.createQuery(CriteriaQuery).getResultList();
		
		if(animals.isEmpty()) {
			return new ArrayList<T>();
		}
		
		return animals;
	}
	
	
	private Predicate [] getPredicates(SearchParams sp, Root<T> root, CriteriaBuilder criteriaBuilder) {
		
		List<Filter> filters = sp.getFilters();
		Predicate [] result = new Predicate[filters.size()];

		for (int i = 0; i < filters.size(); i++) {
			
			Filter filter = filters.get(i);
			
			switch (filter.getFilter()) {
			case Equal:
				result[i] = (criteriaBuilder.equal(root.get(filter.getKey()), filter.getValue()));
				break;
			case NotEqual:
				result[i] = (criteriaBuilder.notEqual(root.get(filter.getKey()), filter.getValue()));
				break;
			case IsNull:
				result[i] = (criteriaBuilder.isNull(root.get(filter.getKey())));
				break;
			case isNotNull:
				result[i] = (criteriaBuilder.isNotNull(root.get(filter.getKey())));
				break;		
			default:
				throw new FarmException("Filter doesn't match any operator");
			}			
		}
				
		return result;
	}
}






package farm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import farm.entity.BaseEntity;
import farm.repository.jpa.JpaUtils;
import farm.repository.query.OrderBy;
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
	public List<T> findAll(int page, int size, String orderBy) {		
		
		SearchParams sp = new SearchParams();
		
		if(orderBy.startsWith("-")) {
			sp.orderBy(OrderBy.desc(orderBy.substring(1)));		
		} else {
			sp.orderBy(OrderBy.asc(orderBy));		
		}
		
		return findAll(page, size, sp);
	}
	
	public List<T> findAll(int page, int size, SearchParams sp) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> CriteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> root = CriteriaQuery.from(type);
		
		Predicate[] predicates = JpaUtils.getPredicates(sp,root,criteriaBuilder);
		List<Order> orders = JpaUtils.getOrderBy(sp, root, criteriaBuilder);
		
		CriteriaQuery.select(root).where(criteriaBuilder.and(predicates)).orderBy(orders);  		
		
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
		
		Predicate[] predicates = JpaUtils.getPredicates(sp,root,criteriaBuilder);
		List<Order> orderBy = JpaUtils.getOrderBy(sp,root,criteriaBuilder);

		CriteriaQuery.select(root).where(criteriaBuilder.and(predicates));    
		
		List<T> animals = entityManager.createQuery(CriteriaQuery).getResultList();
		
		if(animals.isEmpty()) {
			return new ArrayList<T>();
		}
		
		return animals;
	}
}






package farm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import farm.entity.BaseEntity;
import farm.repository.query.OrderBy;
import farm.repository.query.Pagination;
import farm.repository.query.SearchParams;
import farm.utils.JpaUtils;

@Repository
@Transactional(value = TxType.REQUIRED)
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
	public List<T> findAll() {		
		
		return findAllWithOrder(null, new SearchParams());
	}
	
	@Override
	public List<T> findAllWithOrder(String orderBy) {		
		
		return findAllWithPageAndOrder(0, 0, orderBy, new SearchParams());
	}
	
	@Override
	public List<T> findAllWithPage(int page, int size) {		
		
		return findAllWithPageAndOrder(page, size, null, new SearchParams());
	}
	
	@Override
	public List<T> findAllWithPageAndOrder(int page, int size, String orderBy) {		
		
		return findAllWithPageAndOrder(page, size, orderBy, new SearchParams());
	}
	
	// Protected & Private section
	
	protected List<T> findAll(SearchParams sp) {		
		
		return find(sp);	
	}
	
	protected List<T> findAllWithOrder(String orderBy, SearchParams sp) {		
				
		return findAllWithPageAndOrder(0, 0, orderBy, sp);
	}
	
	protected List<T> findAllWithPage(int page, int size, SearchParams sp) {		
		
		return findAllWithPageAndOrder(page, size, null, sp);
	}
	
	protected List<T> findAllWithPageAndOrder(int page, int size, String orderBy, SearchParams sp) {		
		
		if (sp == null) {
			sp = new SearchParams();
		}
		
		sp.sort(OrderBy.orderBy(orderBy))
		  .page(Pagination.page(page, size));
		
		return find(sp);
	}
	
	private List<T> find(SearchParams sp) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> root = criteriaQuery.from(type);
		
		Predicate[] predicates = JpaUtils.getPredicates(sp,root,criteriaBuilder);
		List<Order> orders = JpaUtils.getOrderBy(sp, root, criteriaBuilder);
		Pagination page = sp.getPagination();
		
		criteriaQuery.select(root).where(criteriaBuilder.and(predicates)).orderBy(orders);  		
		
		TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
		List<T> entities = null;
		
		if(page == null) {
			entities = query.getResultList();
		} else {
			entities = query.setFirstResult(page.getPage() * page.getSize())
									.setMaxResults(page.getSize())
									.getResultList();
		}

		return entities;
	}
}






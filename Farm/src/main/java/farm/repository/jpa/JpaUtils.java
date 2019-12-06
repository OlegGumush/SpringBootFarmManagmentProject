package farm.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import farm.exception.FarmException;
import farm.repository.query.Filter;
import farm.repository.query.OrderBy;
import farm.repository.query.SearchParams;

public class JpaUtils {

	public JpaUtils() {}
	
	public static <T> Predicate [] getPredicates(SearchParams sp, Root<T> root, CriteriaBuilder criteriaBuilder) {
		
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

	public static <T> List<Order> getOrderBy(SearchParams sp, Root<T> root, CriteriaBuilder criteriaBuilder) {
		
		List<OrderBy> orders = sp.getOrders();
		List<Order> result = new ArrayList<Order>();

		for (int i = 0; i < orders.size(); i++) {
			
			OrderBy order = orders.get(i);
			
			switch (order.getOrder()) {
			case Asc:
				result.add(criteriaBuilder.asc(root.get(order.getKey())));
				break;
			case Desc:
				result.add(criteriaBuilder.desc(root.get(order.getKey())));
				break;	
			default:
				throw new FarmException("Filter doesn't match any operator");
			}			
		}
				
		return result;
	}
}

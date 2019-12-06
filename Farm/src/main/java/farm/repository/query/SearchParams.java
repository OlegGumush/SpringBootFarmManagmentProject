package farm.repository.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchParams implements Serializable {

	List<Filter> filters = new ArrayList<Filter>();
	List<OrderBy> orders = new ArrayList<OrderBy>();

	public SearchParams where(Filter filter) {
		
		if(filter != null) {
			filters.add(filter);
		}
		return this;
	}
	
	public SearchParams where(Filter ... filters) {

		for (int i = 0; i < filters.length; i++) {
			
			this.filters.add(filters[i]);
		}
		return this;
	}
	
	public SearchParams orderBy(OrderBy orderBy) {

		if(orderBy != null) {
			this.orders.add(orderBy);
		}
		return this;
	}
	
	public SearchParams orderBy(OrderBy ... orders) {

		for (int i = 0; i < orders.length; i++) {
			
			this.orders.add(orders[i]);
		}
		return this;
	}

	public List<Filter> getFilters() {
		return filters;
	}	
	
	public List<OrderBy> getOrders() {
		return orders;
	}
}

package farm.repository.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchParams implements Serializable {

	List<Filter> filters;
	List<OrderBy> orders;
	Pagination page;
	
	public SearchParams() {
		
		this.filters = new ArrayList<Filter>();
		this.orders = new ArrayList<OrderBy>();
		this.page = null;
	}
	
	public SearchParams where(Filter filter) {
		
		if(filter != null) {
			this.filters.add(filter);
		}
		return this;
	}
	
	public SearchParams where(Filter ... filters) {

		for (int i = 0; i < filters.length; i++) {
			
			this.filters.add(filters[i]);
		}
		return this;
	}
	
	public SearchParams sort(OrderBy orderBy) {

		if(orderBy != null) {
			this.orders.add(orderBy);
		}
		return this;
	}
	
	public SearchParams sort(OrderBy ... orders) {

		for (int i = 0; i < orders.length; i++) {
			
			this.orders.add(orders[i]);
		}
		return this;
	}
	
	public SearchParams page(Pagination page) {

		if(page != null) {
			this.page = page;
		}
		return this;
	}

	public List<Filter> getFilters() {
		return this.filters;
	}	
	
	public List<OrderBy> getOrders() {
		return this.orders;
	}
	
	public Pagination getPagination() {
		return this.page;
	}
}

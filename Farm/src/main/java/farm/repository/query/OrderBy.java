package farm.repository.query;

import farm.enums.OrderType;

public class OrderBy {

	public String key;
	public OrderType order;
	
	public static OrderBy asc(String key) {
		return new OrderBy(key, OrderType.Asc);
	}

	public static OrderBy desc(String key) {
		return new OrderBy(key, OrderType.Desc);
	}
	
	public OrderBy() {
		
	}

	private OrderBy(String key, OrderType order) {
		this.key = key;
		this.order = order;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public OrderType getOrder() {
		return order;
	}

	public void setOrder(OrderType order) {
		this.order = order;
	}
}

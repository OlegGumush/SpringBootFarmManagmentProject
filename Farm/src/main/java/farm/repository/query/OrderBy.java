package farm.repository.query;

import farm.enums.OrderType;

public class OrderBy {

	public String key;
	public OrderType order;
	
	public static OrderBy orderBy(String key) {
		
		if (key == null) {
			return null;
		}
		
		if (key.startsWith("-")) {
			return new OrderBy(key.substring(1), OrderType.Desc);
		} else {
			return new OrderBy(key, OrderType.Asc);			
		}
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

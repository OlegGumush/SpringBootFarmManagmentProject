package farm.repository.query;

import java.io.Serializable;

import farm.enums.FilterType;

public class Filter implements Serializable {

	public String key;
	public Object value;
	public FilterType filter;
	
	public static Filter equal(String key, Object value) {
		return new Filter(key, value, FilterType.Equal);
	}

	public static Filter notEqual(String key, Object value) {
		return new Filter(key, value, FilterType.NotEqual);
	}
	
	public static Filter isNull(String key) {
		return new Filter(key, null, FilterType.IsNull);
	}
	
	public static Filter isNotNull(String key) {
		return new Filter(key, null, FilterType.isNotNull);			
	}

	public Filter(String key, Object value, FilterType filter) {
		this.key = key;
		this.value = value;
		this.filter = filter;
	}
	
	public Filter() {

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public FilterType getFilter() {
		return filter;
	}

	public void setFilter(FilterType filter) {
		this.filter = filter;
	}
}

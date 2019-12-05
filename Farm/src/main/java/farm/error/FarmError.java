package farm.error;

import java.io.Serializable;

import farm.enums.ErrorType;

public class FarmError implements Serializable {
	
	private ErrorType error;
	private String field;
	
	public FarmError(ErrorType error) {
		this.error = error;
	}
	
	public FarmError(ErrorType error, String field) {
		this(error);
		this.field = field;
	}

	public ErrorType getError() {
		return error;
	}

	public void setError(ErrorType error) {
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}	
}

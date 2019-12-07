package farm.error;

import java.io.Serializable;

import farm.enums.ErrorType;

public class FarmError implements Serializable {
	
	private ErrorType error;
	private String fieldName;
	
	public FarmError(ErrorType error) {
		this.error = error;
	}
	
	public FarmError(ErrorType error, String field) {
		this(error);
		this.fieldName = field;
	}

	public ErrorType getError() {
		return error;
	}

	public void setError(ErrorType error) {
		this.error = error;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}

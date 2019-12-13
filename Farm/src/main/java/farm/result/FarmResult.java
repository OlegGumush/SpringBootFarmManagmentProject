package farm.result;

import java.io.Serializable;
import java.util.ArrayList;

import farm.enums.ErrorType;
import farm.error.FarmError;

public class FarmResult<T> implements Serializable {

	private T result;
	private boolean isSucceeded;
	private ArrayList<FarmError> errors;
	
	public FarmResult() {
		
		this.errors = new ArrayList<>();
		this.isSucceeded = true;
	}

	public FarmResult(T result) {
		this();
		this.result = result;
	}
	
	public FarmResult(ArrayList<FarmError> errors) {
		this();
		this.errors.addAll(errors);
		this.isSucceeded = false;
	}
	
	public void addError(ErrorType error) {
		this.errors.add(new FarmError(error));
		this.isSucceeded = false;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

	public ArrayList<FarmError> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<FarmError> errors) {
		this.errors = errors;
	}
}

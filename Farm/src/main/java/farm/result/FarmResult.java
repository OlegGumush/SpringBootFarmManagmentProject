package farm.result;

import java.io.Serializable;
import java.util.ArrayList;

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
		if(errors.size() > 0) {
			this.errors.addAll(errors);
			this.isSucceeded = false;			
		}
	}
	
	public FarmResult(FarmError error) {
		this();
		this.errors.add(error);
		this.isSucceeded = false;			
	}
	
	public void addError(FarmError error) {
		this.errors.add(error);
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

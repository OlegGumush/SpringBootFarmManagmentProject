package farm.result;

import java.io.Serializable;
import java.util.ArrayList;

import farm.enums.ErrorType;
import farm.error.FarmError;

public class FarmResult implements Serializable {

	private long entityId;
	private boolean isSucceeded;
	private ArrayList<FarmError> errors;
	
	public FarmResult() {
		
		this.errors = new ArrayList<>();
		this.isSucceeded = true;
	}
	
	public FarmResult(long entityId) {
		this();
		this.entityId = entityId;
	}
	
	public FarmResult(boolean isSucceeded) {
		this();
		this.isSucceeded = isSucceeded;
	}
	
	public FarmResult(ErrorType error) {
		this();
		this.errors.add(new FarmError(error));
		this.isSucceeded = false;
	}
	
	public FarmResult(ErrorType error, String fieldName) {
		this();
		this.errors.add(new FarmError(error, fieldName));
		this.isSucceeded = false;
	}
	
	public void addError(ErrorType error) {
		this.errors.add(new FarmError(error));
		this.isSucceeded = false;
	}
	
	public void addError(ErrorType error, String fieldName) {
		this.errors.add(new FarmError(error, fieldName));
		this.isSucceeded = false;
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

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
}
package farm.response_model;

import java.io.Serializable;

public class BaseResponseModel implements Serializable{
	
	public Long Id;
	
	public Long InsertedDateTime;
	
	public BaseResponseModel(Long id, Long insertedDateTime) {
		this.Id = id;
		this.InsertedDateTime = insertedDateTime;
	}
}

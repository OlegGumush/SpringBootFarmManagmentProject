package farm.request_model;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModelProperty;

public abstract class BaseAnimalModel extends BaseModel {

    @ApiModelProperty(required = true, allowableValues = "range[1, 20]")
	public String AnimalName;
    
	public BaseAnimalModel(ModelType type) {
		super(type);
	}
}
